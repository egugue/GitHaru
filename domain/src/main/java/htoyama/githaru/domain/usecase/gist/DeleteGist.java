package htoyama.githaru.domain.usecase.gist;

import javax.inject.Inject;

import htoyama.githaru.domain.entity.Gist;
import htoyama.githaru.domain.repository.GistRepository;
import rx.Observable;
import rx.Subscriber;

/**
 * A usecase for deleting {@link Gist}.
 */
public class DeleteGist {
    private final GistRepository mGistRepository;

    @Inject
    public DeleteGist(GistRepository repository) {
        mGistRepository = repository;
    }

    /**
     * Deletes a {@link Gist}.
     *
     * @param gistId The gist id to delete.
     * @return an {@link Observable} which will notify delete complete event.
     * If failed, this will emit {@link Throwable}.
     */
    public Observable<Void> execute(final String gistId) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                try {
                    mGistRepository.delete(gistId);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }

}
