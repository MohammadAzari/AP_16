import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.ImageCursor;
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
        if(level < 4) {
            level++;
            capacityUnit += 3;
            capacity = capacityUnit;
        }
        else System.out.println("There's no upgrade more!");
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
        feedCapacityUnit = 5;
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
        if (level >= 4) System.out.println("There's no more upgrade for guinea fowl!");
        else {
            level++;
            feedCapacityUnit--;
            price += 100;
            System.out.println("Chicken is upgraded to level " + level);
        }
    }
}

class Cow extends Pet implements Upgradable , Printable{

    Product milk;
    int namedLabel;

    public Cow(Map map, int label) {
        super(map);
        namedLabel = label;
        feedCapacityUnit = 10;
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
        if (level >= 4) System.out.println("There's no more upgrade for cow!");
        else {
            level++;
            feedCapacityUnit -= 2;
            price += 200;
            System.out.println("Cow is upgraded to level " + level);
        }
    }


}




class Ostrich extends Pet implements Upgradable , Printable{

    Product wool;
    int namedLabel;

    public Ostrich(Map map, int label) {
        super(map);
        namedLabel = label;
        feedCapacityUnit = 8;
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
        if (level >= 4) System.out.println("There's no more upgrade for ostrich!");
        else {
            level++;
            feedCapacityUnit -= 2;
            price += 300;
            System.out.println("Ostrich is upgraded to level " + level);
        }
    }


}

class WeavingFactory{
    private int price;
    private int level;
    private int cyclesToMakeProduct;
    public WeavingFactory(){
        level = 1;
        price = 350;
        cyclesToMakeProduct = 5;
    }

    public String getInfo(){
        return "Get Wool and make\nFabric!" + "\nlevel: " + level + "\nprice: " + price +
                "\ncyclesToMakeProduct: " + cyclesToMakeProduct;
    }

    public int getLevel() {
        return level;
    }

    public int getCyclesToMakeProduct() {
        return cyclesToMakeProduct;
    }

    public int getPrice() {
        return price;
    }

    public void upgrade(){
        if(level >= 3){
            System.out.println("There's no more upgrade for Weaving Factory!");
        }
        else{
            level++;
            cyclesToMakeProduct--;
            price += 300;
        }
    }
}

class SewingFactory{
    private int price;
    private int level;
    private int cyclesToMakeProduct;
    public SewingFactory(){
        level = 1;
        price = 500;
        cyclesToMakeProduct = 5;
    }

    public String getInfo(){
        return "Get Fabric and make\nCarnival Clothes!" + "\nlevel: " + level + "\nprice: " + price +
                "\ncyclesToMakeProduct: " + cyclesToMakeProduct;
    }

    public int getLevel() {
        return level;
    }

    public int getCyclesToMakeProduct() {
        return cyclesToMakeProduct;
    }

    public int getPrice() {
        return price;
    }

    public void upgrade(){
        if(level >= 3){
            System.out.println("There's no more upgrade for Sewing Factory!");
        }
        else{
            level++;
            cyclesToMakeProduct--;
            price += 300;
        }
    }
}

class SpinneryWS{
    private int price;
    private int level;
    private int cyclesToMakeProduct;
    public SpinneryWS(){
        level = 1;
        price = 400;
        cyclesToMakeProduct = 5;
    }

    public String getInfo(){
        return "Get Wool and make Sewing!" + "\nlevel: " + level + "\nprice: " + price +
                "\ncyclesToMakeProduct: " + cyclesToMakeProduct;
    }

    public int getLevel() {
        return level;
    }

    public int getCyclesToMakeProduct() {
        return cyclesToMakeProduct;
    }

    public int getPrice() {
        return price;
    }

    public void upgrade(){
        if(level >= 3){
            System.out.println("There's no more upgrade for Spinnery Ws!");
        }
        else{
            level++;
            cyclesToMakeProduct--;
            price += 300;
        }
    }
}

class FlouryCakeWS{
    private int price;
    private int level;
    private int cyclesToMakeProduct;
    public FlouryCakeWS(){
        level = 1;
        price = 500;
        cyclesToMakeProduct = 5;
    }

    public String getInfo(){
        return "Get Cake and make\nFloury Cake!" + "\nlevel: " + level + "\nprice: " + price +
                "\ncyclesToMakeProduct: " + cyclesToMakeProduct;
    }

    public int getLevel() {
        return level;
    }

    public int getCyclesToMakeProduct() {
        return cyclesToMakeProduct;
    }

    public int getPrice() {
        return price;
    }

    public void upgrade(){
        if(level >= 3){
            System.out.println("There's no more upgrade for Floury Cake Ws!");
        }
        else{
            level++;
            cyclesToMakeProduct--;
            price += 300;
        }
    }
}

class CakeWS{
    private int price;
    private int level;
    private int cyclesToMakeProduct;
    public CakeWS(){
        level = 1;
        price = 400;
        cyclesToMakeProduct = 5;
    }

    public String getInfo(){
        return "Get Egg Powder and Egg\nand make Cake!" + "\nlevel: " + level + "\nprice: " + price +
                "\ncyclesToMakeProduct: " + cyclesToMakeProduct;
    }

    public int getLevel() {
        return level;
    }

    public int getCyclesToMakeProduct() {
        return cyclesToMakeProduct;
    }

    public int getPrice() {
        return price;
    }

    public void upgrade(){
        if(level >= 3){
            System.out.println("There's no more upgrade for CakeWS!");
        }
        else{
            level++;
            cyclesToMakeProduct--;
            price += 300;
        }
    }
}

class EggPowderWS{
    private int price;
    private int level;
    private int cyclesToMakeProduct;
    public EggPowderWS(){
        level = 1;
        price = 300;
        cyclesToMakeProduct = 5;
    }

    public String getInfo(){
        return "Get Egg and make Egg Powder!" + "\nlevel: " + level + "\nprice: " + price +
                "\ncyclesToMakeProduct: " + cyclesToMakeProduct;
    }

    public int getLevel() {
        return level;
    }

    public int getCyclesToMakeProduct() {
        return cyclesToMakeProduct;
    }

    public int getPrice() {
        return price;
    }

