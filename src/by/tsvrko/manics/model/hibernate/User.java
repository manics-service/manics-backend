package by.tsvrko.manics.model.hibernate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * Created by tsvrko on 11/22/2016.
 */

@Entity
@Table(name = "user", catalog = "manics", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "login") })
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonIdentityReference(alwaysAsId = true)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "login")
    private String login;

    @Column(name = "pass")
    private String pass;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "user")
    private UserSession userSession;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",cascade = CascadeType.ALL)
    private List<Chat> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public List<Chat> getList() {
        return list;
    }

    public void setList(List<Chat> list) {
        this.list = list;
    }

    public User(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public User() {
    }

}
