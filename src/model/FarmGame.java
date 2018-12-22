package model;

import java.util.ArrayList;
import java.util.Random;

public class FarmGame{

    int coins;
    int second;

    private ArrayList<Domestic> domesticAnimals = new ArrayList<>();
    private ArrayList<Wild> wildAnimals;
    private ArrayList<Sheep> sheeps;
    private ArrayList<Chicken> chickens;
    ArrayList<Cow> cows;

    public void addToWildAnimals(){

        wildAnimals.add(new Wild());
    }




}

