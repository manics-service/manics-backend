package by.tsvrko.manics.model.dataimport;

import java.io.Serializable;

/**
 * Created main.java.by tsvrko on 2/27/2017.
 */
public class AuthInfo implements Serializable {

    private String token;
    private SourceType type;
    private String session;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SourceType getType() {
        return type;
    }

    public void setType(SourceType type) {
        this.type = type;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public AuthInfo(String token, SourceType type, String session) {
        this.token = token;
        this.type = type;
        this.session = session;
    }

    public AuthInfo() {
    }
}
