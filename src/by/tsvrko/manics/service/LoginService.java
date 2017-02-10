package by.tsvrko.manics.service;

import by.tsvrko.manics.exceptions.InvalidUserInfoException;
import by.tsvrko.manics.model.hibernate.User;
import by.tsvrko.manics.service.implementations.db.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginService {

    private static Logger log = Logger.getLogger(LoginService.class.getName());

    @Autowired
    private UserServiceImpl userServiceImpl;

    public boolean authenticateUser(User user) {
        String login = user.getLogin();
        String password = user.getPass();

        try {
            return authenticate(login, password);

        } catch (InvalidUserInfoException e) {
            log.debug("credentials are invalid", e);
            return false;
        }
    }


    private boolean authenticate(String login, String password) throws InvalidUserInfoException {
        User user = userServiceImpl.getUserByLogin(login);
        String dbPass  = user.getPass();
        if (password.equals(dbPass)){
            return true;
        }
        else throw new InvalidUserInfoException("credentials are invalid");
    }

}



