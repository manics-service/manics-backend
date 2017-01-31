package by.tsvrko.manics.service.services.dao.dataimport;

import by.tsvrko.manics.dao.dataimport.vk.interfaces.ChatImportVK;
import by.tsvrko.manics.model.ChatInfo;
import by.tsvrko.manics.model.hibernate.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tsvrko on 1/12/2017.
 */

@Service("chatImportService")
public class ChatImportService {

    @Autowired
    private ChatImportVK chatImportDAO;

    public  List<ChatInfo> getChats(String token) {
        return chatImportDAO.getChats(token);
    }

    public  List<Integer> getChatUserIds(Chat chat) {
        return chatImportDAO.getChatUserIds(chat);
    }
}
