package by.tsvrko.manics.controller;

import by.tsvrko.manics.model.*;
import by.tsvrko.manics.model.hibernate.Chat;
import by.tsvrko.manics.model.hibernate.User;
import by.tsvrko.manics.service.services.UserMessageCountService;
import by.tsvrko.manics.service.services.LoginService;
import by.tsvrko.manics.service.ServiceUtil;
import by.tsvrko.manics.service.services.dao.db.SessionService;
import by.tsvrko.manics.service.services.dao.dataimport.ChatImportService;
import by.tsvrko.manics.service.services.dao.dataimport.MessageImportService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class AppController {
    @Resource(name="sessionService")
    private SessionService sessionService;
    @Resource(name="chatImportService")
    private ChatImportService chatImportService;
    @Resource(name="messageImportService")
    private  MessageImportService messageImportService;
    @Resource(name="loginService")
    private LoginService loginService;
    @Resource(name="countStatisticsService")
    private UserMessageCountService userMessageCountService;


    @RequestMapping(value = "/api/v1/chats/statistic/countofmessages.json",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public List<UserMessageCount> getChats(@RequestBody Chat chat) {
        return userMessageCountService.getChatStatistics(chat);
    }

    @RequestMapping(value = "/api/v1/chats.json",
            method = RequestMethod.GET,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public List<ChatInfo> getChats(@CookieValue("session") String token) {
        return chatImportService.getChats(token);
    }


    @RequestMapping(value = "/api/v1/chats/messages.json",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public Status getMessages(@RequestBody Chat chat, @CookieValue("session") String token) {
        Status responseStatus = new Status();
        if (messageImportService.getMessages(chat, token)){
            responseStatus.setStatusCode("200");
            responseStatus.setDescription(StatusEnum.OK);
            return responseStatus;
        }
        else {
            responseStatus.setStatusCode("999");
            responseStatus.setDescription(StatusEnum.VK_API_ERROR);
            return responseStatus;
        }

    }

    @RequestMapping(value = "/api/v1/login",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public Status authenticateUser(@RequestBody User user, HttpServletResponse response) {

        Status responseStatus = new Status();
        if (loginService.authenticateUser(user)){
            String token = ServiceUtil.generateToken();
            sessionService.addSession(token, user);
            responseStatus.setStatusCode("200");
            responseStatus.setDescription(StatusEnum.OK);
            response.addCookie(new Cookie("session", token));
            return responseStatus;
        }
        else{
            responseStatus.setStatusCode("401");
            responseStatus.setDescription(StatusEnum.UNAUTHORIZED);
            return responseStatus;
        }


    }
}

