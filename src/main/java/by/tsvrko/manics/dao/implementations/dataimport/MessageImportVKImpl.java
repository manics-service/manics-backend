package by.tsvrko.manics.dao.implementations.dataimport;

import by.tsvrko.manics.dao.interfaces.dataimport.MessageImportVK;
import by.tsvrko.manics.exceptions.TooManyRequestsToApiException;
import by.tsvrko.manics.exceptions.UserIsNotAuthorizedException;
import by.tsvrko.manics.model.dataimport.AuthInfo;
import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.hibernate.Message;
import by.tsvrko.manics.service.interfaces.db.ChatService;
import by.tsvrko.manics.service.interfaces.db.MessageService;
import by.tsvrko.manics.service.interfaces.db.SessionService;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static by.tsvrko.manics.dao.ContentImportUtil.*;
import static by.tsvrko.manics.dao.ParseJSONUtil.*;

/**
 * Created main.main.java.by tsvrko on 1/5/2017.
 */

@Repository
public class MessageImportVKImpl implements MessageImportVK {

    private static Logger log = Logger.getLogger(MessageImportVKImpl.class.getName());

    private MessageService messageService;
    private ChatService chatService;
    private SessionService sessionService;

    @Autowired
    public MessageImportVKImpl(MessageService messageService, ChatService chatService, SessionService sessionService) {
        this.messageService = messageService;
        this.chatService = chatService;
        this.sessionService = sessionService;
    }

    @Override
    public boolean getMessages(ChatInfo chatInfo, AuthInfo authInfo) {

        if (sessionService.getByValue(authInfo.getSession()).getUser()==null){
            throw new UserIsNotAuthorizedException("User isn't authorized");
        }
        chatService.addChat(chatInfo,authInfo.getSession());

        ArrayList<Message> messagesList = new ArrayList<>();
        int offset = 0;
        long chatId = chatInfo.getChatId();
        long count = parseMessageCount(getCountOfMessages(chatId,authInfo.getToken()));

        while (offset < count) {
            String text;
            while (true) {
                try{
                    text = getChatMessages(chatId, offset, authInfo.getToken());
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
        Instant instant = Instant.now();
        messageService.addMessages(messagesList,chatId);
        Instant instant2 = Instant.now();
        System.out.println("delta="+ ChronoUnit.MILLIS.between(instant, instant2));
        return true;
    }

    private String getCountOfMessages(long chatId, String token) {

        String peer_id = String.valueOf(chatId+2000000000);
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/messages.getHistory")
                .setParameter("peer_id", peer_id)
                .setParameter("access_token", token)
                .setParameter("v", "5.62");
        return readContent(uriBuilder);
    }

    private String getChatMessages(long chatId, int offset,String token ) throws TooManyRequestsToApiException {

        String peer_id = String.valueOf(chatId+2000000000);
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/messages.getHistory")
                .setParameter("peer_id", peer_id)
                .setParameter("access_token", token)
                .setParameter("count", "200").setParameter("offset", String.valueOf(offset))
                .setParameter("v", "5.62");
        return readContent(uriBuilder);
    }

}

