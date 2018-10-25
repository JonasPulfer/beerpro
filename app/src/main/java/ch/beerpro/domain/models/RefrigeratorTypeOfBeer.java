package ch.beerpro.domain.models;



public class RefrigeratorTypeOfBeer{

    public static final String COLLECTION = "RefrigeratorTypeOfBeer";
    public static final String FIELD_AMOUNT = "amount";
    public static final String FIELD_BEER_ID = "beerId";
    public static final String FIELD_BEER_NAME = "beerName";
    public static final String FIELD_USER_ID = "beerId";

    private String beerId;
    private String beerName;
    private String userId;
    private int amount;

    public RefrigeratorTypeOfBeer(String beerId, String beerName, String userId, int amount){
        this.beerId = beerId;
        this.beerName = beerName;
        this.userId = userId;
        this.amount = amount;
    }





    public void setAmount(int amount){
        this.amount = amount;
    }

    public int getAmount(){
        return amount;
    }

}
