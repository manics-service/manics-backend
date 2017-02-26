package by.tsvrko.manics.service.interfaces.db;

import by.tsvrko.manics.model.hibernate.UserSession;

/**
 * Created main.by tsvrko on 2/10/2017.
 */
public interface SessionService {

    String addSession(String token);

    UserSession getUserSessionByToken(String token);
}
