package by.tsvrko.manics.service.implementations.db;

import by.tsvrko.manics.dao.implementations.dataimport.MessageImportVKImpl;
import by.tsvrko.manics.dao.interfaces.db.MessageDAO;
import by.tsvrko.manics.model.hibernate.Message;
import by.tsvrko.manics.service.interfaces.db.MessageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created main.by tsvrko on 1/7/2017.
 */

@Service
@Transactional
public class MessageServiceImpl implements MessageService{
    private static Logger log = Logger.getLogger(MessageImportVKImpl.class.getName());

    private MessageDAO messageDAO;

    @Autowired
    public MessageServiceImpl(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @Override
    public List<Message> getMessages(long userId, long chatId) {
        return messageDAO.getByUser(userId,chatId);
    }

    @Override
    public List<Message> getMessages(int id) {
        return messageDAO.getByChat(id);
    }

    @Override
    public boolean addMessages(ArrayList<Message> list, long chatId) {
        messageDAO.addAll(list, chatId);
        return true;
    }

}
