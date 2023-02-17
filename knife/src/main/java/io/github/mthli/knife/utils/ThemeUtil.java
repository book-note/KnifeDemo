package io.github.mthli.knife.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;

public class ThemeUtil {
    public static final int DARK = 0;
    public static final int LIGHT = 1;
    public static final int FOLLOW_SYSTEM = 2;

    private ThemeUtil() {

    }

    public static boolean isNightMode(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return (context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES;
        } else {
            return SpSingleton.getInstance(context.getApplicationContext()).getAppTheme() == ThemeUtil.DARK;
        }
    }
}
