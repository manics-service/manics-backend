package by.tsvrko.manics.service.interfaces.statistics;

import by.tsvrko.manics.model.dataimport.AuthInfo;
import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.statistics.DayActivity;
import by.tsvrko.manics.model.statistics.MessageCount;

import java.util.List;

/**
 * Created by tsvrko on 2/26/2017.
 */
public interface MessageCountService {

    List<MessageCount> getStatistics(ChatInfo chat, AuthInfo authInfo);
}
