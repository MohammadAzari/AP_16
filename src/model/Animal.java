package model;

import java.util.Random;


public class Animal{
    private Position position = new Position();
    private Map map;
    private Random random = new Random();
    private Constants constants = new Constants();

    public Animal(Map map){
        this.map = map;
        position.setX(random.nextInt(map.getSize()));
        position.setY(random.nextInt(map.getSize()));
    }

    public Map getMap() {
        return map;
    }

    public Position getPosition() {
        return position;
    }
}
