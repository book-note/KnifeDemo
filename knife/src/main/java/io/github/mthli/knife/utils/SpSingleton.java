package io.github.mthli.knife.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

public class SpSingleton {
    private static SpSingleton singleton;
    private SharedPreferences sp;

    private SpSingleton(Context context) {
        sp = context.getSharedPreferences(
                context.getPackageName() + "_preferences", Context.MODE_PRIVATE);
    }

    public static SpSingleton getInstance(Context context) {
        if (singleton == null) {
            singleton = new SpSingleton(context);
        }
        return singleton;
    }

    public int getAppTheme() {
        int appTheme = sp.getInt("appTheme", ThemeUtil.LIGHT);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q && appTheme == ThemeUtil.FOLLOW_SYSTEM) {
            appTheme = ThemeUtil.LIGHT;
        }
        return appTheme;
    }
}
