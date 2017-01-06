package by.tsvrko.manics.service.services;

import by.tsvrko.manics.dao.HibernateFactory;
import by.tsvrko.manics.dao.interfaces.SessionDAO;
import by.tsvrko.manics.dao.interfaces.UserDAO;
import by.tsvrko.manics.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SessionService {
    HibernateFactory hibernateFactory = new HibernateFactory();

    public String addSession(String token, User user) {
        hibernateFactory.getSessionDAO().addUserSession(token, user.getLogin());
        return token;

    }}
