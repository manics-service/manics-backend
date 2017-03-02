package by.tsvrko.manics.service.implementations.statistics;

import by.tsvrko.manics.model.dataimport.AuthInfo;
import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.dataimport.MessageInfo;
import by.tsvrko.manics.model.dataimport.UserInfo;
import by.tsvrko.manics.model.hibernate.Message;
import by.tsvrko.manics.model.statistics.PeriodActivity;
import by.tsvrko.manics.service.interfaces.dataimport.ChatImportService;
import by.tsvrko.manics.service.interfaces.dataimport.UserInfoService;
import by.tsvrko.manics.service.interfaces.db.MessageService;
import by.tsvrko.manics.service.interfaces.statistics.PeriodActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.*;

/**
 * Created by tsvrko on 2/28/2017.
 */
@Service
public class PeriodActivityServiceImpl implements PeriodActivityService {

    @Override
    public List<PeriodActivity> getStatistics(ChatInfo chatInfo, AuthInfo authInfo, long date) {

        List<PeriodActivity> periodActivities = new ArrayList<>();
        List<UserInfo> userInfoList =StatUtil.getUserInfo(chatInfo.getChatId(),authInfo);

        Time time = new Time(date*1000);

        for(UserInfo userInfo : userInfoList){
            PeriodActivity periodActivity = new PeriodActivity();
            periodActivity.setUserInfo(userInfo);
            List<MessageInfo> messageInfoList = new ArrayList<>();
            List<Message> messageList = StatUtil.getUserMessagesOnDate(userInfo.getId(), chatInfo.getChatId(),date);

            LocalDate date1 =
                    Instant.ofEpochMilli(messageList.get(0).getDate()*1000).atZone(ZoneId.systemDefault()).toLocalDate();
            int dayMessageCount = 0;

            for(int i=0;i<=messageList.size()-1;i++)
            {

                Message message = messageList.get(i);
                LocalDate date2 =
                        Instant.ofEpochMilli(message.getDate()*1000).atZone(ZoneId.systemDefault()).toLocalDate();
                if(date2.getDayOfMonth()==date1.getDayOfMonth()){
                    dayMessageCount++;
                }
                else {
                    messageInfoList.add(new MessageInfo(message.getDate(),dayMessageCount));
                    i += dayMessageCount;
                    dayMessageCount = 0;
                    date1 = Instant.ofEpochMilli(message.getDate()*1000).atZone(ZoneId.systemDefault()).toLocalDate();
                }

            }

            periodActivity.setMessageInfoList(messageInfoList);
            periodActivities.add(periodActivity);
        }
        return periodActivities;
    }

}