    public void upgrade(){
        if(level >= 3){
            System.out.println("There's no more upgrade for EggPowderWS!");
        }
        else{
            level++;
            cyclesToMakeProduct--;
            price += 300;
        }
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

class Level{
    static int timeReminded = 180;
    //static int timeReminded = 4;
    static int levelNum = 1;

    public int getLevelMoney(){
        if (levelNum == 1) return 800;
        else if (levelNum == 2) return 1400;
        else if (levelNum == 3) return 2000;
        else return 3500;
    }

    public void upLevel(){
        levelNum++;
        if (levelNum == 2){
            timeReminded = 300;
            //timeReminded = 3;
        }
        else if (levelNum == 3){
            timeReminded = 420;
            //timeReminded = 3;
        }
        else if (levelNum == 4){
            //timeReminded = 3;
            timeReminded = 600;
        }
    }
}

class GameInfo{
    Level level = new Level();
    int time;
    int initialMoney = level.getLevelMoney();
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
    int grassChecker = 0;
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
    private static int audioFlag = 0;



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
                if (event.getX() > 350 && event.getY() > 300 && event.getX()<1000 && event.getY()<600 && orders.grassChecker == 0){
                    AudioClip audioClip1 = new AudioClip(this.getClass().getResource("grass.mp3").toString());
                    audioClip1.play();
                    pNum = orders.plant((int)event.getX() , (int)event.getY());
                    orders.grassChecker = 0;
                    System.out.println(orders.grassChecker);
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

                if(pNum == 0 && orders.grassChecker == 0) {
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

        if (audioFlag == 0) {
            audioClip.play();
            audioFlag = 1;
        }


        Image mapImageLevelOne = new Image(Main.class.getResourceAsStream("back.png"));
        ImageView mapViewLevelOne = new ImageView(mapImageLevelOne);
        mapViewLevelOne.setPreserveRatio(false);
        mapViewLevelOne.setSmooth(true);
        mapViewLevelOne.setFitHeight(1000);
        mapViewLevelOne.setFitWidth(1500);

        Image mapImageLevelTwo = new Image(Main.class.getResourceAsStream("back_two.png"));
        ImageView mapViewLevelTwo = new ImageView(mapImageLevelTwo);
        mapViewLevelTwo.setSmooth(true);
        mapViewLevelTwo.setFitHeight(1000);
        mapViewLevelTwo.setFitWidth(1500);

        Image mapImageLevelThree = new Image(Main.class.getResourceAsStream("back_three.png"));
        ImageView mapViewLevelThree = new ImageView(mapImageLevelThree);
        mapViewLevelThree.setSmooth(true);
        mapViewLevelThree.setFitHeight(1000);
        mapViewLevelThree.setFitWidth(1500);

        Image mapImageLevelFour = new Image(Main.class.getResourceAsStream("back_four.png"));
        ImageView mapViewLevelFour = new ImageView(mapImageLevelFour);
        mapViewLevelFour.setSmooth(true);
        mapViewLevelFour.setFitHeight(1000);
        mapViewLevelFour.setFitWidth(1500);

        if (Level.levelNum == 1) {
            root.getChildren().add(mapViewLevelOne);
        }
        else if (Level.levelNum == 2){
            root.getChildren().add(mapViewLevelTwo);
        }

        else if (Level.levelNum == 3){
            root.getChildren().add(mapViewLevelThree);
        }

        else {
            root.getChildren().add(mapViewLevelFour);
        }
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


        //Image wellImage = new Image(Main.class.getResourceAsStream("welll.png"));
        Image animationWell = new Image(Main.class.getResourceAsStream("WellAnimation.png"));
        ImageView wellAnimationImage = new ImageView(animationWell);
        Image wellTwo = new Image(Main.class.getResourceAsStream("well2.png"));
        ImageView wellTwoView = new ImageView(wellTwo);
        Image wellThree = new Image(Main.class.getResourceAsStream("well3.png"));
        ImageView wellThreeView = new ImageView(wellThree);
        Image wellFour = new Image(Main.class.getResourceAsStream("well4.png"));
        ImageView wellFourView = new ImageView(wellFour);

        int COLUMNSW = 4;
        int COUNTW = 16;
        int OFFSET_XW = 0;
        int OFFSET_YW = 0;
        int WIDTHW = 150;
        int HEIGHTW = 135;

        wellAnimationImage.setViewport(new Rectangle2D(OFFSET_XW, OFFSET_YW, WIDTHW, HEIGHTW));
        wellAnimationImage.setX(620);
        wellAnimationImage.setY(130);

        wellTwoView.setViewport(new Rectangle2D(OFFSET_XW, OFFSET_YW, WIDTHW, 145));
        wellTwoView.setX(620);
        wellTwoView.setY(130);

        wellThreeView.setViewport(new Rectangle2D(OFFSET_XW, OFFSET_YW, WIDTHW, 145));
        wellThreeView.setX(620);
        wellThreeView.setY(130);

        wellFourView.setViewport(new Rectangle2D(OFFSET_XW, OFFSET_YW, WIDTHW, 130));
        wellFourView.setX(620);
        wellFourView.setY(130);


        wellAnimationImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AudioClip audioClip1 = new AudioClip(this.getClass().getResource("water.mp3").toString());
                audioClip1.setCycleCount(3);
                audioClip1.play();
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

        wellTwoView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AudioClip audioClip1 = new AudioClip(this.getClass().getResource("water.mp3").toString());
                audioClip1.setCycleCount(3);
                audioClip1.play();
                final Animation wellAnimation = new SpriteAnimation(
                        wellTwoView,
                        Duration.millis(1000),COUNTW, COLUMNSW,
                        OFFSET_XW, OFFSET_YW,
                        148, 150
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

        wellThreeView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AudioClip audioClip1 = new AudioClip(this.getClass().getResource("water.mp3").toString());
                audioClip1.setCycleCount(3);
                audioClip1.play();
                final Animation wellAnimation = new SpriteAnimation(
                        wellThreeView,
                        Duration.millis(1000),COUNTW, COLUMNSW,
                        OFFSET_XW, OFFSET_YW,
                        144, 159
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

        wellFourView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AudioClip audioClip1 = new AudioClip(this.getClass().getResource("water.mp3").toString());
                audioClip1.setCycleCount(3);
                audioClip1.play();
                final Animation wellAnimation = new SpriteAnimation(
                        wellFourView,
                        Duration.millis(1000),COUNTW, COLUMNSW,
                        OFFSET_XW, OFFSET_YW,
                        149, 135
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

        root.getChildren().add(wellAnimationImage);

        Image upgrade = new Image(Main.class.getResourceAsStream("upgrade.png"));

        ImageView wellUpgradeView = new ImageView(upgrade);
        wellUpgradeView.setFitHeight(50);
        wellUpgradeView.setFitWidth(120);
        wellUpgradeView.setX(750);
        wellUpgradeView.setY(190);
        root.getChildren().add(wellUpgradeView);
        wellUpgradeView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AudioClip audioClip1 = new AudioClip(this.getClass().getResource("upgrade.mp3").toString());
                audioClip1.play();
                if (orders.getGameInfo().well.getLevel() >= 4){
                    orders.wellUpgrade();
                }

                else if (orders.getGameInfo().well.getLevel() == 1){
                    orders.wellUpgrade();
                    root.getChildren().remove(wellAnimationImage);
                    root.getChildren().add(wellTwoView);
                }
                else if(orders.getGameInfo().well.getLevel() == 2){
                    orders.wellUpgrade();
                    root.getChildren().remove(wellTwoView);
                    root.getChildren().add(wellThreeView);
                }
                else if (orders.getGameInfo().well.getLevel() == 3){
                    orders.wellUpgrade();
                    root.getChildren().remove(wellThreeView);
                    root.getChildren().add(wellFourView);
                }
            }
        });




        Circle circle = new Circle(40,40,40);
        Image chickenImage = new Image(Main.class.getResourceAsStream("guinea_fowl.png"));
        circle.setFill(new ImagePattern(chickenImage));

        Rectangle chickenUpgrade = new Rectangle(80, 30);
        chickenUpgrade.setFill(new ImagePattern(upgrade));
        chickenUpgrade.relocate(0,80);
        root.getChildren().addAll(circle, chickenUpgrade);

        Rectangle chickenDescription = new Rectangle(128, 90);
        Image descImage = new Image(Main.class.getResourceAsStream("4.png"));
        chickenDescription.setFill(new ImagePattern(descImage));
        chickenDescription.relocate(40, 80);
        Label chickenLabel = new Label();
        Chicken chicken = orders.getGameInfo().chicken;

        final Timeline chickenDescTimeline = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent event)
                    {
                        chickenLabel.setText(chicken.getInfo());
                    }
                }));
        chickenDescTimeline.setCycleCount(Animation.INDEFINITE);
        chickenDescTimeline.play();
        chickenLabel.setLabelFor(chickenDescription);
        chickenLabel.relocate(48, 110);
        chickenLabel.setTextFill(Color.BROWN);

