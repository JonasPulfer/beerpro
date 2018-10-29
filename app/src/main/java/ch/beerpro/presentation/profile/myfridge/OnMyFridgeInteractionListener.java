package ch.beerpro.presentation.profile.myfridge;

import android.widget.ImageView;

import ch.beerpro.domain.models.Beer;

public interface OnMyFridgeInteractionListener {
    void onMoreClickedListener(ImageView photo, Beer beer);
    void onRemoveButtonClickedListener(String userId, String beerId);
    void onIncreaseAmountClickedListener(int oldAmount, String userId, String beerId);
    void onDecreaseAmountClickedListener(int oldAmount, String userId, String beerId);
}
