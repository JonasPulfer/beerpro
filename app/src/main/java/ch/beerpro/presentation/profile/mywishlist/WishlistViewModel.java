package ch.beerpro.presentation.profile.mywishlist;

import android.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import ch.beerpro.data.repositories.BeersRepository;
import ch.beerpro.data.repositories.CurrentUser;
import ch.beerpro.data.repositories.FridgeRepository;
import ch.beerpro.data.repositories.WishlistRepository;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.FridgeContent;
import ch.beerpro.domain.models.Wish;
import com.google.android.gms.tasks.Task;

import java.util.List;

public class WishlistViewModel extends ViewModel implements CurrentUser {

    private static final String TAG = "WishlistViewModel";

    private final MutableLiveData<String> currentUserId = new MutableLiveData<>();
    private final WishlistRepository wishlistRepository;
    private final BeersRepository beersRepository;
    private final FridgeRepository fridgeRepository;


    public WishlistViewModel() {
        wishlistRepository = new WishlistRepository();
        beersRepository = new BeersRepository();
        fridgeRepository = new FridgeRepository();

        currentUserId.setValue(getCurrentUser().getUid());
    }

    public LiveData<List<Pair<Wish, Beer>>> getMyWishlistWithBeers() {
        return wishlistRepository.getMyWishlistWithBeers(currentUserId, beersRepository.getAllBeers());
    }

    public Task<Void> toggleItemInWishlist(String itemId) {
        return wishlistRepository.toggleUserWishlistItem(getCurrentUser().getUid(), itemId);
    }

    public Task<Void> toggleUserFridgeItem(String beerId){
        return fridgeRepository.toggleUserFridgeItem(getCurrentUser().getUid(), beerId);
    }

}