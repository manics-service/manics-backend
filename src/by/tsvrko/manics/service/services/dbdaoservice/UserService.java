package by.tsvrko.manics.service.services.dbdaoservice;

import by.tsvrko.manics.dao.database.HibernateFactory;
import by.tsvrko.manics.dao.database.interfaces.UserDAO;
import by.tsvrko.manics.model.User;

public class UserService {

    private static UserDAO userDAO = HibernateFactory.getInstance().getUserDAO();

    public User getUserByLogin(String login) {
        return userDAO.getUserByLogin(login);

    }

}
