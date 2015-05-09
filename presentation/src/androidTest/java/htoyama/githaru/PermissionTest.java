package htoyama.githaru;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.test.InstrumentationRegistry;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import htoyama.githaru.presentation.BuildConfig;

import static junit.framework.Assert.assertFalse;

/**
 * Check Permisson.
 */
public class PermissionTest {

    @Ignore("ignore while not exit product flavors expected for spoon")
    @Test
    public void checkPermissino_if() {
        if (BuildConfig.BUILD_TYPE.equals("debug")) {
            return;
        }

        String writeExternalStorage = "android.permission.WRITE_EXTERNAL_STORAGE";
        assertFalse(hasPermission(writeExternalStorage));

        String disableKeyGurad = "android.permission.DISABLE_KEYGUARD";
        assertFalse(hasPermission(disableKeyGurad));

        String wakeLock = "android.permission.WAKE_LOCK";
        assertFalse(hasPermission(wakeLock));
    }

    private boolean hasPermission(String checkPermission) {
        Context context = InstrumentationRegistry.getTargetContext();

        List<String> setPermissions = getPermissionList(context);

        if (setPermissions == null) {
            return false;
        }

        if (setPermissions.contains(checkPermission)) {
            return true;
        }

        return false;
    }

    private List<String> getPermissionList(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = null;

        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }

        return Arrays.asList(packageInfo.requestedPermissions);
    }
}
