package by.tsvrko.manics.dao.interfaces.db;

import by.tsvrko.manics.model.dataimport.UserInfo;
import by.tsvrko.manics.model.hibernate.User;

/**
 * Created by tsvrko on 2/27/2017.
 */
public interface UserDAO {

    boolean persistUser(UserInfo userInfo);

    boolean addUser (UserInfo userInfo);

    User getByIdentifier(String userId);

}
