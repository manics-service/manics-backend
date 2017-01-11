package by.tsvrko.manics.dao.database;

import by.tsvrko.manics.dao.database.implementations.UserDAOImpl;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Created by tsvrko on 1/11/2017.
 */
public abstract class EncodingUtil {

   private static Logger log = Logger.getLogger(UserDAOImpl.class.getName());

    public static String encodeMessages(String messageBody) {

        byte[] data = new byte[0];
        try {
            data = messageBody.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.debug("can't encode string message body", e);
        }

        return Base64.getEncoder().encodeToString(data);
    }

    public static String decodeMessages(String messageBody) {

    byte[] data = Base64.getDecoder().decode(messageBody);
        String newStringWithEmojis = null;
        try {
            newStringWithEmojis = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.debug("can't decode string message body", e);
        }
        return newStringWithEmojis;
    }
}
