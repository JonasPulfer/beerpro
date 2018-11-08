package ch.beerpro.presentation.explore.category;

import android.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import ch.beerpro.data.repositories.BeersRepository;
import ch.beerpro.domain.models.Beer;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static androidx.lifecycle.Transformations.map;
import static ch.beerpro.domain.utils.LiveDataExtensions.zip;

public class CategoryViewModel extends ViewModel {

    private final BeersRepository beersRepository;
    private final MutableLiveData<String> searchTerm = new MutableLiveData<>();
    private final LiveData<List<Beer>> filteredBeers;

    public CategoryViewModel() {
        beersRepository = new BeersRepository();
        filteredBeers = map(zip(searchTerm, getAllBeers()), CategoryViewModel::filter);
    }

    private static List<Beer> filter(Pair<String, List<Beer>> input) {
        String category = input.first;
        List<Beer> allBeers = input.second;
        if (Strings.isNullOrEmpty(category)) {
            return allBeers;
        }
        if (allBeers == null) {
            return Collections.emptyList();
        }
        ArrayList<Beer> filtered = new ArrayList<>();
        for (Beer beer : allBeers) {
            if (beer.getCategory().toLowerCase().contains(category.toLowerCase())) {
                filtered.add(beer);
            }
        }
        return filtered;
    }

    public LiveData<List<Beer>> getAllBeers() {
        return beersRepository.getAllBeers();
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm.setValue(searchTerm);
    }

    public LiveData<List<Beer>> getFilteredBeers() {
        return filteredBeers;
    }

}