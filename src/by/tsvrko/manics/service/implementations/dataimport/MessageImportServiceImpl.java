package by.tsvrko.manics.service.implementations.dataimport;

import by.tsvrko.manics.dao.interfaces.dataimport.MessageImportVK;
import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.service.interfaces.dataimport.MessageImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created main.by tsvrko on 1/12/2017.
 */

@Service
public class MessageImportServiceImpl implements MessageImportService{

    private MessageImportVK messageImportDAO;

    @Autowired
    public MessageImportServiceImpl(MessageImportVK messageImportDAO) {
        this.messageImportDAO = messageImportDAO;
    }

    @Override
    public boolean getChatMessages(ChatInfo chat, String token) {
        return messageImportDAO.getMessages(chat, token);
    }

}
