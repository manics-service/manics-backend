package by.tsvrko.manics.service.interfaces.db;

import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.hibernate.Chat;
import by.tsvrko.manics.model.hibernate.User;

import java.util.List;

/**
 * Created by tsvrko on 2/10/2017.
 */
public interface ChatService {

    boolean addChat(ChatInfo chat, String token);

    Chat getChatById(int chatId);

    boolean deleteChat(Chat chat);

    List<Chat> getChats(User user);
}
