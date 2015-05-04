package htoyama.githaru.domain.usecase.gist;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import htoyama.githaru.domain.entity.Gist;
import htoyama.githaru.domain.repository.GistRepository;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class GetGistDetailTest {
    private static final String FAKE_ID = "fake_id";

    private GetGistDetail sut;
    private TestSubscriber<Gist> mTestSubscriber;

    @Mock
    private GistRepository mGistRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        sut = new GetGistDetail(mGistRepository);
        mTestSubscriber = new TestSubscriber<>();
    }

    @Test
    public void execute_emitListOfGist_WhenSuccess() {
        Gist gist = new Gist(FAKE_ID);
        when(mGistRepository.get(FAKE_ID))
                .thenReturn(gist);

        sut.execute(FAKE_ID).subscribeOn(Schedulers.io())
                .subscribe(mTestSubscriber);

        mTestSubscriber.awaitTerminalEvent();
        mTestSubscriber.assertReceivedOnNext(Arrays.asList(gist));
        mTestSubscriber.assertNoErrors();
    }

    @Test
    public void execute_emiThrowable_WhenFail() {
        when(mGistRepository.get(FAKE_ID))
                .thenThrow(new RuntimeException());

        sut.execute(FAKE_ID).subscribe(mTestSubscriber);

        mTestSubscriber.awaitTerminalEvent();
        List<Throwable> errorList = mTestSubscriber.getOnErrorEvents();
        assertThat(errorList.size(), is(1));
        assertThat(errorList.get(0), is(instanceOf(RuntimeException.class)));
    }

}