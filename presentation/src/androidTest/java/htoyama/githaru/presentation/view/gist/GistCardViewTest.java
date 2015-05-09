package htoyama.githaru.presentation.view.gist;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import htoyama.githaru.domain.entity.Gist;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class GistCardViewTest {
    private GistCardView sut;
    private Gist mGist;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Context context = InstrumentationRegistry.getTargetContext();
        sut = new GistCardView(context);
    }

    @Test
    public void title_setGivenTitle_whenTileIsSet() {
        final String fakeTitle = "fake_title";
        mGist = new Gist("hoge");
        mGist.title = fakeTitle;
        mGist.fileList = new ArrayList<>();

        sut.setGist(mGist);

        assertThat((String) sut.getTitle(), is(fakeTitle));
    }

    @Test
    public void title_setIdWithPrefixGist_whenTileIsEmpty() {
        final String fakeId = "fake_id";
        mGist = new Gist(fakeId);
        mGist.title = "";
        mGist.fileList = new ArrayList<>();

        sut.setGist(mGist);

        String expected = "gist:" + fakeId;
        assertThat((String) sut.getTitle(), is(expected));
    }

}