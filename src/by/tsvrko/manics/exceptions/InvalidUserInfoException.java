package by.tsvrko.manics.exceptions;

/**
 * Created main.by irats on 11/24/2016.
 */
public class InvalidUserInfoException extends Exception{
    public InvalidUserInfoException() {
    }

    public InvalidUserInfoException(String message) {
        super(message);
    }

    public InvalidUserInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUserInfoException(Throwable cause) {
        super(cause);
    }

    public InvalidUserInfoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
