package by.tsvrko.manics.dao.implementations;

import by.tsvrko.manics.dao.DAOUtils;
import by.tsvrko.manics.dao.interfaces.SessionDAO;
import by.tsvrko.manics.model.Session;
import by.tsvrko.manics.model.User;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by irats on 11/30/2016.
 */
public class SessionDAOImpl implements SessionDAO{
    private static Logger log = Logger.getLogger(UserDAOImpl.class.getName());
    private static final String SQL_QUERY_GET_USER = "select user.id, user.login, user.pass from user where user.login = ?";
    private static final String SQL_QUERY_ADD_SESSION = "insert into `session`(`session`.`session`) values (?)";

    private DataSource dataSource;
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public boolean addSession(String session_id) {

        Connection conn = null;
        PreparedStatement statement= null;


        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(SQL_QUERY_ADD_SESSION);
            statement.setString(1, session_id);
            statement.executeUpdate();

        } catch (SQLException e) {
            log.debug("dao exception", e);
            return false;
        } finally {
            DAOUtils.closeStatement(statement);
            DAOUtils.closeConnection(conn);
        }
        return true;
    }
}
