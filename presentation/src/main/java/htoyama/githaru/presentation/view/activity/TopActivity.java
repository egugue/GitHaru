package htoyama.githaru.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import htoyama.githaru.domain.entity.Gist;
import htoyama.githaru.domain.usecase.gist.GetGistList;
import htoyama.githaru.presentation.GitharuApp;
import htoyama.githaru.presentation.R;
import htoyama.githaru.presentation.internal.di.DaggerGistComponent;
import htoyama.githaru.presentation.internal.di.GistComponent;
import htoyama.githaru.presentation.view.adapter.GistAdapter;
import htoyama.githaru.presentation.view.widget.recyclerview.SlideInItemAnimator;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class TopActivity extends BaseActivity {
    private static final String EXTRA_EDIT_COMPLETED = "edit_completed";

    private RecyclerView mGistListView;
    private GistAdapter mListAdapter;
    private GistComponent mGistComponent;
    private DrawerLayout mDrawerLayout;

    @Inject
    GetGistList mGetGistList;

    /**
     * Create intent for animating a latest gist.
     * <p>
     * If given isEditCompleted param is true, a latest gist is be animated when list of gist have been loaded.
     */
    public static Intent createIntent(Context context, boolean isEditCompleted) {
        Intent intent = new Intent(context, TopActivity.class);
        intent.putExtra(EXTRA_EDIT_COMPLETED, isEditCompleted);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        setupComponent();
        mGistComponent.inject(this);
        setupList();
        setupNavDrawer();
        setupFab();

        Subscription sub = bind(mGetGistList.execute("egugue"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Gist>>() {
                    @Override
                    public void call(List<Gist> gists) {
                        bindToGistList(gists);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });

        addSubscription(sub);
    }

    private void setupFab() {
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(GistEditActivity
                        .createIntent(getApplicationContext()));
            }
        });
    }

    //TODO: inject
    private void setupComponent() {
        mGistComponent = DaggerGistComponent.builder()
                .appComponent(GitharuApp.get(this).appComponent())
                .build();
    }

    private void setupList() {
        mListAdapter = new GistAdapter(this);
        mListAdapter.setOnItemClickListener(new GistAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Gist gist) {
                Intent intent = GistEditActivity.createIntent(
                        getApplicationContext(), gist.id);
                startActivity(intent);
            }
        });

        mGistListView = (RecyclerView) findViewById(R.id.gist_list);
        mGistListView.setAdapter(mListAdapter);
        mGistListView.setLayoutManager(new LinearLayoutManager(this));
        mGistListView.setItemAnimator(new SlideInItemAnimator());
        mGistListView.getItemAnimator().setAddDuration(500);
        mGistListView.getItemAnimator().setMoveDuration(500);
    }

    private void setupNavDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.nav_drawer);
        mDrawerLayout.setStatusBarBackgroundColor(
                getResources().getColor(R.color.theme_accent));

        Toolbar toolbar = getToolbar();
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }

    private void bindToGistList(List<Gist> gists) {
        if (!isEditComplete()) {
            mListAdapter.setItemList(gists);
            return;
        }

        final Gist gist = gists.get(0);
        gists.remove(0);
        mListAdapter.setItemList(gists);

        //do slide-in animation to latest gist.
        Subscription sub = Observable.just(gist)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Gist>() {
                    @Override
                    public void call(Gist gist) {
                        mListAdapter.add(gist, 0);
                        mGistListView.scrollToPosition(0);
                    }
                });
        addSubscription(sub);
    }

    private boolean isEditComplete() {
        final boolean defaultValue = false;

        Intent intent = getIntent();
        if (intent == null) {
            return defaultValue;
        }

        return intent.getBooleanExtra(EXTRA_EDIT_COMPLETED, defaultValue);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
            mDrawerLayout.closeDrawer(Gravity.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top, menu);
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

}
