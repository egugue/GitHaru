package htoyama.githaru.presentation.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import htoyama.githaru.presentation.R;

/**
 * Base {@link android.app.Activity}.
 */
public class BaseActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setupToolbar();
    }

    /**
     * Get a {@link Toolbar} instance applied {@link #setSupportActionBar(Toolbar)}.
     *
     * @return {@link Toolbar}.
     * If can't find R.id.toolbar in layout, return null.
     */
    protected Toolbar getToolbar() {
        return mToolbar;
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

}
