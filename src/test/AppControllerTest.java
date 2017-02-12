//package main;
//
//import by.tsvrko.manics.controller.AppController;
//import by.tsvrko.manics.model.dataimport.ChatInfo;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runner.Runner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.embedded.LocalServerPort;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.ResponseEntity;
//
//import java.net.URL;
//
//import static org.junit.Assert.*;
//
///**
// * Created main.by tsvrko on 2/11/2017.
// */
//
//@RunWith(Runner.class)
//@WebMvcTest(AppController.class)
//public class AppControllerTest {
//
//    @LocalServerPort
//    private int port;
//
//    private URL base;
//
//    @Autowired
//    private TestRestTemplate template;
//
//    @Before
//    public void setUp() throws Exception {
//        this.base = new URL("http://localhost:" + port + "/api/v1/data/chats.json");
//    }
//
////    @Test
////    public void getChats() throws Exception {
////        ResponseEntity<ChatInfo> response = template.getForEntity(base.toString(),
////                ChatInfo.class);
////        assertSame(response.getBody(), ChatInfo chatinfo);
////    }
//
//    @Test
//    public void getUserCountOfMessages() throws Exception {
//
//    }
//
//
//    @Test
//    public void getMessages() throws Exception {
//
//    }
//
//    @Test
//    public void authenticateUser() throws Exception {
//
//    }
//
//}