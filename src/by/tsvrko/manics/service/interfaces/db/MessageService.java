package by.tsvrko.manics.service.interfaces.db;

import by.tsvrko.manics.model.hibernate.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created main.by tsvrko on 2/10/2017.
 */
public interface MessageService {

    List<Message> getMessagesByUser(String userId, long chatId);

    List<Message> getMessagesByUserDate(String userId, long chatId, long date);

    boolean addMessages(ArrayList<Message> list, long chatId);
}
