package htoyama.githaru.presentation.rx;

import rx.Observable;
import rx.Observer;
import rx.functions.Action0;
import rx.functions.Action1;


interface IBinder {
    public <T> void bind(Observable<T> observable, Action1<T> onNext);

    public <T> void bind(Observable<T> observable,
                         Action1<T> onNext,
                         Action1<Throwable> onError);

    public <T> void bind(Observable<T> observable,
                         Action1<T> onNext,
                         Action1<Throwable> onError,
                         Action0 onComplete);

    public <T> void bind(Observable<T> observable, Observer<T> observer);

    public void clear();
}
