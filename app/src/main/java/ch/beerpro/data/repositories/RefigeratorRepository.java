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

    //toggle für Elemente anzupassen oder zu löschen --> siehe WishList

}
