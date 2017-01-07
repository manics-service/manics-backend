package by.tsvrko.manics.service.services.daoservice;

import by.tsvrko.manics.dao.database.HibernateFactory;
import by.tsvrko.manics.model.User;

public class SessionService {

     public String addSession(String token, User user) {

        HibernateFactory.getInstance().getSessionDAO().addUserSession(token, user.getLogin());
        return token;

    }
}
