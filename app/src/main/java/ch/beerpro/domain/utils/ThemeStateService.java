package ch.beerpro.domain.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;

import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;
import ch.beerpro.R;

public class ThemeStateService extends Object {
    private static Activity mainActivity;

    public static void changeToTheme(Activity activity, ThemeState theme) {
        SharedPreferences.Editor sharedPreferencesEditor = activity.getSharedPreferences("sharedPreferences", 0).edit();
        sharedPreferencesEditor.putString("theme", theme == ThemeState.DEFAULT ? "default" : "dark");
        sharedPreferencesEditor.apply();
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));

        if(activity.getClass().equals("MainActivity") && mainActivity == null) {
            mainActivity = activity;
        }
    }

    public static void setThemeForActivity(Activity activity) {
        if(activity.getClass().getName().equals("ch.beerpro.presentation.MainActivity") && mainActivity == null) {
            mainActivity = activity;
        }

        SharedPreferences sharedPreferences = activity.getSharedPreferences("sharedPreferences", 0);
        String currentThemeString = sharedPreferences.getString("theme", "default");

        if(currentThemeString.equals("default")) {
            activity.setTheme(R.style.AppTheme);
        } else {
            activity.setTheme(R.style.AppTheme_Dark);
        }
    }

    public static void setThemeForToolbar(Toolbar toolbar) {
        SharedPreferences sharedPreferences = toolbar.getContext().getSharedPreferences("sharedPreferences", 0);
        String currentThemeString = sharedPreferences.getString("theme", "default");

        if(currentThemeString.equals("default")) {
            toolbar.setBackgroundColor(toolbar.getContext().getResources().getColor(R.color.colorPrimary));
            toolbar.setTitleTextColor(toolbar.getContext().getResources().getColor(R.color.colorAccent));
            toolbar.getContext().setTheme(R.style.ToolBarStyle);
            toolbar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Light);
            if(toolbar.getNavigationIcon() != null) {
                toolbar.getNavigationIcon().setColorFilter(toolbar.getContext().getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            }
        } else {
            toolbar.setBackgroundColor(toolbar.getContext().getResources().getColor(R.color.colorPrimary_dark));
            toolbar.setTitleTextColor(toolbar.getContext().getResources().getColor(R.color.colorAccent_dark));
            toolbar.getContext().setTheme(R.style.ToolBarStyle_Dark);
            toolbar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Dark);
            if(toolbar.getNavigationIcon() != null) {
                toolbar.getNavigationIcon().setColorFilter(toolbar.getContext().getResources().getColor(R.color.colorAccent_dark), PorterDuff.Mode.SRC_ATOP);
            }
        }
    }

    public static ThemeState getCurrentTheme(Activity activity) {
        if(activity.getClass().getName().equals("ch.beerpro.presentation.MainActivity") && mainActivity == null) {
            mainActivity = activity;
        }

        SharedPreferences sharedPreferences = activity.getSharedPreferences("sharedPreferences", 0);
        String currentThemeString = sharedPreferences.getString("theme", "default");

        return currentThemeString.equals("default") ? ThemeState.DEFAULT : ThemeState.DARK;
    }

    public static ThemeState getCurrentTheme() {
        return getCurrentTheme(mainActivity);
    }
}
