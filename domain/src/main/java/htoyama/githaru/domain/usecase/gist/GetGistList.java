package htoyama.githaru.domain.usecase.gist;

import java.util.List;

import javax.inject.Inject;

import htoyama.githaru.domain.entity.Gist;
import htoyama.githaru.domain.repository.GistRepository;
import rx.Observable;
import rx.Subscriber;

/**
 * A usecase for retrieving a list of {@link Gist}
 */
public class GetGistList {
    private final GistRepository mGistRepository;

    @Inject
    public GetGistList(GistRepository repository) {
        mGistRepository = repository;
    }

    /**
     * Retrieving a lisf of {@link Gist}.
     *
     * @param userName The user name to retrieve a list of gist.
     * @return an {@link Observable} which will emit a list of {@link Gist}.
     * If failed, this will emit {@link Throwable}.
     */
    public Observable<List<Gist>> execute(final String userName) {
        return Observable.create(new Observable.OnSubscribe<List<Gist>>() {
            @Override
            public void call(Subscriber<? super List<Gist>> subscriber) {
                try {
                    List<Gist> list = mGistRepository.getList(userName);
                    subscriber.onNext(list);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
