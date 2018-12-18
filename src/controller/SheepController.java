package controller;

import model.Domestic;
import model.Product;
import model.Wool;

class SheepController{

    public Product produce(){


        Wool wool = new Wool();
        return wool;
    }

    public void die(Domestic sheep){

       // sheeps.remove(sheep);
    }
}
