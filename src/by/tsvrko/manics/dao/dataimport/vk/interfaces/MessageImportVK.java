package by.tsvrko.manics.dao.dataimport.vk.interfaces;

import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.hibernate.Chat;

/**
 * Created by irats on 1/5/2017.
 */
public interface MessageImportVK {

    boolean getMessages(ChatInfo chat, String token);

    int getMessageCount(int chatId);

}
