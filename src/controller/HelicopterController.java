package controller;

import model.*;

import java.util.ArrayList;

public class HelicopterController {

    Domestic domestic;

    public void buyProduct(){

        //removeCoins
        //addToWarehouse
        FarmGame farmGame = new FarmGame();
        Product product = new Product();
        Warehouse warehouse = new Warehouse();
        warehouse.getProducts().add(product);
        int coin = farmGame.getCoins();
        coin -= product.getPrice();
    }

    public void buyDomestics(){

        //addToMap
        Map map = new Map(3);
        map.getDomestics().add(domestic);

    }
}
