package by.tsvrko.manics.service.services;

import by.tsvrko.manics.dao.HibernateFactory;
import by.tsvrko.manics.dao.implementations.UserDAOImpl;
import by.tsvrko.manics.dao.interfaces.UserDAO;
import by.tsvrko.manics.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class UserService {
    HibernateFactory hibernateFactory = new HibernateFactory();
    User getUser(String login) {
        return  hibernateFactory.getUserDAO().getUser(login);

    }
}
