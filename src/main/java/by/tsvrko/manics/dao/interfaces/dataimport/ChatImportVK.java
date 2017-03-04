package main.java.by.tsvrko.manics.dao.interfaces.dataimport;

import main.java.by.tsvrko.manics.model.dataimport.AuthInfo;
import main.java.by.tsvrko.manics.model.dataimport.ChatInfo;

import java.util.List;

/**
 * Created main.main.java.by irats on 1/4/2017.
 */
public interface ChatImportVK {

    List<ChatInfo> getChats(AuthInfo authInfo);

    List <Integer> getChatUsers(long chatId,AuthInfo authInfo);
}
