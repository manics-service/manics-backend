package by.tsvrko.manics.service.implementations.db;

import by.tsvrko.manics.dao.interfaces.db.MessageDAO;
import by.tsvrko.manics.model.hibernate.Chat;
import by.tsvrko.manics.model.hibernate.Message;
import by.tsvrko.manics.model.dataimport.UserInfo;
import by.tsvrko.manics.service.interfaces.db.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsvrko on 1/7/2017.
 */

@Service
@Transactional
public class MessageServiceImpl implements MessageService{

    private MessageDAO messageDAO;

    @Autowired
    public MessageServiceImpl(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @Override
    public List<Message> getMessages(UserInfo userInfo, int chatId) {
        return messageDAO.getMessagesByUser(userInfo,chatId);
    }

    @Override
    public List<Message> getMessages(Chat chat) {
        return messageDAO.getMessages(chat);
    }

    @Override
    public boolean addMessages(ArrayList<Message> list, int chatId) {
        messageDAO.addMessages(list, chatId);
        return true;
    }

}
