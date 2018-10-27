package ch.beerpro.domain.utils;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.widget.Toolbar;

import ch.beerpro.R;

public class ThemeStateService extends Object {
    private static ThemeState currentTheme = ThemeState.DEFAULT;

    public static void changeToTheme(Activity activity, ThemeState theme) {
        currentTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    public static void setThemeForActivity(Activity activity) {
        if(currentTheme == ThemeState.DEFAULT) {
            activity.setTheme(R.style.AppTheme);
        } else {
            //activity.setTheme(R.style.DarkTheme);
            activity.setTheme(R.style.AppTheme);
        }
    }

    /*public static void setThemeForToolbar(Toolbar toolbar) {
        if(currentTheme == ThemeState.DEFAULT) {
            toolbar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Light);
        } else {
            toolbar.setTitleTextColor(0xFFFDD835);
            toolbar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Dark);
        }
    }*/

    public static ThemeState getCurrentTheme() {
        return currentTheme;
    }
}
