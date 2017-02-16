package by.tsvrko.manics.service.implementations.db;

import by.tsvrko.manics.dao.interfaces.db.SessionDAO;
import by.tsvrko.manics.model.hibernate.User;
import by.tsvrko.manics.model.hibernate.UserSession;
import by.tsvrko.manics.service.interfaces.db.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {

    private SessionDAO sessionDAO;

    @Autowired
    public SessionServiceImpl(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }

    @Override
    public String addSession(String token, User user) {
        sessionDAO.addUserSession(token, user);
        return token;
    }

    @Override
    public UserSession getUserSessionByToken(String token){
        return  sessionDAO.getUserSessionByToken(token);
    }
}
