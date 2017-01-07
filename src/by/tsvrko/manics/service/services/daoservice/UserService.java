package by.tsvrko.manics.service.services.daoservice;

import by.tsvrko.manics.dao.database.HibernateFactory;
import by.tsvrko.manics.model.User;

public class UserService {

    public User getUser(String login) {
        return  HibernateFactory.getInstance().getUserDAO().getUser(login);

    }
}
