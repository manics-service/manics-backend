package by.tsvrko.manics.common;

import by.tsvrko.manics.dao.implementations.db.ChatDAOImpl;
import by.tsvrko.manics.dao.interfaces.db.ChatDAO;
import by.tsvrko.manics.model.dataimport.ChatInfo;

import by.tsvrko.manics.model.hibernate.Chat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created main.by tsvrko on 2/11/2017.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase//(replace= AutoConfigureTestDatabase.Replace.NONE)
public class ChatDAOImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ChatDAO repository;

    @Test
    public void addChat() throws Exception {

        String token = "5519ff80-f0e5-440d-9bb3-b90dfba00175";
        ChatInfo chat = new ChatInfo(1233333,"qwerty",433699364,8585858);
        boolean result = this.repository.addChat(chat,token);
        assertTrue(result);
    }


    //
//    @Test
//    public void deleteChat() throws Exception {
//
//    }
//
//    @Test
//    public void getChatById() throws Exception {
//
//        this.entityManager.persist(new Chat(1233333, "qwerty"));
//        long chatId = 1233333;
//        Chat chat = repository.getChatById(chatId);
//        assertEquals("qwerty", chat.getTitle());
//    }
////
//    @Test
//    public void getChats() throws Exception {
//
//    }

}