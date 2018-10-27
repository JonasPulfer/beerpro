package ch.beerpro.presentation;

import androidx.appcompat.app.AppCompatActivity;
import ch.beerpro.R;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import ch.beerpro.domain.utils.ThemeState;
import ch.beerpro.domain.utils.ThemeStateService;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeStateService.setThemeForActivity(this);

        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        CheckBox darkmodeCheckbox = findViewById(R.id.checkbox_darkmode);

        darkmodeCheckbox.setChecked(ThemeStateService.getCurrentTheme() == ThemeState.DARK);

        darkmodeCheckbox.setOnCheckedChangeListener((view, dark) -> {
            //ThemeStateService.changeToTheme(this, dark ? ThemeState.DARK : ThemeState.DEFAULT);
            Toast.makeText(this, "UNDER CONSTRUCTION", Toast.LENGTH_LONG).show();
        });
    }
}
