package htoyama.githaru.infra.exception;

import htoyama.githaru.domain.exception.DataAccessException;
import retrofit.RetrofitError;

/**
 * TODO: modify with ErrorHandler
 */
public class RetrofitApiException extends DataAccessException {
    private static final String TAG = RetrofitApiException.class.getSimpleName();
    private static int STATUS_BAD_REQUEST = 400;
    private static int STATUS_INTERNAL_SERVER_ERROR = 500;

    private final RetrofitError mRetrofitError;

    public RetrofitApiException(RetrofitError error) {
        super(error);
        mRetrofitError = error;
    }

    @Override
    public boolean canRetry() {
        if (mRetrofitError.getResponse() == null) {
            return false;
        }

        int status = mRetrofitError.getResponse().getStatus();
        return status < STATUS_BAD_REQUEST
                || status > STATUS_INTERNAL_SERVER_ERROR;
    }

}
