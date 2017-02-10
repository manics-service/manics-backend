package by.tsvrko.manics.service.interfaces.dataimport;

import by.tsvrko.manics.model.dataimport.ChatInfo;

import java.util.List;

/**
 * Created by tsvrko on 2/10/2017.
 */
public interface ChatImportService {

    List<ChatInfo> getListOfChats(String token) ;

    List<Integer> getChatUsersIds(ChatInfo chat);
}
