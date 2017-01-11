package by.tsvrko.manics.dao.dataimport.vk;

import by.tsvrko.manics.dao.dataimport.vk.implementations.ChatDAOImportImpl;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by tsvrko on 1/6/2017.
 */
public abstract class ParseJSONUtil {

    private static Logger log = Logger.getLogger(ChatDAOImportImpl.class.getName());

    public static JSONArray parseJSONArray(String text) {
        JSONArray jsonArray = null;
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonResp = (JSONObject) parser.parse(text);
            jsonArray = (JSONArray) jsonResp.get("response");
            jsonArray.remove(0);
        } catch (ParseException e) {
            log.debug("json can't be parsed",e);
        }
        return jsonArray;

    }

    public static JSONArray parseJSONObject(String text) {
        JSONArray jsonArray = null;
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonResp1 = (JSONObject) parser.parse(text);
            JSONObject jsonResp2 = (JSONObject) jsonResp1.get("response");
            jsonArray = (JSONArray) jsonResp2.get("items");

        } catch (ParseException e) {
            log.debug("json can't be parsed",e);
        }
        return jsonArray;

    }

    public static String parseJSONArrayCount(String text) {
        String messageCount="";
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonResp = (JSONObject)parser.parse(text);
            JSONArray jsonArray = (JSONArray)jsonResp.get("response");
            messageCount = (jsonArray.get(0)).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return messageCount;

    }

    public static String parseJSONObjectCount(String text) {
        String messageCount="";
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonResp1 = (JSONObject) parser.parse(text);
            JSONObject jsonResp2 = (JSONObject)jsonResp1.get("response");
            try{
            messageCount = (jsonResp2.get("count")).toString();}
            catch (NullPointerException e){
                log.debug("chat contains no messages", e);
                messageCount = "0";

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return messageCount;

    }

}
