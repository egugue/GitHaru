package htoyama.githaru.presentation.view.gist;

import android.os.SystemClock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import htoyama.githaru.domain.entity.Gist;
import htoyama.githaru.domain.usecase.gist.GetGistList;
import rx.Observable;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class GistListPresenterTest {
    private static final int IDLE_TIME = 50;

    private GistListPresenter sut;

    @Mock GetGistList mGetGistList;
    @Mock GistListView mView;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        sut = new GistListPresenter(mGetGistList);
        sut.setup(mView);
    }

    @Test
    public void destroy_unsubscribe_whenDestroyInvoked() {
        sut.destroy();

        assertTrue(sut.isUnsubscribed());
    }

    @Test
    public void loadGistList_GivenTrue_whenSuccess() {
        //setup
        List<Gist> gistList = fakeGistList();
        when(mGetGistList.execute(anyString()))
                .thenReturn(Observable.just(gistList));

        //exercise
        sut.loadGistList(true);
        SystemClock.sleep(IDLE_TIME);

        //verify
        Gist latestGist = gistList.get(0);
        gistList.remove(0);

        verify(mView).showLoading();
        verify(mView).hideLoading();
        verify(mView).showAdditionalGist(latestGist);
        verify(mView).showGistList(gistList);

        verify(mView, never()).showError(anyString());
    }

    @Test
    public void loadGistList_GivenTrue_whenFailure() {
        //setup
        String errorMessage = "error_mesage";
        final Exception throwable = new RuntimeException(errorMessage);
        when(mGetGistList.execute(anyString()))
                .thenReturn(Observable.<List<Gist>>error(throwable));

        //exercise
        sut.loadGistList(true);
        SystemClock.sleep(IDLE_TIME);

        //verify
        verify(mView).showLoading();
        verify(mView).hideLoading();
        verify(mView).showError(errorMessage);

        verify(mView, never()).showGistList(anyListOf(Gist.class));
        verify(mView, never()).showAdditionalGist(any(Gist.class));
    }

    @Test
    public void loadGistList_GivenFalse_whenSuccess() {
        //setup
        List<Gist> gistList = fakeGistList();
        when(mGetGistList.execute(anyString()))
                .thenReturn(Observable.just(gistList));

        //exercise
        sut.loadGistList(false);
        SystemClock.sleep(IDLE_TIME);

        verify(mView).showLoading();
        verify(mView).showGistList(gistList);
        verify(mView).hideLoading();

        verify(mView, never()).showAdditionalGist(any(Gist.class));
        verify(mView, never()).showError(anyString());
    }

    @Test
    public void loadGistList_GivenFalse_whenFailure() {
        //setup
        String errorMessage = "error_mesage";
        final Exception throwable = new RuntimeException(errorMessage);
        when(mGetGistList.execute(anyString()))
                .thenReturn(Observable.<List<Gist>>error(throwable));

        //exercise
        sut.loadGistList(false);
        SystemClock.sleep(IDLE_TIME);

        //verify
        verify(mView).showLoading();
        verify(mView).hideLoading();
        verify(mView).showError(errorMessage);

        verify(mView, never()).showGistList(anyListOf(Gist.class));
        verify(mView, never()).showAdditionalGist(any(Gist.class));
    }

    private List<Gist> fakeGistList() {
        List<Gist> list = new ArrayList<>();
        Gist gist = new Gist("dummy_id");

        list.add(gist);
        list.add(gist);
        list.add(gist);

        return list;
    }

}