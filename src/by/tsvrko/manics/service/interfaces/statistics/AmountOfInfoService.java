package by.tsvrko.manics.service.interfaces.statistics;

import by.tsvrko.manics.model.dataimport.AuthInfo;
import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.statistics.AmountOfInfo;

import java.util.List;

/**
 * Created by tsvrko on 3/3/2017.
 */
public interface AmountOfInfoService {

    List<AmountOfInfo> getStatistics(ChatInfo info, AuthInfo authInfo);

}
