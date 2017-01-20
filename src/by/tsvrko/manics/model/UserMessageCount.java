package by.tsvrko.manics.model;

/**
 * Created by tsvrko on 1/20/2017.
 */
public class UserMessageCount {

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
}
