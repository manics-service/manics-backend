package hello;

import by.tsvrko.manics.dao.interfaces.UserDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Application {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Spring-Module.xml");

        UserDAO userDAO = (UserDAO) context.getBean("userDAO");
        System.out.println(userDAO.getUser("qwerty"));

    }

}
