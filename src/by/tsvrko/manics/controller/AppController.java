package by.tsvrko.manics.controller;

import by.tsvrko.manics.model.controller.Status;
import by.tsvrko.manics.model.controller.StatusEnum;
import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.hibernate.User;
import by.tsvrko.manics.model.statistics.UserMessageCount;
import by.tsvrko.manics.service.LoginService;
import by.tsvrko.manics.service.ServiceUtil;
import by.tsvrko.manics.service.interfaces.dataimport.ChatImportService;
import by.tsvrko.manics.service.interfaces.dataimport.MessageImportService;
import by.tsvrko.manics.service.interfaces.db.SessionService;
import by.tsvrko.manics.service.interfaces.statistics.UserMessageCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class AppController {

    private SessionService sessionService;
    private ChatImportService chatImportService;
    private MessageImportService messageImportService;
    private LoginService loginService;
    private UserMessageCountService userMessageCountService;

    @Autowired
    public AppController(SessionService sessionService, ChatImportService chatImportService, MessageImportService messageImportService, LoginService loginService, UserMessageCountService userMessageCountService) {
        this.sessionService = sessionService;
        this.chatImportService = chatImportService;
        this.messageImportService = messageImportService;
        this.loginService = loginService;
        this.userMessageCountService = userMessageCountService;
    }

    @RequestMapping(value = "/api/v1/statistics/countofusermessages.json",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public List<UserMessageCount> getUserCountOfMessages(@RequestBody ChatInfo chat) {
        return userMessageCountService.getChatStatistics(chat);
    }

    @RequestMapping(value = "/api/v1/data/chats.json",
            method = RequestMethod.GET,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public List<ChatInfo> getChats(@CookieValue("session") String token) {
        return chatImportService.getListOfChats(token);
    }


    @RequestMapping(value = "/api/v1/data/messages.json",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public Status getMessages(@RequestBody ChatInfo chat, @CookieValue("session") String token) {
        Status responseStatus = new Status();
        if (messageImportService.getChatMessages(chat, token)){
            responseStatus.setStatusCode("200");
            responseStatus.setDescription(StatusEnum.OK);
            return responseStatus;
        }
        else {
            responseStatus.setStatusCode("841");
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

