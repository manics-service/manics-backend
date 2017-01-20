package by.tsvrko.manics.service.services.importservice;

import by.tsvrko.manics.dao.dataimport.vk.implementations.ChatImportVKImpl;
import by.tsvrko.manics.dao.dataimport.vk.interfaces.ChatImportVK;
import by.tsvrko.manics.model.Chat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsvrko on 1/12/2017.
 */

@Service("chatImportService")
public class ChatImportService {

   private static ChatImportVK chatImportVK = new ChatImportVKImpl();

    public  List<Chat> getChats(String token) {
        return chatImportVK.getChats(token);
    }

    public  List<Integer> getChatUserIds(Chat chat) {
        return chatImportVK.getChatUserIds(chat);
    }
}
