import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;


class Constants {
    public static final int NORTHEAST = 0;
    public static final int NORTH = 1;
    public static final int NORTHWEST = 2;
    public static final int WEST = 3;
    public static final int EAST = 4;
    public static final int SOUTHWEST = 5;
    public static final int SOUTH = 6;
    public static final int SOUTHEAST = 7;
}

class Well{
    private int capacity;
    private int capacityUnit = 5;
    private int level;
    private static Well singleton = new Well();

    private Well(){
        capacity = capacityUnit;
        level = 1;
    }

    public static Well getclass(){
        return singleton;
    }

    public void upgrade(){
        level++;
        capacityUnit += 3;
        capacity = capacityUnit;
    }

    public int getLevel() {
        return level;
    }

    public void unload(){
        if (capacity <= 0)
            System.out.println("well is empty!!");
        else
            capacity--;
    }

    public int getCapacity() {
        return capacity;
    }

    public void load(){
        if (capacity == capacityUnit)
            System.out.println("well is full!!");
        else
            capacity = capacityUnit;
    }
}

class Position{
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Cage> cages = new ArrayList<>();
    private int x, y;
    private boolean hasFood;

    public void addProduct(Product product){
        products.add(product);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public boolean isHasFood() {
        return hasFood;
    }

    public void setHasFood(boolean hasFood) {
        this.hasFood = hasFood;
    }

    public Position(int x, int y){
        this.x = x;
        this.y = y;
        hasFood = false;
    }
    public Position(){
        hasFood = true;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}

class Map implements Printable{
    Position [][] positions;
    private int size;
    public Map(int size){
        this.size = size;
        positions = new Position[size][];
        for (int i = 0 ; i < size ; i++){
            positions[i] = new Position[size];
            for (int k = 0 ; k < size ; k++)
                positions[i][k] = new Position();
        }
        for (int a = 0 ; a < size ; a++){
            for (int b = 0 ; b < size ; b++){
                positions[a][b].setX(a);
                positions[a][b].setY(b);
            }
        }
    }


    public Position[][] getPositions() {
        return positions;
    }

    public int getSize() {
        return size;
    }

    public void printInfo(){
        for (int i = 0 ; i < size ; i++){
            for (int j = 0 ; j < size ; j++){
                System.out.print("|" + this.positions[i][j].getX() + " ");
                System.out.print(this.positions[i][j].getY()+ "|");
            }
            System.out.println();
        }
        System.out.println();
    }
}

class GetRandonNumber{
    private double doub;
    private Random random = new Random();
    public int getInt(int toN){
        return random.nextInt(toN);
    }
}

interface Moveable{
    void move();
}

interface Upgradable{
    void upgrade();
}

class Animal implements Moveable{
    protected int price;
    protected int priceUnit;
    protected Position position = new Position();
    protected Map map;
    protected Random random = new Random();
    protected Constants constants = new Constants();

    public Animal(Map map){
        this.map = map;
        position.setX(random.nextInt(map.getSize()));
        position.setY(random.nextInt(map.getSize()));
    }

    public Position getPosition() {
        return position;
    }

    public void move(){
        int tempX = this.position.getX();
        int tempY = this.position.getY();
        switch (random.nextInt(8)){

            case Constants.EAST:
                if (position.getX() + 1 < map.getSize()) {
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setX(position.getX() + 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    System.out.println("Moved to East!");
                    //map.printInfo();
                }
                else {
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setX(position.getX() - 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    System.out.println("Moved to West!");
                    //map.printInfo();
                }
                break;

            case Constants.NORTH:
                if (position.getY() - 1 >= 0) {
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setY(position.getY() - 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    System.out.println("Moved to North!");
                    //map.printInfo();
                }
                else {
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setY(position.getY() + 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    System.out.println("Moved to South!");
                    //map.printInfo();
                }
                break;

            case Constants.SOUTH:
                if (position.getY() + 1 < map.getSize()) {
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setY(position.getY() + 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    System.out.println("Moved to South!");
                    //map.printInfo();
                }
                else {
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setY(position.getY() - 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    System.out.println("Moved to North!");
                    //map.printInfo();
                }
                break;

            case Constants.WEST:
                if (position.getX() - 1 >= 0){
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setX(position.getX() - 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    System.out.println("Moved to West!");
                    //map.printInfo();
                }
                else {
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setX(position.getX() + 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    System.out.println("Moved East!");
                    //map.printInfo();
                }
                break;
            case Constants.NORTHEAST:
                if (position.getX() + 1 < map.getSize() && position.getY() - 1 >= 0){
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setX(position.getX() + 1);
                    position.setY(position.getY() - 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    System.out.println("Moved to NorthEast!");
                    //map.printInfo();
                }
                else {
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setX(position.getX() - 1);
                    position.setY(position.getY() + 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    System.out.println("Moved to SouthWest!");
                    //map.printInfo();
                }
                break;
            case Constants.SOUTHEAST:
                if (position.getX() + 1 < map.getSize() && position.getY() + 1 < map.getSize()){
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setX(position.getX() + 1);
                    position.setY(position.getY() + 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    System.out.println("Moved to SouthEast!");
                    //map.printInfo();
                }
                else {
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setX(position.getX() - 1);
                    position.setY(position.getY() - 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    System.out.println("Moved NorthWest!");
                    //map.printInfo();
                }
                break;
            case Constants.NORTHWEST:
                if (position.getX() - 1 >= 0 && position.getY() - 1 >= 0){
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setX(position.getX() - 1);
                    position.setY(position.getY() - 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    System.out.println("Moved to NorthWest!");
                    //map.printInfo();
                }
                else {
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setX(position.getX() + 1);
                    position.setY(position.getX() + 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    System.out.println("Moved to SouthEast!");
                    //map.printInfo();
                }
                break;
            case Constants.SOUTHWEST:
                if (position.getX() - 1 >= 0 && position.getY() + 1 < map.getSize()){
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setX(position.getX() - 1);
                    position.setY(position.getY() + 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    System.out.println("Moved to SouthWest!");
                    //map.printInfo();
                }
                else {
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setX(position.getX() + 1);
                    position.setY(position.getY() - 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    System.out.println("Moved to NorthEast!");
                    //map.printInfo();
                }
                break;

            default:
                System.out.println("nothing!");break;

        }
    }
}

enum TypeOfPet {
    Chicken , Cow , Ostrich
}

abstract class Wild extends Animal implements Upgradable{

    public Wild(Map map) {
        super(map);
    }

    public void upgrade(){

    }
}

abstract class Pet extends Animal implements Upgradable,Printable {
    protected int feedCapacityUnit;
    protected int feedCapacity;
    protected int level;
    protected TypeOfPet type;

    public Pet(Map map) {
        super(map);
        feedCapacity = 0;
        level = 1;
    }

    public void eatGrass() {
        if (map.getPositions()[position.getX()][position.getY()].isHasFood()) {
            map.getPositions()[position.getX()][position.getY()].setHasFood(false);
            feedCapacity++;
            if (feedCapacity == feedCapacityUnit) {
                makeProduct();
            }
        }
    }

    public abstract boolean makeProduct();

    public abstract void upgrade();

}

interface Printable{
    void printInfo();
}

class Chicken extends Pet implements Upgradable, Printable{

    Product egg;
    int namedLabel;

    public Chicken(Map map, int label) {
        super(map);
        namedLabel = label;
        feedCapacityUnit = 3;
        priceUnit = 100;
        price = priceUnit;
        type = TypeOfPet.Chicken;
    }

    public String getInfo(){
        return "price: " + price + "\n" +
                "level: " + level + "\n" +
                "feed capacity unit: " + feedCapacityUnit;
    }

    public void printInfo(){
        System.out.println("Chicken number: " + namedLabel + "{\n\t" +
                "price: " + price + "\n\t" +
                "food capacity: " + feedCapacity + "\n\t" +
                "level: " + level + "\n\t" +
                "feed capacity unit: " + feedCapacityUnit + "\n}");
    }

    @Override
    public boolean makeProduct() {
        if (feedCapacity == feedCapacityUnit){
            egg = new Egg();
            map.getPositions()[position.getX()][position.getY()].addProduct(egg);
            System.out.println("an egg is produced and placed in [" + position.getX() + " " +
                    position.getY() + "]");
            feedCapacity = 0;

            return true;
        }
        return false;
    }

    @Override
    public void upgrade(){
        level++;
        feedCapacityUnit--;
        price += 50;
        System.out.println("Chicken is upgraded to level " + level);
    }
}

class Cow extends Pet implements Upgradable , Printable{

    Product milk;
    int namedLabel;

    public Cow(Map map, int label) {
        super(map);
        namedLabel = label;
        feedCapacityUnit = 5;
        priceUnit = 200;
        price = priceUnit;
        type = TypeOfPet.Cow;
    }

    public String getInfo(){
        return "price: " + price + "\n" +
                "level: " + level + "\n" +
                "feed capacity unit: " + feedCapacityUnit;
    }


    public void printInfo(){
        System.out.println("Cow number: " + namedLabel + "{\n\t" +
                "price: " + price + "\n\t" +
                "food capacity: " + feedCapacity + "\n\t" +
                "level: " + level + "\n\t" +
                "feed capacity unit: " + feedCapacityUnit + "\n}");
    }


    @Override
    public boolean makeProduct() {
        if (feedCapacity == feedCapacityUnit){
            milk = new Milk();
            map.getPositions()[position.getX()][position.getY()].addProduct(milk);
            System.out.println("a bottle of milk is produced and placed in [" + position.getX() + " " +
                    position.getY() + "]");
            feedCapacity = 0;

            return true;
        }

        return false;
    }

    @Override
    public void upgrade(){
        level++;
        feedCapacityUnit--;
        price += 50;
        System.out.println("Cow is upgraded to level " + level);
    }


}




class Ostrich extends Pet implements Upgradable , Printable{

    Product wool;
    int namedLabel;

    public Ostrich(Map map, int label) {
        super(map);
        namedLabel = label;
        feedCapacityUnit = 4;
        priceUnit = 300;
        price = priceUnit;
        type = TypeOfPet.Ostrich;
    }

    public String getInfo(){
        return "price: " + price + "\n" +
                "level: " + level + "\n" +
                "feed capacity unit: " + feedCapacityUnit;
    }

    public void printInfo(){
        System.out.println("Ostrich number: " + namedLabel + "{\n\t" +
                "price: " + price + "\n\t" +
                "food capacity: " + feedCapacity + "\n\t" +
                "level: " + level + "\n\t" +
                "feed capacity unit: " + feedCapacityUnit + "\n}");
    }


    @Override
    public boolean makeProduct() {
        if (feedCapacity == feedCapacityUnit){
            wool = new Wool();
            map.getPositions()[position.getX()][position.getY()].addProduct(wool);
            System.out.println("wool is produced and placed in [" + position.getX() + " " +
                    position.getY() + "]");
            feedCapacity = 0;

            return true;
        }
        return false;
    }

    @Override
    public void upgrade(){
        level++;
        feedCapacityUnit--;
        price += 50;
        System.out.println("Ostrich is upgraded to level " + level);
    }


}






class Product{

}

class Egg extends Product{


}

class Milk extends Product{

}

class Wool extends Product{

}

class Cage{


}

class Warehouse implements Upgradable{
    private int level = 1;
    private int animalCapacity = 2;
    private int productCapacity = 10;
    private ArrayList<Cage> animalsInCage = new ArrayList<>(animalCapacity);
    private ArrayList<Product> products = new ArrayList<>(productCapacity);
    private static Warehouse singleton = new Warehouse();

    public static Warehouse getclass(){
        return singleton;
    }

    public void addAnimal(Cage animalInCage){
        animalsInCage.add(animalInCage);
    }

    public void addProducts(Product product){
        products.add(product);
    }

    public void upgrade(){
        if (level == 5)
            System.out.println("There's no upgrade available!!");
        else {
            level++;
            animalCapacity += 1;
            productCapacity += 3;
            System.out.println("Warehouse is upgraded to level " + level);
        }
    }
    private Warehouse(){
    }

}

class GameInfo{
    int time;
    int initialMoney = 3500;
    int money;
    Map map = new Map(1300);
    Chicken chicken = new Chicken(map, 0);
    Cow cow = new Cow(map, 0);
    Ostrich ostrich = new Ostrich(map, 0);
    Warehouse warehouse = Warehouse.getclass();
    Well well = Well.getclass();
    ArrayList<Pet> pets = new ArrayList<>();

    public GameInfo(){
        time = 0;
        money = initialMoney;
    }
}

class Orders{

    private GameInfo gameInfo = new GameInfo();
    private int chickenLabel = 1;
    private  int cowLabel = 1;
    private  int ostrichLabel = 1;
    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public Chicken buyChicken() throws FileNotFoundException {
        Chicken chicken = new Chicken(gameInfo.map, chickenLabel);
        chickenLabel++;
        gameInfo.pets.add(chicken);
        gameInfo.money -= chicken.price;
        System.out.println("A chicken is bought! and money = " + gameInfo.money);
        return chicken;




    }


    public Cow buyCow() throws FileNotFoundException {
        Cow cow = new Cow(gameInfo.map, cowLabel);
        cowLabel++;
        gameInfo.pets.add(cow);
        gameInfo.money -= cow.price;
        System.out.println("A cow is bought! and money = " + gameInfo.money);
        return cow;




    }


    public Ostrich buyOstrich() throws FileNotFoundException {
        Ostrich ostrich = new Ostrich(gameInfo.map, ostrichLabel);
        cowLabel++;
        gameInfo.pets.add(ostrich);
        gameInfo.money -= ostrich.price;
        System.out.println("A ostrich is bought! and money = " + gameInfo.money);
        return ostrich;




    }




    public void turn(int numOfTurns){
        for (int i = 0 ; i < numOfTurns ; i++){
            for (Pet p : gameInfo.pets){
                p.move();
                p.eatGrass();
            }
            gameInfo.time++;
        }
    }

    public int plant(int x, int y) {
        if (x > 1200 || y > 1200 || x < 350 || y < 350) {
            System.out.println("Entered Numbers are illigal!!!");
            return -1;
        }
        else {
            if (gameInfo.well.getCapacity() == 0){
                System.out.println("well is empty!");

                return -2;
            }
            else if (x - 1 >= 0 && x + 1 < gameInfo.map.getSize() &&
                    y - 1 >= 0 && y + 1 < gameInfo.map.getSize()) {
                gameInfo.well.unload();
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        gameInfo.map.positions[x - i][y - j].setHasFood(true);
                        gameInfo.map.positions[x + i][x + j].setHasFood(true);
                    }
                }
                gameInfo.map.positions[x - 1][y + 1].setHasFood(true);
                gameInfo.map.positions[x + 1][y - 1].setHasFood(true);
                System.out.println("Grass is planted at [" + x + " " + y +
                        "], " + "[" + (x) + " " + (y + 1) +
                        "], " + "[" + (x + 1) + " " + (y) +
                        "], " + "[" + (x + 1) + " " + (y + 1) +
                        "], " + "[" + (x + 1) + " " + (y - 1) +
                        "], " + "[" + (x - 1) + " " + (y + 1) +
                        "], " + "[" + (x) + " " + (y - 1) +
                        "], " + "[" + (x - 1) + " " + (y) +
                        "], " + "[" + (x - 1) + " " + (y - 1) +
                        "]");
            } else if (x - 1 < 0) {
                gameInfo.well.unload();
                if (y - 1 < 0) {
                    gameInfo.map.positions[x][y].setHasFood(true);
                    gameInfo.map.positions[x + 1][y].setHasFood(true);
                    gameInfo.map.positions[x][y + 1].setHasFood(true);
                    gameInfo.map.positions[x + 1][y + 1].setHasFood(true);
                    System.out.println("Grass is planted at [" + x + " " + y +
                            "], " + "[" + (x + 1) + " " + (y) +
                            "], " + "[" + (x) + " " + (y + 1) +
                            "], " + "[" + (x + 1) + " " + (y + 1) +
                            "]");
                } else if (y + 1 == gameInfo.map.getSize()) {
                    gameInfo.map.positions[x][y].setHasFood(true);
                    gameInfo.map.positions[x + 1][y].setHasFood(true);
                    gameInfo.map.positions[x][y - 1].setHasFood(true);
                    gameInfo.map.positions[x + 1][y - 1].setHasFood(true);
                    System.out.println("Grass is planted at [" + x + " " + y +
                            "], " + "[" + (x + 1) + " " + (y) +
                            "], " + "[" + (x) + " " + (y - 1) +
                            "], " + "[" + (x + 1) + " " + (y - 1) +
                            "]");
                } else {
                    gameInfo.map.positions[x][y].setHasFood(true);
                    gameInfo.map.positions[x + 1][y].setHasFood(true);
                    gameInfo.map.positions[x][y + 1].setHasFood(true);
                    gameInfo.map.positions[x + 1][y + 1].setHasFood(true);
                    gameInfo.map.positions[x][y - 1].setHasFood(true);
                    gameInfo.map.positions[x + 1][y - 1].setHasFood(true);
                    System.out.println("Grass is planted at [" + x + " " + y +
                            "], " + "[" + (x + 1) + " " + (y) +
                            "], " + "[" + (x) + " " + (y + 1) +
                            "], " + "[" + (x + 1) + " " + (y + 1) +
                            "], " + "[" + (x) + " " + (y - 1) +
                            "], " + "[" + (x + 1) + " " + (y - 1) +
                            "]");
                }
            } else if (x + 1 == gameInfo.map.getSize()) {
                gameInfo.well.unload();
                if (y - 1 < 0) {
                    gameInfo.map.positions[x][y].setHasFood(true);
                    gameInfo.map.positions[x][y + 1].setHasFood(true);
                    gameInfo.map.positions[x - 1][y + 1].setHasFood(true);
                    gameInfo.map.positions[x - 1][y].setHasFood(true);
                    System.out.println("Grass is planted at [" + x + " " + y +
                            "], " + "[" + (x) + " " + (y + 1) +
                            "], " + "[" + (x - 1) + " " + (y + 1) +
                            "], " + "[" + (x - 1) + " " + (y) +
                            "]");
                } else if (y + 1 == gameInfo.map.getSize()) {
                    gameInfo.map.positions[x][y].setHasFood(true);
                    gameInfo.map.positions[x][y - 1].setHasFood(true);
                    gameInfo.map.positions[x - 1][y - 1].setHasFood(true);
                    gameInfo.map.positions[x - 1][y].setHasFood(true);
                    System.out.println("Grass is planted at [" + x + " " + y +
                            "], " + "[" + (x) + " " + (y - 1) +
                            "], " + "[" + (x - 1) + " " + (y - 1) +
                            "], " + "[" + (x - 1) + " " + (y) +
                            "]");
                } else {
                    gameInfo.map.positions[x][y].setHasFood(true);
                    gameInfo.map.positions[x - 1][y].setHasFood(true);
                    gameInfo.map.positions[x][y + 1].setHasFood(true);
                    gameInfo.map.positions[x - 1][y + 1].setHasFood(true);
                    gameInfo.map.positions[x][y - 1].setHasFood(true);
                    gameInfo.map.positions[x - 1][y - 1].setHasFood(true);
                    System.out.println("Grass is planted at [" + x + " " + y +
                            "], " + "[" + (x - 1) + " " + (y) +
                            "], " + "[" + (x) + " " + (y + 1) +
                            "], " + "[" + (x - 1) + " " + (y + 1) +
                            "], " + "[" + (x) + " " + (y - 1) +
                            "], " + "[" + (x - 1) + " " + (y - 1) +
                            "]");
                }
            } else if (y - 1 < 0) {
                gameInfo.well.unload();
                if (x - 1 < 0) {
                    gameInfo.map.positions[x][y].setHasFood(true);
                    gameInfo.map.positions[x + 1][y].setHasFood(true);
                    gameInfo.map.positions[x][y + 1].setHasFood(true);
                    gameInfo.map.positions[x + 1][y + 1].setHasFood(true);
                    System.out.println("Grass is planted at [" + x + " " + y +
                            "], " + "[" + (x + 1) + " " + (y) +
                            "], " + "[" + (x) + " " + (y + 1) +
                            "], " + "[" + (x + 1) + " " + (y + 1) +
                            "]");
                } else if (x + 1 == gameInfo.map.getSize()) {
                    gameInfo.map.positions[x][y].setHasFood(true);
                    gameInfo.map.positions[x][y + 1].setHasFood(true);
                    gameInfo.map.positions[x - 1][y + 1].setHasFood(true);
                    gameInfo.map.positions[x - 1][y].setHasFood(true);
                    System.out.println("Grass is planted at [" + x + " " + y +
                            "], " + "[" + (x) + " " + (y + 1) +
                            "], " + "[" + (x - 1) + " " + (y + 1) +
                            "], " + "[" + (x - 1) + " " + (y) +
                            "]");
                } else {
                    gameInfo.map.positions[x][y].setHasFood(true);
                    gameInfo.map.positions[x - 1][y].setHasFood(true);
                    gameInfo.map.positions[x][y + 1].setHasFood(true);
                    gameInfo.map.positions[x - 1][y + 1].setHasFood(true);
                    gameInfo.map.positions[x + 1][y].setHasFood(true);
                    gameInfo.map.positions[x + 1][y + 1].setHasFood(true);
                    System.out.println("Grass is planted at [" + x + " " + y +
                            "], " + "[" + (x - 1) + " " + (y) +
                            "], " + "[" + (x) + " " + (y + 1) +
                            "], " + "[" + (x - 1) + " " + (y + 1) +
                            "], " + "[" + (x + 1) + " " + (y) +
                            "], " + "[" + (x + 1) + " " + (y + 1) +
                            "]");
                }
            } else {
                gameInfo.well.unload();
                if (x - 1 < 0) {
                    gameInfo.map.positions[x][y].setHasFood(true);
                    gameInfo.map.positions[x + 1][y].setHasFood(true);
                    gameInfo.map.positions[x][y - 1].setHasFood(true);
                    gameInfo.map.positions[x + 1][y - 1].setHasFood(true);
                    System.out.println("Grass is planted at [" + x + " " + y +
                            "], " + "[" + (x + 1) + " " + (y) +
                            "], " + "[" + (x) + " " + (y - 1) +
                            "], " + "[" + (x + 1) + " " + (y - 1) +
                            "]");
                } else if (x + 1 == gameInfo.map.getSize()) {
                    gameInfo.map.positions[x][y].setHasFood(true);
                    gameInfo.map.positions[x][y - 1].setHasFood(true);
                    gameInfo.map.positions[x - 1][y - 1].setHasFood(true);
                    gameInfo.map.positions[x - 1][y].setHasFood(true);
                    System.out.println("Grass is planted at [" + x + " " + y +
                            "], " + "[" + (x) + " " + (y - 1) +
                            "], " + "[" + (x - 1) + " " + (y - 1) +
                            "], " + "[" + (x - 1) + " " + (y) +
                            "]");
                } else {
                    gameInfo.map.positions[x][y].setHasFood(true);
                    gameInfo.map.positions[x - 1][y].setHasFood(true);
                    gameInfo.map.positions[x][y - 1].setHasFood(true);
                    gameInfo.map.positions[x - 1][y - 1].setHasFood(true);
                    gameInfo.map.positions[x + 1][y].setHasFood(true);
                    gameInfo.map.positions[x + 1][y - 1].setHasFood(true);
                    System.out.println("Grass is planted at [" + x + " " + y +
                            "], " + "[" + (x - 1) + " " + (y) +
                            "], " + "[" + (x) + " " + (y - 1) +
                            "], " + "[" + (x - 1) + " " + (y - 1) +
                            "], " + "[" + (x + 1) + " " + (y) +
                            "], " + "[" + (x + 1) + " " + (y - 1) +
                            "]");
                }
            }
        }
        return 0;
    }

    public void pickupProduct(int x, int y){
        gameInfo.map.positions[x][y].products.remove(0);
        System.out.println("Product at [" + x + " " + y + "] is picked up!!");
    }

    public void pickupCage(int x, int y){
        gameInfo.map.positions[x][y].products.remove(0);
        System.out.println("Wild animal in cage at [" + x + " " + y + "] is picked up!!");
    }

    public void well(){
        gameInfo.money -= 50;
        gameInfo.well.load();
    }

    public void wellUpgrade(){
        gameInfo.money -= 250 * gameInfo.well.getLevel();
        gameInfo.well.upgrade();
    }

    public void upgrade(Upgradable upgradable){
        upgradable.upgrade();
    }

    public void print(Printable printable){
        printable.printInfo();
    }
}

class Truck{

}

class Hellicopter{

}



class MainGame extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {

        Group root = new Group();
        Scene scene1 = new Scene(root, 1000, 1000);
        Orders orders = new Orders();
        primaryStage.setTitle("Farm Crazy");


        scene1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int pNum = -1;
                if (event.getX() > 350 && event.getY() > 300 && event.getX()<1000 && event.getY()<600){
                    pNum = orders.plant((int)event.getX() , (int)event.getY());
                }
                //System.out.println(pNum);

                /*Label emptyWell = new Label("Well is full");
                emptyWell.setTextFill(Color.BLUE);
                emptyWell.setFont(Font.font(24));
                emptyWell.relocate(500, 50);
                *//*root.getChildren().add(emptyWell);*//*
                final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
                        new EventHandler()
                        {
                            @Override
                            public void handle(Event event) {
                                emptyWell.setText("Well is empty!");
                                emptyWell.relocate(500, 70);
                            }
                        }));*/
                //System.out.println((int)event.getX());


                Image grassImage = new Image(Main.class.getResourceAsStream("grass.png"));
                ArrayList<ImageView> graasViewOne = new ArrayList<>(3);
                ArrayList<ImageView> graasViewTwo = new ArrayList<>(3);
                ArrayList<ImageView> graasViewThree = new ArrayList<>(3);
                for (int i = 0 ; i < 3 ; i++){
                    graasViewOne.add(new ImageView(grassImage));
                    graasViewOne.get(i).setFitWidth(70);
                    graasViewOne.get(i).setFitHeight(70);
                    graasViewTwo.add(new ImageView(grassImage));
                    graasViewTwo.get(i).setFitWidth(70);
                    graasViewTwo.get(i).setFitHeight(70);
                    graasViewThree.add(new ImageView(grassImage));
                    graasViewThree.get(i).setFitWidth(70);
                    graasViewThree.get(i).setFitHeight(70);
                }
                //ImageView grassView = new ImageView(grassImage);
                /*grassView.setFitWidth(70);
                grassView.setFitHeight(70);*/
                int e = 0;

                if(pNum == 0) {
                    /*emptyWell.setText("");
                    emptyWell.setText("Well is full");*/
                    for (ImageView s : graasViewOne){
                        s.setX(event.getX() + e);
                        s.setY(event.getY());
                        root.getChildren().add(s);
                        e += 40;
                    }
                    e = 0;
                    for (ImageView s : graasViewTwo){
                        s.setX(event.getX() + e);
                        s.setY(event.getY() + 40);
                        root.getChildren().add(s);
                        e += 40;
                    }
                    e = 0;
                    for (ImageView s : graasViewThree){
                        s.setX(event.getX() + e);
                        s.setY(event.getY() + 80);
                        root.getChildren().add(s);
                        e += 40;
                    }

                    /*grassView.setX(event.getX());
                    grassView.setY(event.getY());
                    root.getChildren().add(grassView);*/

                }
                else if (pNum == -1){
                    //root.getChildren().remove(emptyWell);
                }
                else if (pNum == -2){
                    //timeline.play();
                    /*emptyWell.setText("");
                    emptyWell.setText("Well is empty!");*/
                }
            }
        });

/*
        Label exitLabel = new Label("EXIT");
        Rectangle exit = new Rectangle(60, 70);
        exit.setX(60);
        exit.setY(60);
        exit.setFill(Color.BLACK);
        exitLabel.setLabelFor(exit);
        root.getChildren().add(exit);
        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.close();
            }
        });*/





       /* Button exitButton = new Button("Exit");
        exitButton.relocate(500 , 500);
        root.getChildren().add(exitButton);
        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.close();
            }
        });*/
        /*primaryStage.setScene(scene1);
        primaryStage.show();*/


        AudioClip audioClip = new AudioClip(this.getClass().getResource("main.mp3").toString());
        audioClip.setCycleCount(Animation.INDEFINITE);
        audioClip.play();



        Image mapImage = new Image(Main.class.getResourceAsStream("back.png"));
        ImageView mapView = new ImageView(mapImage);
        mapView.setPreserveRatio(false);
        mapView.setSmooth(true);
        root.getChildren().add(mapView);
        /*mapView.setFitHeight(780);
        mapView.setFitWidth(1400);*/
        mapView.setFitHeight(1000);
        mapView.setFitWidth(1500);
        //mapView.fitWidthProperty();
        //mapView.fitHeightProperty();
        //mapView.setX(100);
        //mapView.setY(100);



        Rectangle emptyShow = new Rectangle(90, 37);
        emptyShow.setFill(Color.BLUE);
        emptyShow.relocate(550, 130);

        Label wellCapacity = new Label();
        wellCapacity.setTextFill(Color.WHITE);
        wellCapacity.setFont(Font.font(16));
        wellCapacity.setLabelFor(emptyShow);
        wellCapacity.relocate(555, 135);

        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent event)
                    {
                        wellCapacity.setText("Capacity: " + String.valueOf(orders.getGameInfo().well.getCapacity()));
                    }
                }));



        root.getChildren().addAll(emptyShow, wellCapacity);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Image wellUpgrade = new Image(Main.class.getResourceAsStream("upgrade.png"));
        ImageView wellUpgradeView = new ImageView(wellUpgrade);
        wellUpgradeView.setFitHeight(50);
        wellUpgradeView.setFitWidth(120);
        wellUpgradeView.setX(750);
        wellUpgradeView.setY(190);
        root.getChildren().add(wellUpgradeView);
        wellUpgradeView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                orders.wellUpgrade();
            }
        });


        //Image wellImage = new Image(Main.class.getResourceAsStream("welll.png"));
        Image animationWell = new Image(Main.class.getResourceAsStream("WellAnimation.png"));
        ImageView wellAnimationImage = new ImageView(animationWell);
        //ImageView wellView = new ImageView(wellImage);
//        wellView.setFitHeight(150);
//        wellView.setFitWidth(150);
//        wellView.setY(115);
//        wellView.setX(620);
        //root.getChildren().add(wellView);

        int COLUMNSW = 8;
        int COUNTW = 3;
        int OFFSET_XW = 2;
        int OFFSET_YW = 10;
        int WIDTHW = 156;
        int HEIGHTW = 113;

        wellAnimationImage.setViewport(new Rectangle2D(OFFSET_XW, OFFSET_YW, WIDTHW, HEIGHTW));

        wellAnimationImage.setX(620);
        wellAnimationImage.setY(130);

        root.getChildren().add(wellAnimationImage);

        wellAnimationImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                final Animation wellAnimation = new SpriteAnimation(
                        wellAnimationImage,
                        Duration.millis(1000),COUNTW, COLUMNSW,
                        OFFSET_XW, OFFSET_YW,
                        WIDTHW, HEIGHTW
                );

                wellAnimation.setCycleCount(5);
                wellAnimation.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        orders.well();
                    }
                });
                wellAnimation.play();
            }
        });




