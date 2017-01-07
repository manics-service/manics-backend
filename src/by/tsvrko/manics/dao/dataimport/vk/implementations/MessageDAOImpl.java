package by.tsvrko.manics.dao.dataimport.vk.implementations;

import by.tsvrko.manics.dao.dataimport.vk.interfaces.MessageDAO;
import by.tsvrko.manics.model.Chat;
import by.tsvrko.manics.model.Message;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

import static by.tsvrko.manics.dao.dataimport.vk.ContentImportUtil.*;
import static by.tsvrko.manics.dao.dataimport.vk.ParseJSONUtil.*;

/**
 * Created by irats on 1/5/2017.
 */
public class MessageDAOImpl implements MessageDAO {


    private static Logger log = Logger.getLogger(ChatDAOImpl.class.getName());
    private static final String ACCESS_TOKEN = "25b606be4db7e217d255d818bd3cdaca8c8a6eb91ab055d4a816d4e71a213153cd7707648f8d29bc92c37";

    @Override
    public ArrayList<Message> getMessages(Chat chat) {
        String peer_id = String.valueOf(Integer.valueOf(chat.getChat_id())+2000000000);
        ArrayList<Message> messagesList = new ArrayList<>();

       int count = Integer.parseInt(parseJSONObjectCount(getCount(peer_id)));
        int offset = 0;

        while (offset < count) {
            String text;
            while (true) {
                text = getChatsString(peer_id, offset);
                if (!text.contains("Too many requests per second")) break;
                try {
                    Thread.sleep(500);
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
                message.setBody(jsonMessage.get("body").toString());
                message.setBody(jsonMessage.get("body").toString());
                message.setUser_id(jsonMessage.get("user_id").toString());
                message.setDate(Long.valueOf(jsonMessage.get("date").toString()));


                messagesList.add(message);


            }
            offset += 200;
        }

        System.out.println(count);
        System.out.println(messagesList.size());
        return messagesList;
    }


    private String getCount(String peer_id) {

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/messages.getHistory")
                .setParameter("access_token", ACCESS_TOKEN)
                .setParameter("peer_id", peer_id)
                .setParameter("count", "1")
                .setParameter("v", "5.60");
        return readContent(uriBuilder);
    }

    private String getChatsString(String peer_id, int offset) {

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/messages.getHistory")
                .setParameter("peer_id", peer_id)
                .setParameter("access_token", ACCESS_TOKEN)
                .setParameter("count", "200").setParameter("offset", String.valueOf(offset))
                .setParameter("v", "5.60");
        return readContent(uriBuilder);
    }
}

