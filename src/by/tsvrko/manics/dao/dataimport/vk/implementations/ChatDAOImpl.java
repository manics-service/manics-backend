package by.tsvrko.manics.dao.dataimport.vk.implementations;

import by.tsvrko.manics.dao.dataimport.vk.interfaces.ChatDAO;
import by.tsvrko.manics.model.Chat;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import static by.tsvrko.manics.dao.dataimport.vk.ContentImportUtil.*;
import static by.tsvrko.manics.dao.dataimport.vk.ParseJSONUtil.*;


/**
 * Created by irats on 1/4/2017.
 */
public class ChatDAOImpl implements ChatDAO {

    private static Logger log = Logger.getLogger(ChatDAOImpl.class.getName());
    private static final String ACCESS_TOKEN = "25b606be4db7e217d255d818bd3cdaca8c8a6eb91ab055d4a816d4e71a213153cd7707648f8d29bc92c37";

    @Override
    public ArrayList<Chat> getChats() {

        ArrayList<Chat> chatList = new ArrayList<>();

        int count = Integer.parseInt(parseJSONArrayCount(getCount()));
        int offset = 0;

        while (offset < count) {
            String text;
            while (true) {
                text = getChatsString(offset);
                if (!text.contains("Too many requests per second")) break;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    log.debug("InterruptedException in current thread", e);
                    Thread.currentThread().interrupt();
                }

            }

            JSONArray jsonChatsArray = parseJSONArray(text);

            for (Object aJsonChatsArray : jsonChatsArray) {
                JSONObject jsonChat = (JSONObject) aJsonChatsArray;

                if (jsonChat.containsKey("chat_id")) {
                    Chat chat = new Chat();
                    chat.setChat_id(jsonChat.get("chat_id").toString());
                    chat.setTitle(jsonChat.get("title").toString());
                    chatList.add(chat);
                }
            }
            offset += 200;
        }
        System.out.println(count);
        System.out.println(chatList.size());

        return chatList;
    }


    private String getCount() {

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/messages.getDialogs")
                .setParameter("access_token", ACCESS_TOKEN)
                .setParameter("count", "0");
        return readContent(uriBuilder);
    }

    private String getChatsString(int offset) {

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/messages.getDialogs")
                .setParameter("access_token", ACCESS_TOKEN)
                .setParameter("count", "200").setParameter("offset", String.valueOf(offset));
        return readContent(uriBuilder);
    }
}
