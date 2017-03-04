package main.java.by.tsvrko.manics.model.statistics;

import main.java.by.tsvrko.manics.model.dataimport.MessageInfo;
import main.java.by.tsvrko.manics.model.dataimport.UserInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created main.java.by tsvrko on 2/28/2017.
 */
public class PeriodActivity implements Serializable{

    private UserInfo userInfo;
    private List<MessageInfo> messageInfoList = new ArrayList<>();

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<MessageInfo> getMessageInfoList() {
        return messageInfoList;
    }

    public void setMessageInfoList(List<MessageInfo> messageInfoList) {
        this.messageInfoList = messageInfoList;
    }

    public PeriodActivity(UserInfo userInfo, List<MessageInfo> messageInfoList) {
        this.userInfo = userInfo;
        this.messageInfoList = messageInfoList;
    }

    public PeriodActivity() {
    }
}
