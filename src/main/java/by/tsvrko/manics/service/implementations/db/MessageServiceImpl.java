package main.java.by.tsvrko.manics.service.implementations.db;

import main.java.by.tsvrko.manics.dao.implementations.dataimport.MessageImportVKImpl;
import main.java.by.tsvrko.manics.dao.interfaces.db.MessageDAO;
import main.java.by.tsvrko.manics.model.hibernate.Message;
import main.java.by.tsvrko.manics.service.interfaces.db.MessageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created main.main.java.by tsvrko on 1/7/2017.
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
    public List<Message> getMessagesByUser(String userId, long chatId) {
        return messageDAO.getByUser(userId,chatId);
    }

    @Override
    public boolean addMessages(ArrayList<Message> list, long chatId) {
        messageDAO.addAll(list, chatId);
        return true;
    }

    @Override
    public List<Message> getMessagesByUserDate(String userId, long chatId, long date) {
        return messageDAO.getByUserDate(userId, chatId, date);
    }
}
