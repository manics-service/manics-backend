package by.tsvrko.manics.model.statistics;

import by.tsvrko.manics.model.dataimport.UserInfo;

import java.io.Serializable;

/**
 * Created by tsvrko on 1/20/2017.
 */
public class UserMessageCount implements Comparable<UserMessageCount>, Serializable {

    private UserInfo userInfo;
    private int messageCount;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }


    @Override
    public int compareTo(UserMessageCount userMessageCount) {
        return userMessageCount.getMessageCount()-this.getMessageCount();
    }

}
