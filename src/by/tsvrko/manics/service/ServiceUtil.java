package by.tsvrko.manics.service;

import java.util.UUID;

/**
 * Created by tsvrko on 12/22/2016.
 */
public abstract class ServiceUtil {

    public static String generateToken() {
            return UUID.randomUUID().toString();
    }
}