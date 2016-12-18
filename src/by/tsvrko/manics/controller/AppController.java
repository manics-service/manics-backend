package by.tsvrko.manics.controller;

import by.tsvrko.manics.model.User;
import by.tsvrko.manics.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {


    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public LoginService loginUser(@RequestBody User user) {
        return new LoginService();

    }
}

