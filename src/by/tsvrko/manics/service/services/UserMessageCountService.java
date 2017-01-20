package by.tsvrko.manics.service.services;

import by.tsvrko.manics.model.Chat;
import by.tsvrko.manics.model.UserMessageCount;
import by.tsvrko.manics.model.UserInfo;
import by.tsvrko.manics.service.services.importservice.ChatImportService;
import by.tsvrko.manics.service.services.importservice.UserImportService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static by.tsvrko.manics.service.services.dbservice.MessageService.getMessages;

/**
 * Created by tsvrko on 1/20/2017.
 */

@Service("countStatisticsService")
public class UserMessageCountService {

    private static ChatImportService chatImportService = new ChatImportService();
    private static UserImportService userImportService = new UserImportService();

    public List <UserMessageCount> getChatStatistics (Chat chat){


        List <UserMessageCount> statList = new ArrayList<>();
        List<UserInfo> userInfoList = userImportService.getUsers(chatImportService.getChatUserIds(chat));
       for(UserInfo userInfo : userInfoList){

           UserMessageCount userMessageCount = new UserMessageCount();
           userMessageCount.setUserInfo(userInfo);
           userMessageCount.setMessageCount(getMessages(userInfo,chat).size());
           statList.add(userMessageCount);

       }
        return statList;
    }
}
