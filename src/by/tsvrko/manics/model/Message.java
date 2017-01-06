package by.tsvrko.manics.model;

import java.io.Serializable;

/**
 * Created by irats on 1/5/2017.
 */
public class Message implements Serializable{
    private int id;
    private String user_id;
    private String body;
    private long date;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != message.id) return false;
        if (date != message.date) return false;
        if (!user_id.equals(message.user_id)) return false;
        return body.equals(message.body);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + user_id.hashCode();
        result = 31 * result + body.hashCode();
        result = 31 * result + (int) (date ^ (date >>> 32));
        return result;
    }

}
