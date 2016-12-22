package by.tsvrko.manics.service;

import by.tsvrko.manics.dao.implementations.UserDAOImpl;
import by.tsvrko.manics.dao.interfaces.SessionDAO;
import by.tsvrko.manics.dao.interfaces.UserDAO;
import by.tsvrko.manics.exceptions.InvalidUserInfoException;
import by.tsvrko.manics.model.Status;
import by.tsvrko.manics.model.User;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.Cookie;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Date;


/**
 * Created by irats on 11/23/2016.
 */

@Path("/login")
public class LoginService {


    private static Logger log = Logger.getLogger(UserDAOImpl.class.getName());
    private static ApplicationContext context =
            new ClassPathXmlApplicationContext("Spring-Module.xml");
    private static UserDAO userDAO = (UserDAO) context.getBean("userDAO");
    private static   SessionDAO sessionDAO = (SessionDAO) context.getBean("sessionDAO");

   @POST
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
        String dbpass = userDAO.getUser(login).getPass();
        if (!dbpass.equals(password)) throw new InvalidUserInfoException("credentials are invalid");
       }


   //при добавлении сессии в контроллере вызывать UserService
        //SessionService: добавление сессии, проверка сессии на валидность
        //вызв в зависимости от статуса аутентификации
        // если да, кладем в таблицу...
    }



