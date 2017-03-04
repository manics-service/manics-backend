package main.java.by.tsvrko.manics.exceptions;

/**
 * Created main.java.by tsvrko on 2/12/2017.
 */
public class TooManyRequestsToApiException extends Exception{

    public TooManyRequestsToApiException() {
        super();
    }

    public TooManyRequestsToApiException(String message) {
        super(message);
    }

    public TooManyRequestsToApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public TooManyRequestsToApiException(Throwable cause) {
        super(cause);
    }

    protected TooManyRequestsToApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
