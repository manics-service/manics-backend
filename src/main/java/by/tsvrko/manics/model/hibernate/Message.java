package by.tsvrko.manics.model.hibernate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created main.main.java.by tsvrko on 1/5/2017.
 */
@Entity
@Table(name = "MESSAGE", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonIdentityReference(alwaysAsId = true)
public class Message implements Comparable<Message>, Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "BODY")
    private String body;

    @Column(name = "DATE")
    private long date;

    @ManyToOne (fetch = FetchType.LAZY)
    private Chat chat;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String user_id) {
        this.userId = user_id;
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
        if (!userId.equals(message.userId)) return false;
        return body.equals(message.body);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + body.hashCode();
        result = 31 * result + (int) (date ^ (date >>> 32));
        return result;
    }


    @Override
    public int compareTo(@NotNull Message message) {
        return Long.compare(this.getDate(), message.getDate());
    }

}
