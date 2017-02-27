package by.tsvrko.manics.service.implementations.statistics;

import by.tsvrko.manics.model.dataimport.AuthInfo;
import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.dataimport.UserInfo;
import by.tsvrko.manics.model.statistics.MessageCount;
import by.tsvrko.manics.service.interfaces.dataimport.ChatImportService;
import by.tsvrko.manics.service.interfaces.dataimport.UserInfoService;
import by.tsvrko.manics.service.interfaces.db.MessageService;
import by.tsvrko.manics.service.interfaces.statistics.MessageCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created main.by tsvrko on 1/20/2017.
 */

@Service
public class MessageCountServiceImpl implements MessageCountService {

    private ChatImportService chatImportService;
    private UserInfoService userInfoService;
    private MessageService messageService;

    @Autowired
    public MessageCountServiceImpl(ChatImportService chatImportService, UserInfoService userInfoService, MessageService messageService) {
        this.chatImportService = chatImportService;
        this.userInfoService = userInfoService;
        this.messageService = messageService;
    }

    @Override
    public List <MessageCount> getMessageCount(ChatInfo chat, AuthInfo authInfo){

        List <MessageCount> statList = new ArrayList<>();
        List<Integer> chatUserIds= chatImportService.getChatUsersIds(chat.getChatId());
        List<UserInfo> userInfoList = userInfoService.getUsers(chatUserIds,authInfo);

        for(UserInfo userInfo : userInfoList){

            MessageCount messageCount = new MessageCount();
            messageCount.setUserInfo(userInfo);
            messageCount.setMessageCount(messageService.getMessages(userInfo.getId(),chat.getChatId()).size());
            statList.add(messageCount);

        }
        statList.sort(MessageCount::compareTo);
        return statList;
    }
}
