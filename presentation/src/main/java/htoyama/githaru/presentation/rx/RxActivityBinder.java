package htoyama.githaru.presentation.rx;

import android.app.Activity;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

import static rx.android.app.AppObservable.bindActivity;

class RxActivityBinder implements IBinder {
    private Activity mActivity;
    private CompositeSubscription mSubscription;

    public RxActivityBinder(Activity activity) {
        mActivity  = activity;
        mSubscription = new CompositeSubscription();
    }

    @Override
    public <T> void bind(Observable<T> observable, Action1<T> onNext) {
        mSubscription.add(bindActivity(mActivity, observable)
                        .subscribe(onNext)
        );
    }

    @Override
    public <T> void bind(Observable<T> observable, Action1<T> onNext, Action1<Throwable> onError) {
        mSubscription.add(bindActivity(mActivity, observable)
                        .subscribe(onNext, onError)
        );

    }

    @Override
    public <T> void bind(Observable<T> observable, Action1<T> onNext, Action1<Throwable> onError, Action0 onComplete) {
        mSubscription.add(bindActivity(mActivity, observable)
                        .subscribe(onNext, onError, onComplete)
        );
    }

    @Override
    public <T> void bind(Observable<T> observable, Observer<T> observer) {
        mSubscription.add(bindActivity(mActivity, observable)
                        .subscribe(observer)
        );
    }

    @Override
    public void clear() {
        Log.d("RxActivityBinder", "clear");
        mSubscription.clear();
    }

}
