package by.tsvrko.manics.dao.interfaces.db;

import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.hibernate.Chat;
import by.tsvrko.manics.model.hibernate.Message;
import by.tsvrko.manics.model.hibernate.User;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created main.by tsvrko on 1/8/2017.
 */
public interface ChatDAO {

     boolean addChat(ChatInfo chatInfo, String token);

     Chat getByChatId(long chatId);

     boolean deleteChat(Chat chat);

     List<Chat> getByUser(long userId);


}
