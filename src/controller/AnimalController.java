package controller;

import model.Animal;
import model.Constants;

import java.util.Random;

public class AnimalController {
    Random random = new Random();
    model.Animal model;
    public AnimalController(Animal model){
        this.model = model;
    }


    public void move(){
        int tempX = model.getPosition().getX();
        int tempY = model.getPosition().getY();
        switch (random.nextInt(model.getMap().getSize())){

            case Constants.EAST:
                if (model.getPosition().getX() + 1 < model.getMap().getSize()) {
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(tempX);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(tempY);
                    model.getPosition().setX(model.getPosition().getX() + 1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(-1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(-1);
                    tempX = model.getPosition().getX();
                    tempY = model.getPosition().getY();
                    model.getMap().printMap();
                }
                else {
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(tempX);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(tempY);
                    model.getPosition().setX(model.getPosition().getX() - 1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(-1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(-1);
                    tempX = model.getPosition().getX();
                    tempY = model.getPosition().getY();
                    model.getMap().printMap();
                }
                break;

            case Constants.NORTH:
                if (model.getPosition().getY() - 1 >= 0) {
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(tempX);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(tempY);
                    model.getPosition().setY(model.getPosition().getY() - 1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(-1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(-1);
                    tempX = model.getPosition().getX();
                    tempY = model.getPosition().getY();
                    model.getMap().printMap();
                }
                else {
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(tempX);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(tempY);
                    model.getPosition().setY(model.getPosition().getY() + 1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(-1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(-1);
                    tempX = model.getPosition().getX();
                    tempY = model.getPosition().getY();
                    model.getMap().printMap();
                }
                break;

            case Constants.SOUTH:
                if (model.getPosition().getY() + 1 < model.getMap().getSize()) {
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(tempX);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(tempY);
                    model.getPosition().setY(model.getPosition().getY() + 1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(-1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(-1);
                    tempX = model.getPosition().getX();
                    tempY = model.getPosition().getY();
                    model.getMap().printMap();
                }
                else {
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(tempX);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(tempY);
                    model.getPosition().setY(model.getPosition().getY() - 1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(-1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(-1);
                    tempX = model.getPosition().getX();
                    tempY = model.getPosition().getY();
                    model.getMap().printMap();
                }
                break;

            case Constants.WEST:
                if (model.getPosition().getX() - 1 >= 0){
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(tempX);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(tempY);
                    model.getPosition().setX(model.getPosition().getX() - 1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(-1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(-1);
                    tempX = model.getPosition().getX();
                    tempY = model.getPosition().getY();
                    model.getMap().printMap();
                }
                else {
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(tempX);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(tempY);
                    model.getPosition().setX(model.getPosition().getX() + 1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(-1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(-1);
                    tempX = model.getPosition().getX();
                    tempY = model.getPosition().getY();
                    model.getMap().printMap();
                }
                break;
            case Constants.NORTHEAST:
                if (model.getPosition().getX() + 1 < model.getMap().getSize() && model.getPosition().getY() - 1 >= 0){
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(tempX);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(tempY);
                    model.getPosition().setX(model.getPosition().getX() + 1);
                    model.getPosition().setY(model.getPosition().getY() - 1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(-1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(-1);
                    tempX = model.getPosition().getX();
                    tempY = model.getPosition().getY();
                    model.getMap().printMap();
                }
                else {
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(tempX);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(tempY);
                    model.getPosition().setX(model.getPosition().getX() - 1);
                    model.getPosition().setY(model.getPosition().getY() + 1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(-1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(-1);
                    tempX = model.getPosition().getX();
                    tempY = model.getPosition().getY();
                    model.getMap().printMap();
                }
                break;
            case Constants.SOUTHEAST:
                if (model.getPosition().getX() + 1 < model.getMap().getSize() && model.getPosition().getY() + 1 < model.getMap().getSize()){
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(tempX);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(tempY);
                    model.getPosition().setX(model.getPosition().getX() + 1);
                    model.getPosition().setY(model.getPosition().getY() + 1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(-1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(-1);
                    tempX = model.getPosition().getX();
                    tempY = model.getPosition().getY();
                    model.getMap().printMap();
                }
                else {
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(tempX);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(tempY);
                    model.getPosition().setX(model.getPosition().getX() - 1);
                    model.getPosition().setY(model.getPosition().getY() - 1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(-1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(-1);
                    tempX = model.getPosition().getX();
                    tempY = model.getPosition().getY();
                    model.getMap().printMap();
                }
                break;
            case Constants.NORTHWEST:
                if (model.getPosition().getX() - 1 >= 0 && model.getPosition().getY() - 1 >= 0){
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(tempX);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(tempY);
                    model.getPosition().setX(model.getPosition().getX() - 1);
                    model.getPosition().setY(model.getPosition().getY() - 1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(-1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(-1);
                    tempX = model.getPosition().getX();
                    tempY = model.getPosition().getY();
                    model.getMap().printMap();
                }
                else {
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(tempX);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(tempY);
                    model.getPosition().setX(model.getPosition().getX() + 1);
                    model.getPosition().setY(model.getPosition().getX() + 1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(-1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(-1);
                    tempX = model.getPosition().getX();
                    tempY = model.getPosition().getY();
                    model.getMap().printMap();
                }
                break;
            case Constants.SOUTHWEST:
                if (model.getPosition().getX() - 1 >= 0 && model.getPosition().getY() + 1 < model.getMap().getSize()){
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(tempX);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(tempY);
                    model.getPosition().setX(model.getPosition().getX() - 1);
                    model.getPosition().setY(model.getPosition().getY() + 1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(-1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(-1);
                    tempX = model.getPosition().getX();
                    tempY = model.getPosition().getY();
                    model.getMap().printMap();
                }
                else {
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(tempX);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(tempY);
                    model.getPosition().setX(model.getPosition().getX() + 1);
                    model.getPosition().setY(model.getPosition().getY() - 1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setX(-1);
                    model.getMap().getPositions()[model.getPosition().getX()][model.getPosition().getY()].setY(-1);
                    tempX = model.getPosition().getX();
                    tempY = model.getPosition().getY();
                    model.getMap().printMap();
                }
                break;

            default:
                System.out.println("nothing!");break;

        }
    }
}
