package by.tsvrko.manics.service.services.dbdaoservice;

import by.tsvrko.manics.dao.database.HibernateFactory;
import by.tsvrko.manics.dao.database.interfaces.SessionDAO;
import by.tsvrko.manics.model.User;
import by.tsvrko.manics.model.UserSession;

public class SessionService {

    private static SessionDAO sessionDAOService = HibernateFactory.getInstance().getSessionDAO();

    public String addSession(String token, User user) {
        sessionDAOService.addUserSession(token, user.getLogin());
        return token;
    }

    public UserSession getUserSessionByToken(String token){
        return  sessionDAOService.getUserSessionByToken(token);
    }


}
