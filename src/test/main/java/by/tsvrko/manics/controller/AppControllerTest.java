package main.java.by.tsvrko.manics.controller;

import main.java.by.tsvrko.manics.dao.interfaces.db.ChatDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.HttpCookie;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

/**
 * Created main.java.by tsvrko on 2/17/2017.
 */


//Doesn't work yet
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppControllerTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private ChatDAO chatRepository;
    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testGetChats() throws JsonProcessingException {

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", "12ДКИ-1");
        requestBody.put("chat_id", "181");
        HttpHeaders requestHeaders = new HttpHeaders();
        
        HttpCookie httpCookie = new HttpCookie("session","5519ff80-f0e5-440d-9bb3-b90dfba00175");
        String token = "5519ff80-f0e5-440d-9bb3-b90dfba00175";
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.add("Set-Cookie","session=" + token + "Path=/");
        HttpEntity<String> httpEntity =
                new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);
        Map<String, Object> apiResponse =
                restTemplate.postForObject("http://localhost:8080/api/v1/data/chats.json", httpEntity, Map.class, Collections.EMPTY_MAP);
        assertNotNull(apiResponse);

    }

    @Test
    public void getUserCountOfMessages() throws Exception {

    }

     @Test
    public void getMessages() throws Exception {

    }

    @Test
    public void authenticateUser() throws Exception {

    }

}