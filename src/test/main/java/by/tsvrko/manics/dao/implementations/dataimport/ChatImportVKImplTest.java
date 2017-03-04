package main.java.by.tsvrko.manics.dao.implementations.dataimport;

import main.java.by.tsvrko.manics.dao.interfaces.dataimport.ChatImportVK;
import main.java.by.tsvrko.manics.dao.interfaces.dataimport.UserImportVK;
import main.java.by.tsvrko.manics.exceptions.UserIsNotAuthorizedException;
import main.java.by.tsvrko.manics.model.dataimport.AuthInfo;
import main.java.by.tsvrko.manics.model.dataimport.ChatInfo;
import main.java.by.tsvrko.manics.model.dataimport.UserInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created main.java.by tsvrko on 2/28/2017.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase( replace= AutoConfigureTestDatabase.Replace.NONE)
public class ChatImportVKImplTest {

    @Autowired
    private ChatImportVK chatImportVK;
    @Autowired
    private UserImportVK userImportVK;

    private  AuthInfo authInfo = new AuthInfo();

    @Before
    public  void setUp() throws Exception {
        authInfo.setSession("7bcafa02-0b7d-45ae-ae3a-c68c0b874871");
        authInfo.setToken("e5e926df72a91c92d176b52b43865093bc60099c12409ae28a2e05fe98629db09fbed59be898a55adf3ce");
    }

    @Test(expected = UserIsNotAuthorizedException.class )
    public void getChatsUnauth() throws Exception {
        authInfo.setSession("");
        chatImportVK.getChats(authInfo);
    }

    @Test
    public void getChats() throws Exception {
        List<ChatInfo> chatInfos = chatImportVK.getChats(authInfo);
        assertNotNull(chatInfos);
    }

    @Test
    public void getChatUsers() throws Exception {
        long chatId = 187;
        List<Integer> userList = chatImportVK.getChatUsers(chatId,authInfo);
        assertNotNull(userList);
    }


    @Test(expected = UserIsNotAuthorizedException.class )
    public void getChatUsersUnauth() throws Exception {
        long chatId = 187;
        authInfo.setSession("");
        List<Integer> userList = chatImportVK.getChatUsers(chatId,authInfo);
    }

    @Test
    public void getChatNotExistUsers() throws Exception {
        long chatId = 999;
        AuthInfo authInfo = new AuthInfo();
        authInfo.setSession("7bcafa02-0b7d-45ae-ae3a-c68c0b874871");
        authInfo.setToken("e5e926df72a91c92d176b52b43865093bc60099c12409ae28a2e05fe98629db09fbed59be898a55adf3ce");
        List<Integer> userList = chatImportVK.getChatUsers(chatId,authInfo);
        UserInfo user  = userImportVK.getUser(authInfo);
        assertEquals(user.getId(),userList.get(0).toString());
    }

}