package by.tsvrko.manics.model;

import java.io.Serializable;

/**
 * Created by irats on 1/4/2017.
 */
public class Chat implements Serializable{
    private String chat_id;
    private String title;

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chat chat = (Chat) o;

        if (!chat_id.equals(chat.chat_id)) return false;
        return title.equals(chat.title);
    }

    @Override
    public int hashCode() {
        int result = chat_id.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }
}
