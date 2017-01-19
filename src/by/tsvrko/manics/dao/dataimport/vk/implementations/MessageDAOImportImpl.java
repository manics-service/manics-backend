package by.tsvrko.manics.dao.dataimport.vk.implementations;

import by.tsvrko.manics.dao.dataimport.vk.interfaces.MessageDAOImport;
import by.tsvrko.manics.model.Chat;
import by.tsvrko.manics.model.Message;
import by.tsvrko.manics.service.services.dbdaoservice.ChatService;
import by.tsvrko.manics.service.services.dbdaoservice.MessageService;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.ResourceBundle;

import static by.tsvrko.manics.dao.dataimport.vk.ContentImportUtil.*;
import static by.tsvrko.manics.dao.dataimport.vk.ParseJSONUtil.*;

/**
 * Created by tsvrko on 1/5/2017.
 */
public class MessageDAOImportImpl implements MessageDAOImport {

    private static final ResourceBundle CONFIG_BUNDLE = ResourceBundle.getBundle("VKapi");
    private static final String ACCESS_TOKEN = CONFIG_BUNDLE.getString("access.token");
    private static Logger log = Logger.getLogger(MessageDAOImportImpl.class.getName());
    private static MessageService messageService = new MessageService();
    private static ChatService chatService = new ChatService();


    @Override
    public boolean getMessages(Chat chat, String token) {

        chatService.addChat(chat,token);
        String peer_id = String.valueOf(chat.getChat_id()+2000000000);
        ArrayList<Message> messagesList = new ArrayList<>();
        int offset = 0;
        int count = Integer.parseInt(parseJSONObjectCount(getChat(peer_id,offset)));

        while (offset < count) {
            String text;
            while (true) {
                text = getChat(peer_id, offset);
                if (!text.contains("Too many requests per second")) break;
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    log.debug("InterruptedException in current thread", e);
                    Thread.currentThread().interrupt();
                }
            }

            JSONArray jsonMessagesArray = parseJSONObject(text);

            for (Object aJsonMessagesArray : jsonMessagesArray) {
                JSONObject jsonMessage = (JSONObject) aJsonMessagesArray;
                Message message = new Message();
                message.setId(Integer.valueOf(jsonMessage.get("id").toString()));
                message.setBody((String)jsonMessage.get("body"));
                message.setUser_id(jsonMessage.get("user_id").toString());
                message.setDate(Long.valueOf(jsonMessage.get("date").toString()));
                messagesList.add(message);
            }
            offset += 200;
        }
        messagesList.sort(Message::compareTo);
        messageService.addMessages(messagesList,chat);
        return true;
    }


    private String getChat(String peer_id, int offset) {

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/messages.getHistory")
                .setParameter("peer_id", peer_id)
                .setParameter("access_token", ACCESS_TOKEN)
                .setParameter("count", "200").setParameter("offset", String.valueOf(offset))
                .setParameter("v", "5.60");
        return readContent(uriBuilder);
    }
}

