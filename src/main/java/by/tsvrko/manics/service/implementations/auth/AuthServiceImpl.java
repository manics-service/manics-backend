package by.tsvrko.manics.service.implementations.auth;

import by.tsvrko.manics.model.dataimport.AuthInfo;
import by.tsvrko.manics.model.dataimport.UserInfo;
import by.tsvrko.manics.model.hibernate.User;
import by.tsvrko.manics.service.interfaces.auth.AuthService;
import by.tsvrko.manics.service.interfaces.dataimport.UserInfoService;
import by.tsvrko.manics.service.interfaces.db.SessionService;
import by.tsvrko.manics.service.interfaces.db.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created main.java.by tsvrko on 2/27/2017.
 */
@Service
public class AuthServiceImpl implements AuthService {

    private UserService userService;
    private SessionService sessionService;
    private UserInfoService userInfoService;

    @Autowired
    public AuthServiceImpl(UserService userService, SessionService sessionService, UserInfoService userInfoService) {
        this.userService = userService;
        this.sessionService = sessionService;
        this.userInfoService = userInfoService;
    }

    @Override
    public AuthInfo authenticateUser(AuthInfo authInfo) {

        UserInfo userInfo = userInfoService.getUser(authInfo);
        User user = userService.getUserByIdentifier(userInfo.getId());
        if (user.getId()==0){
            userService.addUser(userInfo);
        }
        String session = UUID.randomUUID().toString();
        sessionService.addSession(session,userInfo.getId());

        authInfo.setSession(session);
        return authInfo;
    }
}
