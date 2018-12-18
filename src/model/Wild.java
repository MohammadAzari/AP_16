package model;

import java.util.ArrayList;

public class Wild extends Animal {

    private ArrayList<Wild> wildAnimals; //game

    public Wild(Map map) {
        super(map);
    }

    public Wild() {
        super();
    }

    public void addToWildAnimals(){//game

        wildAnimals.add(new Wild());
    }



}
