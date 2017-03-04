package main.java.by.tsvrko.manics.model.statistics;

import main.java.by.tsvrko.manics.model.dataimport.UserInfo;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * Created main.main.java.by tsvrko on 1/20/2017.
 */
public class MessageCount implements Comparable<MessageCount>, Serializable {

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
    public int compareTo(@NotNull MessageCount messageCount) {
        return messageCount.getMessageCount()-this.getMessageCount();
    }

}
