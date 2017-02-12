package by.tsvrko.manics.service.interfaces.statistics;

import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.statistics.UserMessageCount;
import java.util.List;

/**
 * Created main.by tsvrko on 2/10/2017.
 */
public interface UserMessageCountService {

    List<UserMessageCount> getChatStatistics (ChatInfo chat);
}
