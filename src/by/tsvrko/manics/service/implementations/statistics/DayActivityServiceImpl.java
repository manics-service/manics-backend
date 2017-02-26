package by.tsvrko.manics.service.implementations.statistics;

import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.dataimport.UserInfo;
import by.tsvrko.manics.model.hibernate.Message;
import by.tsvrko.manics.model.statistics.DayActivity;
import by.tsvrko.manics.service.interfaces.dataimport.ChatImportService;
import by.tsvrko.manics.service.interfaces.dataimport.UserImportService;
import by.tsvrko.manics.service.interfaces.db.MessageService;
import by.tsvrko.manics.service.interfaces.statistics.DayActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsvrko on 2/25/2017.
 */
@Service
public class DayActivityServiceImpl implements DayActivityService{

    private ChatImportService chatImportService;
    private UserImportService userImportService;
    private MessageService messageService;

    @Autowired
    public DayActivityServiceImpl(ChatImportService chatImportService, UserImportService userImportService, MessageService messageService) {
        this.chatImportService = chatImportService;
        this.userImportService = userImportService;
        this.messageService = messageService;
    }

    @Override
    public List<DayActivity> getDayActivity(ChatInfo info) {
        List<Integer> chatUserIds= chatImportService.getChatUsersIds(info.getChatId());
        List<UserInfo> userInfoList = userImportService.getUsers(chatUserIds);
        List<DayActivity> dayActivityList = new ArrayList<>();

        for(UserInfo userInfo : userInfoList){
            DayActivity dayActivity= new DayActivity();
            dayActivity.setUserInfo(userInfo);
            List<Message> messageList = messageService.getMessages(userInfo.getId(),info.getChatId());

            int messNight = 0;
            int messMorning = 0;
            int messDay = 0;
            int messEvening = 0;

            for(Message message:messageList){

                Time time = new Time(message.getDate());
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
