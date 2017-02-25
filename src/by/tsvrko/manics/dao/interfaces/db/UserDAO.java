package by.tsvrko.manics.dao.interfaces.db;

import by.tsvrko.manics.model.hibernate.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

/**
 * Created main.by tsvrko on 11/22/2016.
 */

public interface UserDAO {

    User getByLogin(String login);

    boolean addUser (User user);

  }
