package htoyama.githaru.presentation.rx;

import android.app.Activity;
import android.app.Fragment;

import rx.Observable;
import rx.Observer;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 *
 */
public class RxBinder implements IBinder {
    private final IBinder IMPL;

    public RxBinder(Activity activity) {
        IMPL = new RxActivityBinder(activity);
    }

    public RxBinder(Fragment fragment) {
        IMPL = new RxFragmentBinder(fragment);
    }

    public RxBinder(android.support.v4.app.Fragment supportFragment) {
        IMPL = new RxFragmentBinder(supportFragment);
    }

    @Override
    public <T> void bind(Observable<T> observable, Action1<T> onNext) {
        IMPL.bind(observable, onNext);
    }

    @Override
    public <T> void bind(Observable<T> observable, Action1<T> onNext, Action1<Throwable> onError) {
        IMPL.bind(observable, onNext, onError);
    }

    @Override
    public <T> void bind(Observable<T> observable, Action1<T> onNext, Action1<Throwable> onError, Action0 onComplete) {
        IMPL.bind(observable, onNext, onError, onComplete);
    }

    @Override
    public <T> void bind(Observable<T> observable, Observer<T> observer) {
        IMPL.bind(observable, observer);
    }

    @Override
    public void clear() {
        IMPL.clear();
    }

}
