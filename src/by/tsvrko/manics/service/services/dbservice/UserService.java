package by.tsvrko.manics.service.services.dbservice;

import by.tsvrko.manics.dao.database.HibernateFactory;
import by.tsvrko.manics.dao.database.interfaces.UserDAO;
import by.tsvrko.manics.model.User;
import org.springframework.stereotype.Service;

@Service("userService")
//@Configurable(autowire=Autowire.BY_NAME,dependencyCheck=true)
//@Transactional
public class UserService {

    private static UserDAO userDAO = HibernateFactory.getInstance().getUserDAO();
  //  private UserDAO userDao ;

//    @Autowired
//    public void setUserDao(UserDAO userDao) {
//        this.userDao = userDao;
//    }

    public User getUserByLogin(String login) {
        return userDAO.getUserByLogin(login);
    }

}
