package by.tsvrko.manics.dao.dataimport.vk.implementations;

import by.tsvrko.manics.dao.dataimport.vk.interfaces.UserImportVK;
import by.tsvrko.manics.model.UserInfo;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static by.tsvrko.manics.dao.dataimport.vk.ParseJSONUtil.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static by.tsvrko.manics.dao.dataimport.vk.ContentImportUtil.readContent;

/**
 * Created by tsvrko on 1/20/2017.
 */
public class UserImportVKImpl implements UserImportVK {

    private static final ResourceBundle CONFIG_BUNDLE = ResourceBundle.getBundle("VKapi");
    private static final String ACCESS_TOKEN = CONFIG_BUNDLE.getString("access.token");
    private static Logger log = Logger.getLogger(ChatImportVKImpl.class.getName());

    @Override
    public List<UserInfo> getUsers(List<Integer> list) {
        List<UserInfo> userList = new ArrayList<>();
        for(Integer i:list){
            String text = getUserName(i);
            JSONArray jsonUserInfoArray = parseUserJSON(text);

            for (Object ajsonUserInfoArray : jsonUserInfoArray) {
                JSONObject jsonMessage = (JSONObject) ajsonUserInfoArray;
                UserInfo userInfo = new UserInfo();

                userInfo.setId(Integer.valueOf(jsonMessage.get("id").toString()));
                userInfo.setFirst_name(jsonMessage.get("first_name").toString());
                userInfo.setLast_name(jsonMessage.get("last_name").toString());

                userList.add(userInfo);
            }



        }




            return userList;
    }

    private String getUserName(int user_id) {

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/users.get")
                .setParameter("user_id",String.valueOf(user_id))
                .setParameter("access_token", ACCESS_TOKEN)
                .setParameter("v","5.62");
        return readContent(uriBuilder);
    }


}
