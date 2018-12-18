package controller;

import model.Domestic;
import model.Egg;
import model.Product;

class ChickenController{

    public Product produce(){

        Egg egg = new Egg();
        return egg;
    }

    public void die(Domestic chicken){

       // chickens.remove(chicken);
    }
}
