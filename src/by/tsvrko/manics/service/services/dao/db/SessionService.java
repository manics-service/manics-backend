package by.tsvrko.manics.service.services.dao.db;

import by.tsvrko.manics.dao.database.interfaces.SessionDAO;
import by.tsvrko.manics.model.hibernate.User;
import by.tsvrko.manics.model.hibernate.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sessionService")
@Transactional
public class SessionService {

   @Autowired
    SessionDAO sessionDAO;

    public String addSession(String token, User user) {
        sessionDAO.addUserSession(token, user);
        return token;
    }

    public UserSession getUserSessionByToken(String token){
        return  sessionDAO.getUserSessionByToken(token);
    }


}
