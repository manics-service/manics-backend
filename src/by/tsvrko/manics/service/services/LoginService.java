package by.tsvrko.manics.service.services;

import by.tsvrko.manics.exceptions.InvalidUserInfoException;
import by.tsvrko.manics.model.User;
import by.tsvrko.manics.service.services.dbservice.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("loginService")
@Configurable(autowire= Autowire.BY_NAME,dependencyCheck=true)
public class LoginService {

    private static Logger log = Logger.getLogger(LoginService.class.getName());

    @Resource(name="userService")
    @Autowired
    private UserService userService;

    public boolean authenticateUser(User user) {
        String login = user.getLogin();
        String password = user.getPass();


        try {
            authenticate(login, password);
            return true;

        } catch (InvalidUserInfoException e) {
            log.debug("credentials are invalid", e);
            return false;
        }
    }

    private void authenticate(String login, String password) throws InvalidUserInfoException {
        String dbpass  = userService.getUserByLogin(login).getPass();
        if (!password.equals(dbpass)) throw new InvalidUserInfoException("credentials are invalid");

    }

}



