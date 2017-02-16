package by.tsvrko.manics.model.hibernate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created main.by tsvrko on 1/4/2017.
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
    private long chatId;

    @Column(name = "name")
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Message> messageList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chat_id) {
        this.chatId = chat_id;
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
        if (chatId != chat.chatId) return false;
        if (!title.equals(chat.title)) return false;
        if (!user.equals(chat.user)) return false;
        return messageList.equals(chat.messageList);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (int) (chatId ^ (chatId >>> 32));
        result = 31 * result + title.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + messageList.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ChatInfo{" +
                "id=" + id +
                ", chat_id=" + chatId +
                ", title='" + title + '\'' +
                ", user=" + user +
                ", messageList=" + messageList +
                '}';
    }
}
