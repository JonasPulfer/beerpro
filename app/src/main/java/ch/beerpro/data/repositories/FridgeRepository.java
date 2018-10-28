package ch.beerpro.data.repositories;

import android.util.Pair;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

import androidx.lifecycle.LiveData;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.FridgeContent;
import ch.beerpro.domain.utils.FirestoreQueryLiveData;

import static androidx.lifecycle.Transformations.switchMap;
import static ch.beerpro.domain.utils.LiveDataExtensions.combineLatest;

public class FridgeRepository {

//    public static LiveData<List<FridgeContent>> getRefrigeratorContent(String userId){
//        return new FirestoreQueryLiveDataArray<>(
//                FirebaseFirestore.getInstance().collection(FridgeContent.COLLECTION)
//                .orderBy(FridgeContent.FIELD_BEER_NAME, Query.Direction.DESCENDING)
//                .whereEqualTo(FridgeContent.FIELD_USER_ID, userId), FridgeContent.class);
//
//
//    }

    //toggle für Elemente anzupassen oder zu löschen --> siehe WishList

    public Task<Void> toggleUserFridgeItem(String userId, String itemId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String fridgeContentId = FridgeContent.generateId(userId, itemId);

        DocumentReference fridgeContentQuery = db.collection(FridgeContent.COLLECTION).document(fridgeContentId);

        return fridgeContentQuery.get().continueWithTask(task -> {
            if (task.isSuccessful() && task.getResult().exists()) {
                return fridgeContentQuery.delete();
            } else if (task.isSuccessful()) {
                return fridgeContentQuery.set(new FridgeContent(userId, itemId, new Date()));
            } else {
                throw task.getException();
            }
        });
    }

//    private static LiveData<FridgeContent> getUserFridgeFor (Pair<String, Beer> input){
//        String userId = input.first;
//        Beer beer = input.second;
//        DocumentReference document = FirebaseFirestore.getInstance().collection(FridgeContent.COLLECTION)
//                .document(FridgeContent.generateId(userId, beer.getId()));
//        return new FirestoreQueryLiveData<>(document, FridgeContent.class);
//    }


//    public LiveData<FridgeContent> getFridgeContentForBeer(LiveData<String> currentUserId, LiveData<Beer> beer){
//
//        return switchMap(combineLatest(currentUserId, beer), FridgeRepository::getUserFridgeFor );
//    }


}
