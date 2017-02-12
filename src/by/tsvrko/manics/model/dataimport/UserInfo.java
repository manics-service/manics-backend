package by.tsvrko.manics.model.dataimport;

import java.io.Serializable;

/**
 * Created main.by tsvrko on 1/20/2017.
 */
public class UserInfo implements Serializable{

    private long id;
    private String firstName;
    private String lastName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
