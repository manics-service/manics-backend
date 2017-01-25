package by.tsvrko.manics.service.services.dao.dataimport;

import by.tsvrko.manics.dao.dataimport.vk.interfaces.MessageImportVK;
import by.tsvrko.manics.model.hibernate.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tsvrko on 1/12/2017.
 */

@Service("messageImportService")
public class MessageImportService {

    @Autowired
    private MessageImportVK messageImportVkDAO;

    public boolean getMessages(Chat chat, String token) {

        return messageImportVkDAO.getMessages(chat, token);
    }

}
