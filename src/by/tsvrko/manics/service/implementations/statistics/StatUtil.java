package by.tsvrko.manics.service.implementations.statistics;

import by.tsvrko.manics.model.dataimport.AuthInfo;
import by.tsvrko.manics.model.dataimport.UserInfo;
import by.tsvrko.manics.model.hibernate.Message;
import by.tsvrko.manics.service.interfaces.dataimport.ChatImportService;
import by.tsvrko.manics.service.interfaces.dataimport.UserInfoService;
import by.tsvrko.manics.service.interfaces.db.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by tsvrko on 2/28/2017.
 */
@Component
public final class StatUtil {

    private static ChatImportService chatImportService;
    private static UserInfoService userInfoService;
    private static MessageService messageService;

    @Autowired
    public StatUtil(ChatImportService chatImportService1, UserInfoService userInfoService1, MessageService messageService1) {
        chatImportService = chatImportService1;
        userInfoService = userInfoService1;
        messageService = messageService1;
    }

    static List<UserInfo> getUserInfo(long chatId, AuthInfo authInfo){
        List<Integer> chatUserIds= chatImportService.getChatUsersIds(chatId,authInfo);
        return userInfoService.getUsers(chatUserIds,authInfo);
    }

    static List<Message> getUserMessages(String userId, long chatId){
        return  messageService.getMessagesByUser(userId,chatId);
    }

    static List<Message> getUserMessagesOnDate(String userId, long chatId, long date){
        return  messageService.getMessagesByUserDate(userId, chatId, date);
    }



}
