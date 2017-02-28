package by.tsvrko.manics.exceptions;

import org.jetbrains.annotations.NonNls;

/**
 * Created by tsvrko on 2/28/2017.
 */
public class UserIsNotAuthorizedException extends RuntimeException {
    public UserIsNotAuthorizedException() {
        super();
    }

    public UserIsNotAuthorizedException(@NonNls String message) {
        super(message);
    }

    public UserIsNotAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIsNotAuthorizedException(Throwable cause) {
        super(cause);
    }

    protected UserIsNotAuthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
