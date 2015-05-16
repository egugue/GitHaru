package htoyama.githaru.presentation.view.gist.top;

import java.util.List;

import javax.inject.Inject;

import htoyama.githaru.domain.entity.Gist;
import htoyama.githaru.domain.usecase.gist.GetGistList;
import htoyama.githaru.presentation.view.common.Presenter;
import htoyama.githaru.presentation.view.common.di.PerActivity;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Presenter for managing to show {@link Gist} list.
 */
@PerActivity
class GistListPresenter implements Presenter {
    private final GetGistList mGetGistList;
    private final CompositeSubscription mSubscriptions = new CompositeSubscription();
    private GistListView mView;

    @Inject
    public GistListPresenter(GetGistList getGistList) {
        mGetGistList = getGistList;
    }

    /**
     * Set up view.
     */
    public void setup(GistListView view) {
        mView = view;
    }

    /**
     * Load List of {@link Gist} to show.
     *
     * @param isShowAdditionalGist
     *      If true, invoked {@link GistListView#showAdditionalGist(Gist)}.
     *      If false, not invoked this.
     */
    public void loadGistList(final boolean isShowAdditionalGist) {
        mView.showLoading();

        Subscription sub = mGetGistList.execute("egugue")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Gist>>() {
                    @Override
                    public void call(List<Gist> gists) {
                        mView.hideLoading();
                        showGistListInView(gists, isShowAdditionalGist);
                    }

                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.hideLoading();
                        mView.showError(throwable.getMessage());
                    }
                });

        mSubscriptions.add(sub);
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        unsubscribe();
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.unsubscribe();
    }

    @Override
    public boolean isUnsubscribed() {
        return mSubscriptions.isUnsubscribed();
    }

    private void showGistListInView(List<Gist> gists, boolean isShowAdditionalGist) {
        if (!isShowAdditionalGist) {
            mView.showGistList(gists);
            return;
        }

        //retrive latest gist.
        final Gist gist = gists.get(0);
        gists.remove(0);

        mView.showGistList(gists);

        //do slide-in animation to latest gist.
        Subscription sub = Observable.just(gist)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Gist>() {
                    @Override
                    public void call(Gist gist) {
                        mView.showAdditionalGist(gist);
                    }
                });

        mSubscriptions.add(sub);
    }

}
