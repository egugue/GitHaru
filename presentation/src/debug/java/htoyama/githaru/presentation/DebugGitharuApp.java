package htoyama.githaru.presentation;

import android.app.Application;
import android.app.KeyguardManager;
import android.os.PowerManager;

import com.facebook.stetho.Stetho;

import static android.os.PowerManager.ACQUIRE_CAUSES_WAKEUP;
import static android.os.PowerManager.FULL_WAKE_LOCK;
import static android.os.PowerManager.ON_AFTER_RELEASE;

/**
 * {@link GitharuApp} for debug build.
 */
public class DebugGitharuApp extends GitharuApp {

    @Override
    public void onCreate() {
        super.onCreate();
        setupStetho();
        wakeUpDevice();
    }

    private void setupStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());

    }

    private void wakeUpDevice() {
        Runnable command = new Runnable() {
            @Override public void run() {
                Application app = (Application) getApplicationContext();
                String simpleName = app.getClass().getSimpleName();

                // Unlock the device so that the tests can input keystrokes.
                ((KeyguardManager) app.getSystemService(KEYGUARD_SERVICE)) //
                        .newKeyguardLock(simpleName) //
                        .disableKeyguard();
                // Wake up the screen.
                ((PowerManager) app.getSystemService(POWER_SERVICE)) //
                        .newWakeLock(FULL_WAKE_LOCK | ACQUIRE_CAUSES_WAKEUP | ON_AFTER_RELEASE, simpleName) //
                        .acquire();
            }
        };

        command.run();
    }
}
