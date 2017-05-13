package by.tsvrko.manics.dao.implementations.dataimport;

import by.tsvrko.manics.dao.interfaces.dataimport.ChatImportVK;
import by.tsvrko.manics.exceptions.TooManyRequestsToApiException;
import by.tsvrko.manics.exceptions.UserIsNotAuthorizedException;
import by.tsvrko.manics.model.dataimport.AuthInfo;
import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.service.interfaces.db.SessionService;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static by.tsvrko.manics.dao.ContentImportUtil.*;
import static by.tsvrko.manics.dao.ParseJSONUtil.*;


/**
 * Created main.main.java.by tsvrko on 1/4/2017.
 */

@Repository
public class ChatImportVKImpl implements ChatImportVK {

    private static Logger log = Logger.getLogger(ChatImportVKImpl.class.getName());
    private SessionService sessionService;
    @Autowired
    public ChatImportVKImpl(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public List<ChatInfo> getChats(AuthInfo authInfo) {
        if (sessionService.getByValue(authInfo.getSession()).getUser()==null){
           throw new UserIsNotAuthorizedException("User isn't authorized");
        }
        List<ChatInfo> chatInfoList = new ArrayList<>();
        int offset = 0;
        long count = parseChatsCount(getChatsCount(authInfo.getToken()));
        while (offset < count) {
            String text;
            while (true) {
                try {
                    text = getChats(offset,authInfo.getToken());
                    if (!text.contains("Too many requests per second")) break;
                }
                catch (TooManyRequestsToApiException e1){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        log.debug("InterruptedException in current thread", e);
                        Thread.currentThread().interrupt();
                    }
                }
            }
            JSONArray jsonChatsArray = parseChatsJSON(text);
            for (Object aJsonChatsArray : jsonChatsArray) {
                JSONObject jsonChat = (JSONObject) aJsonChatsArray;
                if (jsonChat.containsKey("chat_id")&&jsonChat.containsKey("users_count")) {
                    ChatInfo chatInfo = new ChatInfo();
                    chatInfo.setChatId(Long.valueOf(jsonChat.get("chat_id").toString()));
                    chatInfo.setTitle(jsonChat.get("title").toString());
                    chatInfo.setLastMessageDate(Long.valueOf(jsonChat.get("date").toString()));
                    chatInfo.setUserCount(Integer.valueOf(jsonChat.get("users_count").toString()));
                    try {
                        chatInfo.setImage50(jsonChat.get("photo_50").toString());
                        chatInfo.setImage100(jsonChat.get("photo_100").toString());
                        chatInfo.setImage200(jsonChat.get("photo_200").toString());
                    }
                    catch (NullPointerException e){
                        chatInfo.setImage50("null");
                        chatInfo.setImage100("null");
                        chatInfo.setImage200("null");}

                    chatInfoList.add(chatInfo);
                }
            }
            offset += 200;
        }
        return chatInfoList;
    }

    @Override
    public List<Integer> getChatUsers(long chatId, AuthInfo authInfo) {
        if (sessionService.getByValue(authInfo.getSession()).getUser()==null){
            throw new UserIsNotAuthorizedException("User isn't authorized");
        }
        List<Integer> list = new ArrayList<>();
        String text  = getUsers(chatId,authInfo.getToken());
        JSONArray jsonUsersArray = parseUsersJSON(text);
        for (Object aJsonUsersArray : jsonUsersArray) {
            list.add(Integer.valueOf(aJsonUsersArray.toString()));
        }
        return list;
    }

    private String getChatsCount(String token) {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/messages.getDialogs")
                .setParameter("access_token", token)
                .setParameter("count", "200");
        return readContent(uriBuilder);
    }

    private String getChats(int offset, String token) throws TooManyRequestsToApiException {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/messages.getDialogs")
                .setParameter("access_token", token)
                .setParameter("count", "200").setParameter("offset", String.valueOf(offset));
        return readContent(uriBuilder);
    }

    private String getUsers(long chat_id, String token){
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/messages.getChat")
                .setParameter("chat_id",String.valueOf(chat_id))
                .setParameter("access_token", token)
                .setParameter("v","5.62");
        return readContent(uriBuilder);
    }

}
