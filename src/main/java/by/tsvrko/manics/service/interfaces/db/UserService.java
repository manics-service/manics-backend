package by.tsvrko.manics.service.interfaces.db;

import by.tsvrko.manics.model.dataimport.UserInfo;
import by.tsvrko.manics.model.hibernate.User;

/**
 * Created main.java.by tsvrko on 2/27/2017.
 */
public interface UserService {

    User getUserByIdentifier(String userId);

    boolean addUser(UserInfo userInfo);

}
