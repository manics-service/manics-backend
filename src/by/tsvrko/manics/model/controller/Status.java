package by.tsvrko.manics.model.controller;

/**
 * Created by tsvrko on 12/23/2016.
 */
public class Status {

    private String statusCode;
    private StatusEnum description;


    public StatusEnum getDescription() {
        return description;
    }

    public void setDescription(StatusEnum status) {
        this.description = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
