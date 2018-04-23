package com.practice.themepractice;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ThemeUtil {
    public static final String PREF_KEY_APP_THEME = "PREF_KEY_APP_THEME";

    public static void switchToTheme(Activity activity, int theme) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        sharedPreferences.edit().putInt(PREF_KEY_APP_THEME, theme).apply();
        Intent intent=new Intent(activity,activity.getClass());
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        activity.startActivity(intent);
        activity.overridePendingTransition(0,0);
    }

    public static void useTheme(Activity activity) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        int theme = sharedPreferences.getInt(PREF_KEY_APP_THEME, R.style.LightAppTheme);
        activity.setTheme(theme);
    }


}
