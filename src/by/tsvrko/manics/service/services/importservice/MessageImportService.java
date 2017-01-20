package by.tsvrko.manics.service.services.importservice;

import by.tsvrko.manics.dao.dataimport.vk.implementations.MessageImportVKImpl;
import by.tsvrko.manics.dao.dataimport.vk.interfaces.MessageImportVK;
import by.tsvrko.manics.model.Chat;
import org.springframework.stereotype.Service;

/**
 * Created by tsvrko on 1/12/2017.
 */

@Service("messageImportService")
public class MessageImportService {

    private static MessageImportVK messageImportVK = new MessageImportVKImpl();

    public boolean getMessages(Chat chat, String token) {

        return messageImportVK.getMessages(chat, token);

    }

    public int getMessageCount(Chat chat) {
        return messageImportVK.getMessageCount(chat);
    }
}
