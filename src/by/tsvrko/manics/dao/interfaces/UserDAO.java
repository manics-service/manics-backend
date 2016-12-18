package by.tsvrko.manics.dao.interfaces;

import by.tsvrko.manics.model.User;

/**
 * Created by irats on 11/22/2016.
 */
public interface UserDAO {

    User getUser(String login);

    boolean addUserSession (String session_id, String login);
}
