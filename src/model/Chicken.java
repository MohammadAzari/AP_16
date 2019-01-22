package model;

import java.util.ArrayList;

public class Chicken extends Domestic {

    private ArrayList<Chicken> chickens;

    {
        chickens = new ArrayList<>();
    }

    public Chicken(Map map) {
        super(map);
    }
}
