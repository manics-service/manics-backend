package by.tsvrko.manics.model;

import java.io.Serializable;

/**
 * Created by irats on 11/22/2016.
 */
public class User implements Serializable {

    private int id;
    private String login;
    private String pass;
    private Session session;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", session=" + session +
                '}';
    }
}
