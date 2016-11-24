package by.tsvrko.manics.dao.implementations;

import by.tsvrko.manics.dao.interfaces.UserDAO;
import by.tsvrko.manics.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by irats on 11/22/2016.
 */
public class UserDAOImpl implements UserDAO {
    private static final String SQL_QUERY_GET_USER = "select user.login, user.pass from user where user.login = ?";
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User getUser(String login) {
        Connection conn = null;
        User user = new User();
        try {
            conn = dataSource.getConnection();
            PreparedStatement  ps = conn.prepareStatement(SQL_QUERY_GET_USER);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user.setLogin(rs.getString(1));
                user.setPass(rs.getString(2));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}
