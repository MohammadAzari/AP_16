package gilgCity.model;

import gilgCity.controller.Counter;
import gilgCity.costants.Constant;

public class User {
    private double score=0L;
    private double baseScore;
    private Counter counter=new Counter();
    private double gills;
    private City city=new City();
    private boolean turn=false;
    private String name;
    private Long money= Constant.START_MONEY;

    public boolean turn() {
        return turn;
    }

    public void turn(boolean turn) {
        turn = turn;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(double baseScore) {
        this.baseScore = baseScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void showScore(){

        System.out.printf("%.2f\n",baseScore+this.city.calculateScore());
    }

    public void showGills(){

        System.out.println(this.money);
    }

    public void yield(){

    }
}
