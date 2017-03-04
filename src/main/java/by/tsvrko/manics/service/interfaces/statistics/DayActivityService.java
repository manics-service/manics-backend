package main.java.by.tsvrko.manics.service.interfaces.statistics;

import main.java.by.tsvrko.manics.model.dataimport.AuthInfo;
import main.java.by.tsvrko.manics.model.dataimport.ChatInfo;
import main.java.by.tsvrko.manics.model.statistics.DayActivity;

import java.util.List;

/**
 * Created main.java.by tsvrko on 2/26/2017.
 */
public interface DayActivityService {

    List<DayActivity> getStatistics(ChatInfo info, AuthInfo authInfo);

}
