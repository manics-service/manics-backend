package by.tsvrko.manics.model;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by irats on 11/22/2016.
 */

@Entity
@Table(name = "user", catalog = "manics", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "login") })
public class User implements Serializable {

    private int id;
    private String login;
    private String pass;
    private UserSession userSession;

    public User() {
    }

    public User(int id, String login, String pass, UserSession userSession) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.userSession = userSession;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "pass")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", userSession=" + userSession +
                '}';
    }
}
