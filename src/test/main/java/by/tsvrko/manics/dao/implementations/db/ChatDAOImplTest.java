package main.java.by.tsvrko.manics.dao.implementations.db;

import by.tsvrko.manics.dao.interfaces.db.ChatDAO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created main.main.java.by tsvrko on 2/11/2017.
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class ChatDAOImplTest {

    @Autowired
    private ChatDAO chatRepository;

//    @Test
//    public void addChat() throws Exception {
//
//        String token = "5519ff80-f0e5-440d-9bb3-b90dfba00175";
//     //   ChatInfo chat = new ChatInfo(12333,"qwerty",433699364,8585858);
//        boolean result = chatRepository.addChat(chat,token);
//        assertTrue(result);
//        Chat dbChat = new Chat();
//        dbChat.setChatId(chat.getChatId());
//        chatRepository.deleteChat(dbChat);
//    }
//


    @Test
    public void deleteChat() throws Exception {

    }

  //  @Test
//    public void getByChatId() throws Exception {
//
//        this.entityManager.persist(new Chat(1233333, "qwerty"));
//        long chatId = 1233333;
//        Chat chat = chatRepository.getByChatId(chatId);
//        assertEquals("qwerty", chat.getTitle());
//    }
//
    @Test
    public void getByUser() throws Exception {

    }

}