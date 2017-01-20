package by.tsvrko.manics.dao.database.interfaces;

import by.tsvrko.manics.model.Chat;
import by.tsvrko.manics.model.User;

import java.util.List;

/**
 * Created by tsvrko on 1/8/2017.
 */
public interface ChatDAO {

     boolean addChat(Chat chat, String token);

     Chat getChatById(Chat chat);

     boolean deleteChat(Chat chat);

     List<Chat> getChats(User user);

}
