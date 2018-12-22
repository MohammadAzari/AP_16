package controller;

import model.Domestic;
import model.Map;
import model.Milk;
import model.Product;

class CowController{

    public Product produce(){

        Milk milk = new Milk();
        Map map = new Map(3);
        map.getProducts().add(milk);
        return milk;
    }

    public void die(Domestic cow){

        Map map = new Map(3);
        map.getDomestics().remove(cow);
       // cows.remove(cow);
    }
}
