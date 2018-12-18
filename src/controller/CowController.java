package controller;

import model.Domestic;
import model.Milk;
import model.Product;

class CowController{

    public Product produce(){

        Milk milk = new Milk();
        return milk;
    }

    public void die(Domestic cow){

       // cows.remove(cow);
    }
}
