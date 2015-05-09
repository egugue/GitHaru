package htoyama.githaru;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.test.InstrumentationRegistry;

import org.junit.Ignore;
import org.junit.Test;

import htoyama.githaru.presentation.BuildConfig;

import static junit.framework.Assert.assertFalse;
import static android.Manifest.permission.*;

/**
 * Check Permission.
 */
public class PermissionTest {

    @Ignore("ignore while not exit product flavors expected for spoon")
    @Test
    public void checkPermissino_if() {
        if (BuildConfig.BUILD_TYPE.equals("debug")) {
            return;
        }

        String target;

        target = WRITE_EXTERNAL_STORAGE;
        assertFalse(hasPermission(target));

        target = DISABLE_KEYGUARD;
        assertFalse(hasPermission(target));

        target = WAKE_LOCK;
        assertFalse(hasPermission(target));
    }

    private boolean hasPermission(String checkPermission) {
        Context context = InstrumentationRegistry.getTargetContext();
        int result = context.checkCallingOrSelfPermission(checkPermission);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}
