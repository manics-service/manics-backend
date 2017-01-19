package by.tsvrko.manics.service.services.importdaoservice;

import by.tsvrko.manics.dao.dataimport.vk.implementations.MessageDAOImportImpl;
import by.tsvrko.manics.dao.dataimport.vk.interfaces.MessageDAOImport;
import by.tsvrko.manics.model.Chat;
import by.tsvrko.manics.model.Message;

import java.util.ArrayList;

/**
 * Created by tsvrko on 1/12/2017.
 */
public class MessageImportService {

    private static MessageDAOImport messageDAOImport = new MessageDAOImportImpl();

    public static boolean getMessages(Chat chat, String token) {

        return messageDAOImport.getMessages(chat, token);

    }
}
