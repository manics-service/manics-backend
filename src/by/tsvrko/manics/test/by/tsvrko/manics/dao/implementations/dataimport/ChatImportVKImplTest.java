package by.tsvrko.manics.dao.implementations.dataimport;

import by.tsvrko.manics.dao.interfaces.dataimport.ChatImportVK;
import by.tsvrko.manics.exceptions.UserIsNotAuthorizedException;
import by.tsvrko.manics.model.dataimport.AuthInfo;
import by.tsvrko.manics.model.dataimport.ChatInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tsvrko on 2/28/2017.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase( replace= AutoConfigureTestDatabase.Replace.NONE)
public class ChatImportVKImplTest {

    @Autowired
    private ChatImportVK chatImportVK;

    @Test(expected = UserIsNotAuthorizedException.class )
    public void getChatsUnauth() throws Exception {
        AuthInfo authInfo = new AuthInfo();
        authInfo.setSession("7bcafa02-0b7d-45ae-ae3a-c68c0b87487");
        chatImportVK.getChats(authInfo);
    }

    @Test
    public void getChats() throws Exception {
        AuthInfo authInfo = new AuthInfo();
        authInfo.setSession("7bcafa02-0b7d-45ae-ae3a-c68c0b874871");
        authInfo.setToken("25b606be4db7e217d255d818bd3cdaca8c8a6eb91ab055d4a816d4e71a213153cd7707648f8d29bc92c37");
        List<ChatInfo> chatInfos = chatImportVK.getChats(authInfo);
        assertNotNull(chatInfos);
    }



    @Test
    public void getChatUsers() throws Exception {

    }

}