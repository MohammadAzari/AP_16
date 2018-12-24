package controller;

import model.Domestic;
import model.Map;
import model.Product;
import model.Wool;

class SheepController{

    public Product produce(){

        Wool wool = new Wool();
        Map map = new Map(3);
        map.getProducts().add(wool);
        return wool;
    }

    public void die(Domestic sheep){

        Map map = new Map(3);
        map.getDomestics().remove(sheep);

    }
}
