package by.tsvrko.manics.dao.interfaces.db;

import by.tsvrko.manics.model.hibernate.User;

/**
 * Created main.by tsvrko on 11/22/2016.
 */
public interface UserDAO {

    User getUserByLogin(String login);

  }
