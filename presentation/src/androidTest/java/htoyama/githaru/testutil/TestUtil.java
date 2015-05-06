package htoyama.githaru.testutil;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;

import htoyama.githaru.presentation.GitharuApp;

/**
 * Utility class for testing
 */
public class TestUtil {

    /**
     * Get {@link GitharuApp}
     */
    public static GitharuApp getApplication() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        GitharuApp app
                = (GitharuApp) instrumentation.getTargetContext().getApplicationContext();

        return app;
    }
}
