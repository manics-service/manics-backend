//package by.tsvrko.manics.common;
//
//import by.tsvrko.manics.dao.implementations.db.ChatDAOImpl;
//import by.tsvrko.manics.dao.interfaces.db.ChatDAO;
//import by.tsvrko.manics.model.dataimport.ChatInfo;
//
//import by.tsvrko.manics.model.hibernate.Chat;
//import by.tsvrko.manics.service.AppConfig;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.Assert.*;
//
///**
// * Created main.by tsvrko on 2/11/2017.
// */
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class ChatDAOImplTest {
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private ChatDAO repository;
//
//    @Test
//    public void addChat() throws Exception {
//
//        String token = "123456789";
//        ChatInfo chat = new ChatInfo(1233333,"qwerty");
//        boolean result = this.repository.addChat(chat,token);
//        assertTrue(result);
//    }
//
//
//    //
////    @Test
////    public void deleteChat() throws Exception {
////
////    }
////
//    @Test
//    public void getChatById() throws Exception {
//
//        this.entityManager.persist(new Chat(1233333, "qwerty"));
//        long chatId = 1233333;
//        Chat chat = repository.getChatById(chatId);
//        assertEquals("qwerty", chat.getTitle());
//    }
////
////    @Test
////    public void getChats() throws Exception {
////
////    }
//
//}