package model;

import java.util.Random;


public class Animal implements Moveable {
    private Position position = new Position();
    private Map map;
    private Random random = new Random();
    private Constants constants = new Constants();

    public Animal(Map map){
        this.map = map;
        position.setX(random.nextInt(map.getSize()));
        position.setY(random.nextInt(map.getSize()));



    }

    public Animal() {

    }

    public Position getPosition() {
        return position;
    }


    public Map getMap() {
        return map;
    }

    public void move(){
        switch (random.nextInt(map.getSize())){

            case Constants.EAST:
                if (position.getX() + 1 < map.getSize()) {
                    position.setX(position.getX() + 1);
                    map.printMap();
                }
                else {
                    position.setX(position.getX() - 1);
                    map.printMap();
                }
                break;

            case Constants.NORTH:
                if (position.getY() + 1 < map.getSize()) {
                    position.setY(position.getY() + 1);
                    map.printMap();
                }
                else {
                    position.setY(position.getY() - 1);
                    map.printMap();
                }
                break;

            case Constants.SOUTH:
                if (position.getY() - 1 < map.getSize()) {
                    position.setY(position.getY() - 1);
                    map.printMap();
                }
                else {
                    position.setY(position.getY() + 1);
                    map.printMap();
                }
                break;

            case Constants.WEST:
                if (position.getX() - 1 < 0){
                    position.setX(position.getX() + 1);
                    map.printMap();
                }
                else {
                    position.setX(position.getX() - 1);
                    map.printMap();
                }
                break;

            default: break;

        }
    }
}
