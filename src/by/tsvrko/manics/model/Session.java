package by.tsvrko.manics.model;

import java.io.Serializable;

/**
 * Created by irats on 11/30/2016.
 */

public class Session implements Serializable {

    int id;
    String session_id;
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }
}
