package by.tsvrko.manics.service;

import by.tsvrko.manics.dao.implementations.UserDAOImpl;
import by.tsvrko.manics.dao.interfaces.UserDAO;
import by.tsvrko.manics.model.User;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by irats on 11/23/2016.
 */
@Path("/authentication")

public class LoginService {
    public static   UserDAO userDAO = new UserDAOImpl();
    @POST
    @Produces("application/json")
    @Consumes("")
    public Response authenticateUser(@FormParam("login") String login,
                                     @FormParam("password") String password) {


        try {
            authenticate(login, password);
            String token = issueToken(login);
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private void authenticate(String login, String password) throws Exception {
        if (checkPassword(login, password)) {
           User user = userDAO.getUser(login);
          } else throw new Exception("credentials are invalid");
       }

    private String issueToken(String username) {

    }

    public boolean checkPassword(String email, String password) {
        String dbpass = userDAO.getUser(email).getPass();
        return password.equals(dbpass);
    }
}

