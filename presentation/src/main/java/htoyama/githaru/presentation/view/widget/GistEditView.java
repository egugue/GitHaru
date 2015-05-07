package htoyama.githaru.presentation.view.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import htoyama.githaru.domain.entity.File;
import htoyama.githaru.domain.entity.Gist;
import htoyama.githaru.presentation.R;
import rx.Observable;
import rx.functions.Func2;
import rx.subjects.BehaviorSubject;

/**
 *
 */
public class GistEditView extends LinearLayout {
    private EditText mGistTitleEt;
    private EditText mFileNameEt;
    private EditText mFileContentEt;
    private Gist mGist;

    public GistEditView(Context context) {
        this(context, null);
    }

    public GistEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    public void bind(Gist gist) {
        mGist = gist;

        //setup gist title
        String gistTitle = TextUtils.isEmpty(gist.title)
                ? "gist:" + gist.id
                : gist.title;
        mGistTitleEt.setText(gistTitle);

        //setup file
        String fileName = "";
        String fileContent = "";
        if (gist.fileList.size() > 0) {
            File file = gist.fileList.get(0);
            fileName = file.name;
            fileContent = file.content;
        }

        mFileNameEt.setText(fileName);
        mFileContentEt.setText(fileContent);
    }

    public Gist getGist() {
        if (mGist != null) {
            mGist.title = mGistTitleEt.getText().toString();
            mGist.fileList.get(0).name = mFileNameEt.getText().toString();
            mGist.fileList.get(0).content = mFileContentEt.getText().toString();
            return mGist;
        }

        //TODO: stub
        Gist gist = new Gist(Gist.NO_ASSINGED_ID);
        gist.title = "hoge";

        File file = new File();
        file.content = "test gist content";
        file.name = "test title";
        gist.fileList.add(file);
        return gist;
    }

    public Observable<Boolean> isValid() {
        final Observable<String> gistTitle   = text(mGistTitleEt);
        final Observable<String> fileContent = text(mFileContentEt);

        final Observable<Boolean> isValidAll;
        isValidAll = Observable.combineLatest(gistTitle, fileContent,
                new Func2<String, String, Boolean>() {
                    @Override
                    public Boolean call(String gistTitle, String fileContent) {
                        if (TextUtils.isEmpty(gistTitle)) {
                            return false;
                        }
                        if (TextUtils.isEmpty(fileContent)) {
                            return false;
                        }
                        return true;
                    }
                });

        return isValidAll;
    }

    @Override
    protected void onFinishInflate() {
        mGistTitleEt = (EditText) findViewById(R.id.edit_gist_title);
        mFileNameEt = (EditText) findViewById(R.id.edit_file_name);
        mFileContentEt = (EditText) findViewById(R.id.edit_file_content);
    }

    public static Observable<String> text(TextView view) {
        String currentText = String.valueOf(view.getText());
        final BehaviorSubject<String> subject = BehaviorSubject.create(currentText);
        view.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) { }
            @Override public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) { }

            @Override
            public void afterTextChanged(Editable editable) {
                subject.onNext(editable.toString());
            }
        });
        return subject;
    }

}
