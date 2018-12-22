package controller;

import model.FarmGame;
import model.Product;
import model.Warehouse;

public class TruckController {

    public void sell(){
        //removeFromWarehouse
        FarmGame farmGame=new FarmGame();
        Product product = new Product();
        Warehouse warehouse = new Warehouse();
        warehouse.getProducts().remove(product);
        int coin = farmGame.getCoins();
        coin += product.getPrice();

    }
}
