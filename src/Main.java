import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import javax.sound.sampled.*;
import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;


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
        level = 0;
    }

    public static Well getclass(){
        return singleton;
    }

    public void upgrade(){
        level++;
        capacityUnit += 3;
        capacity = capacityUnit;
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
        switch (random.nextInt(map.getSize())){

            case Constants.EAST:
                if (position.getX() + 1 < map.getSize()) {
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setX(position.getX() + 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    map.printInfo();
                }
                else {
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setX(position.getX() - 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    map.printInfo();
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
                    map.printInfo();
                }
                else {
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setY(position.getY() + 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    map.printInfo();
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
                    map.printInfo();
                }
                else {
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setY(position.getY() - 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    map.printInfo();
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
                    map.printInfo();
                }
                else {
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setX(position.getX() + 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    map.printInfo();
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
                    map.printInfo();
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
                    map.printInfo();
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
                    map.printInfo();
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
                    map.printInfo();
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
                    map.printInfo();
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
                    map.printInfo();
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
                    map.printInfo();
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
                    map.printInfo();
                }
                break;

            default:
                System.out.println("nothing!");break;

        }
    }
}

enum TypeOfPet {
    Chicken
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

    public abstract void makeProduct();

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

    public void printInfo(){
        System.out.println("Chicken number: " + namedLabel + "{\n\t" +
                "price: " + price + "\n\t" +
                "food capacity: " + feedCapacity + "\n\t" +
                "level: " + level + "\n\t" +
                "feed capacity unit: " + feedCapacityUnit + "\n}");
    }

    @Override
    public void makeProduct() {
        if (feedCapacity == feedCapacityUnit){
            egg = new Egg();
            map.getPositions()[position.getX()][position.getY()].addProduct(egg);
            System.out.println("an egg is produced and placed in [" + position.getX() + " " +
                    position.getY() + "]");
            feedCapacity = 0;
        }
    }

    @Override
    public void upgrade(){
        level++;
        feedCapacityUnit--;
        price += 50;
        System.out.println("Chicken is upgraded to level " + level);
    }
}

class Product{

}

class Egg extends Product{


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
    int initialMoney = 350;
    int money;
    Map map = new Map(10);
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
    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public void buyChicken() throws FileNotFoundException {
        Pet chicken = new Chicken(gameInfo.map, chickenLabel);
        chickenLabel++;
        gameInfo.pets.add(chicken);
        gameInfo.money -= chicken.price;
        System.out.println("A chicken is bought! and money = " + gameInfo.money);




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

    public void plant(int x, int y) {
        if (x > 9 || y > 9) System.out.println("Entered Numbers are illigal!!!");
        else {
            if (x - 1 >= 0 && x + 1 < gameInfo.map.getSize() &&
                    y - 1 >= 0 && y + 1 < gameInfo.map.getSize()) {
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
        gameInfo.well.load();
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



class ChickenView{


}



public class Main extends Application {






    private Parent createContent() throws IOException {
        Pane root = new Pane();

        root.setPrefSize(1050, 600);

        try (InputStream is = getClass().getResourceAsStream("menu.jpg")) {
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(1500);
            img.setFitHeight(1000);
            root.getChildren().add(img);
        } catch (IOException e) {
            System.out.println("Couldn't load image");
        }

        Title title = new Title(" F A R M   C R A Z Y ");
        title.setTranslateX(50);
        title.setTranslateY(200);

        MenuBox vbox = new MenuBox(
                new MenuItem("Start"),
                new MenuItem("Load"),
                new MenuItem("Exit"));
        vbox.setTranslateX(100);
        vbox.setTranslateY(300);

        root.getChildren().addAll(title, vbox);

        return root;


    }







    @Override
    public void start(Stage primaryStage) throws Exception {


        Group root = new Group();





























        Scene scene = new Scene(createContent());
        primaryStage.setTitle("VIDEO GAME");
        primaryStage.setScene(scene);
        primaryStage.show();







        Scene scene1 = new Scene(root, 1000, 1000);
        Orders orders = new Orders();
        Map map = new Map(5);
        map.printInfo();
        Chicken chicken = new Chicken(map  , 10);

        //Image wellImage = new Image(new FileInputStream("/Users/sara/Desktop/Textures/Service/Well/01.png"));
        //ImageView wellView = new ImageView(wellImage);
        //wellView.fitHeightProperty();

//        AnchorPane anchorPane = new AnchorPane();
//        anchorPane.getChildren().add(wellView);
//        ImageView app = (ImageView)(Node) anchorPane.getChildren().get(0);
//        app.setX(50);
//        app.setY(50);

        Image mapImage = new Image(Main.class.getResourceAsStream("back.png"));
        ImageView mapView = new ImageView(mapImage);
        root.getChildren().add(mapView);
        mapView.setFitHeight(1000);
        mapView.setFitWidth(1500);
        //mapView.fitWidthProperty();
        //mapView.fitHeightProperty();
        //mapView.setX(100);
        //mapView.setY(100);



        Circle circle = new Circle(40,40,40);
        Image chickenImage = new Image(Main.class.getResourceAsStream("chicken.png"));
        circle.setFill(new ImagePattern(chickenImage));

        root.getChildren().add(circle);

        circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    orders.buyChicken();
                    Image chickenImage = new Image(Main.class.getResourceAsStream("down_left.png"));
                    ImageView chickenView = new ImageView(chickenImage);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });



        //Circle circle1 = new Circle(150,150,50);
        //Image cowImage = new Image(new FileInputStream("/Users/sara/Desktop/Textures/UI/Icons/Products/brown_cow.png"));
        //circle.setFill(new ImagePattern(cowImage));

//        root.getChildren().add(circle1);
//        circle1.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//
//            }
//        });



        // circle.setFill();

        // Circle circle = new Circle(100,100,50);
        //circle.setFill(Color.GRAY);
        // root.getChildren().add(circle);


        // Button chickenButton = new Button("chicken");
        // root.getChildren().add(chickenButton);
        //chickenButton.relocate(100,100);






        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();

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
        Thread musicp = new Thread(new Runnable() {
            @Override
            public void run() {
                String path = Main.class.getResource("/sm.mp3").toString();
                Media media = new Media(path);
                MediaPlayer mp = new MediaPlayer(media);
                mp.setCycleCount(Timeline.INDEFINITE);
                mp.play();
            }
        });
        musicp.start();
        launch(args);

    }

}




