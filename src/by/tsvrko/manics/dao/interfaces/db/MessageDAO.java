package by.tsvrko.manics.dao.interfaces.db;

import by.tsvrko.manics.model.hibernate.Chat;
import by.tsvrko.manics.model.hibernate.Message;
import by.tsvrko.manics.model.dataimport.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsvrko on 1/7/2017.
 */
public interface MessageDAO {

   boolean addMessages(ArrayList<Message> list, int chatId);

   List<Message> getMessages(Chat chat);

   List<Message> getMessagesByUser(UserInfo userInfo, int chatId);
}
