package htoyama.githaru.domain.usecase.gist;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import htoyama.githaru.domain.repository.GistRepository;
import rx.observers.TestSubscriber;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

public class DeleteGistTest {
    private static final String FAKE_ID = "fake_id";

    private DeleteGist sut;
    private TestSubscriber<Void> mTestSubscriber;

    @Mock
    private GistRepository mGistRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        sut = new DeleteGist(mGistRepository);
        mTestSubscriber = new TestSubscriber<>();
    }

    @Test
    public void execute_invokedOnCmplete_whenSuccess() {
        doNothing().when(mGistRepository).delete(FAKE_ID);

        sut.execute(FAKE_ID).subscribe(mTestSubscriber);

        mTestSubscriber.awaitTerminalEvent();
        mTestSubscriber.getOnErrorEvents();
        mTestSubscriber.assertTerminalEvent();
    }

    @Test
    public void execute_emitThrowable_whenFailure() {
        doThrow(new RuntimeException())
                .when(mGistRepository).delete(FAKE_ID);

        sut.execute(FAKE_ID).subscribe(mTestSubscriber);

        mTestSubscriber.awaitTerminalEvent();
        List<Throwable> errorList = mTestSubscriber.getOnErrorEvents();
        assertThat(errorList.size(), is(1));
        assertThat(errorList.get(0), is(instanceOf(RuntimeException.class)));
    }

}