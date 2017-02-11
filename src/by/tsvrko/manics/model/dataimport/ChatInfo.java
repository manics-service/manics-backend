package by.tsvrko.manics.model.dataimport;

import java.io.Serializable;

/**
 * Created by tsvrko on 1/25/2017.
 */
public class ChatInfo implements Serializable {

    private long chatId;
    private String title;

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
