package by.tsvrko.manics.service.implementations.statistics;

import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.dataimport.UserInfo;
import by.tsvrko.manics.model.statistics.MessageCount;
import by.tsvrko.manics.service.interfaces.dataimport.ChatImportService;
import by.tsvrko.manics.service.interfaces.dataimport.UserImportService;
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
    private UserImportService userImportService;
    private MessageService messageService;

    @Autowired
    public MessageCountServiceImpl(ChatImportService chatImportService, UserImportService userImportService, MessageService messageService) {
        this.chatImportService = chatImportService;
        this.userImportService = userImportService;
        this.messageService = messageService;
    }

    @Override
    public List <MessageCount> getMessageCount(ChatInfo chat){

        List <MessageCount> statList = new ArrayList<>();
        List<Integer> chatUserIds= chatImportService.getChatUsersIds(chat.getChatId());
        List<UserInfo> userInfoList = userImportService.getUsers(chatUserIds);

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
