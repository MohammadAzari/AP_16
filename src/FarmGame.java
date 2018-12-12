import java.util.ArrayList;
import java.util.Random;

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

class GetRandonNumber{
    private double doub;
    private Random random = new Random();
    public int getInt(int toN){
        return random.nextInt(toN);
    }
}

class Position{
    private int x, y;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Position(){
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
    }
}

interface Moveable{
    void move();
}
class Animal implements Moveable{
    private Position position = new Position();
    private Map map;
    private Random random = new Random();
    private Constants constants = new Constants();

    public Animal(Map map){
        this.map = map;
        position.setX(random.nextInt(map.getSize()));
        position.setY(random.nextInt(map.getSize()));
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

class Wild extends Animal{

    private ArrayList <Wild> wildAnimals; //game

    Wild() {
        wildAnimals = new ArrayList<>();
    }

    public void addToWildAnimals(){//game

        System.out.println("HI");
        wildAnimals.add(new Wild());
    }



}

class WildController{

    private Wild wild;

    public void collision(Product product , Wild wild){//wild controller

        //if(map)
        //remove product bgdcsbgb

    }
}

class Lion extends Wild{

}

class Bear extends Wild{

}

class Domestic extends Animal{

    int stomach;
    private ArrayList <Domestic> domesticAnimals = new ArrayList<>();//game

}

class DomesticController{

    private Domestic domestic;
    public void eatGrass(double grass) {//controler


    }

    public Product produce(){//con

        Product product = new Product();
        return product;
    }

    public void die(Domestic domestics){     //control

        domestic.domesticAnimals.remove(domestics);
    }
}

class Sheep extends Domestic{

    private ArrayList<Sheep> sheeps;//game

    Sheep() {
        sheeps = new ArrayList<>();
    }


}

class SheepController{

    public Product produce(){

        Wool wool = new Wool();
        return wool;
    }

    public void die(Domestic sheep){

        sheeps.remove(sheep);
    }
}

class Cow extends Domestic{

    ArrayList<Cow> cows;//game

    Cow() {
        cows = new ArrayList<>();
    }

}

class CowController{

    public Product produce(){

        Milk milk = new Milk();
        return milk;
    }

    public void die(Domestic cow){

        cows.remove(cow);
    }
}

class Chicken extends Domestic{

    private ArrayList<Chicken> chickens;

    {
        chickens = new ArrayList<>();
    }

}

class ChickenController{

    public Product produce(){

        Egg egg = new Egg();
        return egg;
    }

    public void die(Domestic chicken){

        chickens.remove(chicken);
    }
}

class Dog extends Animal{

}

class DogController{

    public void run(){

    }
}

class Cat extends Animal{

    int level;

}

class CatController{

    public void move(){
        if(level == 1)
            stupidMove();
        else if (level == 2)
            smartMove();
    }

    private void stupidMove(){

    }

    private void smartMove(){

    }

    public void pickUp(Product product){

    }

    public void upgrade(){
        level++;
    }
}

class Product{

    double price;
    private ArrayList<Product> products = new ArrayList<>();//game
}

class Feather extends Product{

}

class Egg extends Product{

}

class Wool extends Product{

}

class NewFlour extends Product{

}

class Milk extends Product{

}

class Powder extends Product{

}

class Cookie extends Product{

}

class Cake extends Product{

}

class Cotton extends Product{

}

class Cloth extends Product{

}

class Clothes extends Product{

}

//upgrade(where?)