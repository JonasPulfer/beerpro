package ch.beerpro.domain.utils;

import android.app.Activity;
import android.content.Intent;

import ch.beerpro.R;

public class ThemeUtils extends Object {
    private static int currentTheme;

    public final static int DEFAULT = 0;
    public final static int DARK = 1;

    public static void changeToTheme(Activity activity, int theme) {
        currentTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    public static void onActivityCreateSetTheme(Activity activity) {
        if(currentTheme == 1) {
            activity.setTheme(R.style.DarkTheme);
        } else {
            activity.setTheme(R.style.AppTheme);
        }
    }

    public static int getCurrentTheme() {
        return currentTheme;
    }
}
