package by.tsvrko.manics.service;

import by.tsvrko.manics.dao.interfaces.SessionDAO;
import by.tsvrko.manics.dao.interfaces.UserDAO;
import by.tsvrko.manics.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by irats on 12/22/2016.
 */
public class SessionService {

    private static ApplicationContext context =
            new ClassPathXmlApplicationContext("Spring-Module.xml");
    private static UserDAO userDAO = (UserDAO) context.getBean("userDAO");
    private static SessionDAO sessionDAO = (SessionDAO) context.getBean("sessionDAO");

    public String addSession(String token, User user) {
        sessionDAO.addSession(token);
        userDAO.addUserSession(token, user.getLogin());
        return token;

    }}
