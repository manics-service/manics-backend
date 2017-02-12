package by.tsvrko.manics.service.interfaces.db;

import by.tsvrko.manics.model.hibernate.User;

/**
 * Created main.by tsvrko on 2/10/2017.
 */
public interface UserService {

    User getUserByLogin(String login);
}
