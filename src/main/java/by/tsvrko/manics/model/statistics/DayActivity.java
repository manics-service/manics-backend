package by.tsvrko.manics.model.statistics;

import by.tsvrko.manics.model.dataimport.UserInfo;

import java.io.Serializable;

/**
 * Created main.java.by tsvrko on 2/25/2017.
 */

public class DayActivity implements Serializable{

    private UserInfo userInfo;
    private int messMorning;
    private int messDay;
    private int messEvening;
    private int messNight;

    public int getMessMorning() {
        return messMorning;
    }

    public void setMessMorning(int messMorning) {
        this.messMorning = messMorning;
    }

    public int getMessDay() {
        return messDay;
    }

    public void setMessDay(int messDay) {
        this.messDay = messDay;
    }

    public int getMessEvening() {
        return messEvening;
    }

    public void setMessEvening(int messEvening) {
        this.messEvening = messEvening;
    }

    public int getMessNight() {
        return messNight;
    }

    public void setMessNight(int messNight) {
        this.messNight = messNight;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public DayActivity(UserInfo userInfo, int messMorning, int messDay, int messEvening, int messNight) {
        this.userInfo = userInfo;
        this.messMorning = messMorning;
        this.messDay = messDay;
        this.messEvening = messEvening;
        this.messNight = messNight;
    }

    public DayActivity() {
    }
}
