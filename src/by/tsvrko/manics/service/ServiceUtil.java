package by.tsvrko.manics.service;

import java.util.UUID;

/**
 * Created main.by tsvrko on 12/22/2016.
 */
public final class ServiceUtil {

    public static String generateSession() {
            return UUID.randomUUID().toString();
    }
}