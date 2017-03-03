package by.tsvrko.manics.model.statistics;

import by.tsvrko.manics.model.dataimport.UserInfo;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * Created by tsvrko on 3/3/2017.
 */
public class AmountOfInfo implements Serializable,Comparable<AmountOfInfo> {

    private UserInfo userInfo;
    private int infoAmount;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public int getInfoAmount() {
        return infoAmount;
    }

    public void setInfoAmount(int infoAmount) {
        this.infoAmount = infoAmount;
    }

    public AmountOfInfo(UserInfo userInfo, int infoAmount) {
        this.userInfo = userInfo;
        this.infoAmount = infoAmount;
    }

    public AmountOfInfo() {
    }

    @Override
    public int compareTo(@NotNull AmountOfInfo amountOfInfo) {
        return amountOfInfo.getInfoAmount() - this.getInfoAmount();
    }
}
