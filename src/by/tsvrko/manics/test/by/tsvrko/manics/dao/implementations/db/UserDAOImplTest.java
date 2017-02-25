package by.tsvrko.manics.dao.implementations.db;

import by.tsvrko.manics.dao.interfaces.db.UserDAO;
import by.tsvrko.manics.model.hibernate.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by tsvrko on 2/16/2017.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserDAOImplTest {

    @Autowired
    private UserDAO userDAO;

//    @Before
//    public void setUp() throws Exception {
//        userDAO.addUser(new User("qwertyu","123456"));
//    }

    @Test
    @Rollback
    public void getUserByLogin() throws Exception {
        User dbUser = new User("Irina","123456");
        User user = userDAO.getByLogin("Irina");
        assertEquals(user,dbUser);

    }

    @Test
    @Transactional("transactionManager")
    @Rollback
    public void addUser() throws Exception {
        boolean result = userDAO.addUser(new User("qwerty","123456"));
        assertTrue(result);
    }


    @Test
    @Rollback
    public void addExistingUser() throws Exception {
        boolean result = userDAO.addUser(new User("qwertyu","123456"));
        assertTrue(result);
    }

}