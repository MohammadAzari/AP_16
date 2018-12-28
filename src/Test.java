import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Constants{
    public static final int NORTHEAST = 0;
    public static final int NORTH = 1;
    public static final int NORTHWEST = 2;
    public static final int WEST = 3;
    public static final int EAST = 4;
    public static final int SOUTHWEST = 5;
    public static final int SOUTH = 6;
    public static final int SOUTHEAST = 7;
}

class Time implements Runnable{
    private int timeUnits;

    public Time(){
        timeUnits = 0;
    }

    public int getTimeUnits() {
        return timeUnits;
    }

    public void repairTime(){
        timeUnits++;
    }

    @Override
    public void run(){
        timeUnits++;
    }
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
    private ArrayList<Product> products = new ArrayList<>();
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
        hasFood = true;
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

class Map{
    private Position [][] positions;
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

    public void printMap(){
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
class Animal implements Moveable{
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
                    map.printMap();
                }
                else {
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setX(position.getX() - 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    map.printMap();
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
                    map.printMap();
                }
                else {
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setY(position.getY() + 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    map.printMap();
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
                    map.printMap();
                }
                else {
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setY(position.getY() - 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    map.printMap();
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
                    map.printMap();
                }
                else {
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(tempX);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(tempY);
                    position.setX(position.getX() + 1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setX(-1);
                    this.map.getPositions()[this.getPosition().getX()][this.getPosition().getY()].setY(-1);
                    tempX = position.getX();
                    tempY = position.getY();
                    map.printMap();
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
                    map.printMap();
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
                    map.printMap();
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
                    map.printMap();
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
                    map.printMap();
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
                    map.printMap();
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
                    map.printMap();
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
                    map.printMap();
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
                    map.printMap();
                }
                break;

            default:
                System.out.println("nothing!");break;

        }
    }
}

abstract class Pet extends Animal {
    protected int feedCapacityUnit;
    protected int feedCapacity;
    protected int level;

    public Pet(Map map) {
        super(map);
        feedCapacity = 0;
        level = 0;
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

    public void upgrade(){
        level++;
        feedCapacityUnit--;
    }

}

class Chicken extends Pet{

    Product egg;

    public Chicken(Map map) {
        super(map);
        feedCapacityUnit = 3;
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
}

class Product{

}

class Egg extends Product{

}


public class Test {
    public static void main(String[] args) {

    }
}
