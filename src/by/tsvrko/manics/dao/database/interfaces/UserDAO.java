package by.tsvrko.manics.dao.database.interfaces;

import by.tsvrko.manics.model.User;

/**
 * Created by irats on 11/22/2016.
 */
public interface UserDAO {

    User getUser(String login);

  }
