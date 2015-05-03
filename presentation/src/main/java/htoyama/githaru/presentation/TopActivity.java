package htoyama.githaru.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import htoyama.githaru.R;
import htoyama.githaru.domain.entity.File;
import htoyama.githaru.domain.entity.Gist;
import htoyama.githaru.domain.entity.Owner;
import htoyama.githaru.domain.entity.Repository;
import htoyama.githaru.domain.repository.GistRepository;
import htoyama.githaru.domain.repository.RepositoryRepository;


public class TopActivity extends AppCompatActivity {

    @Inject
    RepositoryRepository mRepository;

    @Inject
    GistRepository mGistRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        AppComponent component = DaggerAppComponent.builder()
                .build();

        component.inject(this);

        //getGistList();
        //getGist();

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
