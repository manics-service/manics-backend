package by.tsvrko.manics.dao.dataimport.vk.interfaces;

import by.tsvrko.manics.model.Chat;
import by.tsvrko.manics.model.UserSession;

import java.util.ArrayList;

/**
 * Created by irats on 1/4/2017.
 */
public interface ChatDAOImport {

    ArrayList<Chat> getChats(String token);
}
