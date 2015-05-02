package htoyama.githaru.presentation;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import htoyama.githaru.R;
import htoyama.githaru.infra.entitiy.RepositoryEntity;
import htoyama.githaru.infra.net.GithubApi;
import retrofit.RestAdapter;


public class TopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);


        //TODO:
        final RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("https://api.github.com")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        Runnable command = new Runnable() {
            @Override
            public void run() {
                GithubApi api = adapter.create(GithubApi.class);
                List<RepositoryEntity> list = api.getRepositoryList("egugue");

                for (RepositoryEntity entity : list) {
                    Log.d("HOGE", entity.toString());
                }
            }
        };

        new Thread(command).start();


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
