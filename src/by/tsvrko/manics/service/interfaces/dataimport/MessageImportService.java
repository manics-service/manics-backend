package by.tsvrko.manics.service.interfaces.dataimport;

import by.tsvrko.manics.model.dataimport.ChatInfo;

/**
 * Created main.by tsvrko on 2/10/2017.
 */
public interface MessageImportService {

    boolean getChatMessages(ChatInfo chat, String token) ;
}