        chickenUpgrade.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AudioClip audioClip1 = new AudioClip(this.getClass().getResource("upgrade.mp3").toString());
                audioClip1.play();
                orders.getGameInfo().chicken.upgrade();
            }
        });



        circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().addAll(chickenDescription, chickenLabel);
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
            }
        });

        circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    AudioClip audioClip1 = new AudioClip(this.getClass().getResource("click.mp3").toString());
                    audioClip1.play();
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
        Rectangle cowUpgrade = new Rectangle(80, 30);
        cowUpgrade.setFill(new ImagePattern(upgrade));
        cowUpgrade.relocate(80,80);
        root.getChildren().addAll(circle4, cowUpgrade);

        Rectangle cowDescription = new Rectangle(120, 90);
        cowDescription.setFill(new ImagePattern(descImage));
        cowDescription.relocate(120, 80);
        Label cowLabel = new Label();
        Cow cow = orders.getGameInfo().cow;

        final Timeline buffaloDescTimeline = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent event)
                    {

                        cowLabel.setText(cow.getInfo());
                    }
                }));
        buffaloDescTimeline.setCycleCount(Animation.INDEFINITE);
        buffaloDescTimeline.play();
        cowLabel.setLabelFor(cowDescription);
        cowLabel.relocate(127, 110);
        cowLabel.setTextFill(Color.BROWN);

        cowUpgrade.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AudioClip audioClip1 = new AudioClip(this.getClass().getResource("upgrade.mp3").toString());
                audioClip1.play();
                orders.getGameInfo().cow.upgrade();
            }
        });



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
                    AudioClip audioClip1 = new AudioClip(this.getClass().getResource("click.mp3").toString());
                    audioClip1.play();
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
        Rectangle ostrichUpgrade = new Rectangle(80, 30);
        ostrichUpgrade.setFill(new ImagePattern(upgrade));
        ostrichUpgrade.relocate(160,80);
        root.getChildren().addAll(circle6);
        root.getChildren().addAll(ostrichUpgrade);

        ostrichUpgrade.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AudioClip audioClip1 = new AudioClip(this.getClass().getResource("upgrade.mp3").toString());
                audioClip1.play();

                orders.getGameInfo().ostrich.upgrade();
            }
        });

        Rectangle ostrichDescription = new Rectangle(120, 90);
        //Image descImage = new Image(Main.class.getResourceAsStream("4.png"));
        ostrichDescription.setFill(new ImagePattern(descImage));
        ostrichDescription.relocate(200, 80);
        Label ostrichLabel = new Label();
        Ostrich ostrich = orders.getGameInfo().ostrich;

        final Timeline ostrichDescTimeline = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler<ActionEvent>()
                {
                    @Override
                    public void handle(ActionEvent event)
                    {
                        ostrichLabel.setText(ostrich.getInfo());
                    }
                }));
        ostrichDescTimeline.setCycleCount(Animation.INDEFINITE);
        ostrichDescTimeline.play();

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
                    AudioClip audioClip1 = new AudioClip(this.getClass().getResource("click.mp3").toString());
                    audioClip1.play();
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


        /*Rectangle exitButton = new Rectangle(120, 50);
        Image exitImage = new Image(Main.class.getResourceAsStream("exit.png"));
        exitButton.setFill(new ImagePattern(exitImage));
        exitButton.relocate(1245, 45);
        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AudioClip audioClip1 = new AudioClip(this.getClass().getResource("click1.mp3").toString());
                audioClip1.play();

                primaryStage.close();
            }
        });
        root.getChildren().add(exitButton);*/




        Image truckImage = new Image(Main.class.getResourceAsStream("01.png"));
        ImageView truckView = new ImageView(truckImage);
        truckView.setX(400);
        truckView.setY(700);
        truckView.setFitWidth(200);
        truckView.setFitHeight(200);
        root.getChildren().add(truckView);






        Image tipForDescription = new Image(Main.class.getResourceAsStream("tip.png"));


        Rectangle buildEggPowderWsBtn = new Rectangle(80, 30);
        Image buildImage = new Image(Main.class.getResourceAsStream("buildBtn.png"));
        buildEggPowderWsBtn.setFill(new ImagePattern(buildImage));
        buildEggPowderWsBtn.relocate(150, 280);
        Label eggPowderPrice = new Label();
        eggPowderPrice.setTextFill(Color.YELLOW);
        eggPowderPrice.relocate(185, 285);
        Rectangle upgradeEggPowderBtn = new Rectangle(80, 30);
        upgradeEggPowderBtn.setFill(new ImagePattern(upgrade));
        upgradeEggPowderBtn.relocate(230, 285);
        Label eggPWSInfo = new Label();
        Rectangle eggPWSI = new Rectangle(240, 150);
        eggPWSI.setFill(new ImagePattern(tipForDescription));
        eggPWSI.relocate(210, 345);
        eggPWSInfo.relocate(250, 380);




