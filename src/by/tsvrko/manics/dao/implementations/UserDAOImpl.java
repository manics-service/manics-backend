package by.tsvrko.manics.dao.implementations;

import static by.tsvrko.manics.dao.DAOUtils.*;
import by.tsvrko.manics.dao.interfaces.UserDAO;
import by.tsvrko.manics.model.User;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by irats on 11/22/2016.
 */
public class UserDAOImpl implements UserDAO {

    private static Logger log = Logger.getLogger(UserDAOImpl.class.getName());
    private static final String SQL_QUERY_GET_USER = "select user.id, user.login, user.pass from user where user.login = ?";
    private static final String  SQL_QUERY_ADD_USER_SESSION = "update user set user.`session` = ? where user.login = ?";

    private DataSource dataSource;
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User getUser(String login) {
        Connection conn = null;
        PreparedStatement  ps = null;
        User user = new User();
        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(SQL_QUERY_GET_USER);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt(1));
                user.setLogin(rs.getString(2));
                user.setPass(rs.getString(3));
            }

        } catch (SQLException e) {
            log.debug("SQL exception",e);
        } finally {
            closeStatement(ps);
            closeConnection(conn);
        }
        return user;
    }

    @Override
    public boolean addUserSession(String session_id, String login) {

        Connection conn = null;
        PreparedStatement statement= null;


        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(SQL_QUERY_ADD_USER_SESSION);
            statement.setString(1, session_id);
            statement.setString(2, login);
            statement.executeUpdate();

        } catch (SQLException e) {
            log.debug("dao exception", e);
            return false;
        } finally {
            closeStatement(statement);
            closeConnection(conn);
        }
        return true;
    }
}
