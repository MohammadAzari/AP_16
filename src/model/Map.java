package model;

import java.util.ArrayList;

public class Map{

    ArrayList<Wild> wilds = new ArrayList<>();
    ArrayList<Domestic> domestics = new ArrayList<>();
    ArrayList<Sheep> sheeps = new ArrayList<>();
    ArrayList<Cow> cows = new ArrayList<>();
    ArrayList<Chicken> chickens = new ArrayList<>();
    ArrayList<Lion> lions = new ArrayList<>();
    ArrayList<Bear> bears = new ArrayList<>();
    ArrayList<Product> products = new ArrayList<>();
    //ArrayList<Workshop> workshops = new ArrayList<>();
    ArrayList<Cat> cats = new ArrayList<>();
    ArrayList<Dog> dogs = new ArrayList<>();
    ArrayList<Grass> grasses = new ArrayList<>();

    public ArrayList<Wild> getWilds() {
        return wilds;
    }

    public void setWilds(ArrayList<Wild> wilds) {
        this.wilds = wilds;
    }

    public ArrayList<Domestic> getDomestics() {
        return domestics;
    }

    public void setDomestics(ArrayList<Domestic> domestics) {
        this.domestics = domestics;
    }

    public ArrayList<Sheep> getSheeps() {
        return sheeps;
    }

    public void setSheeps(ArrayList<Sheep> sheeps) {
        this.sheeps = sheeps;
    }

    public ArrayList<Cow> getCows() {
        return cows;
    }

    public void setCows(ArrayList<Cow> cows) {
        this.cows = cows;
    }

    public ArrayList<Chicken> getChickens() {
        return chickens;
    }

    public void setChickens(ArrayList<Chicken> chickens) {
        this.chickens = chickens;
    }

    public ArrayList<Lion> getLions() {
        return lions;
    }

    public void setLions(ArrayList<Lion> lions) {
        this.lions = lions;
    }

    public ArrayList<Bear> getBears() {
        return bears;
    }

    public void setBears(ArrayList<Bear> bears) {
        this.bears = bears;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Cat> getCats() {
        return cats;
    }

    public void setCats(ArrayList<Cat> cats) {
        this.cats = cats;
    }

    public ArrayList<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(ArrayList<Dog> dogs) {
        this.dogs = dogs;
    }

    public ArrayList<Grass> getGrasses() {
        return grasses;
    }

    public void setGrasses(ArrayList<Grass> grasses) {
        this.grasses = grasses;
    }

    private Position[][] positions;
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
