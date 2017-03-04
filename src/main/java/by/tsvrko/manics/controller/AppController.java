package main.java.by.tsvrko.manics.controller;

import main.java.by.tsvrko.manics.model.controller.Status;
import main.java.by.tsvrko.manics.model.controller.StatusEnum;
import main.java.by.tsvrko.manics.model.dataimport.AuthInfo;
import main.java.by.tsvrko.manics.model.dataimport.ChatInfo;
import main.java.by.tsvrko.manics.model.dataimport.RequestInfo;
import main.java.by.tsvrko.manics.model.statistics.AmountOfInfo;
import main.java.by.tsvrko.manics.model.statistics.DayActivity;
import main.java.by.tsvrko.manics.model.statistics.MessageCount;
import main.java.by.tsvrko.manics.model.statistics.PeriodActivity;
import main.java.by.tsvrko.manics.service.interfaces.auth.AuthService;
import main.java.by.tsvrko.manics.service.interfaces.dataimport.ChatImportService;
import main.java.by.tsvrko.manics.service.interfaces.dataimport.MessageImportService;
import main.java.by.tsvrko.manics.service.interfaces.statistics.AmountOfInfoService;
import main.java.by.tsvrko.manics.service.interfaces.statistics.DayActivityService;
import main.java.by.tsvrko.manics.service.interfaces.statistics.MessageCountService;
import main.java.by.tsvrko.manics.service.interfaces.statistics.PeriodActivityService;
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
    private AmountOfInfoService amountOfInfoService;


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
    @Autowired
    public void setAmountOfInfoService(AmountOfInfoService amountOfInfoService) {
        this.amountOfInfoService = amountOfInfoService;
    }

    @RequestMapping(value = "/api/v1/authentication",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public AuthInfo authenticateUser(@RequestBody AuthInfo authInfo) {
        return authService.authenticateUser(authInfo);
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

    @RequestMapping(value = "/api/v1/stats/infoamount.json",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public List<AmountOfInfo> getUserInfoAmount(@RequestBody RequestInfo requestInfo) {
        return amountOfInfoService.getStatistics(requestInfo.getChatInfo(),requestInfo.getAuthInfo());
    }


    @RequestMapping(value = "/api/v1/data/chats.json",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public List<ChatInfo> getChats(@RequestBody AuthInfo authInfo) {
        return chatImportService.getListOfChats(authInfo);
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

