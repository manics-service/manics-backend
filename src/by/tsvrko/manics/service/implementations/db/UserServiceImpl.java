package by.tsvrko.manics.service.implementations.db;

import by.tsvrko.manics.dao.interfaces.db.UserDAO;
import by.tsvrko.manics.model.dataimport.UserInfo;
import by.tsvrko.manics.model.hibernate.User;
import by.tsvrko.manics.service.interfaces.db.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by tsvrko on 2/27/2017.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUserByIdentifier(String userId) {
        return userDAO.getByIdentifier(userId);
    }

    @Override
    public boolean addUser(UserInfo userInfo) {
        return userDAO.addUser(userInfo);
    }
}
