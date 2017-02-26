package by.tsvrko.manics.controller;

import by.tsvrko.manics.model.controller.Status;
import by.tsvrko.manics.model.controller.StatusEnum;
import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.statistics.DayActivity;
import by.tsvrko.manics.model.statistics.MessageCount;
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
    public void setMessageCountService(MessageCountService messageCountService) {
        this.messageCountService = messageCountService;
    }
    @Autowired
    public void setDayActivityService(DayActivityService dayActivityService) {
        this.dayActivityService = dayActivityService;
    }

    @RequestMapping(value = "/api/v1/stats/countofmessages.json",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public List<MessageCount> getUserCountOfMessages(@RequestBody ChatInfo chat) {
        return messageCountService.getMessageCount(chat);
    }


    @RequestMapping(value = "/api/v1/stats/dayactivity.json",
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
    public boolean authenticateUser(@RequestBody String token, HttpServletResponse response) {
        String session = ServiceUtil.generateSession();
        sessionService.addSession(session);
        response.addCookie(new Cookie("session", session));

        return true;

    }
}

