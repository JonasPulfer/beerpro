package ch.beerpro.domain.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.widget.Toolbar;

import androidx.core.content.ContextCompat;
import ch.beerpro.R;

public class ThemeStateService extends Object {

    public static void changeToTheme(Activity activity, ThemeState theme) {
        SharedPreferences.Editor sharedPreferencesEditor = activity.getSharedPreferences("sharedPreferences", 0).edit();
        sharedPreferencesEditor.putString("theme", theme == ThemeState.DEFAULT ? "default" : "dark");
        sharedPreferencesEditor.apply();
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    public static void setThemeForActivity(Activity activity) {
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
            toolbar.getContext().setTheme(R.style.ToolBarStyle);
            toolbar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Light);
        } else {
            toolbar.getContext().setTheme(R.style.ToolBarStyle_Dark);
            toolbar.setPopupTheme(R.style.ThemeOverlay_AppCompat_Dark);
        }
    }

    public static ThemeState getCurrentTheme(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("sharedPreferences", 0);
        String currentThemeString = sharedPreferences.getString("theme", "default");

        return currentThemeString.equals("default") ? ThemeState.DEFAULT : ThemeState.DARK;
    }
}
