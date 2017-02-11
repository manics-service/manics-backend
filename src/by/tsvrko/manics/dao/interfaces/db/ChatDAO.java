package by.tsvrko.manics.dao.interfaces.db;

import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.hibernate.Chat;
import by.tsvrko.manics.model.hibernate.User;

import java.util.List;

/**
 * Created by tsvrko on 1/8/2017.
 */
public interface ChatDAO {

     boolean addChat(ChatInfo chatInfo, String token);

     Chat getChatById(long chatId);

     boolean deleteChat(Chat chat);

     List<Chat> getChats(User user);

}