// for animation of egg powder workshop

        Image eggPowderWS = new Image(Main.class.getResourceAsStream("eggp.png"));
        ImageView eggPowderWSView = new ImageView(eggPowderWS);
        EggPowderWS eggPowderWSIns = new EggPowderWS();

        Label eggPowderWSName = new Label("Egg Powder WorkShop");
        eggPowderWSName.setTextFill(Color.YELLOW);
        eggPowderWSName.relocate(150, 260);
        root.getChildren().add(eggPowderWSName);


        Timeline eggPWIShow = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler()
                {
                    @Override
                    public void handle(Event event) {
                        eggPWSInfo.setText(eggPowderWSIns.getInfo());
                    }
                }));
        eggPWIShow.setCycleCount(Animation.INDEFINITE);
        eggPWIShow.play();


        Timeline eggPowderPriceShower = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler()
                {
                    @Override
                    public void handle(Event event) {
                        eggPowderPrice.setText(String.valueOf(eggPowderWSIns.getPrice()));
                    }
                }));
        eggPowderPriceShower.setCycleCount(Animation.INDEFINITE);
        eggPowderPriceShower.play();

        root.getChildren().addAll(buildEggPowderWsBtn, eggPowderPrice);

        chickenUpgrade.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                eggPowderWSIns.upgrade();
            }
        });

        eggPowderPrice.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                orders.getGameInfo().money -= eggPowderWSIns.getPrice();
                root.getChildren().removeAll(buildEggPowderWsBtn, eggPowderPrice, eggPowderWSName);
                root.getChildren().addAll(eggPowderWSView, upgradeEggPowderBtn);
            }
        });

        buildEggPowderWsBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                orders.getGameInfo().money -= eggPowderWSIns.getPrice();
                root.getChildren().removeAll(buildEggPowderWsBtn, eggPowderPrice, eggPowderWSName);
                root.getChildren().addAll(eggPowderWSView, upgradeEggPowderBtn);
            }
        });

        upgradeEggPowderBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (eggPowderWSIns.getLevel() <= 2)
                orders.getGameInfo().money -= eggPowderWSIns.getLevel() * 300;
                eggPowderWSIns.upgrade();
            }
        });

        /*buildEggPowderWsBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().addAll(eggPWSI, eggPWSInfo);
            }
        });

        buildEggPowderWsBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(eggPWSI, eggPWSInfo);
            }
        });*/

        eggPowderWSView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().addAll(eggPWSI, eggPWSInfo);
            }
        });


        eggPowderWSView.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(eggPWSI, eggPWSInfo);
            }
        });

        eggPWSI.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(eggPWSI, eggPWSInfo);
            }
        });




        int COLUMNS1 = 8;
        int COUNT1 = 3;
        int OFFSET_X1 = 10;
        int OFFSET_Y1 = 0;
        int WIDTH1 = 131;
        int HEIGHT1 = 104;

        eggPowderWSView.setViewport(new Rectangle2D(OFFSET_X1, OFFSET_Y1, WIDTH1, HEIGHT1));

        eggPowderWSView.setX(150);
        eggPowderWSView.setY(280);

        //root.getChildren().add(eggPowderWSView);


        eggPowderWSView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // if ... rules should be written!

                AudioClip audioClip1 = new AudioClip(this.getClass().getResource("eggf.mp3").toString());
                audioClip1.setCycleCount(eggPowderWSIns.getCyclesToMakeProduct());
                audioClip1.play();


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

        Image rightTip = new Image(Main.class.getResourceAsStream("rightTip.png"));
        Rectangle buildCakeWsBtn = new Rectangle(80, 30);
        buildCakeWsBtn.setFill(new ImagePattern(buildImage));
        buildCakeWsBtn.relocate(1210, 460);
        Label cakePrice = new Label();
        cakePrice.setTextFill(Color.YELLOW);
        cakePrice.relocate(1245, 468);
        Rectangle upgradeCakeBtn = new Rectangle(80, 30);
        upgradeCakeBtn.setFill(new ImagePattern(upgrade));
        upgradeCakeBtn.relocate(1300, 500);
        Label cakeWSInfo = new Label();
        Rectangle cakeWSI = new Rectangle(220, 150);
        cakeWSI.setFill(new ImagePattern(rightTip));
        cakeWSI.relocate(1035, 510);
        cakeWSInfo.relocate(1075, 542);

        Image cakeWS = new Image(Main.class.getResourceAsStream("cakeW.png"));
        ImageView cakeWSview = new ImageView(cakeWS);
        CakeWS cakeWSIns = new CakeWS();

        Label cakeWSName = new Label("Cake WorkShop");
        cakeWSName.setTextFill(Color.YELLOW);
        cakeWSName.relocate(1210, 440);
        root.getChildren().add(cakeWSName);

        Timeline cakeWIShow = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler()
                {
                    @Override
                    public void handle(Event event) {
                        cakeWSInfo.setText(cakeWSIns.getInfo());
                    }
                }));
        cakeWIShow.setCycleCount(Animation.INDEFINITE);
        cakeWIShow.play();


        Timeline cakePriceShower = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler()
                {
                    @Override
                    public void handle(Event event) {
                        cakePrice.setText(String.valueOf(cakeWSIns.getPrice()));
                    }
                }));
        cakePriceShower.setCycleCount(Animation.INDEFINITE);
        cakePriceShower.play();

        root.getChildren().addAll(buildCakeWsBtn, cakePrice);

        chickenUpgrade.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cakeWSIns.upgrade();
            }
        });

        cakePrice.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                orders.getGameInfo().money -= cakeWSIns.getPrice();
                root.getChildren().removeAll(buildCakeWsBtn, cakePrice, cakeWSName);
                root.getChildren().add(cakeWSview);
                root.getChildren().add(upgradeCakeBtn);
            }
        });

        buildCakeWsBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                orders.getGameInfo().money -= cakeWSIns.getPrice();
                root.getChildren().removeAll(buildCakeWsBtn, cakePrice, cakeWSName);
                root.getChildren().add(cakeWSview);
                root.getChildren().add(upgradeCakeBtn);
            }
        });

        upgradeCakeBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (cakeWSIns.getLevel() <= 2)
                orders.getGameInfo().money -= cakeWSIns.getLevel() * 300;
                cakeWSIns.upgrade();
            }
        });

        /*buildCakeWsBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().addAll(cakeWSI, cakeWSInfo);
            }
        });

        buildCakeWsBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(cakeWSI, cakeWSInfo);
            }
        });*/

        cakeWSview.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().addAll(cakeWSI, cakeWSInfo);
            }
        });


        cakeWSview.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(cakeWSI, cakeWSInfo);
            }
        });

        cakeWSI.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(cakeWSI, cakeWSInfo);
            }
        });

        int COLUMNS2 = 8;
        int COUNT2 = 3;
        int OFFSET_X2 = 10;
        int OFFSET_Y2 = 10;
        int WIDTH2 = 131;
        int HEIGHT2 = 140;
        cakeWSview.setViewport(new Rectangle2D(OFFSET_X2, OFFSET_Y2, WIDTH2, HEIGHT2));

        cakeWSview.setX(1210);
        cakeWSview.setY(460);

        //root.getChildren().add(cakeWSview);


        cakeWSview.setOnMouseClicked(new EventHandler<MouseEvent>() {


            @Override
            public void handle(MouseEvent event) {

                // for working
                AudioClip audioClip1 = new AudioClip(this.getClass().getResource("cakef1.mp3").toString());
                audioClip1.setCycleCount(11);
                audioClip1.play();
                final Animation cookieWSanimation = new SpriteAnimation(
                        cakeWSview,
                        Duration.millis(1000),COUNT2, COLUMNS2,
                        OFFSET_X2, OFFSET_Y2,
                        WIDTH2, HEIGHT2
                );

                cookieWSanimation.setCycleCount(cakeWSIns.getCyclesToMakeProduct());
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

        Image flouryCakeWS = new Image(Main.class.getResourceAsStream("flouryCakeWS.png"));
        ImageView flouryCakeWSView = new ImageView(flouryCakeWS);
        Rectangle buildFlouryCakeWsBtn = new Rectangle(80, 30);
        buildFlouryCakeWsBtn.setFill(new ImagePattern(buildImage));
        buildFlouryCakeWsBtn.relocate(1180, 250);
        Label flouryCakePrice = new Label();
        flouryCakePrice.setTextFill(Color.YELLOW);
        flouryCakePrice.relocate(1218, 255);
        Rectangle upgradeFlouryCakeBtn = new Rectangle(80, 30);
        upgradeFlouryCakeBtn.setFill(new ImagePattern(upgrade));
        upgradeFlouryCakeBtn.relocate(1240, 255);
        Label flourycakeWSInfo = new Label();
        Rectangle flourycakeWSI = new Rectangle(220, 170);
        flourycakeWSI.setFill(new ImagePattern(rightTip));
        flourycakeWSI.relocate(1000, 330);
        flourycakeWSInfo.relocate(1035, 370);

        Label flouryCakeWSName = new Label("Floury Cake WorkShop");
        flouryCakeWSName.setTextFill(Color.YELLOW);
        flouryCakeWSName.relocate(1160, 230);
        root.getChildren().add(flouryCakeWSName);

        FlouryCakeWS flouryCakeWSIns = new FlouryCakeWS();

        Timeline flourycakeWIShow = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler()
                {
                    @Override
                    public void handle(Event event) {
                        flourycakeWSInfo.setText(flouryCakeWSIns.getInfo());
                    }
                }));
        flourycakeWIShow.setCycleCount(Animation.INDEFINITE);
        flourycakeWIShow.play();


        Timeline flourycakePriceShower = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler()
                {
                    @Override
                    public void handle(Event event) {
                        flouryCakePrice.setText(String.valueOf(flouryCakeWSIns.getPrice()));
                    }
                }));
        flourycakePriceShower.setCycleCount(Animation.INDEFINITE);
        flourycakePriceShower.play();

        root.getChildren().addAll(buildFlouryCakeWsBtn, flouryCakePrice);

        chickenUpgrade.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                flouryCakeWSIns.upgrade();
            }
        });

        flouryCakePrice.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                orders.getGameInfo().money -= flouryCakeWSIns.getPrice();
                root.getChildren().removeAll(buildFlouryCakeWsBtn, flouryCakePrice, flouryCakeWSName);
                root.getChildren().add(flouryCakeWSView);
                root.getChildren().add(upgradeFlouryCakeBtn);
            }
        });

        buildFlouryCakeWsBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                orders.getGameInfo().money -= flouryCakeWSIns.getPrice();
                root.getChildren().removeAll(buildFlouryCakeWsBtn, flouryCakePrice, flouryCakeWSName);
                root.getChildren().add(flouryCakeWSView);
                root.getChildren().add(upgradeFlouryCakeBtn);
            }
        });

        upgradeFlouryCakeBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (flouryCakeWSIns.getLevel() <= 2)
                orders.getGameInfo().money -= flouryCakeWSIns.getLevel() * 300;
                flouryCakeWSIns.upgrade();
            }
        });

        /*buildFlouryCakeWsBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().addAll(flourycakeWSI, flourycakeWSInfo);
            }
        });

        buildFlouryCakeWsBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(flourycakeWSI, flourycakeWSInfo);
            }
        });*/

       flouryCakeWSView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().addAll(flourycakeWSI, flourycakeWSInfo);
            }
        });


        flouryCakeWSView.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(flourycakeWSI, flourycakeWSInfo);
            }
        });

        flourycakeWSI.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(flourycakeWSI, flourycakeWSInfo);
            }
        });

        int COLUMNS3 = 8;
        int COUNT3 = 2;
        int OFFSET_X3 = 0;
        int OFFSET_Y3 = 10;
        int WIDTH3 = 170;
        int HEIGHT3 = 152;
        flouryCakeWSView.setViewport(new Rectangle2D(OFFSET_X3, OFFSET_Y3, WIDTH3, HEIGHT3));

        flouryCakeWSView.setX(1100);
        flouryCakeWSView.setY(220);

        //root.getChildren().add(flouryCakeWSView);


        flouryCakeWSView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // if ... rules should be written!

                AudioClip audioClip1 = new AudioClip(this.getClass().getResource("cakef.mp3").toString());
                audioClip1.setCycleCount(20);
                audioClip1.play();
                final Animation FlouryCakeAnimation = new SpriteAnimation(
                        flouryCakeWSView,
                        Duration.millis(1000),COUNT3, COLUMNS3,
                        OFFSET_X3, OFFSET_Y3,
                        WIDTH3, HEIGHT3

                );
                FlouryCakeAnimation.setCycleCount(flouryCakeWSIns.getCyclesToMakeProduct());
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
        ImageView spinneryWSview = new ImageView(SpinneryWS);

        Rectangle buildspinneryWsBtn = new Rectangle(80, 30);
        buildspinneryWsBtn.setFill(new ImagePattern(buildImage));
        buildspinneryWsBtn.relocate(140, 650);
        Label spinneryPrice = new Label();
        spinneryPrice.setTextFill(Color.YELLOW);
        spinneryPrice.relocate(175, 657);
        Rectangle upgradespinneryBtn = new Rectangle(80, 30);
        upgradespinneryBtn.setFill(new ImagePattern(upgrade));
        upgradespinneryBtn.relocate(220, 652);
        Label spinneryWSInfo = new Label();
        Rectangle spinneryWSI = new Rectangle(220, 150);
        spinneryWSI.setFill(new ImagePattern(tipForDescription));
        spinneryWSI.relocate(198, 700);
        spinneryWSInfo.relocate(230, 730);

        Label spinneryWSName = new Label("Spinnery WorkShop");
        spinneryWSName.setTextFill(Color.YELLOW);
        spinneryWSName.relocate(145, 630);
        root.getChildren().add(spinneryWSName);

        SpinneryWS spinneryWSIns = new SpinneryWS();

        Timeline spinneryWIShow = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler()
                {
                    @Override
                    public void handle(Event event) {
                        spinneryWSInfo.setText(spinneryWSIns.getInfo());
                    }
                }));
        spinneryWIShow.setCycleCount(Animation.INDEFINITE);
        spinneryWIShow.play();


        Timeline spinneryPriceShower = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler()
                {
                    @Override
                    public void handle(Event event) {
                        spinneryPrice.setText(String.valueOf(spinneryWSIns.getPrice()));
                    }
                }));
        spinneryPriceShower.setCycleCount(Animation.INDEFINITE);
        spinneryPriceShower.play();

        root.getChildren().addAll(buildspinneryWsBtn, spinneryPrice);

        chickenUpgrade.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                spinneryWSIns.upgrade();
            }
        });

        spinneryPrice.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                orders.getGameInfo().money -= spinneryWSIns.getPrice();
                root.getChildren().removeAll(buildspinneryWsBtn, spinneryPrice, spinneryWSName);
                root.getChildren().add(spinneryWSview);
                root.getChildren().add(upgradespinneryBtn);
            }
        });

        buildspinneryWsBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                orders.getGameInfo().money -= spinneryWSIns.getPrice();
                root.getChildren().removeAll(buildspinneryWsBtn, spinneryPrice, spinneryWSName);
                root.getChildren().add(spinneryWSview);
                root.getChildren().add(upgradespinneryBtn);
            }
        });

        /*buildspinneryWsBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().addAll(spinneryWSI, spinneryWSInfo);
            }
        });

        buildspinneryWsBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(spinneryWSI, spinneryWSInfo);
            }
        });*/

        spinneryWSview.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().addAll(spinneryWSI, spinneryWSInfo);
            }
        });


        spinneryWSview.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(spinneryWSI, spinneryWSInfo);
            }
        });

        spinneryWSI.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(spinneryWSI, spinneryWSInfo);
            }
        });

        upgradespinneryBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (spinneryWSIns.getLevel() <= 2)
                orders.getGameInfo().money -= spinneryWSIns.getLevel() * 300;
                spinneryWSIns.upgrade();
            }
        });

        int COLUMNS4 = 8;
        int COUNT4 = 2;
        int OFFSET_X4 = 10;
        int OFFSET_Y4 = 1;
        int WIDTH4 = 130;
        int HEIGHT4 = 100;
        spinneryWSview.setViewport(new Rectangle2D(OFFSET_X4, OFFSET_Y4, WIDTH4, HEIGHT4));

        spinneryWSview.setX(130);
        spinneryWSview.setY(630);


        spinneryWSview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // if ... rules should be written!

                AudioClip audioClip1 = new AudioClip(this.getClass().getResource("sewf.mp3").toString());
                audioClip1.setCycleCount(4);
                audioClip1.play();

                final Animation SpinneryAnimation = new SpriteAnimation(
                        spinneryWSview,
                        Duration.millis(1000),COUNT4, COLUMNS4,
                        OFFSET_X4, OFFSET_Y4,
                        WIDTH4, HEIGHT4

                );
                SpinneryAnimation.setCycleCount(spinneryWSIns.getCyclesToMakeProduct());
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
        ImageView sewingFactoryview = new ImageView(sewingFactory);

        Rectangle buildsewingFactoryBtn = new Rectangle(80, 30);
        buildsewingFactoryBtn.setFill(new ImagePattern(buildImage));
        buildsewingFactoryBtn.relocate(160, 440);
        Label sewingFactoryPrice = new Label();
        sewingFactoryPrice.setTextFill(Color.YELLOW);
        sewingFactoryPrice.relocate(192, 447);
        Rectangle upgradesewingFactoryBtn = new Rectangle(80, 30);
        upgradesewingFactoryBtn.setFill(new ImagePattern(upgrade));
        upgradesewingFactoryBtn.relocate(280, 460);
        Label sewingFactoryInfo = new Label();
        Rectangle sewingFactoryI = new Rectangle(220, 150);
        sewingFactoryI.setFill(new ImagePattern(tipForDescription));
        sewingFactoryI.relocate(220, 520);
        sewingFactoryInfo.relocate(260, 550);

        Label sewingFactoryName = new Label("Sewing Factory");
        sewingFactoryName.setTextFill(Color.YELLOW);
        sewingFactoryName.relocate(145, 421);
        root.getChildren().add(sewingFactoryName);

        SewingFactory sewingFactoryIns = new SewingFactory();

        Timeline sewingFactoryIShow = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler()
                {
                    @Override
                    public void handle(Event event) {
                        sewingFactoryInfo.setText(sewingFactoryIns.getInfo());
                    }
                }));
        sewingFactoryIShow.setCycleCount(Animation.INDEFINITE);
        sewingFactoryIShow.play();


        Timeline sewingFactoryPriceShower = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler()
                {
                    @Override
                    public void handle(Event event) {
                        sewingFactoryPrice.setText(String.valueOf(sewingFactoryIns.getPrice()));
                    }
                }));
        sewingFactoryPriceShower.setCycleCount(Animation.INDEFINITE);
        sewingFactoryPriceShower.play();

        root.getChildren().addAll(buildsewingFactoryBtn, sewingFactoryPrice);

        chickenUpgrade.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                sewingFactoryIns.upgrade();
            }
        });

        sewingFactoryPrice.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                orders.getGameInfo().money -= sewingFactoryIns.getPrice();
                root.getChildren().removeAll(buildsewingFactoryBtn, sewingFactoryPrice, sewingFactoryName);
                root.getChildren().add(sewingFactoryview);
                root.getChildren().add(upgradesewingFactoryBtn);
            }
        });

        buildsewingFactoryBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                orders.getGameInfo().money -= sewingFactoryIns.getPrice();
                root.getChildren().removeAll(buildsewingFactoryBtn, sewingFactoryPrice, sewingFactoryName);
                root.getChildren().add(sewingFactoryview);
                root.getChildren().add(upgradesewingFactoryBtn);
            }
        });

        /*buildsewingFactoryBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().addAll(sewingFactoryI, sewingFactoryInfo);
            }
        });

        buildsewingFactoryBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(sewingFactoryI, sewingFactoryInfo);
            }
        });*/

        sewingFactoryview.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().addAll(sewingFactoryI, sewingFactoryInfo);
            }
        });


        sewingFactoryview.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(sewingFactoryI, sewingFactoryInfo);
            }
        });

        sewingFactoryI.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(sewingFactoryI, sewingFactoryInfo);
            }
        });

        upgradesewingFactoryBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (sewingFactoryIns.getLevel() <= 2)
                orders.getGameInfo().money -= sewingFactoryIns.getLevel() * 300;
                sewingFactoryIns.upgrade();
            }
        });

        int COLUMNS5 = 8;
        int COUNT5 = 2;
        int OFFSET_X5 = 10;
        int OFFSET_Y5 = 5;
        int WIDTH5 = 170;
        int HEIGHT5 = 120;
        sewingFactoryview.setViewport(new Rectangle2D(OFFSET_X5, OFFSET_Y5, WIDTH5, HEIGHT5));

        sewingFactoryview.setX(150);
        sewingFactoryview.setY(420);


        sewingFactoryview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // if ... rules should be written!
                AudioClip audioClip1 = new AudioClip(this.getClass().getResource("clothf.mp3").toString());
                audioClip1.setCycleCount(6);
                audioClip1.play();

                final Animation SewingAnimation = new SpriteAnimation(
                        sewingFactoryview,
                        Duration.millis(1000),COUNT5, COLUMNS5,
                        OFFSET_X5, OFFSET_Y5,
                        WIDTH5, HEIGHT5

                );
                SewingAnimation.setCycleCount(sewingFactoryIns.getCyclesToMakeProduct());
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

        Image weavingFactoryImage = new Image(Main.class.getResourceAsStream("weaving.png"));
        ImageView weavingFactoryview = new ImageView(weavingFactoryImage);

        Rectangle buildweavingFactoryBtn = new Rectangle(80, 30);
        buildweavingFactoryBtn.setFill(new ImagePattern(buildImage));
        buildweavingFactoryBtn.relocate(1180, 650);
        Label weavingFactoryPrice = new Label();
        weavingFactoryPrice.setTextFill(Color.YELLOW);
        weavingFactoryPrice.relocate(1218, 655);
        Rectangle upgradeweavingFactoryBtn = new Rectangle(80, 30);
        upgradeweavingFactoryBtn.setFill(new ImagePattern(upgrade));
        upgradeweavingFactoryBtn.relocate(1240, 660);
        Label weavingFactoryInfo = new Label();
        Rectangle weavingFactoryI = new Rectangle(220, 170);
        weavingFactoryI.setFill(new ImagePattern(rightTip));
        weavingFactoryI.relocate(980, 680);
        weavingFactoryInfo.relocate(1000, 720);

        Label weavingFactoryName = new Label("Weaving Factory WorkShop");
        weavingFactoryName.setTextFill(Color.YELLOW);
        weavingFactoryName.relocate(1190, 630);
        root.getChildren().add(weavingFactoryName);

        WeavingFactory weavingFactoryIns = new WeavingFactory();

        Timeline weavingFactoryIShow = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler()
                {
                    @Override
                    public void handle(Event event) {
                        weavingFactoryInfo.setText(weavingFactoryIns.getInfo());
                    }
                }));
        weavingFactoryIShow.setCycleCount(Animation.INDEFINITE);
        weavingFactoryIShow.play();


        Timeline weavingFactoryPriceShower = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler()
                {
                    @Override
                    public void handle(Event event) {
                        weavingFactoryPrice.setText(String.valueOf(weavingFactoryIns.getPrice()));
                    }
                }));
        weavingFactoryPriceShower.setCycleCount(Animation.INDEFINITE);
        weavingFactoryPriceShower.play();

        root.getChildren().addAll(buildweavingFactoryBtn, weavingFactoryPrice);

        chickenUpgrade.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                weavingFactoryIns.upgrade();
            }
        });

        weavingFactoryPrice.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                orders.getGameInfo().money -= weavingFactoryIns.getPrice();
                root.getChildren().removeAll(buildweavingFactoryBtn, weavingFactoryPrice, weavingFactoryName);
                root.getChildren().add(weavingFactoryview);
                root.getChildren().add(upgradeweavingFactoryBtn);
            }
        });

        buildweavingFactoryBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                orders.getGameInfo().money -= weavingFactoryIns.getPrice();
                root.getChildren().removeAll(buildweavingFactoryBtn, weavingFactoryPrice, weavingFactoryName);
                root.getChildren().add(weavingFactoryview);
                root.getChildren().add(upgradeweavingFactoryBtn);
            }
        });

        upgradeweavingFactoryBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (weavingFactoryIns.getLevel() <= 2)
                orders.getGameInfo().money -= weavingFactoryIns.getLevel() * 300;
                weavingFactoryIns.upgrade();
            }
        });

        /*buildweavingFactoryBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().addAll(weavingFactoryI, weavingFactoryInfo);
            }
        });

        buildweavingFactoryBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(weavingFactoryI, weavingFactoryInfo);
            }
        });*/

        weavingFactoryview.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().addAll(weavingFactoryI, weavingFactoryInfo);
            }
        });


        weavingFactoryview.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(weavingFactoryI, weavingFactoryInfo);
            }
        });

        weavingFactoryI.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getChildren().removeAll(weavingFactoryI, weavingFactoryInfo);
            }
        });

        int COLUMNS6 = 8;
        int COUNT6 = 3;
        int OFFSET_X6 = 1;
        int OFFSET_Y6 = 9;
        int WIDTH6 = 170;
        int HEIGHT6 = 105;
        weavingFactoryview.setViewport(new Rectangle2D(OFFSET_X6, OFFSET_Y6, WIDTH6, HEIGHT6));

        weavingFactoryview.setX(1150);
        weavingFactoryview.setY(640);


        weavingFactoryview.setOnMouseClicked(new EventHandler<MouseEvent>() {


            @Override
            public void handle(MouseEvent event) {

                // for working
                AudioClip audioClip1 = new AudioClip(this.getClass().getResource("fabricf.mp3").toString());
                audioClip1.setCycleCount(3);
                audioClip1.play();
                final Animation weavingWSAnimation = new SpriteAnimation(
                        weavingFactoryview,
                        Duration.millis(1000),COUNT6, COLUMNS6,
                        OFFSET_X6, OFFSET_Y6,
                        WIDTH6, HEIGHT6
                );

                weavingWSAnimation.setCycleCount(weavingFactoryIns.getCyclesToMakeProduct());
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

        Rectangle timeBox = new Rectangle(150, 34);
        Image timeShape = new Image(Main.class.getResourceAsStream("btn.png"));
        timeBox.setFill(new ImagePattern(timeShape));
        timeBox.relocate(1050, 5);
        Label currentTime = new Label();
        currentTime.setLabelFor(timeBox);
        currentTime.relocate(1064, 10);
        currentTime.setTextFill(Color.WHITE);
        currentTime.toFront();
        root.getChildren().addAll(timeBox,currentTime);

        Rectangle levelBox = new Rectangle(126, 34);
        levelBox.setFill(new ImagePattern(timeShape));
        levelBox.relocate(915, 5);
        Label currentLevel = new Label();
        currentLevel.setLabelFor(levelBox);
        currentLevel.relocate(929, 10);
        currentLevel.setTextFill(Color.WHITE);
        currentLevel.toFront();
        root.getChildren().addAll(levelBox, currentLevel);

        Timeline levelShow = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler()
                {
                    @Override
                    public void handle(Event event) {
                        currentLevel.setText(String.valueOf("Current Level: "+ Level.levelNum));
                    }
                }));
        levelShow.setCycleCount(Animation.INDEFINITE);
        levelShow.play();



        Image pauseBack = new Image(Main.class.getResourceAsStream("pausebg.png"));
        ImageView endLevel = new ImageView(pauseBack);
        endLevel.relocate(1050, 0);
        endLevel.setFitHeight(790);
        endLevel.setFitWidth(400);

        Image endLevelImage = new Image(Main.class.getResourceAsStream("endLevelHeader.png"));
        ImageView endLevelView = new ImageView(endLevelImage);
        endLevelView.setFitWidth(200);
        endLevelView.setFitHeight(90);
        endLevelView.relocate(1120, 140);

        Image starImage = new Image(Main.class.getResourceAsStream("star.png"));
        ImageView starView = new ImageView(starImage);
        starView.setFitWidth(150);
        starView.setFitHeight(85);
        starView.relocate(1145, 50);

        Image closeGameImage = new Image(Main.class.getResourceAsStream("close.png"));
        ImageView closeGameView = new ImageView(closeGameImage);
        closeGameView.setFitHeight(70);
        closeGameView.setFitWidth(70);
        closeGameView.relocate(1187, 500);

        Image nextLevelImage = new Image(Main.class.getResourceAsStream("next.png"));
        ImageView nextLevelView = new ImageView(nextLevelImage);
        nextLevelView.setFitHeight(70);
        nextLevelView.setFitWidth(70);
        nextLevelView.relocate(1187, 300);

        Image mainMenuImage = new Image(Main.class.getResourceAsStream("menu.png"));
        ImageView mainMenuView = new ImageView(mainMenuImage);
        mainMenuView.setFitHeight(70);
        mainMenuView.setFitWidth(70);
        mainMenuView.relocate(1187, 400);

        mainMenuView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    new Main().start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        closeGameView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.close();
            }
        });

        nextLevelView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                orders.getGameInfo().level.upLevel();
                try {
                    new MainGame().start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        long startSecond = System.currentTimeMillis();
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



        Timeline timeShow = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler()
                {
                    @Override
                    public void handle(Event event) {
                        //currentTime.setText(String.valueOf(System.currentTimeMillis()));
                        final long timePassed = (System.currentTimeMillis() - startSecond)/1000;
                        final long timeReminded = orders.getGameInfo().level.timeReminded - timePassed;
                        //currentTime.setText(String.valueOf("Time Passed: "+((System.currentTimeMillis() - startSecond)/1000) + "s"));
                        //currentTime.setText(String.valueOf("Time Passed: "+ timePassed + " s"));
                        currentTime.setText(String.valueOf("Time Reminded: " + timeReminded + " s"));
                        if (timeReminded == 0){
                            modeShow.pause();
                            root.getChildren().addAll(endLevel, endLevelView, starView,
                                    closeGameView, nextLevelView, mainMenuView);
                        }
                    }
                }));
        timeShow.setCycleCount(Animation.INDEFINITE);
        timeShow.play();


        Image musicOffImage = new Image(Main.class.getResourceAsStream("music_off.png"));
        ImageView musicOffView = new ImageView(musicOffImage);
        musicOffView.setFitWidth(40);
        musicOffView.setFitHeight(40);
        musicOffView.relocate(860, 5);
        root.getChildren().add(musicOffView);

        Image musicOnImage = new Image(Main.class.getResourceAsStream("music.png"));
        ImageView musicOnView = new ImageView(musicOnImage);
        musicOnView.setFitHeight(40);
        musicOnView.setFitWidth(40);
        musicOnView.relocate(860, 5);

        musicOffView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                audioClip.stop();
                root.getChildren().remove(musicOffView);
                root.getChildren().add(musicOnView);
            }
        });

        musicOnView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                audioClip.play();
                root.getChildren().remove(musicOnView);
                root.getChildren().add(musicOffView);
            }
        });

        Image pauseImage = new Image(Main.class.getResourceAsStream("pause.png"));
        ImageView pauseView = new ImageView(pauseImage);
        pauseView.relocate(1210, 5);
        pauseView.setFitWidth(40);
        pauseView.setFitHeight(40);
        root.getChildren().add(pauseView);

        ImageView pauseBgView = new ImageView(pauseBack);
        pauseBgView.relocate(450, 150);
        pauseBgView.setFitHeight(500);
        pauseBgView.setFitWidth(500);

        Image pauseHeader = new Image(Main.class.getResourceAsStream("pauseheader.png"));
        ImageView pauseHeaderView = new ImageView(pauseHeader);
        pauseHeaderView.relocate(590, 170);
        pauseHeaderView.setFitWidth(200);
        pauseHeaderView.setFitHeight(90);

        Image pauseTable = new Image(Main.class.getResourceAsStream("pausetable.png"));
        ImageView pauseTableView = new ImageView(pauseTable);
        pauseTableView.relocate(500, 270);
        pauseTableView.setFitHeight(250);
        pauseTableView.setFitWidth(380);

        Image pauseText = new Image(Main.class.getResourceAsStream("pauseText.png"));
        ImageView pauseTextView = new ImageView(pauseText);
        pauseTextView.relocate(550, 310);
        pauseTextView.setFitWidth(280);
        pauseTextView.setFitHeight(150);

        Image pauseResumeButton = new Image(Main.class.getResourceAsStream("play.png"));
        ImageView pauseResumeView = new ImageView(pauseResumeButton);
        pauseResumeView.relocate(600, 530);
        pauseResumeView.setFitHeight(60);
        pauseResumeView.setFitWidth(60);

        Image pauseExitImage = new Image(Main.class.getResourceAsStream("close.png"));
        ImageView pauseExView = new ImageView(pauseExitImage);
        pauseExView.relocate(670, 530);
        pauseExView.setFitWidth(60);
        pauseExView.setFitHeight(60);

        Image pauseMenuImage = new Image(Main.class.getResourceAsStream("menu.png"));
        ImageView pauseMenuView = new ImageView(pauseMenuImage);
        pauseMenuView.relocate(740, 530);
        pauseMenuView.setFitHeight(60);
        pauseMenuView.setFitWidth(60);



        pauseView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AudioClip audioClip1 = new AudioClip(this.getClass().getResource("pause.mp3").toString());
                audioClip1.play();
                orders.grassChecker = -1;
                System.out.println(orders.grassChecker);
                root.getChildren().addAll(pauseBgView, pauseHeaderView, pauseTableView,
                        pauseTextView, pauseResumeView, pauseExView, pauseMenuView);
                modeShow.pause();
                timeShow.pause();
                audioClip.stop();
            }
        });

        pauseResumeView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AudioClip audioClip1 = new AudioClip(this.getClass().getResource("pause.mp3").toString());
                audioClip1.play();
                root.getChildren().removeAll(pauseBgView, pauseHeaderView, pauseTableView,
                        pauseTextView, pauseResumeView, pauseExView, pauseMenuView);
                modeShow.play();
                timeShow.play();
                audioClip.play();
                orders.grassChecker = 0;
            }
        });

        pauseExView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AudioClip audioClip1 = new AudioClip(this.getClass().getResource("pause.mp3").toString());
                audioClip1.play();
                primaryStage.close();
            }
        });

        pauseMenuView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    AudioClip audioClip1 = new AudioClip(this.getClass().getResource("pause.mp3").toString());
                    audioClip1.play();
                    new Main().start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

        AudioClip audioClip1 = new AudioClip(this.getClass().getResource("click1.mp3").toString());


        startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    //audioClip1.play();
                    new MainGame().start(primaryStage);
                    mp.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


//        loadButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                audioClip1.play();
//            }
//        });


        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //audioClip1.play();
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

            AudioClip audioClip1 = new AudioClip(this.getClass().getResource("click1.mp3").toString());

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);
            setOnMouseEntered(event -> {
                bg.setFill(gradient);
                text.setFill(Color.BLACK);
                audioClip1.play();

            });

            setOnMouseExited(event -> {
                bg.setFill(Color.BLACK);
                text.setFill(Color.DARKGREY);
            });
            setOnMousePressed(event -> {
                bg.setFill(Color.DARKVIOLET);
                audioClip1.play();

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

