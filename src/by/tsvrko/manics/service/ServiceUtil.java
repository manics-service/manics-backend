package by.tsvrko.manics.service;

import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;

import java.util.Date;
import java.util.UUID;

/**
 * Created by irats on 12/22/2016.
 */
public class ServiceUtil {

    public static String generateToken() {
        String session_id = UUID.randomUUID().toString();

        return session_id;
    }
}