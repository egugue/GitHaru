package htoyama.githaru.infra.net;

import android.util.Log;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by toyamaosamuyu on 2015/05/03.
 */
public class ApiErrorHandler implements ErrorHandler {
    private static final String TAG = ApiErrorHandler.class.getSimpleName();

    @Override
    public Throwable handleError(RetrofitError cause) {
        Response r = cause.getResponse();

        if (r == null) {
            return cause;
        }

        //Log.d(TAG, "header = "+r.getHeaders().get(0).toString());
        Log.d(TAG, "code = "+r.getStatus());
        Log.d(TAG, "size = "+r.getHeaders().size());
        Log.d(TAG, "body = "+r.getBody());
//        Log.d(TAG, "cause = "+cause.getCause().getClass());
//        Log.d(TAG, "body = "+r.getBody().toString());

        return cause;
    }
}
