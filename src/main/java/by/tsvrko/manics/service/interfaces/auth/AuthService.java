package by.tsvrko.manics.service.interfaces.auth;

import by.tsvrko.manics.model.dataimport.AuthInfo;

/**
 * Created main.java.by tsvrko on 2/27/2017.
 */
public interface AuthService {

    AuthInfo authenticateUser (AuthInfo noSessionInfo);
}
