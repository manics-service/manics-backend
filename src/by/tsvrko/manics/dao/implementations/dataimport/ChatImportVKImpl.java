package by.tsvrko.manics.dao.implementations.dataimport;

import by.tsvrko.manics.dao.interfaces.dataimport.ChatImportVK;
import by.tsvrko.manics.model.dataimport.ChatInfo;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static by.tsvrko.manics.dao.ContentImportUtil.*;
import static by.tsvrko.manics.dao.ParseJSONUtil.*;


/**
 * Created by tsvrko on 1/4/2017.
 */
@Repository
public class ChatImportVKImpl implements ChatImportVK {

    private static final ResourceBundle CONFIG_BUNDLE = ResourceBundle.getBundle("VKapi");
    private static final String ACCESS_TOKEN = CONFIG_BUNDLE.getString("access.token");
    private static Logger log = Logger.getLogger(ChatImportVKImpl.class.getName());

    @Override
    public List<ChatInfo> getChats(String token) {

        List<ChatInfo> chatInfoList = new ArrayList<>();
        int offset = 0;
        int count = Integer.parseInt(parseJSONArrayCount(getChats(offset)));

        while (offset < count) {
            String text;

            while (true) {
                text = getChats(offset);
                if (!text.contains("Too many requests per second")) break;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    log.debug("InterruptedException in current thread", e);
                    Thread.currentThread().interrupt();
                }

            }
            JSONArray jsonChatsArray = parseChatsJSON(text);

            for (Object aJsonChatsArray : jsonChatsArray) {
                JSONObject jsonChat = (JSONObject) aJsonChatsArray;

                if (jsonChat.containsKey("chat_id")) {
                    ChatInfo chatInfo = new ChatInfo();
                    chatInfo.setChatId(Long.valueOf(jsonChat.get("chat_id").toString()));
                    chatInfo.setTitle(jsonChat.get("title").toString());
                    chatInfoList.add(chatInfo);
                }
            }
            offset += 200;
        }
        return chatInfoList;
    }

    @Override
    public List<Integer> getUsers(ChatInfo chatInfo) {
        List<Integer> list = new ArrayList<>();
        String text  = getUsers(chatInfo.getChatId());
        JSONArray jsonUsersArray = parseUserCountJSON(text);
        for (Object aJsonUsersArray : jsonUsersArray) {
            list.add(Integer.valueOf(aJsonUsersArray.toString()));
        }
        return list;
    }

    private String getChats(int offset) {

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/messages.getDialogs")
                .setParameter("access_token", ACCESS_TOKEN)
                .setParameter("count", "200").setParameter("offset", String.valueOf(offset));
        return readContent(uriBuilder);
    }

    private String getUsers(long chat_id) {

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/messages.getChat")
                .setParameter("chat_id",String.valueOf(chat_id))
                .setParameter("access_token", ACCESS_TOKEN)
                .setParameter("v","5.62");
        return readContent(uriBuilder);
    }

}
