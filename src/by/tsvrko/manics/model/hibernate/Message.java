package by.tsvrko.manics.model.hibernate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by tsvrko on 1/5/2017.
 */

@Entity
@Table(name = "message", catalog = "manics", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonIdentityReference(alwaysAsId = true)
public class Message implements Comparable<Message>, Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "body")
    private String body;

    @Column(name = "date")
    private long date;

    @ManyToOne (fetch = FetchType.EAGER)
    private Chat chat;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Message() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (date != message.date) return false;
        if (!user_id.equals(message.user_id)) return false;
        return body.equals(message.body);
    }

    @Override
    public int hashCode() {
        int result = user_id.hashCode();
        result = 31 * result + body.hashCode();
        result = 31 * result + (int) (date ^ (date >>> 32));
        return result;
    }


    @Override
    public int compareTo(Message message) {
        return Long.compare(this.getDate(), message.getDate());
    }

}
