package by.tsvrko.manics.dao;

import by.tsvrko.manics.dao.implementations.dataimport.ChatImportVKImpl;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created main.main.java.by tsvrko on 1/6/2017.
 */
public final class ParseJSONUtil {

    private static Logger log = Logger.getLogger(ChatImportVKImpl.class.getName());

    private static JSONParser parser = new JSONParser();


    public static JSONArray parseUserJSON(String text) {
        return getJSONArrayResponse(text);
    }

    public static JSONArray parseMessagesJSON(String text) {
        JSONObject jsonResp = getJSONObjectResponse(text);
        return (JSONArray) jsonResp.get("items");
    }

    public static JSONArray parseChatsJSON(String text) {
        JSONArray jsonResp = getJSONArrayResponse(text);
        jsonResp.remove(0);
        return jsonResp;
    }

    public static JSONArray parseUsersJSON(String text) {
        JSONObject jsonResp = getJSONObjectResponse(text);
        return (JSONArray) jsonResp.get("users");
    }

    public static long parseMessageCount(String text) {
        JSONObject jsonResp = getJSONObjectResponse(text);
        return (long)jsonResp.get("count");
    }

    public static long parseChatsCount(String text) {
        JSONArray jsonResp = getJSONArrayResponse(text);
        return (long)jsonResp.get(0);
    }

    public static List<Number> parseChatInfo(String text) {

        List<Number> chatInfo = new ArrayList<>();
        long messageCount;
        long lastMessageDate;
        try{
            messageCount = parseMessageCount(text);
            JSONArray itemsArray =  parseMessagesJSON(text);
            JSONObject lastMessage = (JSONObject)itemsArray.get(0);
            lastMessageDate = (long)(lastMessage.get("date"));
            chatInfo.add(messageCount);
            chatInfo.add(lastMessageDate);
        }
        catch (NullPointerException e){
            log.debug("chat contains no messages", e);
        }
        return chatInfo;
    }

    private static JSONObject parseText(String text){
        JSONObject jsonResp = null;
        try {
            jsonResp =(JSONObject)parser.parse(text);
        } catch (ParseException e) {
            log.debug("json can't be parsed",e);
        }
        return jsonResp; }

    private static JSONObject getJSONObjectResponse (String text)  {
        return (JSONObject) parseText(text).get("response");
    }

    private static JSONArray getJSONArrayResponse (String text)  {
        return (JSONArray) parseText(text).get("response");
    }

}
