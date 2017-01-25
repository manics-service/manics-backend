package by.tsvrko.manics.dao.database.interfaces;

import by.tsvrko.manics.model.hibernate.User;
import by.tsvrko.manics.model.hibernate.UserSession;

/**
 * Created by tsvrko on 11/30/2016.
 */
public interface SessionDAO {

    boolean addUserSession (String session_id, User login);

    UserSession getUserSessionByToken(String token);

    }
