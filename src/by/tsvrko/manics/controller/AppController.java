package by.tsvrko.manics.controller;

import by.tsvrko.manics.model.Status;
import by.tsvrko.manics.model.StatusEnum;
import by.tsvrko.manics.model.User;
import by.tsvrko.manics.service.services.LoginService;
import by.tsvrko.manics.service.ServiceUtil;
import by.tsvrko.manics.service.services.SessionService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AppController {

    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public Status authenticateUser(@RequestBody User user, HttpServletResponse response) {

        boolean auth =  new LoginService().authenticateUser(user);
        Status responseStatus = new Status();
        if (auth){
            String token = ServiceUtil.generateToken();
            new SessionService().addSession(token,user);
            responseStatus.setStatusCode("200");
            responseStatus.setDescription(StatusEnum.OK);
            response.setHeader("Set-Cookie","session=" + token + "; Path=/");
            return responseStatus;
        }
        else{
            responseStatus.setStatusCode("401");
            responseStatus.setDescription(StatusEnum.UNATHOURIZED);
            return responseStatus;


        }


    }
}

