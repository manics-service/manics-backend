package by.tsvrko.manics.service.services.importdaoservice;

import by.tsvrko.manics.dao.dataimport.vk.implementations.ChatDAOImportImpl;
import by.tsvrko.manics.dao.dataimport.vk.interfaces.ChatDAOImport;
import by.tsvrko.manics.model.Chat;
import by.tsvrko.manics.model.UserSession;

import java.util.ArrayList;

/**
 * Created by tsvrko on 1/12/2017.
 */
public class ChatImportService {

   private static ChatDAOImport chatDAOImport = new ChatDAOImportImpl();

    public ArrayList<Chat> getChats(String token) {
        return chatDAOImport.getChats(token);
    }
}
