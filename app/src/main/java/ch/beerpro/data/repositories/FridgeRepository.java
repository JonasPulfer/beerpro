package ch.beerpro.data.repositories;

import android.util.Pair;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import androidx.lifecycle.LiveData;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.Entity;
import ch.beerpro.domain.models.FridgeContent;
import ch.beerpro.domain.utils.FirestoreQueryLiveData;
import ch.beerpro.domain.utils.FirestoreQueryLiveDataArray;

import static androidx.lifecycle.Transformations.map;
import static androidx.lifecycle.Transformations.switchMap;
import static ch.beerpro.domain.utils.LiveDataExtensions.combineLatest;

public class FridgeRepository {

   private static LiveData<List<FridgeContent>> getAllFridgeContentInstancesForUser(String userId) {
       return new FirestoreQueryLiveDataArray<>(FirebaseFirestore.getInstance().collection(FridgeContent.COLLECTION)
               .orderBy(FridgeContent.FIELD_ADDED_AT, Query.Direction.DESCENDING)
               .whereEqualTo(FridgeContent.FIELD_USER_ID, userId), FridgeContent.class);

   }

    private static LiveData<FridgeContent> getSpecificFridgeContent(Pair<String, Beer> input){
        String userId = input.first;
        Beer beer = input.second;
        DocumentReference document = FirebaseFirestore.getInstance()
                .collection(FridgeContent.COLLECTION)
                .document(FridgeContent.generateId(userId, beer.getId()));
        return new FirestoreQueryLiveData<>(document, FridgeContent.class);
    }

    public Task<Void> toggleUserFridgeItem(String userId, String itemId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String fridgeContentId = FridgeContent.generateId(userId, itemId);

        DocumentReference fridgeContentQuery = db.collection(FridgeContent.COLLECTION).document(fridgeContentId);

        return fridgeContentQuery.get().continueWithTask(task -> {
            if (task.isSuccessful() && task.getResult().exists()) {
                return fridgeContentQuery.delete();
            } else if (task.isSuccessful()) {
                return fridgeContentQuery.set(new FridgeContent(new Date(), userId, itemId));
            } else {
                throw task.getException();
            }
        });
    }


    public LiveData<List<Pair<FridgeContent, Beer>>> getWholeFridgeWithBeers(LiveData<String> currentUserId,
                                                                          LiveData<List<Beer>> allBeers){
        return map(combineLatest(getAllFridgeContentInstancesForUser(currentUserId), map(allBeers, Entity::entitiesById)),
                input -> {
                    List<FridgeContent> wholeContent = input.first;
                    HashMap<String, Beer> beersById = input.second;

                    ArrayList<Pair<FridgeContent, Beer>> result = new ArrayList<>();
                    for (FridgeContent content: wholeContent){
                        Beer beer = beersById.get(content.getBeerId());
                        result.add(Pair.create(content, beer));
                    }
                    return result;
                });
    }

    public LiveData<List<FridgeContent>> getAllFridgeContentInstancesForUser(LiveData<String> currentUserId){
        return switchMap(currentUserId, FridgeRepository::getAllFridgeContentInstancesForUser);
    }


    public LiveData<FridgeContent> getSpecificFridgeContent(LiveData<String> currentUserId, LiveData<Beer> beer){

        return switchMap(combineLatest(currentUserId, beer), FridgeRepository::getSpecificFridgeContent);
    }


}
