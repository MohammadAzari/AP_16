package gilgCity.model;

import gilgCity.costants.Constant;

import java.util.ArrayList;

public class Bazaar extends Entity {


    private final int score=5;
    private int level=1;
    private int unitId;
    private int blockId;
    private double increaseScoreAmount = Constant.START_MARKET_AMOUNT_SCORE;
    private int dayExists=1;
    private long numberOfemployees=50L;
    private ArrayList<Person> employees =new ArrayList<>();

    public int getDayExists() {
        return dayExists;
    }

    public void setDayExists(int dayExists) {
        this.dayExists = dayExists;
    }

    public void setEmployees(ArrayList<Person> employees) {
        this.employees = employees;
    }



    public ArrayList<Person> getEmployees() {
        return employees;
    }



    public long getNumberOfemployees() {
        return numberOfemployees;
    }

    public void setNumberOfemployees(long numberOfemployees) {
        this.numberOfemployees = numberOfemployees;
    }




    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    public double getIncreaseScoreAmount() {
        return increaseScoreAmount;
    }

    public void setIncreaseScoreAmount(double increaseScoreAmount) {
        this.increaseScoreAmount = increaseScoreAmount;
    }

    @Override
    public void update() {

    }

    @Override
    public void remove(int unitId) {

    }

    @Override
    public double calculateScore() {
        return Math.pow(score,dayExists);
    }










}
