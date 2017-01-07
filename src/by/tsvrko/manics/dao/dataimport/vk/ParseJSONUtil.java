package by.tsvrko.manics.dao.dataimport.vk;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by irats on 1/6/2017.
 */
public abstract class ParseJSONUtil {
    public static JSONArray parseJSONArray(String text) {
        JSONArray jsonArray = null;
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonResp = (JSONObject) parser.parse(text);
            jsonArray = (JSONArray) jsonResp.get("response");
            jsonArray.remove(0);
        } catch (ParseException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
            messageCount = (jsonResp2.get("count")).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return messageCount;

    }

}
