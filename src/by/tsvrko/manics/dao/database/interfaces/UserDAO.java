package by.tsvrko.manics.dao.database.interfaces;

import by.tsvrko.manics.model.hibernate.User;

/**
 * Created by tsvrko on 11/22/2016.
 */
public interface UserDAO {

    User getUserByLogin(String login);

  }
