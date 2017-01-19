package by.tsvrko.manics.controller;

import by.tsvrko.manics.model.*;
import by.tsvrko.manics.service.services.LoginService;
import by.tsvrko.manics.service.ServiceUtil;
import by.tsvrko.manics.service.services.dbdaoservice.SessionService;
import by.tsvrko.manics.service.services.importdaoservice.ChatImportService;
import by.tsvrko.manics.service.services.importdaoservice.MessageImportService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
public class AppController {

    private static SessionService sessionService = new SessionService();
    private static ChatImportService chatImportService = new ChatImportService();
    private static MessageImportService messageImportService = new MessageImportService();
    private static LoginService loginService = new LoginService();


    @RequestMapping(value = "/getChats",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public ArrayList<Chat> getChats(@CookieValue("session") String token) {
        return chatImportService.getChats(token);
    }


    @RequestMapping(value = "/getMessages",
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


    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public Status authenticateUser(@RequestBody User user, HttpServletResponse response) {

        Status responseStatus = new Status();
        if (loginService.authenticateUser(user)){
            String token = ServiceUtil.generateToken();
            sessionService.addSession(token,user);
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

