package ch.beerpro.presentation;

import androidx.appcompat.app.AppCompatActivity;
import ch.beerpro.R;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        CheckBox darkmodeCheckbox = findViewById(R.id.checkbox_darkmode);
        darkmodeCheckbox.setOnCheckedChangeListener((view, value) -> {
            switchStyleToDark(value);
        });
    }

    private void switchStyleToDark(boolean dark) {
        Toast.makeText(getApplicationContext(), "Theme will be changed to " + (dark ? "'dark'" : "'light'"), Toast.LENGTH_LONG).show();
    }
}
