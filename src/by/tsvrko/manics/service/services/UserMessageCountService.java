package by.tsvrko.manics.service.services;

import by.tsvrko.manics.model.hibernate.Chat;
import by.tsvrko.manics.model.UserMessageCount;
import by.tsvrko.manics.model.UserInfo;
import by.tsvrko.manics.service.services.dao.dataimport.ChatImportService;
import by.tsvrko.manics.service.services.dao.dataimport.UserImportService;
import by.tsvrko.manics.service.services.dao.db.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsvrko on 1/20/2017.
 */

@Service("countStatisticsService")
public class UserMessageCountService {

    @Autowired
    private ChatImportService chatImportService;
    @Autowired
    private UserImportService userImportService;
    @Autowired
    private MessageService messageService;

    public List <UserMessageCount> getChatStatistics (Chat chat){

        List <UserMessageCount> statList = new ArrayList<>();
        List<UserInfo> userInfoList = userImportService.getUsers(chatImportService.getChatUserIds(chat));
        for(UserInfo userInfo : userInfoList){

            UserMessageCount userMessageCount = new UserMessageCount();
            userMessageCount.setUserInfo(userInfo);
            userMessageCount.setMessageCount(messageService.getMessages(userInfo,chat).size());
            statList.add(userMessageCount);

        }
        return statList;
    }
}
