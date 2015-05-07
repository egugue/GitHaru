package htoyama.githaru.domain.usecase.gist;

import javax.inject.Inject;

import htoyama.githaru.domain.entity.Gist;
import htoyama.githaru.domain.repository.GistRepository;
import rx.Observable;
import rx.Subscriber;

/**
 * A usecase for editing {@link Gist}.
 */
public class EditGist {

    private final GistRepository mGistRepository;

    @Inject
    public EditGist(GistRepository repository) {
        mGistRepository = repository;
    }

    /**
     * Edits {@link Gist}.
     *
     * @param gist The gist to edit.
     * @return an {@link Observable} which will notify edit complete event.
     * If failed, this will emit {@link Throwable}.
     */
    public Observable<Void> execute(final Gist gist) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                try {

                    //TODO
                    if (gist.id.equals(Gist.NO_ASSINGED_ID)) {
                        mGistRepository.create(gist);
                    } else {
                        mGistRepository.edit(gist);
                    }

                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }

            }
        });
    }

}
