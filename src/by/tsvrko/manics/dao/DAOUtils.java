package by.tsvrko.manics.dao;

import by.tsvrko.manics.dao.implementations.UserDAOImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by irats on 11/28/2016.
 */
public class DAOUtils {
    private static Logger log = Logger.getLogger(DAOUtils.class.getName());

    public static void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            log.error("statement was not closed", e);
        }
    }

    public static void closeConnection(Connection conn){
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                log.debug("no connection",e);
            }
        }
    }
}
