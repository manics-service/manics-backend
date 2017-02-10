package by.tsvrko.manics.service.interfaces.db;

import by.tsvrko.manics.model.hibernate.User;
import by.tsvrko.manics.model.hibernate.UserSession;

/**
 * Created by tsvrko on 2/10/2017.
 */
public interface SessionService {

    String addSession(String token, User user);

    UserSession getUserSessionByToken(String token);
}
