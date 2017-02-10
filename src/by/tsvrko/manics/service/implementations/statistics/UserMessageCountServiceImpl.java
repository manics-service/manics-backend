package by.tsvrko.manics.service.implementations.statistics;

import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.dataimport.UserInfo;
import by.tsvrko.manics.model.statistics.UserMessageCount;
import by.tsvrko.manics.service.implementations.dataimport.ChatImportServiceImpl;
import by.tsvrko.manics.service.implementations.dataimport.UserImportServiceImpl;
import by.tsvrko.manics.service.implementations.db.MessageServiceImpl;
import by.tsvrko.manics.service.interfaces.statistics.UserMessageCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsvrko on 1/20/2017.
 */

@Service("countStatisticsService")
public class UserMessageCountServiceImpl implements UserMessageCountService{

    private ChatImportServiceImpl chatImportServiceImpl;
    private UserImportServiceImpl userImportServiceImpl;
    private MessageServiceImpl messageServiceImpl;

    @Autowired
    public UserMessageCountServiceImpl(ChatImportServiceImpl chatImportServiceImpl, UserImportServiceImpl userImportServiceImpl, MessageServiceImpl messageServiceImpl) {
        this.chatImportServiceImpl = chatImportServiceImpl;
        this.userImportServiceImpl = userImportServiceImpl;
        this.messageServiceImpl = messageServiceImpl;
    }

    @Override
    public List <UserMessageCount> getChatStatistics (ChatInfo chat){

        List <UserMessageCount> statList = new ArrayList<>();
        List<Integer> chatUserIds= chatImportServiceImpl.getChatUsersIds(chat);
        List<UserInfo> userInfoList = userImportServiceImpl.getUsers(chatUserIds);

        for(UserInfo userInfo : userInfoList){

            UserMessageCount userMessageCount = new UserMessageCount();
            userMessageCount.setUserInfo(userInfo);
            userMessageCount.setMessageCount(messageServiceImpl.getMessages(userInfo,chat.getChatId()).size());
            statList.add(userMessageCount);

        }
        statList.sort(UserMessageCount::compareTo);
        return statList;
    }
}
