package by.tsvrko.manics.dao.database.interfaces;

import by.tsvrko.manics.model.Chat;
import by.tsvrko.manics.model.Message;
import by.tsvrko.manics.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsvrko on 1/7/2017.
 */
public interface MessageDAO {

   boolean addMessages(ArrayList<Message> list, Chat chat);

   List<Message> getMessages(Chat chat);

   List<Message> getMessagesByUser(UserInfo userInfo, Chat chat);
}
