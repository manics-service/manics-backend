package by.tsvrko.manics.service.implementations.dataimport;

import by.tsvrko.manics.dao.interfaces.dataimport.ChatImportVK;
import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.service.interfaces.dataimport.ChatImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created main.by tsvrko on 1/12/2017.
 */

@Service
public class ChatImportServiceImpl implements ChatImportService{


    private ChatImportVK chatImportDAO;

    @Autowired
    public ChatImportServiceImpl(ChatImportVK chatImportDAO) {
        this.chatImportDAO = chatImportDAO;
    }

    @Override
    public  List<ChatInfo> getListOfChats(String token) {
        return chatImportDAO.getChats(token);
    }

    @Override
    public  List<Integer> getChatUsersIds(ChatInfo chat) {
        return chatImportDAO.getUsers(chat);
    }
}
