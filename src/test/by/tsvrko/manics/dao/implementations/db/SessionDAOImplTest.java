package by.tsvrko.manics.dao.implementations.db;

import by.tsvrko.manics.dao.interfaces.db.SessionDAO;
import by.tsvrko.manics.model.hibernate.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created main.java.by tsvrko on 2/28/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase( replace= AutoConfigureTestDatabase.Replace.NONE)
public class SessionDAOImplTest {

    @Autowired
    private SessionDAO sessionDAO;

    @Test
    public void getBySession() throws Exception {
        String session = "7bcafa02-0b7d-45ae-ae3a-c68c0b874871";
        User user = sessionDAO.getSession(session).getUser();
        assertNotNull(user);
    }

    @Test
    public void addUserSession() throws Exception {

    }

    @Test
    public void getSession() throws Exception {

    }

}