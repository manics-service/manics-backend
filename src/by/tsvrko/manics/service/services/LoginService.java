package by.tsvrko.manics.service.services;

import by.tsvrko.manics.exceptions.InvalidUserInfoException;
import by.tsvrko.manics.model.User;
import by.tsvrko.manics.service.services.dbdaoservice.UserService;
import org.apache.log4j.Logger;

public class LoginService {


    private static Logger log = Logger.getLogger(LoginService.class.getName());

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
          String dbpass = new UserService().getUserByLogin(login).getPass();
            if (!password.equals(dbpass)) throw new InvalidUserInfoException("credentials are invalid");

    }

}



