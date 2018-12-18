package model;

public class Truck extends Transportation {


    public void sell(){
        //removeFromWarehouse , addToCoins
        FarmGame farmGame=new FarmGame();
        Product product = new Product();
        farmGame.coins += product.price;
    }
}
