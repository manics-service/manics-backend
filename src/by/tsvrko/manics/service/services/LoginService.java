package by.tsvrko.manics.service.services;

import by.tsvrko.manics.exceptions.InvalidUserInfoException;
import by.tsvrko.manics.model.hibernate.User;
import by.tsvrko.manics.service.services.dao.db.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("loginService")
public class LoginService {

    private static Logger log = Logger.getLogger(LoginService.class.getName());

    @Autowired
    private UserService userService;

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
        User user = userService.getUserByLogin(login);
        String dbPass  = user.getPass();
        if (password.equals(dbPass)){
            return true;
        }
        else throw new InvalidUserInfoException("credentials are invalid");
    }

}



