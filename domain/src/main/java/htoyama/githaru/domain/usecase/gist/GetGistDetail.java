package htoyama.githaru.domain.usecase.gist;

import javax.inject.Inject;

import htoyama.githaru.domain.entity.Gist;
import htoyama.githaru.domain.repository.GistRepository;
import rx.Observable;
import rx.Subscriber;

/**
 * A usecase for retrieving a {@link Gist} specific data.
 */
public class GetGistDetail {
    private final GistRepository mGistRepository;

    @Inject
    public GetGistDetail(GistRepository repository) {
        mGistRepository = repository;
    }

    /**
     * Retrieves a {@link Gist}.
     *
     * @param gistId The gist id to retrieve.
     * @return an {@link Observable} which will emit {@link Gist}.
     * If failed, this will emit {@link Throwable}.
     */
    public Observable<Gist> execute(final String gistId) {
        return Observable.create(new Observable.OnSubscribe<Gist>() {
            @Override
            public void call(Subscriber<? super Gist> subscriber) {
                try {
                    Gist gist = mGistRepository.get(gistId);
                    subscriber.onNext(gist);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

}
