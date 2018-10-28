package ch.beerpro.data.repositories;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

import ch.beerpro.domain.models.FridgeContent;

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

}