        Circle circle = new Circle(40,40,40);
        Image chickenImage = new Image(Main.class.getResourceAsStream("guinea_fowl.png"));
        circle.setFill(new ImagePattern(chickenImage));
        root.getChildren().add(circle);

        Rectangle chickenDescription = new Rectangle(128, 90);
        Image descImage = new Image(Main.class.getResourceAsStream("4.png"));
        chickenDescription.setFill(new ImagePattern(descImage));
        chickenDescription.relocate(40, 80);
        Label chickenLabel = new Label();
        Chicken chicken = orders.getGameInfo().chicken;
        chickenLabel.setText(chicken.getInfo());
        chickenLabel.setLabelFor(chickenDescription);
        chickenLabel.relocate(48, 110);
        chickenLabel.setTextFill(Color.BROWN);



        circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().addAll(chickenDescription, chickenLabel);
                /*circle.setFill(Color.BLACK);
                circle.setOpacity(2);*/
            }
        });

        chickenDescription.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(chickenDescription, chickenLabel);
            }
        });

        chickenDescription.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(chickenDescription, chickenLabel);
            }
        });

        circle.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(chickenDescription, chickenLabel);
                //circle.setFill(new ImagePattern(chickenImage));
            }
        });

        circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Pet chicken = orders.buyChicken();
                    Image chickenImage = new Image(Main.class.getResourceAsStream("guinea_fowl_map.png"));
                    ImageView chickenView = new ImageView(chickenImage);
                    chickenView.setFitWidth(90);
                    chickenView.setFitHeight(90);
                    chickenView.setY(Math.random()*350 + 300);
                    chickenView.setX(Math.random()*770 + 300);
                    chickenView.toFront();

                    chicken.move();

                    root.getChildren().add(chickenView);








                   if(chicken.makeProduct()){
                      //Image
                    }




