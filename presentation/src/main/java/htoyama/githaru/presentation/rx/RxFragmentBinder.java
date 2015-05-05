package htoyama.githaru.presentation.rx;

import rx.Observable;
import rx.Observer;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

import static rx.android.app.AppObservable.bindFragment;

class RxFragmentBinder implements IBinder {
    private Object mFragment;
    private CompositeSubscription mSubscription;

    public RxFragmentBinder(Object fragment) {
        mFragment = fragment;
        mSubscription = new CompositeSubscription();
    }

    @Override
    public <T> void bind(Observable<T> observable, Action1<T> onNext) {
        mSubscription.add(bindFragment(mFragment, observable)
                        .subscribe(onNext)
        );
    }

    @Override
    public <T> void bind(Observable<T> observable, Action1<T> onNext, Action1<Throwable> onError) {
        mSubscription.add(bindFragment(mFragment, observable)
                        .subscribe(onNext, onError)
        );
    }

    @Override
    public <T> void bind(Observable<T> observable, Action1<T> onNext, Action1<Throwable> onError, Action0 onComplete) {
        mSubscription.add(bindFragment(mFragment, observable)
                        .subscribe(onNext, onError, onComplete)
        );
    }

    @Override
    public <T> void bind(Observable<T> observable, Observer<T> observer) {
        mSubscription.add(bindFragment(mFragment, observable)
                        .subscribe(observer)
        );
    }

    @Override
    public void clear() {
        mSubscription.clear();
    }

}
