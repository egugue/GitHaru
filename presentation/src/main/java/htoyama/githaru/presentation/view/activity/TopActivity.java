package htoyama.githaru.presentation.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import htoyama.githaru.domain.entity.File;
import htoyama.githaru.domain.entity.Gist;
import htoyama.githaru.domain.entity.Repository;
import htoyama.githaru.domain.repository.GistRepository;
import htoyama.githaru.domain.repository.RepositoryRepository;
import htoyama.githaru.domain.usecase.gist.GetGistDetail;
import htoyama.githaru.presentation.GitharuApp;
import htoyama.githaru.presentation.R;
import htoyama.githaru.presentation.internal.di.ActivityModule;
import htoyama.githaru.presentation.internal.di.DaggerGistComponent;
import htoyama.githaru.presentation.internal.di.GistComponent;
import rx.Subscription;
import rx.android.app.AppObservable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class TopActivity extends BaseActivity {

    /*
    @Inject RepositoryRepository mRepository;
    @Inject GistRepository mGistRepository;
    */
    //TODO: remove
    RepositoryRepository mRepository;
    GistRepository mGistRepository;

    @Inject
    GetGistDetail mGetGistDetailUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        GistComponent component = DaggerGistComponent.builder()
                .appComponent(GitharuApp.get(this).appComponent())
                .activityModule(new ActivityModule(this))
                .build();

        component.inject(this);

        //getGistList();
        //getGist();

        Subscription sub;
        sub = AppObservable.bindActivity(this, mGetGistDetailUs.execute("b34506680e8f9f9c7340"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Gist>() {
                    @Override
                    public void call(Gist gist) {
                        Log.d("HOGE", gist.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

        setupCreateGist();
        setupEditGist();
        setupDeleteGist();
    }

    public void getRepositoryList() {
        Runnable command = new Runnable() {
            @Override
            public void run() {
                List<Repository> list = mRepository.getList("egugue");
                for (Repository repo : list) {
                    Log.d("HOGE", repo.toString());
                }
            }
        };

        new Thread(command).start();
    }

    public void setupCreateGist() {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                File file = new File();
                file.content = "test1";
                file.name = "title1";

                File file2 = new File();
                file2.content = "text2";
                file2.name = "title2";


                Gist gist = new Gist("-1");
                gist.fileList = Arrays.asList(file, file2);
                gist.isPublic = true;
                gist.description = "test";

                mGistRepository.create(gist);
            }
        };

        findViewById(R.id.create_gist_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(runnable).start();
            }
        });
    }

    public void setupEditGist() {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                File file = new File();
                file.content = "edit11";
                file.name = "edit1";

                File file2 = new File();
                file2.content = "edit22";
                file2.name = "edit2";

                Gist gist = new Gist("b34506680e8f9f9c7340");
                gist.fileList = Arrays.asList(file, file2);
                gist.isPublic = true;
                gist.description = "edit";

                mGistRepository.edit(gist);
            }
        };

        findViewById(R.id.edit_gist_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(runnable).start();
            }
        });
    }

    public void setupDeleteGist() {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mGistRepository.delete("8912fedab44d17cfc881");
            }
        };

        findViewById(R.id.delete_gist_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(runnable).start();
            }
        });
    }

    public void getGist() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Gist gist = mGistRepository.get("1cb6927d924efb0f51e1");
                Log.d("HOGE", gist.toString());
            }
        };

        new Thread(runnable).start();
    }

    public void getGistList() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                List<Gist> list = mGistRepository.getList("egugue");
                for (Gist gist : list) {
                    Log.d("HOGE", gist.toString());
                }

            }
        };

        new Thread(runnable).start();
    }

    public void deleteRepository() {
        /*
        findViewById(R.id.delete_gist_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Owner owner = new Owner("egugue");
                        Repository repo = new Repository("test", owner);
                        mRepository.delete(repo);
                    }
                }).start();
            }
        });
        */
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
