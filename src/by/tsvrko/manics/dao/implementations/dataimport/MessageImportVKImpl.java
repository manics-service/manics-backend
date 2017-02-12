package by.tsvrko.manics.dao.implementations.dataimport;

import by.tsvrko.manics.dao.interfaces.dataimport.MessageImportVK;
import by.tsvrko.manics.exceptions.TooManyRequestsToApiException;
import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.hibernate.Message;
import by.tsvrko.manics.service.interfaces.db.ChatService;
import by.tsvrko.manics.service.interfaces.db.MessageService;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static by.tsvrko.manics.dao.ContentImportUtil.*;
import static by.tsvrko.manics.dao.ParseJSONUtil.*;

/**
 * Created main.by tsvrko on 1/5/2017.
 */

@Repository
public class MessageImportVKImpl implements MessageImportVK {

    private static final ResourceBundle CONFIG_BUNDLE = ResourceBundle.getBundle("VKapi");
    private static final String ACCESS_TOKEN = CONFIG_BUNDLE.getString("access.token");
    private static Logger log = Logger.getLogger(MessageImportVKImpl.class.getName());

    private MessageService messageService;
    private ChatService chatService;

    @Autowired
    public MessageImportVKImpl(MessageService messageService, ChatService chatService) {
        this.messageService = messageService;
        this.chatService = chatService;
    }

    @Override
    public boolean getMessages(ChatInfo chat, String token) {

        chatService.addChat(chat,token);

        ArrayList<Message> messagesList = new ArrayList<>();
        int offset = 0;
        long chatId = chat.getChatId();
        long count = parseMessageCount(getCountOfMessages(chatId));

        while (offset < count) {
            String text;
            while (true) {
                try{
                    text = getChatMessages(chatId, offset);
                    if (!text.contains("Too many requests per second")) break;}
                catch (TooManyRequestsToApiException e){
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e1) {
                        log.debug("InterruptedException in current thread", e1);
                        Thread.currentThread().interrupt();
                    }}
            }

            JSONArray jsonMessagesArray = parseMessagesJSON(text);

            for (Object aJsonMessagesArray : jsonMessagesArray) {
                JSONObject jsonMessage = (JSONObject) aJsonMessagesArray;
                Message message = new Message();
                message.setId(Integer.valueOf(jsonMessage.get("id").toString()));
                message.setBody((String)jsonMessage.get("body"));
                message.setUserId(jsonMessage.get("user_id").toString());
                message.setDate(Long.valueOf(jsonMessage.get("date").toString()));
                messagesList.add(message);
            }
            offset += 200;
        }
        messagesList.sort(Message::compareTo);
        messageService.addMessages(messagesList,chatId);
        return true;
    }

    @Override
    public List <Number> getChatInfo(long chatId) {
        int offset =0;
        String text;

        while (true) {
            try {
                text = getChatMessages(chatId, offset);
                if (!text.contains("Too many requests per second")) break;
            }
            catch (TooManyRequestsToApiException e) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    log.debug("InterruptedException in current thread", e1);
                    Thread.currentThread().interrupt();
                }
            }
        }

        return parseChatInfo(text);
    }

    private String getCountOfMessages(long chatId) {

        String peer_id = String.valueOf(chatId+2000000000);
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/messages.getHistory")
                .setParameter("peer_id", peer_id)
                .setParameter("access_token", ACCESS_TOKEN)
                .setParameter("v", "5.60");
        return readContent(uriBuilder);
    }

    private String getChatMessages(long chatId, int offset) throws TooManyRequestsToApiException {

        String peer_id = String.valueOf(chatId+2000000000);
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/messages.getHistory")
                .setParameter("peer_id", peer_id)
                .setParameter("access_token", ACCESS_TOKEN)
                .setParameter("count", "200").setParameter("offset", String.valueOf(offset))
                .setParameter("v", "5.60");
        return readContent(uriBuilder);
    }

}

