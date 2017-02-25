package by.tsvrko.manics.controller;

import by.tsvrko.manics.model.controller.Status;
import by.tsvrko.manics.model.controller.StatusEnum;
import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.hibernate.User;
import by.tsvrko.manics.model.statistics.DayActivity;
import by.tsvrko.manics.model.statistics.MessageCount;
import by.tsvrko.manics.service.LoginService;
import by.tsvrko.manics.service.ServiceUtil;
import by.tsvrko.manics.service.interfaces.dataimport.ChatImportService;
import by.tsvrko.manics.service.interfaces.dataimport.MessageImportService;
import by.tsvrko.manics.service.interfaces.db.SessionService;
import by.tsvrko.manics.service.interfaces.statistics.DayActivityService;
import by.tsvrko.manics.service.interfaces.statistics.MessageCountService;
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
    private MessageCountService messageCountService;
    private DayActivityService dayActivityService;

    @Autowired
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }
    @Autowired
    public void setChatImportService(ChatImportService chatImportService) {
        this.chatImportService = chatImportService;
    }
    @Autowired
    public void setMessageImportService(MessageImportService messageImportService) {
        this.messageImportService = messageImportService;
    }
    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
    @Autowired
    public void setMessageCountService(MessageCountService messageCountService) {
        this.messageCountService = messageCountService;
    }
    @Autowired
    public void setDayActivityService(DayActivityService dayActivityService) {
        this.dayActivityService = dayActivityService;
    }



    @RequestMapping(value = "/api/v1/statistics/countofmessages.json",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public List<MessageCount> getUserCountOfMessages(@RequestBody ChatInfo chat) {
        return messageCountService.getMessageCount(chat);
    }

    @RequestMapping(value = "/api/v1/statistics/dayactivity.json",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public List<DayActivity> getUserDayActivity(@RequestBody ChatInfo chat) {
        return dayActivityService.getDayActivity(chat);
    }




    @RequestMapping(value = "/api/v1/data/chats.json",
            method = RequestMethod.POST,
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

