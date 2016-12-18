package by.tsvrko.manics.common;

import by.tsvrko.manics.dao.interfaces.SessionDAO;
import by.tsvrko.manics.dao.interfaces.UserDAO;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by irats on 11/23/2016.
 */

@SpringBootApplication
public class Runner {
    public static void main( String[] args )
    {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Spring-Module.xml");

        UserDAO userDAO = (UserDAO) context.getBean("userDAO");
      System.out.println(userDAO.addUserSession("34384384934", "qwerty"));

    }
}
