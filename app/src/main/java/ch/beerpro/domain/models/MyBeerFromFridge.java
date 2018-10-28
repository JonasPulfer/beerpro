package ch.beerpro.domain.models;

import java.util.Date;

import lombok.Data;

@Data
public class MyBeerFromFridge implements MyBeer {

    private FridgeContent content;
    private Beer beer;

    public MyBeerFromFridge(FridgeContent content, Beer beer) {
        this.content = content;
        this.beer = beer;
    }

    @Override
    public String getBeerId(){
        return content.getBeerId();
    }

    @Override
    public Date getDate() {
        return content.getAddedAt();
    }

}
