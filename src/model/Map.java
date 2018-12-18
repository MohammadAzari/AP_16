package model;

import java.util.ArrayList;

class Map{

    ArrayList<Sheep> sheeps = new ArrayList<>();
    ArrayList<Cow> cows = new ArrayList<>();
    ArrayList<Chicken> chickens = new ArrayList<>();
    ArrayList<Lion> lions = new ArrayList<>();
    ArrayList<Bear> bears = new ArrayList<>();
    ArrayList<Product> products = new ArrayList<>();
    //ArrayList<Workshop> workshops = new ArrayList<>();
    ArrayList<Cat> cats = new ArrayList<>();
    ArrayList<Dog> dogs = new ArrayList<>();
    //ArrayList<Grass> grasses = new ArrayList<>();

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
