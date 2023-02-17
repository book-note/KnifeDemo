package io.github.mthli.knife.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;

import io.github.mthli.knife.Constant;

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

    public static int optimizeDarkMode(Context context, int markBackgroundColor) {
        boolean isDarkMode = ThemeUtil.isNightMode(context);
        if (!isDarkMode) {
            return markBackgroundColor;
        }
        Integer darkModeColor = Constant.LIGHT_TO_DARK_COLOR_MAP.get(markBackgroundColor);
        if (darkModeColor == null) {
            return Constant.DEFAULT_MARK_BACKGROUND_COLOR;
        } else {
            return darkModeColor;
        }
    }
}
