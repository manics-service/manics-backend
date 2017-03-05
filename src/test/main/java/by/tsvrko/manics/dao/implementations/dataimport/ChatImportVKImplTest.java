package main.java.by.tsvrko.manics.dao.implementations.dataimport;

import by.tsvrko.manics.dao.interfaces.dataimport.ChatImportVK;
import by.tsvrko.manics.dao.interfaces.dataimport.UserImportVK;
import by.tsvrko.manics.exceptions.UserIsNotAuthorizedException;
import by.tsvrko.manics.model.dataimport.AuthInfo;
import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.dataimport.UserInfo;
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
        authInfo.setToken("cf0a4e065ffe83f3332ddc0af4c84548d003257953e83cb5bae80cb3ec33662b2893adb499a8e0db6aef2");
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
        List<Integer> userList = chatImportVK.getChatUsers(chatId,authInfo);
        UserInfo user  = userImportVK.getUser(authInfo);
        assertEquals(user.getId(),userList.get(0).toString());
    }

}