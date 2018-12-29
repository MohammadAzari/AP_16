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
    ArrayList<Pet> pets = new ArrayList<>();
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
        hasFood = false;
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
    Chicken, Sheep, Cow
}

enum TypeOfWild{
    Lion, Bear
}

abstract class Wild extends Animal{

    int level;
    TypeOfWild type;

    public Wild(Map map) {
        super(map);
        level = 1;
    }

    /*public void eatPets(){
        if (map.positions[position.getX()][position.getY()].)
    }*/
}

abstract class Pet extends Animal implements Upgradable,Printable {
    protected int feedCapacityUnit;
    protected int feedCapacity;
    protected int level;
    protected TypeOfPet type;
    protected Position tempPosition;

    public Pet(Map map) {
        super(map);
        feedCapacity = 0;
        level = 1;
        tempPosition = map.positions[position.getX()][position.getY()];
    }

    public void placeInPosition(){
        map.positions[position.getX()][position.getY()].pets.add(this);
        tempPosition = map.positions[position.getX()][position.getY()];
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

class Cow extends Pet implements Upgradable, Printable{

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

    @Override
    public void makeProduct() {
        if (feedCapacity == feedCapacityUnit){
            milk = new Milk();
            map.positions[position.getX()][position.getY()].addProduct(milk);
            System.out.println("a milk is produced and placed in [" + position.getX() + " " +
                    position.getY() + "]");
            feedCapacity = 0;
        }
    }

    @Override
    public void upgrade() {
        if (feedCapacityUnit == 1){
            System.out.println("There's no more upgrade for Cow!");
        }
        else {
            level++;
            feedCapacityUnit--;
            price += 50;
            System.out.println("Cow is upgraded to level " + level);
        }
    }

    @Override
    public void printInfo() {
        System.out.println("Cow number: " + namedLabel + "{\n\t" +
                "price: " + price + "\n\t" +
                "food capacity: " + feedCapacity + "\n\t" +
                "level: " + level + "\n\t" +
                "feed capacity unit: " + feedCapacityUnit + "\n}");
    }
}

class Sheep extends Pet implements Upgradable,Printable{

    Product wool;
    int namedLabel;


    public Sheep(Map map, int label) {
        super(map);
        namedLabel = label;
        feedCapacityUnit = 4;
        priceUnit = 150;
        price = priceUnit;
        type = TypeOfPet.Sheep;
    }

    @Override
    public void makeProduct() {
        if (feedCapacity == feedCapacityUnit){
            wool = new Wool();
            map.positions[position.getX()][position.getY()].addProduct(wool);
            System.out.println("a wool is produced and placed in [" + position.getX() + " " +
                    position.getY() + "]");
            feedCapacity = 0;
        }
    }

    @Override
    public void upgrade() {
        if (feedCapacityUnit == 1){
            System.out.println("There's no more upgrade for Sheep!");
        }
        else {
            level++;
            feedCapacityUnit--;
            price += 50;
            System.out.println("Chicken is upgraded to level " + level);
        }
    }

    @Override
    public void printInfo() {
        System.out.println("Sheep number: " + namedLabel + "{\n\t" +
                "price: " + price + "\n\t" +
                "food capacity: " + feedCapacity + "\n\t" +
                "level: " + level + "\n\t" +
                "feed capacity unit: " + feedCapacityUnit + "\n}");
    }
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
        if (feedCapacityUnit == 1){
            System.out.println("There's no more upgrade for Chicken!");
        }
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

class Wool extends Product{

}

class Milk extends Product{

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
    ArrayList<Sheep> sheeps = new ArrayList<>();
    ArrayList<Cow> cows = new ArrayList<>();
    ArrayList<Chicken> chickens = new ArrayList<>();

    public GameInfo(){
        time = 0;
        money = initialMoney;
    }
}

class Orders{
    private GameInfo gameInfo = new GameInfo();
    private int chickenLabel = 1;
    private int sheepLabel = 1;
    private int cowLabel = 1;
    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public void buyCow(){
        Cow cow = new Cow(gameInfo.map, cowLabel);
        cowLabel++;
        gameInfo.cows.add(cow);
        if (gameInfo.money - cow.price < 0) System.out.println("You haven't enough money!!");
        else {
            gameInfo.money -= cow.price;
            System.out.println("A cow is bought and money = " + gameInfo.money);
        }
    }

    public void buyChicken(){
        Chicken chicken = new Chicken(gameInfo.map, chickenLabel);
        chickenLabel++;
        gameInfo.chickens.add(chicken);
        if (gameInfo.money - chicken.price < 0) System.out.println("You haven't enough money!!");
        else {
            gameInfo.money -= chicken.price;
            System.out.println("A chicken is bought! and money = " + gameInfo.money);
        }
    }

    public void  buySheep(){
        Sheep sheep = new Sheep(gameInfo.map, sheepLabel);
        sheepLabel++;
        gameInfo.sheeps.add(sheep);
        if (gameInfo.money - sheep.price < 0) System.out.println("You haven't enough money!!");
        else {
            gameInfo.money -= sheep.price;
            System.out.println("A sheep is bought and money = " + gameInfo.money);
        }
    }

    public void turn(int numOfTurns){
        for (int i = 0 ; i < numOfTurns ; i++){
            for (Cow c : gameInfo.cows){
                c.move();
                c.eatGrass();
            }
            for (Sheep s : gameInfo.sheeps){
                s.move();
                s.eatGrass();
            }
            for (Chicken c : gameInfo.chickens){
                c.move();
                c.eatGrass();
            }
            gameInfo.time++;
        }
    }

    public void addMoney(int moneyAdded){
        gameInfo.money += moneyAdded;
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
                gameInfo.well.unload();
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
                    gameInfo.well.unload();
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
                    gameInfo.well.unload();
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
                    gameInfo.well.unload();
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
                    gameInfo.well.unload();
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
                    gameInfo.well.unload();
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
                    gameInfo.well.unload();
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
                    gameInfo.well.unload();
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
                    gameInfo.well.unload();
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
                    gameInfo.well.unload();
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
                    gameInfo.well.unload();
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
                    gameInfo.well.unload();
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
                    gameInfo.well.unload();
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


public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String order;
        Orders orders = new Orders();
        System.out.println("Welcome to Farm Frenzy 3!!");
        System.out.println("Your money is: " + orders.getGameInfo().money);
        System.out.println("Please enter an order:\n\t\"buy [Animal]\": to buy an animal" +
                "\n\t\"plant [x y]\": to plant grass around point [x y]\n\t" +
                "\"well\": to load well\n\t\"print [Printable]\": to print info about a Printable" +
                "\n\t\"turn [n]\": to trun the game n times" +
                "\n\t\"upgrade [Upgradable]\": to level up an Upgradable" +
                "\n\t\"add money [amount]\": to add money" +
                "\n\t\"end game\": to end up the game");
        order = scanner.nextLine();
        while (true){
            if (order.equals("end game")) break;
            else {
                if (order.startsWith("buy")){
                    if (order.endsWith("chicken")) orders.buyChicken();
                    else if (order.endsWith("sheep")) orders.buySheep();
                    else if (order.endsWith("cow")) orders.buyCow();
                    else System.out.println("Please enter a valid type order!");
                }

                else if (order.startsWith("plant")){
                    String[] strTemp = order.split(" ");
                    int x, y;
                    x = Integer.parseInt(strTemp[1]);
                    y = Integer.parseInt(strTemp[2]);
                    orders.plant(x, y);
                }

                else if (order.startsWith("well")){
                    orders.well();
                }

                else if (order.startsWith("print")){
                    String [] strTemp = order.split(" ");
                    switch (strTemp[1]){
                        case "sheep": orders.print(orders.getGameInfo().sheeps.get(0)); break;
                        case "chicken": orders.print(orders.getGameInfo().chickens.get(0)); break;
                        case "cow" : orders.print(orders.getGameInfo().cows.get(0)); break;
                        case "map": orders.print(orders.getGameInfo().map); break;
                        default:
                            System.out.println("Please enter a valid format to print!!");
                            break;
                    }
                }

                else if (order.startsWith("turn")){
                    String [] strTemp = order.split(" ");
                    orders.turn(Integer.parseInt(strTemp[1]));
                }

                else if (order.startsWith("upgrade")){

                }

                else if (order.startsWith("add money")){
                    String []strTemp = order.split(" ");
                    orders.addMoney(Integer.parseInt(strTemp[1]));
                }
                System.out.println("Your money is: " + orders.getGameInfo().money);
                System.out.println("Please enter an order:\n\t\"buy [Animal]\": to buy an animal" +
                        "\n\t\"plant [x y]\": to plant grass around point [x y]\n\t" +
                        "\"well\": to load well\n\t\"print [Printable]\": to print info about a Printable" +
                        "\n\t\"turn [n]\": to trun the game n times" +
                        "\n\t\"upgrade [Upgradable]\": to level up an Upgradable" +
                        "\n\t\"add money [amount]\": to add money" +
                        "\n\t\"end game\": to end up the game");
                order = scanner.nextLine();
            }
        }
    }
}
