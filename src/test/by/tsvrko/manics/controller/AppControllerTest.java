package by.tsvrko.manics.controller;

import by.tsvrko.manics.dao.interfaces.db.ChatDAO;
import by.tsvrko.manics.model.dataimport.AuthInfo;
import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.dataimport.SourceType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
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
import java.util.*;

import static org.junit.Assert.assertNotNull;

/**
 * Created main.java.by tsvrko on 2/17/2017.
 */


//Doesn't work yet
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppControllerTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private TestRestTemplate restTemplate = new TestRestTemplate();
    private AuthInfo authInfo = new AuthInfo();

    @Before
    public void setUp() throws Exception {
        authInfo.setToken("cf0a4e065ffe83f3332ddc0af4c84548d003257953e83cb5bae80cb3ec33662b2893adb499a8e0db6aef2");
        authInfo.setSession("7bcafa02-0b7d-45ae-ae3a-c68c0b874871");
        authInfo.setType(SourceType.VK);

    }

    @Test
    public void testGetChats() throws JsonProcessingException {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity =
                new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(authInfo), requestHeaders);
        List<ChatInfo> apiResponse =
                restTemplate.postForObject("http://localhost:8080/api/v1/data/chats.json", httpEntity, ArrayList.class, Collections.EMPTY_MAP);
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