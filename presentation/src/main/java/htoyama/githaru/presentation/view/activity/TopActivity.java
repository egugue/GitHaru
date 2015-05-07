package htoyama.githaru.presentation.view.activity;

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
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class TopActivity extends BaseActivity {
    private GistAdapter mListAdapter;
    private GistComponent mGistComponent;
    private DrawerLayout mDrawerLayout;

    @Inject
    GetGistList mGetGistList;

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
                        mListAdapter.setItemList(gists);
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

        RecyclerView list = (RecyclerView) findViewById(R.id.gist_list);
        list.setAdapter(mListAdapter);
        list.setLayoutManager(new LinearLayoutManager(this));
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
