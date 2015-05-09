package htoyama.githaru.presentation.view.gist;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.view.LayoutInflater;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import htoyama.githaru.domain.entity.File;
import htoyama.githaru.domain.entity.Gist;
import htoyama.githaru.presentation.R;
import htoyama.githaru.presentation.view.gist.GistEditView;
import rx.observers.TestSubscriber;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class GistEditViewTest {
    private static final String TEXT_EMPTY = "";
    private static final String TEXT_SET = "dummy_text";
    private static final String FAKE_GIST_ID = "fake_gist_id";

    private GistEditView sut;
    private EditText mGistTitleEt;
    private EditText mFileNameEt;
    private EditText mFileContentEt;

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getTargetContext();

        sut = (GistEditView) LayoutInflater.from(context).inflate(R.layout.view_gist_edit, null);
        mGistTitleEt = (EditText) sut.findViewById(R.id.edit_gist_title);
        mFileNameEt = (EditText) sut.findViewById(R.id.edit_file_name);
        mFileContentEt = (EditText) sut.findViewById(R.id.edit_file_content);
    }

    @Test
    public void bind_titleIsSetGivenTitle_whenGivenTitleIsSet() {
        String gistTitle = "gist_title";

        Gist gist = defaultGist(FAKE_GIST_ID);
        gist.title = gistTitle;

        sut.bind(gist);

        assertThat(mGistTitleEt.getText().toString(), is(gistTitle));
    }

    @Test
    public void bind_titleIsSetIdWithPrefixGist_whenTitleIsEmpty() {
        Gist gist = defaultGist(FAKE_GIST_ID);
        gist.title = "";

        sut.bind(gist);

        String expected = "gist:" + FAKE_GIST_ID;
        assertThat(mGistTitleEt.getText().toString(), is(expected));
    }

    @Test
    public void bind_fileIsSetGivenFile_whenFileListIsSet() {
        //setup
        String fileName = "file_name";
        String contentName = "content_name";

        Gist gist = defaultGist(FAKE_GIST_ID);
        File file = new File();
        file.name = fileName;
        file.content = contentName;
        gist.fileList.add(file);

        //exercise
        sut.bind(gist);

        //verify
        assertThat(mFileNameEt.getText().toString(),    is(fileName));
        assertThat(mFileContentEt.getText().toString(), is(contentName));
    }

    @Test
    public void bind_fileIsEmpty_whenFileListIsEmpty() {
        Gist gist = defaultGist(FAKE_GIST_ID);
        gist.fileList = new ArrayList<>();

        //exercise
        sut.bind(gist);

        //verify
        assertThat(mFileNameEt.getText().toString(),    is(""));
        assertThat(mFileContentEt.getText().toString(), is(""));
    }

    /**
     * Git Title    -> empty
     * File Content -> empty
     * isValid      -> false
     */
    @Test
    public void isValid_emitFalse_whenGitTitleAndFileContentAreEmpty() {
        TestSubscriber<Boolean> testSub = new TestSubscriber<>();
        sut.isValid().subscribe(testSub);

        mGistTitleEt.setText(TEXT_EMPTY);
        mFileContentEt.setText(TEXT_EMPTY);

        Boolean lastItem = lastEmittedItem(testSub);
        assertThat(lastItem, is(false));
    }

    /**
     * Git Title    -> empty
     * File Content -> set
     * isValid      -> false
     */
    @Test
    public void isValid_emitFalse_whenGitTitleIsSetButFileContentIsEmpty() {
        TestSubscriber<Boolean> testSub = new TestSubscriber<>();
        sut.isValid().subscribe(testSub);

        mGistTitleEt.setText(TEXT_EMPTY);
        mFileContentEt.setText(TEXT_SET);

        Boolean lastItem = lastEmittedItem(testSub);
        assertThat(lastItem, is(false));
    }

    /**
     * Git Title    -> set
     * File Content -> set
     * isValid      -> true
     */
    @Test
    public void isValid_emitTrue_whenGitTitleAndFileContentAreSet() {
        TestSubscriber<Boolean> testSub = new TestSubscriber<>();
        sut.isValid().subscribe(testSub);

        mGistTitleEt.setText(TEXT_SET);
        mFileContentEt.setText(TEXT_SET);

        Boolean lastItem = lastEmittedItem(testSub);
        assertThat(lastItem, is(true));
    }

    private Gist defaultGist(String id) {
        Gist gist = new Gist(id);
        gist.title = "";
        gist.fileList = new ArrayList<>();

        return gist;
    }

    private <T> T lastEmittedItem(TestSubscriber<T> sub) {
        List<T> emitList = sub.getOnNextEvents();
        return emitList.get(emitList.size() - 1);
    }

}