package by.tsvrko.manics.service.services;

import by.tsvrko.manics.dao.interfaces.UserDAO;
import by.tsvrko.manics.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

 class UserService {
    private static ApplicationContext context =
            new ClassPathXmlApplicationContext("Spring-Module.xml");
    private static UserDAO userDAO = (UserDAO) context.getBean("userDAO");

    User getUser(String login) {
        return  userDAO.getUser(login);

    }
}
