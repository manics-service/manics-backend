package by.tsvrko.manics.model.dataimport;

import java.io.Serializable;

/**
 * Created main.java.by tsvrko on 3/2/2017.
 */
public class MessageInfo implements Serializable {

    private long date;
    private int count;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public MessageInfo(long date, int count) {
        this.date = date;
        this.count = count;
    }

    public MessageInfo() {
    }
}