//                    Rectangle rectangle = new Rectangle(300 , 300 , 400 , 400);
//                    PathTransition pathTransition = new PathTransition();
//                    pathTransition.setNode(chickenView);
//                    pathTransition.setDuration(Duration.seconds(10));
//                    pathTransition.setPath(rectangle);
//                    pathTransition.setCycleCount(PathTransition.INDEFINITE);
//                    pathTransition.play();





                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });








        Circle circle4 = new Circle(120,40,40);
        Image cowImage = new Image(Main.class.getResourceAsStream("buffalo.png"));
        circle4.setFill(new ImagePattern(cowImage));
        root.getChildren().add(circle4);

        Rectangle cowDescription = new Rectangle(120, 90);
        //Image descImage = new Image(Main.class.getResourceAsStream("4.png"));
        cowDescription.setFill(new ImagePattern(descImage));
        cowDescription.relocate(120, 80);
        Label cowLabel = new Label();
        Cow cow = orders.getGameInfo().cow;
        //Chicken chicken = orders.buyChicken();
        cowLabel.setText(cow.getInfo());
        cowLabel.setLabelFor(cowDescription);
        cowLabel.relocate(127, 110);
        cowLabel.setTextFill(Color.BROWN);



        circle4.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().addAll(cowDescription, cowLabel);
                /*circle.setFill(Color.BLACK);
                circle.setOpacity(2);*/
            }
        });

        cowDescription.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(cowDescription, cowLabel);
            }
        });

        cowDescription.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(cowDescription, cowLabel);
            }
        });

        circle4.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(cowDescription, cowLabel);
                //circle.setFill(new ImagePattern(chickenImage));
            }
        });

        circle4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Cow cowInGame = orders.buyCow();
                    Image cowImage = new Image(Main.class.getResourceAsStream("cow.png"));
                    ImageView cowView = new ImageView(cowImage);
                    cowView.setX(Math.random()*770 + 300);
                    cowView.setY(Math.random()*350 + 300);
                    cowView.setFitHeight(150);
                    cowView.setFitWidth(150);
                    root.getChildren().add(cowView);






                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });






        Circle circle6 = new Circle(200,40,40);
        Image ostrichImage = new Image(Main.class.getResourceAsStream("ostrich.png"));
        circle6.setFill(new ImagePattern(ostrichImage));
        root.getChildren().add(circle6);

        Rectangle ostrichDescription = new Rectangle(120, 90);
        //Image descImage = new Image(Main.class.getResourceAsStream("4.png"));
        ostrichDescription.setFill(new ImagePattern(descImage));
        ostrichDescription.relocate(200, 80);
        Label ostrichLabel = new Label();
        Ostrich ostrich = orders.getGameInfo().ostrich;
        //Chicken chicken = orders.buyChicken();
        ostrichLabel.setText(ostrich.getInfo());
        ostrichLabel.setLabelFor(cowDescription);
        ostrichLabel.relocate(205, 110);
        ostrichLabel.setTextFill(Color.BROWN);



        circle6.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().addAll(ostrichDescription, ostrichLabel);
                /*circle.setFill(Color.BLACK);
                circle.setOpacity(2);*/
            }
        });

        ostrichDescription.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(ostrichDescription, ostrichLabel);
            }
        });

        ostrichDescription.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(ostrichDescription, ostrichLabel);
            }
        });

        circle6.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(ostrichDescription, ostrichLabel);
                //circle.setFill(new ImagePattern(chickenImage));
            }
        });

        circle6.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    orders.buyOstrich();
                    Image ostrichImage = new Image(Main.class.getResourceAsStream("OstrichGame.png"));
                    ImageView ostrichView = new ImageView(ostrichImage);
                    ostrichView.setX(Math.random()*770 + 300);
                    ostrichView.setY(Math.random()*350 + 300);
                    ostrichView.setFitWidth(150);
                    ostrichView.setFitHeight(150);

                    root.getChildren().add(ostrichView);






                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //Circle currentMoney = new Circle(1275, 75, 70);
        Rectangle moneyCurrent = new Rectangle(120, 40);
        moneyCurrent.relocate(1245, 0);
        Image moneyImage = new Image(Main.class.getResourceAsStream("money.png"));
        moneyCurrent.setFill(new ImagePattern(moneyImage));
        //currentMoney.setFill(new ImagePattern(moneyImage));
        //root.getChildren().add(currentMoney);
        final Label money = new Label();
        money.setTextFill(Color.YELLOW);
        final Timeline MoneyTimeline = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent event)
                    {
                        money.setText(String.valueOf(orders.getGameInfo().money));
                    }
                }));
        MoneyTimeline.setCycleCount(Animation.INDEFINITE);
        MoneyTimeline.play();
        money.relocate(1300, 12);
        money.setLabelFor(moneyCurrent);
        root.getChildren().addAll(moneyCurrent, money);


        Rectangle exitButton = new Rectangle(120, 50);
        Image exitImage = new Image(Main.class.getResourceAsStream("exit.png"));
        exitButton.setFill(new ImagePattern(exitImage));
        exitButton.relocate(1245, 45);
        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.close();
            }
        });
        root.getChildren().add(exitButton);




        Image truckImage = new Image(Main.class.getResourceAsStream("01.png"));
        ImageView truckView = new ImageView(truckImage);
        truckView.setX(400);
        truckView.setY(700);
        truckView.setFitWidth(200);
        truckView.setFitHeight(200);
        root.getChildren().add(truckView);








