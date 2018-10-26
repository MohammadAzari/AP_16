package gilgCity.model;

import gilgCity.controller.counter;

public class User {
    private double score=0L;
    private double baseScore;
    private counter counter=new counter();
    private double gills;
    private City city=new City();
    private boolean turn=false;
    private String name;

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

        System.out.println(this.score);
    }

    public void showGills(){

        System.out.println(this.gills);
    }

    public void yield(){

    }
}
