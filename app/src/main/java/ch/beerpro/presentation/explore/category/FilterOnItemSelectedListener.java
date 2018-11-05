package ch.beerpro.presentation.explore.category;

import android.view.View;
import ch.beerpro.domain.models.Beer;

public interface FilterOnItemSelectedListener {
    void onItemSelected(View animationSource, Beer item);
}
