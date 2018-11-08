package ch.beerpro.domain.models;


import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@IgnoreExtraProperties
@Data
@NoArgsConstructor
@RequiredArgsConstructor

public class FridgeContent implements Entity{

    public static final String COLLECTION = "fridgeContent";
    public static final String FIELD_ADDED_AT = "addedAt";
    public static final String FIELD_AMOUNT = "amount";
    public static final String FIELD_BEER_ID = "beerId";
    public static final String FIELD_ID = "id";
    public static final String FIELD_USER_ID = "userId";

    /**
     * The id is formed by `$userId_$beerId` to make queries easier.
     */
    @NonNull
    private Date addedAt;

    private int amount = 1;

    @NonNull
    private String beerId;

    @Exclude
    private String id;

    @NonNull
    private String userId;

    public static String generateId(String userId, String beerId) {
        return String.format("%s_%s", userId, beerId);
    }

}