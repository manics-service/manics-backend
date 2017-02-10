package by.tsvrko.manics.dao.interfaces.dataimport;

import by.tsvrko.manics.model.dataimport.ChatInfo;

import java.util.List;

/**
 * Created by irats on 1/4/2017.
 */
public interface ChatImportVK {

    List<ChatInfo> getChats(String token);

    List <Integer> getUsers(ChatInfo chatInfo);
}
