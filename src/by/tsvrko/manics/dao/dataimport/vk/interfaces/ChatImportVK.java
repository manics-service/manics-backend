package by.tsvrko.manics.dao.dataimport.vk.interfaces;

import by.tsvrko.manics.model.Chat;
import by.tsvrko.manics.model.UserSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by irats on 1/4/2017.
 */
public interface ChatImportVK {

    List<Chat> getChats(String token);

    List getChatUserIds(Chat chat);
}
