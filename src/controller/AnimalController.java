package controller;

import model.Animal;
import model.Constants;

import java.util.Random;

public class AnimalController {
    Random random = new Random();
    model.Animal animalModel;
    public AnimalController(Animal model){
        this.animalModel = model;
    }


    public void move(){
        int tempX = animalModel.getPosition().getX();
        int tempY = animalModel.getPosition().getY();
        switch (random.nextInt(animalModel.getMap().getSize())){

            case Constants.EAST:
                if (animalModel.getPosition().getX() + 1 < animalModel.getMap().getSize()) {
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(tempX);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(tempY);
                    animalModel.getPosition().setX(animalModel.getPosition().getX() + 1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(-1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(-1);
                    tempX = animalModel.getPosition().getX();
                    tempY = animalModel.getPosition().getY();
                    animalModel.getMap().printMap();
                }
                else {
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(tempX);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(tempY);
                    animalModel.getPosition().setX(animalModel.getPosition().getX() - 1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(-1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(-1);
                    tempX = animalModel.getPosition().getX();
                    tempY = animalModel.getPosition().getY();
                    animalModel.getMap().printMap();
                }
                break;

            case Constants.NORTH:
                if (animalModel.getPosition().getY() - 1 >= 0) {
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(tempX);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(tempY);
                    animalModel.getPosition().setY(animalModel.getPosition().getY() - 1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(-1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(-1);
                    tempX = animalModel.getPosition().getX();
                    tempY = animalModel.getPosition().getY();
                    animalModel.getMap().printMap();
                }
                else {
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(tempX);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(tempY);
                    animalModel.getPosition().setY(animalModel.getPosition().getY() + 1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(-1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(-1);
                    tempX = animalModel.getPosition().getX();
                    tempY = animalModel.getPosition().getY();
                    animalModel.getMap().printMap();
                }
                break;

            case Constants.SOUTH:
                if (animalModel.getPosition().getY() + 1 < animalModel.getMap().getSize()) {
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(tempX);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(tempY);
                    animalModel.getPosition().setY(animalModel.getPosition().getY() + 1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(-1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(-1);
                    tempX = animalModel.getPosition().getX();
                    tempY = animalModel.getPosition().getY();
                    animalModel.getMap().printMap();
                }
                else {
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(tempX);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(tempY);
                    animalModel.getPosition().setY(animalModel.getPosition().getY() - 1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(-1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(-1);
                    tempX = animalModel.getPosition().getX();
                    tempY = animalModel.getPosition().getY();
                    animalModel.getMap().printMap();
                }
                break;

            case Constants.WEST:
                if (animalModel.getPosition().getX() - 1 >= 0){
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(tempX);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(tempY);
                    animalModel.getPosition().setX(animalModel.getPosition().getX() - 1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(-1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(-1);
                    tempX = animalModel.getPosition().getX();
                    tempY = animalModel.getPosition().getY();
                    animalModel.getMap().printMap();
                }
                else {
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(tempX);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(tempY);
                    animalModel.getPosition().setX(animalModel.getPosition().getX() + 1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(-1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(-1);
                    tempX = animalModel.getPosition().getX();
                    tempY = animalModel.getPosition().getY();
                    animalModel.getMap().printMap();
                }
                break;
            case Constants.NORTHEAST:
                if (animalModel.getPosition().getX() + 1 < animalModel.getMap().getSize() && animalModel.getPosition().getY() - 1 >= 0){
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(tempX);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(tempY);
                    animalModel.getPosition().setX(animalModel.getPosition().getX() + 1);
                    animalModel.getPosition().setY(animalModel.getPosition().getY() - 1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(-1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(-1);
                    tempX = animalModel.getPosition().getX();
                    tempY = animalModel.getPosition().getY();
                    animalModel.getMap().printMap();
                }
                else {
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(tempX);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(tempY);
                    animalModel.getPosition().setX(animalModel.getPosition().getX() - 1);
                    animalModel.getPosition().setY(animalModel.getPosition().getY() + 1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(-1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(-1);
                    tempX = animalModel.getPosition().getX();
                    tempY = animalModel.getPosition().getY();
                    animalModel.getMap().printMap();
                }
                break;
            case Constants.SOUTHEAST:
                if (animalModel.getPosition().getX() + 1 < animalModel.getMap().getSize() && animalModel.getPosition().getY() + 1 < animalModel.getMap().getSize()){
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(tempX);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(tempY);
                    animalModel.getPosition().setX(animalModel.getPosition().getX() + 1);
                    animalModel.getPosition().setY(animalModel.getPosition().getY() + 1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(-1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(-1);
                    tempX = animalModel.getPosition().getX();
                    tempY = animalModel.getPosition().getY();
                    animalModel.getMap().printMap();
                }
                else {
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(tempX);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(tempY);
                    animalModel.getPosition().setX(animalModel.getPosition().getX() - 1);
                    animalModel.getPosition().setY(animalModel.getPosition().getY() - 1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(-1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(-1);
                    tempX = animalModel.getPosition().getX();
                    tempY = animalModel.getPosition().getY();
                    animalModel.getMap().printMap();
                }
                break;
            case Constants.NORTHWEST:
                if (animalModel.getPosition().getX() - 1 >= 0 && animalModel.getPosition().getY() - 1 >= 0){
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(tempX);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(tempY);
                    animalModel.getPosition().setX(animalModel.getPosition().getX() - 1);
                    animalModel.getPosition().setY(animalModel.getPosition().getY() - 1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(-1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(-1);
                    tempX = animalModel.getPosition().getX();
                    tempY = animalModel.getPosition().getY();
                    animalModel.getMap().printMap();
                }
                else {
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(tempX);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(tempY);
                    animalModel.getPosition().setX(animalModel.getPosition().getX() + 1);
                    animalModel.getPosition().setY(animalModel.getPosition().getX() + 1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(-1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(-1);
                    tempX = animalModel.getPosition().getX();
                    tempY = animalModel.getPosition().getY();
                    animalModel.getMap().printMap();
                }
                break;
            case Constants.SOUTHWEST:
                if (animalModel.getPosition().getX() - 1 >= 0 && animalModel.getPosition().getY() + 1 < animalModel.getMap().getSize()){
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(tempX);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(tempY);
                    animalModel.getPosition().setX(animalModel.getPosition().getX() - 1);
                    animalModel.getPosition().setY(animalModel.getPosition().getY() + 1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(-1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(-1);
                    tempX = animalModel.getPosition().getX();
                    tempY = animalModel.getPosition().getY();
                    animalModel.getMap().printMap();
                }
                else {
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(tempX);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(tempY);
                    animalModel.getPosition().setX(animalModel.getPosition().getX() + 1);
                    animalModel.getPosition().setY(animalModel.getPosition().getY() - 1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setX(-1);
                    animalModel.getMap().getPositions()[animalModel.getPosition().getX()][animalModel.getPosition().getY()].setY(-1);
                    tempX = animalModel.getPosition().getX();
                    tempY = animalModel.getPosition().getY();
                    animalModel.getMap().printMap();
                }
                break;

            default:
                System.out.println("nothing!");break;

        }
    }
}
