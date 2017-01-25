package by.tsvrko.manics.dao.dataimport.vk.interfaces;

import by.tsvrko.manics.model.ChatInfo;
import by.tsvrko.manics.model.hibernate.Chat;

import java.util.List;

/**
 * Created by irats on 1/4/2017.
 */
public interface ChatImportVK {

    List<ChatInfo> getChats(String token);

    List getChatUserIds(Chat chat);
}
