package by.tsvrko.manics.service.implementations.db;

import by.tsvrko.manics.dao.interfaces.db.UserDAO;
import by.tsvrko.manics.model.hibernate.User;
import by.tsvrko.manics.service.interfaces.db.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUserByLogin(String login) {
        return userDAO.getByLogin(login);
    }

    @Override
    public boolean addUser(User user) {
        return userDAO.addUser(user);
    }


}
