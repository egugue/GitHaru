package htoyama.githaru.testutil;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;

import htoyama.githaru.presentation.GitharuApp;

/**
 * Created by toyamaosamuyu on 2015/05/05.
 */
public class TestUtil {

    public static GitharuApp getApp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        GitharuApp app
                = (GitharuApp) instrumentation.getTargetContext().getApplicationContext();

        return app;
    }
}
