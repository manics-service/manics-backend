package by.tsvrko.manics.dao.interfaces.db;

import by.tsvrko.manics.model.hibernate.User;
import by.tsvrko.manics.model.hibernate.UserSession;
import org.springframework.data.repository.Repository;

/**
 * Created main.by tsvrko on 11/30/2016.
 */
public interface SessionDAO {

    boolean addUserSession (String session_id, User login);

    UserSession getByToken(String token);

    }
