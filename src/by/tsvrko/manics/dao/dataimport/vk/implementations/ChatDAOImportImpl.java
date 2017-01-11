package by.tsvrko.manics.dao.dataimport.vk.implementations;

import by.tsvrko.manics.dao.dataimport.vk.interfaces.ChatDAOImport;
import by.tsvrko.manics.model.Chat;
import by.tsvrko.manics.model.UserSession;
import by.tsvrko.manics.service.services.dbdaoservice.ChatService;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static by.tsvrko.manics.dao.dataimport.vk.ContentImportUtil.*;
import static by.tsvrko.manics.dao.dataimport.vk.ParseJSONUtil.*;


/**
 * Created by tsvrko on 1/4/2017.
 */
public class ChatDAOImportImpl implements ChatDAOImport {

    private static final ResourceBundle CONFIG_BUNDLE = ResourceBundle.getBundle("VKapi");
    private static final String ACCESS_TOKEN = CONFIG_BUNDLE.getString("access.token");
    private static Logger log = Logger.getLogger(ChatDAOImportImpl.class.getName());
    private static ChatService chatService = new ChatService();

    @Override
    public ArrayList<Chat> getChats(String token) {

        ArrayList<Chat> chatList = new ArrayList<>();
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

            JSONArray jsonChatsArray = parseJSONArray(text);

            for (Object aJsonChatsArray : jsonChatsArray) {
                JSONObject jsonChat = (JSONObject) aJsonChatsArray;

                if (jsonChat.containsKey("chat_id")) {
                    Chat chat = new Chat();
                    chat.setChat_id(Long.valueOf(jsonChat.get("chat_id").toString()));
                    chat.setTitle(jsonChat.get("title").toString());
                    chatList.add(chat);
                }
            }
            offset += 200;
        }
        chatService.addChats(chatList,token);
        return chatList;
    }



    private String getChats(int offset) {

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/messages.getDialogs")
                .setParameter("access_token", ACCESS_TOKEN)
                .setParameter("count", "200").setParameter("offset", String.valueOf(offset));
        return readContent(uriBuilder);
    }
}
