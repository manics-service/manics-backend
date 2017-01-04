package by.tsvrko.manics.dao.implementations;

import by.tsvrko.manics.dao.interfaces.ChatImport;
import by.tsvrko.manics.model.Chat;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import static by.tsvrko.manics.dao.ContentImportUtils.*;

/**
 * Created by irats on 1/4/2017.
 */
public class ChatImportImplVK implements ChatImport {

    private static Logger log = Logger.getLogger(ChatImportImplVK.class.getName());
    private static final String ACCESS_TOKEN = "25b606be4db7e217d255d818bd3cdaca8c8a6eb91ab055d4a816d4e71a213153cd7707648f8d29bc92c37";

    @Override
    public ArrayList<Chat> getChats() {

        ArrayList<Chat> chatList = new ArrayList<>();

        int count = Integer.parseInt(parseToJSON(getCount()).get(0).toString());
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

            JSONArray jsonChatsArray = parseToJSON(text);

            for (int i = 1; i < jsonChatsArray.size(); i++) {
                JSONObject jsonChat = (JSONObject) jsonChatsArray.get(i);

                if (jsonChat.containsKey("chat_id")) {
                    Chat chat = new Chat();
                    chat.setChat_id(jsonChat.get("chat_id").toString());
                    chat.setTitle(jsonChat.get("title").toString());
                    chatList.add(chat);
                }
            }
            offset += 200;
        }
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
