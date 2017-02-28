package by.tsvrko.manics.model.dataimport;

import java.io.Serializable;

/**
 * Created by tsvrko on 2/28/2017.
 */
public class RequestInfo implements Serializable{

    private AuthInfo authInfo;
    private ChatInfo chatInfo;

    public AuthInfo getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(AuthInfo authInfo) {
        this.authInfo = authInfo;
    }

    public ChatInfo getChatInfo() {
        return chatInfo;
    }

    public void setChatInfo(ChatInfo chatInfo) {
        this.chatInfo = chatInfo;
    }

    public RequestInfo(AuthInfo authInfo, ChatInfo chatInfo) {
        this.authInfo = authInfo;
        this.chatInfo = chatInfo;
    }

    public RequestInfo() {
    }
}
