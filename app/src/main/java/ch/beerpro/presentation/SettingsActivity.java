package ch.beerpro.presentation;

import androidx.appcompat.app.AppCompatActivity;
import ch.beerpro.R;

import android.os.Bundle;
import android.widget.CheckBox;
import androidx.appcompat.widget.Toolbar;
import ch.beerpro.domain.utils.ThemeUtils;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeUtils.onActivityCreateSetTheme(this);

        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        CheckBox darkmodeCheckbox = findViewById(R.id.checkbox_darkmode);

        darkmodeCheckbox.setChecked(ThemeUtils.getCurrentTheme() == 1);

        darkmodeCheckbox.setOnCheckedChangeListener((view, value) -> {
            switchStyleToDark(value);
        });
    }

    private void switchStyleToDark(boolean dark) {
        ThemeUtils.changeToTheme(this, dark ? ThemeUtils.DARK : ThemeUtils.DEFAULT);
    }
}
