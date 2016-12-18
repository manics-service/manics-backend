package by.tsvrko.manics.service;

import by.tsvrko.manics.dao.interfaces.SessionDAO;
import by.tsvrko.manics.dao.interfaces.UserDAO;
import by.tsvrko.manics.exceptions.InvalidUserInfoException;
import by.tsvrko.manics.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.ws.rs.*;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.Date;


/**
 * Created by irats on 11/23/2016.
 */

@Path("/login")
public class LoginService {
    private static ApplicationContext context =
            new ClassPathXmlApplicationContext("Spring-Module.xml");
    private static UserDAO userDAO = (UserDAO) context.getBean("userDAO");
    private static   SessionDAO sessionDAO = (SessionDAO) context.getBean("sessionDAO");

   @POST
    @Produces("application/json")
    @Consumes("application/json")

    public Response authenticateUser(User user) {
       String login = user.getLogin();
       String password = user.getPass();


        try {
            authenticate(login, password);
            String token = generateToken(login);
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private void authenticate(String login, String password) throws InvalidUserInfoException {
        String dbpass = userDAO.getUser(login).getPass();
        if (!dbpass.equals(password)) throw new InvalidUserInfoException("credentials are invalid");
       }

    private String generateToken(String login) {
        String session_id = (String.valueOf((new Date()).getTime()));
        sessionDAO.addSession(session_id);
        userDAO.addUserSession(session_id,login);
        Response.ok().cookie(NewCookie.valueOf(session_id));
        return session_id;
    }

}

