package by.tsvrko.manics.dao.interfaces.dataimport;

import by.tsvrko.manics.model.dataimport.ChatInfo;

import java.util.List;

/**
 * Created main.by irats on 1/5/2017.
 */
public interface MessageImportVK {

    boolean getMessages(ChatInfo chat, String token);

    List <Number> getChatInfo(long chatId);

}
