package controller;

import model.Domestic;
import model.Egg;
import model.Map;
import model.Product;

public class ChickenController{

    public Product produce(){

        Egg egg = new Egg();
        Map map = new Map(3);
        map.getProducts().add(egg);
        return egg;
    }

    public void die(Domestic chicken){

        Map map = new Map(3);
        map.getDomestics().remove(chicken);

    }
}
