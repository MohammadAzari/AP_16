package controller;

import model.Domestic;
import model.Grass;
import model.Map;
import model.Product;

import java.util.ArrayList;

class DomesticController{

    private Domestic domestic;

    public void eatGrass() {


        Map map = new Map(3);
        if (map.getGrasses().isEmpty())
            die();
        else
            //map.getGrasses().remove();
        produce();
    }

    public Product produce(){

        Product product = new Product();
        Map map = new Map(3);
        map.getProducts().add(product);
        return product;
    }

    public void die(){

        Map map = new Map(3);
        map.getDomestics().remove(domestic);

    }
}
