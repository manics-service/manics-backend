package by.tsvrko.manics.model.dataimport;

import java.io.Serializable;

/**
 * Created main.by tsvrko on 1/25/2017.
 */
public class ChatInfo implements Serializable {

    private long chatId;
    private String title;
    private long messageCount;
    private long lastMessageDate;

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

    public long getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(long messageCount) {
        this.messageCount = messageCount;
    }

    public long getLastMessageDate() {
        return lastMessageDate;
    }

    public void setLastMessageDate(long lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
    }

    public ChatInfo() {
    }

    public ChatInfo(long chatId, String title, long messageCount, long lastMessageDate) {
        this.chatId = chatId;
        this.title = title;
        this.messageCount = messageCount;
        this.lastMessageDate = lastMessageDate;
    }
}
