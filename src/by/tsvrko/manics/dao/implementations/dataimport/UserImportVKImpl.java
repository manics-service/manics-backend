package by.tsvrko.manics.dao.implementations.dataimport;

import by.tsvrko.manics.dao.interfaces.dataimport.UserImportVK;
import by.tsvrko.manics.exceptions.TooManyRequestsToApiException;
import by.tsvrko.manics.exceptions.UserIsNotAuthorizedException;
import by.tsvrko.manics.model.dataimport.AuthInfo;
import by.tsvrko.manics.model.dataimport.UserInfo;
import by.tsvrko.manics.service.interfaces.db.SessionService;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

     private static Logger log = Logger.getLogger(MessageImportVKImpl.class.getName());
    private SessionService sessionService;

    @Autowired
    public UserImportVKImpl(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public List<UserInfo> getUsers(List<Integer> list, AuthInfo authInfo) {

        if (sessionService.getByValue(authInfo.getSession()).getUser()==null){
            throw new UserIsNotAuthorizedException("User isn't authorized");
        }

        List<UserInfo> userInfoList = new ArrayList<>();
        String text;
        for(int i:list){
            while (true) {
                try{
                    text = getUserName(authInfo.getToken(),i);
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
                userInfo.setId(jsonMessage.get("id").toString());
                userInfo.setFirstName(jsonMessage.get("first_name").toString());
                userInfo.setLastName(jsonMessage.get("last_name").toString());
                userInfoList.add(userInfo);
            }}

        return userInfoList;
    }

    @Override
    public UserInfo getUser(AuthInfo authInfo) {
        UserInfo userInfo =new UserInfo();
        String text = getDefaultUserName(authInfo.getToken());
        JSONArray jsonUserInfoArray = parseUserJSON(text);

        for(Object object:jsonUserInfoArray)   {
            JSONObject jsonMessage = (JSONObject)object;
            userInfo.setId(jsonMessage.get("id").toString());
            userInfo.setFirstName(jsonMessage.get("first_name").toString());
            userInfo.setLastName(jsonMessage.get("last_name").toString());

        }
        return userInfo;
    }

    private String getUserName(String token, int user_id) throws TooManyRequestsToApiException {

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/users.get")
                .setParameter("user_id",String.valueOf(user_id))
                .setParameter("access_token", token)
                .setParameter("v","5.62");
        return readContent(uriBuilder);
    }

    private String getDefaultUserName(String token)  {

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("api.vk.com").setPath("/method/users.get")
                .setParameter("access_token", token)
                .setParameter("v","5.62");
        return readContent(uriBuilder);
    }


}
