package by.tsvrko.manics.service.services.dao.db;

import by.tsvrko.manics.dao.database.interfaces.MessageDAO;
import by.tsvrko.manics.model.hibernate.Chat;
import by.tsvrko.manics.model.hibernate.Message;
import by.tsvrko.manics.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsvrko on 1/7/2017.
 */

@Service("messageService")
public class MessageService {

    @Autowired
    MessageDAO messageDAO;

    public List<Message> getMessages(UserInfo userInfo, Chat chat) {
        return messageDAO.getMessagesByUser(userInfo,chat);
    }

    public List<Message> getMessages(Chat chat) {
        return messageDAO.getMessages(chat);
    }

    public boolean addMessages(ArrayList<Message> list, Chat chat) {

        messageDAO.addMessages(list, chat);
        return true;


    }





}