// for animation of egg powder workshop

        Image flouryCakeWS = new Image(Main.class.getResourceAsStream("eggp.png"));
        ImageView eggPowderWSView = new ImageView(flouryCakeWS);

        int COLUMNS1 = 8;
        int COUNT1 = 3;
        int OFFSET_X1 = 10;
        int OFFSET_Y1 = 0;
        int WIDTH1 = 131;
        int HEIGHT1 = 104;

        eggPowderWSView.setViewport(new Rectangle2D(OFFSET_X1, OFFSET_Y1, WIDTH1, HEIGHT1));

        eggPowderWSView.setX(150);
        eggPowderWSView.setY(280);

        root.getChildren().add(eggPowderWSView);


        eggPowderWSView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // if ... rules should be written!

                final Animation eggpWSanimation = new SpriteAnimation(
                        eggPowderWSView,
                        Duration.millis(1000),COUNT1, COLUMNS1,
                        OFFSET_X1, OFFSET_Y1,
                        WIDTH1, HEIGHT1
                );
                eggpWSanimation.setCycleCount(5);
                eggpWSanimation.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Image flour = new Image(Main.class.getResourceAsStream("EggPowder.png"));
                        ImageView flourView = new ImageView(flour);
                        flourView.setX(270);
                        flourView.setY(360);

                        root.getChildren().add(flourView);
                        flourView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                // add from warehouse
                               Image flourIcon = new Image(Main.class.getResourceAsStream("EggPowder.png"));

                                Rectangle rec = new Rectangle(400, 900, 25, 25);
                                rec.setFill(new ImagePattern(flourIcon));

                                root.getChildren().add(rec);
                                root.getChildren().remove(flourView);

                            }
                        });
                    }
                });
                eggpWSanimation.play();
            }
        });



        // for animation of cookie workshop


        // for adding workshop

        Image cookieWS = new Image(Main.class.getResourceAsStream("cakeW.png"));
        ImageView cookieWSview = new ImageView(cookieWS);

        int COLUMNS2 = 8;
        int COUNT2 = 3;
        int OFFSET_X2 = 10;
        int OFFSET_Y2 = 10;
        int WIDTH2 = 131;
        int HEIGHT2 = 140;
        cookieWSview.setViewport(new Rectangle2D(OFFSET_X2, OFFSET_Y2, WIDTH2, HEIGHT2));

        cookieWSview.setX(1210);
        cookieWSview.setY(460);

        root.getChildren().add(cookieWSview);


        cookieWSview.setOnMouseClicked(new EventHandler<MouseEvent>() {


            @Override
            public void handle(MouseEvent event) {

                // for working
                final Animation cookieWSanimation = new SpriteAnimation(
                        cookieWSview,
                        Duration.millis(1000),COUNT2, COLUMNS2,
                        OFFSET_X2, OFFSET_Y2,
                        WIDTH2, HEIGHT2
                );

                cookieWSanimation.setCycleCount(5);
                cookieWSanimation.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Image cake = new Image(Main.class.getResourceAsStream("Cake.png"));
                        ImageView cakeView = new ImageView(cake);
                        cakeView.setX(1180);
                        cakeView.setY(500);

                        root.getChildren().add(cakeView);

                        cakeView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                Image cakeIcon = new Image(Main.class.getResourceAsStream("Cake.png"));

                                Rectangle rec = new Rectangle(400, 900, 25, 25);
                                rec.setFill(new ImagePattern(cakeIcon));

                                root.getChildren().add(rec);
                                root.getChildren().remove(cakeView);

                            }
                        });

                    }
                });

                cookieWSanimation.play();
            }
        });




        // for animation of Floury cake workshop

        Image FlouryCakeWS = new Image(Main.class.getResourceAsStream("flouryCakeWS.png"));
        ImageView flouryCakeWSView = new ImageView(FlouryCakeWS);

        int COLUMNS3 = 8;
        int COUNT3 = 2;
        int OFFSET_X3 = 0;
        int OFFSET_Y3 = 10;
        int WIDTH3 = 170;
        int HEIGHT3 = 152;
        flouryCakeWSView.setViewport(new Rectangle2D(OFFSET_X3, OFFSET_Y3, WIDTH3, HEIGHT3));

        flouryCakeWSView.setX(1100);
        flouryCakeWSView.setY(220);

        root.getChildren().add(flouryCakeWSView);


        flouryCakeWSView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // if ... rules should be written!

                final Animation FlouryCakeAnimation = new SpriteAnimation(
                        flouryCakeWSView,
                        Duration.millis(1000),COUNT3, COLUMNS3,
                        OFFSET_X3, OFFSET_Y3,
                        WIDTH3, HEIGHT3

                );
                FlouryCakeAnimation.setCycleCount(6);
                FlouryCakeAnimation.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Image flouryCake = new Image(Main.class.getResourceAsStream("FlouryCake.png"));
                        ImageView flouryCakeView = new ImageView(flouryCake);
                        flouryCakeView.setX(1100);
                        flouryCakeView.setY(340);

                        root.getChildren().add(flouryCakeView);
                        flouryCakeView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                // add from warehouse
                                Image flouryCakeIcon = new Image(Main.class.getResourceAsStream("FlouryCake.png"));

                                Rectangle rec = new Rectangle(400, 900, 25, 25);
                                rec.setFill(new ImagePattern(flouryCakeIcon));

                                root.getChildren().add(rec);
                                root.getChildren().remove(flouryCakeView);

                            }
                        });
                    }
                });
                FlouryCakeAnimation.play();
            }
        });




        // for animation of Spinnery workshop

        Image SpinneryWS = new Image(Main.class.getResourceAsStream("spinneryWS.png"));
        ImageView SpinView = new ImageView(SpinneryWS);

        int COLUMNS4 = 8;
        int COUNT4 = 2;
        int OFFSET_X4 = 10;
        int OFFSET_Y4 = 1;
        int WIDTH4 = 130;
        int HEIGHT4 = 100;
        SpinView.setViewport(new Rectangle2D(OFFSET_X4, OFFSET_Y4, WIDTH4, HEIGHT4));

        SpinView.setX(130);
        SpinView.setY(630);

        root.getChildren().add(SpinView);


        SpinView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // if ... rules should be written!

                final Animation SpinneryAnimation = new SpriteAnimation(
                        SpinView,
                        Duration.millis(1000),COUNT4, COLUMNS4,
                        OFFSET_X4, OFFSET_Y4,
                        WIDTH4, HEIGHT4

                );
                SpinneryAnimation.setCycleCount(5);
                SpinneryAnimation.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Image sewing = new Image(Main.class.getResourceAsStream("Sewing.png"));
                        ImageView sewingView = new ImageView(sewing);
                        sewingView.setX(220);
                        sewingView.setY(705);

                        root.getChildren().add(sewingView);
                        sewingView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                // add from warehouse
                                Image sewingIcon = new Image(Main.class.getResourceAsStream("Sewing.png"));

                                Rectangle rec = new Rectangle(400, 900, 25, 25);
                                rec.setFill(new ImagePattern(sewingIcon));

                                root.getChildren().add(rec);
                                root.getChildren().remove(sewingView);

                            }
                        });
                    }
                });
                SpinneryAnimation.play();
            }
        });





        // for adding sewing factory

        Image sewingFactory = new Image(Main.class.getResourceAsStream("SewingFactory.png"));
        ImageView SewingFactoryView = new ImageView(sewingFactory);

        int COLUMNS5 = 8;
        int COUNT5 = 2;
        int OFFSET_X5 = 10;
        int OFFSET_Y5 = 5;
        int WIDTH5 = 170;
        int HEIGHT5 = 120;
        SewingFactoryView.setViewport(new Rectangle2D(OFFSET_X5, OFFSET_Y5, WIDTH5, HEIGHT5));

        SewingFactoryView.setX(150);
        SewingFactoryView.setY(420);

        root.getChildren().add(SewingFactoryView);


        SewingFactoryView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // if ... rules should be written!

                final Animation SewingAnimation = new SpriteAnimation(
                        SewingFactoryView,
                        Duration.millis(1000),COUNT5, COLUMNS5,
                        OFFSET_X5, OFFSET_Y5,
                        WIDTH5, HEIGHT5

                );
                SewingAnimation.setCycleCount(5);
                SewingAnimation.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Image fabric = new Image(Main.class.getResourceAsStream("CarnivalDress.png"));
                        ImageView fabricView = new ImageView(fabric);
                        fabricView.setX(260);
                        fabricView.setY(550);

                        root.getChildren().add(fabricView);
                        fabricView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                // add from warehouse
                                Image sewingIcon = new Image(Main.class.getResourceAsStream("CarnivalDress.png"));

                                Rectangle rec = new Rectangle(400, 900, 25, 25);
                                rec.setFill(new ImagePattern(sewingIcon));

                                root.getChildren().add(rec);
                                root.getChildren().remove(fabricView);

                            }
                        });
                    }
                });
                SewingAnimation.play();
            }
        });




        // for weaving factory

        Image weavingWS = new Image(Main.class.getResourceAsStream("weaving.png"));
        ImageView weavingWSview = new ImageView(weavingWS);

        int COLUMNS6 = 8;
        int COUNT6 = 3;
        int OFFSET_X6 = 1;
        int OFFSET_Y6 = 9;
        int WIDTH6 = 170;
        int HEIGHT6 = 105;
        weavingWSview.setViewport(new Rectangle2D(OFFSET_X6, OFFSET_Y6, WIDTH6, HEIGHT6));

        weavingWSview.setX(1150);
        weavingWSview.setY(640);

        root.getChildren().add(weavingWSview);


        weavingWSview.setOnMouseClicked(new EventHandler<MouseEvent>() {


            @Override
            public void handle(MouseEvent event) {

                // for working
                final Animation weavingWSAnimation = new SpriteAnimation(
                        weavingWSview,
                        Duration.millis(1000),COUNT6, COLUMNS6,
                        OFFSET_X6, OFFSET_Y6,
                        WIDTH6, HEIGHT6
                );

                weavingWSAnimation.setCycleCount(5);
                weavingWSAnimation.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Image fabric = new Image(Main.class.getResourceAsStream("Fabric.png"));
                        ImageView fabricView = new ImageView(fabric);
                        fabricView.setX(1100);
                        fabricView.setY(680);

                        root.getChildren().add(fabricView);
                        fabricView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                // add from warehouse
                                Image sewingIcon = new Image(Main.class.getResourceAsStream("Fabric.png"));

                                Rectangle rec = new Rectangle(400, 900, 25, 25);
                                rec.setFill(new ImagePattern(sewingIcon));

                                root.getChildren().add(rec);
                                root.getChildren().remove(fabricView);

                            }
                        });

                    }
                });

                weavingWSAnimation.play();
            }
        });
















        Image wareHouseImage = new Image(Main.class.getResourceAsStream("1.png"));
        ImageView wareHouseView = new ImageView(wareHouseImage);
        wareHouseView.setFitHeight(250);
        wareHouseView.setFitWidth(250);
        wareHouseView.setY(665);
        wareHouseView.setX(590);
        root.getChildren().add(wareHouseView);
        wareHouseView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });

        Rectangle timeBox = new Rectangle(125, 34);
        Image timeShape = new Image(Main.class.getResourceAsStream("btn.png"));
        timeBox.setFill(new ImagePattern(timeShape));
        timeBox.relocate(1236, 103);
        Label currentTime = new Label();
        currentTime.setLabelFor(timeBox);
        currentTime.relocate(1249, 110);
        currentTime.setTextFill(Color.BLACK);
        currentTime.toFront();
        root.getChildren().addAll(timeBox,currentTime);
        long startSecond = System.currentTimeMillis();
        Timeline timeShow = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler()
                {
                    @Override
                    public void handle(Event event) {
                        //currentTime.setText(String.valueOf(System.currentTimeMillis()));
                        final long timePassed = (System.currentTimeMillis() - startSecond)/1000;
                        //currentTime.setText(String.valueOf("Time Passed: "+((System.currentTimeMillis() - startSecond)/1000) + "s"));
                        currentTime.setText(String.valueOf("Time Passed: "+ timePassed + " s"));
                    }
                }));
        timeShow.setCycleCount(Animation.INDEFINITE);
        timeShow.play();


        Circle circle1 = new Circle(250, 40 ,15);
        circle1.setFill(Color.RED);
        root.getChildren().addAll(circle1);
        Timeline modeShow = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler()
                {
                    @Override
                    public void handle(Event event) {
                        final long timePassed = (System.currentTimeMillis() - startSecond)/1000;
                        if (timePassed % 3 == 0){
                            circle1.setFill(Color.GREEN);
                            orders.turn(1);
                        }
                        else if (timePassed % 3 != 0) circle1.setFill(Color.RED);
                    }
                }));
        modeShow.setCycleCount(Animation.INDEFINITE);
        modeShow.play();

        Image pauseImage = new Image(Main.class.getResourceAsStream("pause.png"));
        ImageView pauseView = new ImageView(pauseImage);
        pauseView.relocate(1210, 5);
        pauseView.setFitWidth(40);
        pauseView.setFitHeight(40);
        root.getChildren().add(pauseView);

        Image pauseBack = new Image(Main.class.getResourceAsStream("pausebg.png"));
        ImageView pauseBgView = new ImageView(pauseBack);
        pauseBgView.relocate(450, 150);
        pauseBgView.setFitHeight(500);
        pauseBgView.setFitWidth(500);



        pauseView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().add(pauseBgView);
                /*AnchorPane pauseMenu = new AnchorPane();
                Scene pauseScene = new Scene(pauseMenu, 400, 500);
                Stage pauseStage = new Stage();
                pauseStage.setScene(pauseScene);*/
                modeShow.pause();
                timeShow.pause();
                audioClip.stop();

                /*pauseStage.toFront();
                pauseStage.show();*/
            }
        });

        Button button = new Button();
        /*Rectangle rt = new Rectangle(90, 30);
        rt.setFill(Color.RED);
        rt.relocate(90, 30);
        root.getChildren().addAll(rt);
        button.setShape(rt);*/
        button.relocate(280, 27);
        button.setText("Open Chat Room");

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Label secondLabel = new Label("There will be a chatroom here soon!");

                BorderPane secondaryLayout = new BorderPane();

                Image chatBack = new Image(Main.class.getResourceAsStream("table.png"));

                Scene secondScene = new Scene(secondaryLayout, 500, 500);

                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("ChatRoom");
                newWindow.setScene(secondScene);

                // Set position of second window, related to primary window.
                newWindow.setX(primaryStage.getX() + 200);
                newWindow.setY(primaryStage.getY() + 100);


                ImageView chatView = new ImageView(chatBack);
                chatView.setPreserveRatio(false);
                chatView.setSmooth(true);
                secondaryLayout.getChildren().addAll(chatView, secondLabel);
                chatView.setFitHeight(secondScene.getHeight());
                chatView.setFitWidth(secondScene.getWidth());


                newWindow.show();
            }
        });

        root.getChildren().addAll(button);










        // for animation

       // Image image1 = new Image(new FileInputStream("/Users/macbookpro/Desktop/Textures/Animals/Africa/Cat/left.png"));
       // ImageView imageView = new ImageView(chickenImage);
