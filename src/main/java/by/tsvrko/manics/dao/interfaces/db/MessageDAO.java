package main.java.by.tsvrko.manics.dao.interfaces.db;

import main.java.by.tsvrko.manics.model.hibernate.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created main.main.java.by tsvrko on 1/7/2017.
 */
public interface MessageDAO {

   boolean addAll(ArrayList<Message> list, long chatId);

   List<Message> getByChat(int id);

   List<Message> getByUser(String userId, long chatId) ;

   List<Message> getByUserDate(String userId, long chatId, long date) ;

}
