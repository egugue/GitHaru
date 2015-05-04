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

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class GetGistListTest {
    private static final String FAKE_USER = "fake_user";

    private GetGistList sut;
    private TestSubscriber<List<Gist>> mTestSubscriber;

    @Mock
    private GistRepository mGistRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        sut = new GetGistList(mGistRepository);
        mTestSubscriber = new TestSubscriber<>();
    }

    @Test
    public void execute_emitListOfGist_WhenSuccess() {
        Gist gist1 = new Gist("id1");
        Gist gist2 = new Gist("id2");
        List<Gist> gistList = Arrays.asList(gist1, gist2);
        when(mGistRepository.getList(FAKE_USER))
                .thenReturn(gistList);

        sut.execute(FAKE_USER).subscribe(mTestSubscriber);

        mTestSubscriber.awaitTerminalEvent();
        mTestSubscriber.assertReceivedOnNext(Arrays.asList(gistList));
        mTestSubscriber.assertNoErrors();
    }

    @Test
    public void execute_emiThrowable_WhenFail() {
        when(mGistRepository.getList(FAKE_USER))
                .thenThrow(new RuntimeException());

        sut.execute(FAKE_USER).subscribe(mTestSubscriber);

        mTestSubscriber.awaitTerminalEvent();
        List<Throwable> errorList = mTestSubscriber.getOnErrorEvents();
        assertThat(errorList.size(), is(1));
        assertThat(errorList.get(0), is(instanceOf(RuntimeException.class)));
    }

}