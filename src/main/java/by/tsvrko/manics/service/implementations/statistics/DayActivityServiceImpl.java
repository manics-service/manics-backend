package by.tsvrko.manics.service.implementations.statistics;

import by.tsvrko.manics.model.dataimport.AuthInfo;
import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.dataimport.UserInfo;
import by.tsvrko.manics.model.hibernate.Message;
import by.tsvrko.manics.model.statistics.DayActivity;
import by.tsvrko.manics.service.interfaces.statistics.DayActivityService;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created main.java.by tsvrko on 2/25/2017.
 */
@Service
public class DayActivityServiceImpl implements DayActivityService{


    @Override
    public List<DayActivity> getStatistics(ChatInfo chatInfo, AuthInfo authInfo) {
        List<UserInfo> userInfoList =StatUtil.getUserInfo(chatInfo.getChatId(),authInfo);
        List<DayActivity> dayActivityList = new ArrayList<>();
        for(UserInfo userInfo : userInfoList){
            DayActivity dayActivity= new DayActivity();
            dayActivity.setUserInfo(userInfo);
            List<Message> messageList = StatUtil.getUserMessages(userInfo.getId(), chatInfo.getChatId());
            int messNight = 0;
            int messMorning = 0;
            int messDay = 0;
            int messEvening = 0;
            for(Message message:messageList){
                Time time = new Time(message.getDate()*1000);
                LocalTime localTime = time.toLocalTime();
                int hour =  localTime.getHour();
                if (hour>=0&&hour<6){
                    messNight++;
                }
                else if(hour>=6&&hour<12){
                    messMorning++;
                }
                else if(hour>=12&&hour<18){
                    messDay++;
                }
                else if(hour>=18&&hour<24){
                    messEvening++;
                }
            }
            dayActivity.setMessNight(messNight);
            dayActivity.setMessDay(messDay);
            dayActivity.setMessEvening(messEvening);
            dayActivity.setMessMorning(messMorning);
            dayActivityList.add(dayActivity);
        }
        return dayActivityList;
    }
}
