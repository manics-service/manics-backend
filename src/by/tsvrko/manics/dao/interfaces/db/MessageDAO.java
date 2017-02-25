package by.tsvrko.manics.dao.interfaces.db;

import by.tsvrko.manics.model.hibernate.Chat;
import by.tsvrko.manics.model.hibernate.Message;
import by.tsvrko.manics.model.dataimport.UserInfo;
import org.springframework.data.repository.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created main.by tsvrko on 1/7/2017.
 */
public interface MessageDAO {

   boolean addAll(ArrayList<Message> list, long chatId);

   List<Message> getByChat(long chatId);

   List<Message> getByUser(long userId, long chatId) ;
}
