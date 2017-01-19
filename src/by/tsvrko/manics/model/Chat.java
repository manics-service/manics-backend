package by.tsvrko.manics.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by tsvrko on 1/4/2017.
 */

@Entity
@Table(name = "chat", catalog = "manics", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Chat implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "chat_id")
    private long chat_id;

    @Column(name = "name")
    private String title;

    @ManyToOne(fetch = FetchType.EAGER )
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "chat", cascade = CascadeType.ALL)
    private List<Message> messageList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getChat_id() {
        return chat_id;
    }

    public void setChat_id(long chat_id) {
        this.chat_id = chat_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public Chat() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chat chat = (Chat) o;

        if (id != chat.id) return false;
        if (chat_id != chat.chat_id) return false;
        if (!title.equals(chat.title)) return false;
        if (!user.equals(chat.user)) return false;
        return messageList.equals(chat.messageList);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) (chat_id ^ (chat_id >>> 32));
        result = 31 * result + title.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + messageList.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", chat_id=" + chat_id +
                ", title='" + title + '\'' +
                ", user=" + user +
                ", messageList=" + messageList +
                '}';
    }
}
