package htoyama.githaru.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import javax.inject.Inject;

import htoyama.githaru.domain.entity.Gist;
import htoyama.githaru.domain.usecase.gist.EditGist;
import htoyama.githaru.domain.usecase.gist.GetGistDetail;
import htoyama.githaru.presentation.GitharuApp;
import htoyama.githaru.presentation.R;
import htoyama.githaru.presentation.internal.di.DaggerGistComponent;
import htoyama.githaru.presentation.internal.di.GistComponent;
import htoyama.githaru.presentation.view.widget.GistEditView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class GistEditActivity extends BaseActivity {
    private static final String EXTRA_GIST_ID = "gist_id";

    private GistComponent mGistComponent;
    private GistEditView mGistEditView;
    private Button mSaveButton;

    @Inject
    EditGist mEditGist;

    @Inject
    GetGistDetail mGetGistDetail;

    public static Intent createIntent(Context context) {
        return new Intent(context, GistEditActivity.class);
    }

    public static Intent createIntent(Context context, String gistId) {
        Intent intent = createIntent(context);
        intent.putExtra(EXTRA_GIST_ID, gistId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gist_edit);
        setupComponent();
        mGistComponent.inject(this);

        mGistEditView = (GistEditView) findViewById(R.id.gist_edit_view);
        mSaveButton = (Button) findViewById(R.id.edit_save);

        setupGistEditView();
        setupSaveButton();
    }

    private void setupGistEditView() {
        mGistEditView.isValid().subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean isValid) {
                mSaveButton.setEnabled(isValid);
            }
        });

        Observable<Gist> gist = getGistFromIntent();
        if (gist == null) {
            return;
        }

        gist.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Gist>() {
                    @Override
                    public void call(Gist gist) {
                        mGistEditView.bind(gist);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    private void setupSaveButton() {
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editGist();
            }
        });
    }

    private Observable<Gist> getGistFromIntent() {
        Intent intent = getIntent();
        if (intent == null) {
            return null;
        }

        String gistId = intent.getStringExtra(EXTRA_GIST_ID);
        return mGetGistDetail.execute(gistId);
    }

    private void editGist() {
        Gist gist = mGistEditView.getGist();
        mEditGist.execute(gist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    @Override public void onNext(Void aVoid) {}
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

    private void setupComponent() {
        mGistComponent = DaggerGistComponent.builder()
                .appComponent(GitharuApp.get(this).appComponent())
                .build();
    }

}
