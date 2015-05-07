package htoyama.githaru.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import htoyama.githaru.presentation.R;
import htoyama.githaru.presentation.view.widget.GistEditView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.view.ViewObservable;
import rx.android.widget.OnTextChangeEvent;
import rx.android.widget.WidgetObservable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.subjects.BehaviorSubject;

public class GistEditActivity extends BaseActivity {

    private EditText mGistTitleEt;
    private EditText mFileNameEt;
    private EditText mFileContentEt;
    private Button mSaveButton;

    public static Intent createIntent(Context context) {
        return new Intent(context, GistEditActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gist_edit);

        /*
        mGistTitleEt = (EditText) findViewById(R.id.edit_gist_title);
        mFileNameEt = (EditText) findViewById(R.id.edit_file_name);
        mFileContentEt = (EditText) findViewById(R.id.edit_file_content);

        createObservable();
        */
        mSaveButton = (Button) findViewById(R.id.edit_save);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "save", Toast.LENGTH_SHORT).show();
            }
        });

        GistEditView editView = (GistEditView) findViewById(R.id.gist_edit_view);
        editView.isValid().subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean isValid) {
                mSaveButton.setEnabled(isValid);
            }
        });
    }

    private void createObservable() {
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

        isValidAll.subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(final Boolean isValid) {
                        Log.d("HOGE", Boolean.toString(isValid));
                        mSaveButton.setEnabled(isValid);
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_gist_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
