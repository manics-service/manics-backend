package by.tsvrko.manics.dao.implementations.dataimport;

import by.tsvrko.manics.dao.interfaces.dataimport.UserImportVK;
import by.tsvrko.manics.exceptions.TooManyRequestsToApiException;
import by.tsvrko.manics.model.dataimport.UserInfo;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import static by.tsvrko.manics.dao.ParseJSONUtil.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static by.tsvrko.manics.dao.ContentImportUtil.readContent;

/**
 * Created main.by tsvrko on 1/20/2017.
 */


@Repository
public class UserImportVKImpl implements UserImportVK {

    private static final ResourceBundle CONFIG_BUNDLE = ResourceBundle.getBundle("VKapi");
    private static final String ACCESS_TOKEN = CONFIG_BUNDLE.getString("access.token");
    private static Logger log = Logger.getLogger(MessageImportVKImpl.class.getName());

    @Override
    public List<UserInfo> getUsers(List<Integer> list) {
        List<UserInfo> userInfoList = new ArrayList<>();
        String text;
        for(Integer i:list){
            while (true) {
                try{
                    text = getUserName(i);
                    if (!text.contains("Too many requests per second")) break;}
                catch (TooManyRequestsToApiException e1) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        log.debug("InterruptedException in current thread", e);
                        Thread.currentThread().interrupt();
                    }
                }
            }

            JSONArray jsonUserInfoArray = parseUserJSON(text);
            for(Object object:jsonUserInfoArray)   {
                JSONObject jsonMessage = (JSONObject)object;
                UserInfo userInfo = new UserInfo();
                userInfo.setId(Integer.valueOf(jsonMessage.get("id").toString()));
                userInfo.setFirstName(jsonMessage.get("first_name").toString());
                userInfo.setLastName(jsonMessage.get("last_name").toString());
                userInfoList.add(userInfo);
            }}

        return userInfoList;
    }

    private String getUserName(int user_id) throws TooManyRequestsToApiException {

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/users.get")
                .setParameter("user_id",String.valueOf(user_id))
                .setParameter("access_token", ACCESS_TOKEN)
                .setParameter("v","5.62");
        return readContent(uriBuilder);
    }

}
