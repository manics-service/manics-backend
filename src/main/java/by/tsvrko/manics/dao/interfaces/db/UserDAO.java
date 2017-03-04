package main.java.by.tsvrko.manics.dao.interfaces.db;

import main.java.by.tsvrko.manics.model.dataimport.UserInfo;
import main.java.by.tsvrko.manics.model.hibernate.User;

/**
 * Created main.java.by tsvrko on 2/27/2017.
 */
public interface UserDAO {

    boolean persistUser(UserInfo userInfo);

    boolean addUser (UserInfo userInfo);

    User getByIdentifier(String userId);

}
