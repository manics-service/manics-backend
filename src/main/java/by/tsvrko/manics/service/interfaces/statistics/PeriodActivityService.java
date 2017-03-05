package by.tsvrko.manics.service.interfaces.statistics;

import by.tsvrko.manics.model.dataimport.AuthInfo;
import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.statistics.PeriodActivity;

import java.util.List;

/**
 * Created main.java.by tsvrko on 2/28/2017.
 */
public interface PeriodActivityService {

    List<PeriodActivity> getStatistics (ChatInfo chatInfo,AuthInfo authInfo, long date);

}
