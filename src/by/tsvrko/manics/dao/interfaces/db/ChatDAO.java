package by.tsvrko.manics.dao.interfaces.db;

import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.hibernate.Chat;

import java.util.List;

/**
 * Created main.by tsvrko on 1/8/2017.
 */
public interface ChatDAO {

     boolean addChat(ChatInfo chatInfo, String session);

     Chat getByChatId(long chatId);

     boolean deleteChat(Chat chat);

     List<Chat> getBySession(String session);


}
