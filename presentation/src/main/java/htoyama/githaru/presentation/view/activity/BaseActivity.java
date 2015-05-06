package htoyama.githaru.presentation.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import htoyama.githaru.presentation.R;
import rx.Observable;
import rx.Subscription;
import rx.android.app.AppObservable;
import rx.subscriptions.CompositeSubscription;

/**
 * Base {@link android.app.Activity}.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private CompositeSubscription mSubscriptions;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setupToolbar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubscriptions.clear();
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

    protected <T> Observable<T> bind(Observable<T> source) {
        return AppObservable.bindActivity(this, source);
    }

    protected void addSubscription(Subscription sub) {
        mSubscriptions.add(sub);
    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

}
