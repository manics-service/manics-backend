package main.java.by.tsvrko.manics.service.interfaces.statistics;

import main.java.by.tsvrko.manics.model.dataimport.AuthInfo;
import main.java.by.tsvrko.manics.model.dataimport.ChatInfo;
import main.java.by.tsvrko.manics.model.statistics.MessageCount;

import java.util.List;

/**
 * Created main.java.by tsvrko on 2/26/2017.
 */
public interface MessageCountService {

    List<MessageCount> getStatistics(ChatInfo chat, AuthInfo authInfo);
}
