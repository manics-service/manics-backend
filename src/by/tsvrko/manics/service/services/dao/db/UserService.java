package by.tsvrko.manics.service.services.dao.db;

import by.tsvrko.manics.dao.database.interfaces.UserDAO;
import by.tsvrko.manics.model.hibernate.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserService {

    @Autowired
    private UserDAO userDao ;

    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

}
