package by.tsvrko.manics.service.interfaces.db;

import by.tsvrko.manics.model.dataimport.UserInfo;
import by.tsvrko.manics.model.hibernate.Chat;
import by.tsvrko.manics.model.hibernate.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsvrko on 2/10/2017.
 */
public interface MessageService {

    List<Message> getMessages(UserInfo userInfo, int chatId);

    List<Message> getMessages(Chat chat);

    boolean addMessages(ArrayList<Message> list, int chatId);
}
