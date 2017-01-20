package by.tsvrko.manics.service.services.dbservice;

import by.tsvrko.manics.dao.database.HibernateFactory;
import by.tsvrko.manics.dao.database.interfaces.SessionDAO;
import by.tsvrko.manics.model.User;
import by.tsvrko.manics.model.UserSession;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

@Service("sessionService")
@Configurable
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
