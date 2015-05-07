package htoyama.githaru.domain.usecase.gist;

import javax.inject.Inject;

import htoyama.githaru.domain.entity.Gist;
import htoyama.githaru.domain.repository.GistRepository;
import rx.Observable;
import rx.Subscriber;

/**
 * A usecase for creating {@link Gist}.
 */
public class CreateGist {
    private final GistRepository mGistRepository;

    @Inject
    public CreateGist(GistRepository repository) {
        mGistRepository = repository;
    }

    /**
     * Create {@link htoyama.githaru.domain.entity.Gist}.
     *
     * @param gist The gist to create.
     * @return an {@link rx.Observable} which will notify create complete event.
     * If failed, this will emit {@link Throwable}.
     */
    public Observable<Void> execute(final Gist gist) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                try {
                    mGistRepository.create(gist);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }

            }
        });
    }

}
