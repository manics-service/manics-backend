package by.tsvrko.manics.dao;

import by.tsvrko.manics.dao.implementations.db.UserDAOImpl;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Created by tsvrko on 1/11/2017.
 */
public final class EncodingUtil {

   private static Logger log = Logger.getLogger(UserDAOImpl.class.getName());

    public static String encodeText(String messageBody) {

        byte[] data = new byte[0];
        try {
            data = messageBody.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.debug("can't encode string message body", e);
        }

        return Base64.getEncoder().encodeToString(data);
    }

    public static String decodeText(String messageBody) {

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
