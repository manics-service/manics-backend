package by.tsvrko.manics.service.implementations.statistics;

import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.dataimport.UserInfo;
import by.tsvrko.manics.model.statistics.UserMessageCount;
import by.tsvrko.manics.service.interfaces.dataimport.ChatImportService;
import by.tsvrko.manics.service.interfaces.dataimport.UserImportService;
import by.tsvrko.manics.service.interfaces.db.MessageService;
import by.tsvrko.manics.service.interfaces.statistics.UserMessageCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created main.by tsvrko on 1/20/2017.
 */

@Service
public class UserMessageCountServiceImpl implements UserMessageCountService{

    private ChatImportService chatImportService;
    private UserImportService userImportService;
    private MessageService messageService;

    @Autowired
    public UserMessageCountServiceImpl(ChatImportService chatImportService, UserImportService userImportService, MessageService messageService) {
        this.chatImportService = chatImportService;
        this.userImportService = userImportService;
        this.messageService = messageService;
    }

    @Override
    public List <UserMessageCount> getChatStatistics (ChatInfo chat){

        List <UserMessageCount> statList = new ArrayList<>();
        List<Integer> chatUserIds= chatImportService.getChatUsersIds(chat);
        List<UserInfo> userInfoList = userImportService.getUsers(chatUserIds);

        for(UserInfo userInfo : userInfoList){

            UserMessageCount userMessageCount = new UserMessageCount();
            userMessageCount.setUserInfo(userInfo);
            userMessageCount.setMessageCount(messageService.getMessages(userInfo,chat.getChatId()).size());
            statList.add(userMessageCount);

        }
        statList.sort(UserMessageCount::compareTo);
        return statList;
    }
}
