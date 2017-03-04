package main.java.by.tsvrko.manics.service.interfaces.statistics;

import main.java.by.tsvrko.manics.model.dataimport.AuthInfo;
import main.java.by.tsvrko.manics.model.dataimport.ChatInfo;
import main.java.by.tsvrko.manics.model.statistics.AmountOfInfo;

import java.util.List;

/**
 * Created main.java.by tsvrko on 3/3/2017.
 */
public interface AmountOfInfoService {

    List<AmountOfInfo> getStatistics(ChatInfo info, AuthInfo authInfo);

}
