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

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addToWildAnimals(){

        wildAnimals.add(new Wild());
    }




}

