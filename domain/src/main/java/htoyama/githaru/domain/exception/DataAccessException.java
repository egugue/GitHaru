package htoyama.githaru.domain.exception;

/**
 *
 */
public abstract class DataAccessException extends RuntimeException {

    public DataAccessException(String detailMessage) {
        super(detailMessage);
    }

    public DataAccessException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public DataAccessException(Throwable throwable) {
        super(throwable);
    }

    public abstract boolean canRetry();

}
