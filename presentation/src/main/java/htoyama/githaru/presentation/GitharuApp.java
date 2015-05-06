package htoyama.githaru.presentation;

import android.app.Application;
import android.content.Context;

/**
 * Main Application
 */
public class GitharuApp extends Application {
    private AppComponent mAppComponent;

    /**
     *  Gets {@link GitharuApp} instance.
     */
    public static GitharuApp get(Context context) {
        return (GitharuApp) context.getApplicationContext();
    }

    /**
     * Gets {@link AppComponent}.
     */
    public AppComponent appComponent() {
        return mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        buildComponent();
    }

    private void buildComponent() {
        mAppComponent = DaggerAppComponent.builder()
                .build();
    }

    //visible for testing
    public void setAppComponent(AppComponent component) {
        mAppComponent = component;
    }

}
