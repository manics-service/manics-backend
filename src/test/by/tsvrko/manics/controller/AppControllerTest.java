package by.tsvrko.manics.controller;

import by.tsvrko.manics.exceptions.UserIsNotAuthorizedException;
import by.tsvrko.manics.model.dataimport.*;
import by.tsvrko.manics.model.statistics.AmountOfInfo;
import by.tsvrko.manics.model.statistics.DayActivity;
import by.tsvrko.manics.model.statistics.MessageCount;
import by.tsvrko.manics.model.statistics.PeriodActivity;
import by.tsvrko.manics.service.interfaces.auth.AuthService;
import by.tsvrko.manics.service.interfaces.dataimport.UserInfoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

/**
 * Created by tsvrko on 3/6/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppControllerTest {

    @Autowired
    private UserInfoService userInfoService;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static TestRestTemplate restTemplate = new TestRestTemplate();
    private static AuthInfo authInfo = new AuthInfo();
    private static ChatInfo chatInfo = new ChatInfo();
    private static RequestInfo requestInfo = new RequestInfo();


    @BeforeClass
    public static void setUp() throws Exception {
        authInfo.setToken("cf0a4e065ffe83f3332ddc0af4c84548d003257953e83cb5bae80cb3ec33662b2893adb499a8e0db6aef2");
        authInfo.setSession("7bcafa02-0b7d-45ae-ae3a-c68c0b874871");
        authInfo.setType(SourceType.VK);
        chatInfo.setChatId(187);
        chatInfo.setTitle("Null");
        requestInfo.setAuthInfo(authInfo);
        requestInfo.setChatInfo(chatInfo);
    }



    @Test
    public void authenticateUser() throws Exception {
        HttpEntity<String> httpEntity =  setHttpInfo(authInfo);
        AuthInfo apiResponse =
                restTemplate.postForObject("http://localhost:8080/api/v1/authentication.json", httpEntity, AuthInfo.class, Collections.EMPTY_MAP);
        assertNotNull(apiResponse.getSession());
    }

    @Test
    public void getUserCountOfMessages() throws Exception {
        HttpEntity<String> httpEntity =  setHttpInfo(requestInfo);
        List<MessageCount> apiResponse =
                restTemplate.postForObject("http://localhost:8080/api/v1/stats/countofmessages.json", httpEntity, ArrayList.class, Collections.EMPTY_MAP);
        assertNotNull(apiResponse);
    }

    @Test
    public void getUserCountOfMessagesNoSession() throws Exception {
        requestInfo.getAuthInfo().setSession("");
        HttpEntity<String> httpEntity =  setHttpInfo(requestInfo);
        Map<String, String> apiResponse =
                restTemplate.postForObject("http://localhost:8080/api/v1/stats/countofmessages.json", httpEntity, Map.class, Collections.EMPTY_MAP);
        assertEquals(UserIsNotAuthorizedException.class.getName(),apiResponse.get("exception"));
    }


    @Test
    public void getUserDayActivity() throws Exception {
        HttpEntity<String> httpEntity =  setHttpInfo(requestInfo);
        List<DayActivity> apiResponse =
                restTemplate.postForObject("http://localhost:8080//api/v1/stats/dayactivity.json", httpEntity, ArrayList.class, Collections.EMPTY_MAP);
        assertNotNull(apiResponse);
    }

    @Test
    public void getUserDayActivityNoSession() throws Exception {
        requestInfo.getAuthInfo().setSession("");
        HttpEntity<String> httpEntity =  setHttpInfo(requestInfo);
        Map<String, String> apiResponse =
                restTemplate.postForObject("http://localhost:8080/api/v1/stats/dayactivity.json", httpEntity, Map.class, Collections.EMPTY_MAP);
        assertEquals(UserIsNotAuthorizedException.class.getName(),apiResponse.get("exception"));
    }


    @Test
    public void getUserPeriodActivity() throws Exception {
        requestInfo.setDate(Instant.now().toEpochMilli());
        HttpEntity<String> httpEntity =  setHttpInfo(requestInfo);
        List<PeriodActivity> apiResponse =
                restTemplate.postForObject("http://localhost:8080//api/v1/stats/periodactivity.json", httpEntity, ArrayList.class, Collections.EMPTY_MAP);
        assertNotNull(apiResponse);
    }

    @Test
    public void getUserPeriodActivityNoSession() throws Exception {
        requestInfo.setDate(Instant.now().toEpochMilli());
        requestInfo.getAuthInfo().setSession("");
        HttpEntity<String> httpEntity =  setHttpInfo(requestInfo);
        Map<String, String> apiResponse =
                restTemplate.postForObject("http://localhost:8080/api/v1/stats/periodactivity.json", httpEntity, Map.class, Collections.EMPTY_MAP);
        assertEquals(UserIsNotAuthorizedException.class.getName(),apiResponse.get("exception"));
    }

    @Test
    public void getUserInfoAmount() throws Exception {
        HttpEntity<String> httpEntity =  setHttpInfo(requestInfo);
        List<AmountOfInfo> apiResponse =
                restTemplate.postForObject("http://localhost:8080//api/v1/stats/infoamount.json", httpEntity, ArrayList.class, Collections.EMPTY_MAP);
        assertNotNull(apiResponse);
    }

    @Test
    public void getUserInfoAmountNoSession() throws Exception {
        requestInfo.getAuthInfo().setSession("");
        HttpEntity<String> httpEntity =  setHttpInfo(requestInfo);
        Map<String, String> apiResponse =
                restTemplate.postForObject("http://localhost:8080/api/v1/stats/infoamount.json", httpEntity, Map.class, Collections.EMPTY_MAP);
        assertEquals(UserIsNotAuthorizedException.class.getName(),apiResponse.get("exception"));
    }

    @Test
    public void testGetChats() throws JsonProcessingException {
        HttpEntity<String> httpEntity =  setHttpInfo(authInfo);
        List<ChatInfo> apiResponse =
                restTemplate.postForObject("http://localhost:8080/api/v1/data/chats.json", httpEntity, ArrayList.class, Collections.EMPTY_MAP);
        assertNotNull(apiResponse);

    }

    @Test
    public void testGetChatsNoSession() throws JsonProcessingException {
        authInfo.setSession("");
        HttpEntity<String> httpEntity =  setHttpInfo(authInfo);
        Map<String, String> apiResponse =
                restTemplate.postForObject("http://localhost:8080/api/v1/data/chats.json", httpEntity, Map.class, Collections.EMPTY_MAP);
        assertEquals(UserIsNotAuthorizedException.class.getName(),apiResponse.get("exception"));

    }


    @Test
    public void getMessages() throws Exception {

    }


    private static HttpEntity<String> setHttpInfo(Object entity) throws JsonProcessingException {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(entity), requestHeaders);
    }

}