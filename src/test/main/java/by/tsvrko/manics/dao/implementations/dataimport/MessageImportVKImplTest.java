package main.java.by.tsvrko.manics.dao.implementations.dataimport;

import main.java.by.tsvrko.manics.dao.interfaces.dataimport.MessageImportVK;
import main.java.by.tsvrko.manics.dao.interfaces.db.ChatDAO;
import main.java.by.tsvrko.manics.dao.interfaces.db.SessionDAO;
import main.java.by.tsvrko.manics.dao.interfaces.db.UserDAO;
import main.java.by.tsvrko.manics.model.dataimport.AuthInfo;
import main.java.by.tsvrko.manics.model.dataimport.ChatInfo;
import main.java.by.tsvrko.manics.model.dataimport.UserInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created main.java.by tsvrko on 3/4/2017.
 */


//Doesn't work
@RunWith(SpringRunner.class)
@DataJpaTest
//@AutoConfigureTestDatabase( replace= AutoConfigureTestDatabase.Replace.NONE)
//@Transactional
public class MessageImportVKImplTest {
    @Autowired
    private ChatDAO chatDAO;
    @Autowired
    private MessageImportVK messageImportVK;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private SessionDAO sessionDAO;

    private AuthInfo authInfo = new AuthInfo();
    private ChatInfo chatInfo = new ChatInfo();

    @Before
    public  void setUp() throws Exception {
        String userId = "2342342342";
        String sessionId = "7bcafa02-0b7d-45ae-ae3a-c68c0b874871";
         userDAO.persistUser(new UserInfo(userId,"qwerty","qwerty"));
     //   userDAO.addUser(new UserInfo(userId,"qwerty","qwerty"));
        sessionDAO.addUserSession(userId,sessionId);
        authInfo.setSession(sessionId);
        authInfo.setToken("e5e926df72a91c92d176b52b43865093bc60099c12409ae28a2e05fe98629db09fbed59be898a55adf3ce");
        chatInfo.setChatId(187);
        chatInfo.setTitle("Null");
    }


    @Test
    public void getMessages() throws Exception {
        boolean addMessages = messageImportVK.getMessages(chatInfo,authInfo);
        assertTrue(addMessages);
     //   chatDAO.deleteChat(chatInfo.getChatId());
    }

}