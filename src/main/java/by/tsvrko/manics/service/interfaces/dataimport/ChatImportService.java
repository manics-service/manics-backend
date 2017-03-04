package main.java.by.tsvrko.manics.service.interfaces.dataimport;

import main.java.by.tsvrko.manics.model.dataimport.AuthInfo;
import main.java.by.tsvrko.manics.model.dataimport.ChatInfo;

import java.util.List;

/**
 * Created main.main.java.by tsvrko on 2/10/2017.
 */
public interface ChatImportService {

    List<ChatInfo> getListOfChats(AuthInfo authInfo) ;

    List<Integer> getChatUsersIds(long chatId, AuthInfo authInfo);
}
