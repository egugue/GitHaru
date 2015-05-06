package htoyama.githaru.presentation;

import com.facebook.stetho.Stetho;

/**
 * {@link GitharuApp} for debug build.
 */
public class DebugGitharApp extends GitharuApp {

    @Override
    public void onCreate() {
        super.onCreate();
        setupStetho();
    }

    private void setupStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());

    }

}
