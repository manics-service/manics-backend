package by.tsvrko.manics.controller;

import by.tsvrko.manics.model.Status;
import by.tsvrko.manics.model.User;
import by.tsvrko.manics.service.LoginService;
import by.tsvrko.manics.service.ServiceUtil;
import by.tsvrko.manics.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

@RestController
public class AppController {

    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public Status authenticateUser(@RequestBody User user, HttpServletResponse response) {

        boolean auth =  new LoginService().authenticateUser(user);
        if (auth){
            String token = ServiceUtil.generateToken();
            new SessionService().addSession(token,user);
            response.setHeader("Set-Cookie","session=" + token + "Path=/");
            return Status.OK;
        }
        else{
            return Status.UNATHOURIZED;

        }


    }
}