//        imageView.setX(20);
//        imageView.setY(20);
//
//        root.getChildren().add(imageView);
//
//        imageView.setViewport(new Rectangle2D(0,0,820,300));
//
//        final Animation animation = new SpriteAnimation(
//                imageView,
//                Duration.millis(7000),
//                24, 4,
//                0, 0,
//                // 64=829/13
//                100, 50
//        );
//        animation.setCycleCount(Animation.INDEFINITE);
//        animation.play();


        primaryStage.setScene(scene1);
        primaryStage.setResizable(true);
        primaryStage.setFullScreen(true);
        //primaryStage.setResizable(true);
        primaryStage.show();

    }






}

//class Move implements Initializable{
//
//
//    public void initialize(URL url , ResourceBundle resourceBundle){
//
//
//
//        PathTransition pathTransition = new PathTransition();
//        pathTransition.setNode();
//        pathTransition.setDuration(Duration.INDEFINITE);
//        pathTransition.setPath();
//
//    }
//}



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane menuRoot = new Pane();

        menuRoot.setPrefSize(1050, 600);
        primaryStage.setTitle("Farm Crazy");

        try (InputStream is = getClass().getResourceAsStream("menu.jpg")) {
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(1500);
            img.setFitHeight(1000);
            menuRoot.getChildren().add(img);
        } catch (IOException e) {
            System.out.println("Couldn't load image");
        }

        Title title = new Title(" F A R M   C R A Z Y ");
        title.setTranslateX(50);
        title.setTranslateY(200);

        MenuBox vbox = new MenuBox();
        MenuItem startButton = new MenuItem("Start");
        MenuItem loadButton = new MenuItem("Load");
        MenuItem exitButton = new MenuItem("Exit");
        vbox.getChildren().addAll(startButton, loadButton, exitButton);
        vbox.setTranslateX(100);
        vbox.setTranslateY(300);

        menuRoot.getChildren().addAll(title, vbox);


        Scene scene = new Scene(menuRoot);
        primaryStage.setTitle("VIDEO GAME");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        /*Thread musicp = new Thread(new Runnable() {
            @Override
            public void run() {
                String path = Main.class.getResource("/sm.mp3").toString();
                Media media = new Media(path);
                MediaPlayer mp = new MediaPlayer(media);
                mp.setCycleCount(Timeline.INDEFINITE);
                mp.play();
            }
        });
        musicp.start();*/
        String path = Main.class.getResource("/sm.wav").toString();
        Media media = new Media(path);
        MediaPlayer mp = new MediaPlayer(media);
        mp.setCycleCount(Timeline.INDEFINITE);
        mp.play();
        primaryStage.show();


        startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    new MainGame().start(primaryStage);
                    mp.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.close();
            }
        });

    }




    private static class Title extends StackPane{
        public Title(String name) {
            Rectangle bg = new Rectangle(375, 60);
            bg.setStroke(Color.WHITE);
            bg.setStrokeWidth(2);
            bg.setFill(null);

            Text text = new Text(name);
            text.setFill(Color.BLACK);
            text.setFont(Font.font("sara" , 30));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg,text);
        }
    }

    private static class MenuBox extends VBox{
        public MenuBox(Main.MenuItem...items) {
            getChildren().add(createSeperator());

            for(Main.MenuItem item : items) {
                getChildren().addAll(item, createSeperator());
            }
        }

        private Line createSeperator() {
            Line sep = new Line();
            sep.setEndX(210);
            sep.setStroke(Color.DARKGREY);
            return sep;
        }

    }


    private static class MenuItem extends StackPane{
        public MenuItem(String name) {
            LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] {
                    new Stop(0, Color.DARKBLUE),
                    new Stop(0.1, Color.BLACK),
                    new Stop(0.9, Color.BLACK),
                    new Stop(1, Color.DARKBLUE)

            });

            Rectangle bg = new Rectangle(200,30);
            bg.setOpacity(0.4);

            Text text = new Text(name);
            text.setFill(Color.DARKGREY);
            text.setFont(javafx.scene.text.Font.font(30));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);
            setOnMouseEntered(event -> {
                bg.setFill(gradient);
                text.setFill(Color.BLACK);

            });

            setOnMouseExited(event -> {
                bg.setFill(Color.BLACK);
                text.setFill(Color.DARKGREY);
            });
            setOnMousePressed(event -> {
                bg.setFill(Color.DARKVIOLET);

            });

            setOnMouseReleased(event -> {
                bg.setFill(gradient);
            });




        }
    }








    public static void main(String[] args){
        launch(args);
    }

}




class SpriteAnimation extends Transition {
    private final ImageView imageView;
    private final int count;
    private final int columns;
    private final int offsetX;
    private final int offsetY;
    private final int width;
    private final int height;

    private int lastIndex;

    public SpriteAnimation(
            ImageView imageView,
            Duration duration,
            int count, int columns,
            int offsetX, int offsetY,
            int width, int height) {
        this.imageView = imageView;
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    protected void interpolate(double k) {
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        if (index != lastIndex) {
            final int x = (index % columns) * width + offsetX;
            final int y = (index / columns) * height + offsetY;
            imageView.setViewport(new Rectangle2D(x, y, width, height));
            lastIndex = index;
        }
    }
}

