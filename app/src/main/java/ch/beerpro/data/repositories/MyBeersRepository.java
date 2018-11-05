package ch.beerpro.data.repositories;

import androidx.lifecycle.LiveData;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.Entity;
import ch.beerpro.domain.models.FridgeContent;
import ch.beerpro.domain.models.MyBeerFromFridge;
import ch.beerpro.domain.models.Rating;
import ch.beerpro.domain.models.Wish;
import ch.beerpro.domain.models.MyBeer;
import ch.beerpro.domain.models.MyBeerFromRating;
import ch.beerpro.domain.models.MyBeerFromWishlist;
import ch.beerpro.domain.utils.Quadruple;

import org.apache.commons.lang3.tuple.Triple;

import java.util.*;

import static androidx.lifecycle.Transformations.map;
import static ch.beerpro.domain.utils.LiveDataExtensions.combineLatest;

public class MyBeersRepository {

    private static List<MyBeer> getMyBeersHelpers(Quadruple<List<Wish>, List<Rating>
                                             , List<FridgeContent>,   HashMap<String, Beer>> input) {

    List<Wish> wishList = input.getFirst();
    List<Rating> ratings = input.getSecond();
    List<FridgeContent> myFridge = input.getThird();
    HashMap<String, Beer> beers = input.getForth();

        ArrayList<MyBeer> result = new ArrayList<>();
        Set<String> beersAlreadyOnTheList = new HashSet<>();

        for (FridgeContent content : myFridge){
            String beerId = content.getBeerId();
            result.add(new MyBeerFromFridge(content, beers.get(beerId)));
            beersAlreadyOnTheList.add(beerId);
        }

        for (Wish wish : wishList) {
            String beerId = wish.getBeerId();

            if (!beersAlreadyOnTheList.contains(beerId)) {
                result.add(new MyBeerFromWishlist(wish, beers.get(beerId)));
                beersAlreadyOnTheList.add(beerId);
            }
        }

        for (Rating rating : ratings) {
            String beerId = rating.getBeerId();
            if (beersAlreadyOnTheList.contains(beerId)) {
                // if the beer is already on the wish list, don't add it again
            } else {
                result.add(new MyBeerFromRating(rating, beers.get(beerId)));
                // we also don't want to see a rated beer twice
                beersAlreadyOnTheList.add(beerId);
            }
        }
        Collections.sort(result, (r1, r2) -> r2.getDate().compareTo(r1.getDate()));
        return result;
    }





    public LiveData<List<MyBeer>> getMyBeers(LiveData<List<Beer>> allBeers, LiveData<List<Wish>> myWishList,
                                              LiveData<List<Rating>> myRatings, LiveData<List<FridgeContent>> myFridge) {
        return map(combineLatest(myWishList, myRatings, myFridge, map(allBeers, Entity::entitiesById)),
                MyBeersRepository::getMyBeersHelpers);
    }




}
