package by.tsvrko.manics.service.implementations.statistics;

import by.tsvrko.manics.model.dataimport.AuthInfo;
import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.dataimport.UserInfo;
import by.tsvrko.manics.model.statistics.MessageCount;
import by.tsvrko.manics.service.interfaces.statistics.MessageCountService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created main.by tsvrko on 1/20/2017.
 */

@Service
public class MessageCountServiceImpl implements MessageCountService {

    @Override
    public List<MessageCount> getStatistics(ChatInfo chatInfo, AuthInfo authInfo){

        List <MessageCount> statList = new ArrayList<>();
        List<UserInfo> userInfoList =StatUtil.getUserInfo(chatInfo.getChatId(),authInfo);

        for(UserInfo userInfo : userInfoList){

            MessageCount messageCount = new MessageCount();
            messageCount.setUserInfo(userInfo);
            messageCount.setMessageCount(StatUtil.getUserMessages(userInfo.getId(), chatInfo.getChatId()).size());
            statList.add(messageCount);

        }
        statList.sort(MessageCount::compareTo);
        return statList;
    }
}
