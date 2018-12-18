package model;

import java.util.ArrayList;

public class Domestic extends Animal{

    int stomach;
    private ArrayList<Domestic> domesticAnimals = new ArrayList<>();//game

    public Domestic(Map map) {
        super(map);
    }
}
