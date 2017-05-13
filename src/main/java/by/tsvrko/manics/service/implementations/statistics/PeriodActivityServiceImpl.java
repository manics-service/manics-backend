package by.tsvrko.manics.service.implementations.statistics;

import by.tsvrko.manics.model.dataimport.AuthInfo;
import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.dataimport.MessageInfo;
import by.tsvrko.manics.model.dataimport.UserInfo;
import by.tsvrko.manics.model.hibernate.Message;
import by.tsvrko.manics.model.statistics.PeriodActivity;
import by.tsvrko.manics.service.interfaces.statistics.PeriodActivityService;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Created main.java.by tsvrko on 2/28/2017.
 */
@Service
public class PeriodActivityServiceImpl implements PeriodActivityService {



    @Override
    public List<PeriodActivity> getStatistics(ChatInfo chatInfo, AuthInfo authInfo, long date) {
        List<PeriodActivity> periodActivities = new ArrayList<>();
        List<UserInfo> userInfoList =StatUtil.getUserInfo(chatInfo.getChatId(),authInfo);
        for(UserInfo userInfo : userInfoList){
            PeriodActivity periodActivity = new PeriodActivity();
            periodActivity.setUserInfo(userInfo);
            List<MessageInfo> messageInfoList = new ArrayList<>();
            List<Message> messageList = StatUtil.getUserMessagesOnDate(userInfo.getId(), chatInfo.getChatId(),date);
            LocalDate messageDate =
                    Instant.ofEpochMilli(messageList.get(0).getDate()*1000).atZone(ZoneId.systemDefault()).toLocalDate();
            int dayMessageCount = 0;
            for(int i=0;i<=messageList.size()-1;i++)
            {
                Message message = messageList.get(i);
                LocalDate currentDate =
                        Instant.ofEpochMilli(message.getDate()*1000).atZone(ZoneId.systemDefault()).toLocalDate();
                if(currentDate.getDayOfMonth()==messageDate.getDayOfMonth()){
                    dayMessageCount++;
                }
                else {
                    messageInfoList.add(new MessageInfo(message.getDate(),dayMessageCount));
                    i += dayMessageCount;
                    dayMessageCount = 0;
                    messageDate = Instant.ofEpochMilli(message.getDate()*1000).atZone(ZoneId.systemDefault()).toLocalDate();
                }
            }
            periodActivity.setMessageInfoList(messageInfoList);
            periodActivities.add(periodActivity);
        }
        return periodActivities;
    }

}
