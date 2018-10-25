package ch.beerpro.data.repositories;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.List;

import androidx.lifecycle.LiveData;
import ch.beerpro.domain.models.RefrigeratorTypeOfBeer;
import ch.beerpro.domain.utils.FirestoreQueryLiveDataArray;

public class RefigeratorRepository {

    public static LiveData<List<RefrigeratorTypeOfBeer>> getRefrigeratorContent(String userId){
        return new FirestoreQueryLiveDataArray<>(
                FirebaseFirestore.getInstance().collection(RefrigeratorTypeOfBeer.COLLECTION)
                .orderBy(RefrigeratorTypeOfBeer.FIELD_BEER_NAME, Query.Direction.DESCENDING)
                .whereEqualTo(RefrigeratorTypeOfBeer.FIELD_USER_ID, userId), RefrigeratorTypeOfBeer.class);


    }

//    public static LiveData<List<Rating>> getRatingsByUser(String userId) {
//        return new FirestoreQueryLiveDataArray<>(
//                 FirebaseFirestore.getInstance().collection(Rating.COLLECTION)
//                .orderBy(Rating.FIELD_CREATION_DATE, Query.Direction.DESCENDING)
//                .whereEqualTo(Rating.FIELD_USER_ID, userId), Rating.class);
//    }


}
