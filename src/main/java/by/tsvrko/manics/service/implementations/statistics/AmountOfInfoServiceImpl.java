package main.java.by.tsvrko.manics.service.implementations.statistics;

import main.java.by.tsvrko.manics.dao.EncodingUtil;
import main.java.by.tsvrko.manics.model.dataimport.AuthInfo;
import main.java.by.tsvrko.manics.model.dataimport.ChatInfo;
import main.java.by.tsvrko.manics.model.dataimport.UserInfo;
import main.java.by.tsvrko.manics.model.hibernate.Message;
import main.java.by.tsvrko.manics.model.statistics.AmountOfInfo;
import main.java.by.tsvrko.manics.service.interfaces.statistics.AmountOfInfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created main.java.by tsvrko on 3/3/2017.
 */

@Service
public class AmountOfInfoServiceImpl implements AmountOfInfoService {

    @Override
    public List<AmountOfInfo> getStatistics(ChatInfo chatInfo, AuthInfo authInfo){

        List <AmountOfInfo> statList = new ArrayList<>();
        List<UserInfo> userInfoList =StatUtil.getUserInfo(chatInfo.getChatId(),authInfo);

        for(UserInfo userInfo : userInfoList){
            List<Message> messageList = StatUtil.getUserMessages(userInfo.getId(), chatInfo.getChatId());
            int messageCount = messageList.size();
            int textLength = 0;

            for(Message message: messageList){
                textLength += EncodingUtil.decodeText(message.getBody()).length();
            }

            int infoAmount = textLength/messageCount;
            AmountOfInfo amountOfInfo = new AmountOfInfo();
            amountOfInfo.setUserInfo(userInfo);
            amountOfInfo.setInfoAmount(infoAmount);

            statList.add(amountOfInfo);
        }
        statList.sort(AmountOfInfo::compareTo);
        return statList;
    }
}
