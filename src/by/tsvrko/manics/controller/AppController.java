package by.tsvrko.manics.controller;

import by.tsvrko.manics.model.controller.Status;
import by.tsvrko.manics.model.controller.StatusEnum;
import by.tsvrko.manics.model.dataimport.AuthInfo;
import by.tsvrko.manics.model.dataimport.ChatInfo;
import by.tsvrko.manics.model.dataimport.RequestInfo;
import by.tsvrko.manics.model.statistics.DayActivity;
import by.tsvrko.manics.model.statistics.MessageCount;
import by.tsvrko.manics.model.statistics.PeriodActivity;
import by.tsvrko.manics.service.interfaces.auth.AuthService;
import by.tsvrko.manics.service.interfaces.dataimport.ChatImportService;
import by.tsvrko.manics.service.interfaces.dataimport.MessageImportService;
import by.tsvrko.manics.service.interfaces.statistics.DayActivityService;
import by.tsvrko.manics.service.interfaces.statistics.MessageCountService;
import by.tsvrko.manics.service.interfaces.statistics.PeriodActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppController {

    private ChatImportService chatImportService;
    private MessageImportService messageImportService;
    private MessageCountService messageCountService;
    private DayActivityService dayActivityService;
    private PeriodActivityService periodActivityService;
    private AuthService authService;

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

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setPeriodActivityService(PeriodActivityService periodActivityService) {
        this.periodActivityService = periodActivityService;
    }

    @RequestMapping(value = "/api/v1/authentication",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public AuthInfo authenticateUser(@RequestBody RequestInfo requestInfo) {
        return authService.authenticateUser(requestInfo.getAuthInfo());
    }


    @RequestMapping(value = "/api/v1/stats/countofmessages.json",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public List<MessageCount> getUserCountOfMessages(@RequestBody RequestInfo requestInfo) {
        return messageCountService.getStatistics(requestInfo.getChatInfo(),requestInfo.getAuthInfo());
    }


    @RequestMapping(value = "/api/v1/stats/dayactivity.json",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public List<DayActivity> getUserDayActivity(@RequestBody RequestInfo requestInfo) {
        return dayActivityService.getStatistics(requestInfo.getChatInfo(),requestInfo.getAuthInfo());
    }

    @RequestMapping(value = "/api/v1/stats/periodactivity.json",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public List<PeriodActivity> getUserPeriodActivity(@RequestBody RequestInfo requestInfo) {
        return periodActivityService.getStatistics(requestInfo.getChatInfo(),requestInfo.getAuthInfo(),requestInfo.getDate());
    }


    @RequestMapping(value = "/api/v1/data/chats.json",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public List<ChatInfo> getChats(@RequestBody RequestInfo requestInfo) {
        return chatImportService.getListOfChats(requestInfo.getAuthInfo());
    }


    @RequestMapping(value = "/api/v1/data/messages.json",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public Status getMessages(@RequestBody RequestInfo requestInfo) {

        Status responseStatus = new Status();
        ChatInfo chatInfo = requestInfo.getChatInfo();
        AuthInfo authInfo = requestInfo.getAuthInfo();

        if (messageImportService.getChatMessages(chatInfo, authInfo)){
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


}

