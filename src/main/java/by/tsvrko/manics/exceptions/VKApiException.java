package by.tsvrko.manics.exceptions;

/**
 * Created main.main.java.by irats on 1/4/2017.
 */
public class VKApiException extends RuntimeException {
    public VKApiException() {
        super();
    }

    public VKApiException(String message) {
        super(message);
    }

    public VKApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public VKApiException(Throwable cause) {
        super(cause);
    }

    protected VKApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
