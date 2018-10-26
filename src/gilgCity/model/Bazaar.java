package gilgCity.model;

import java.util.ArrayList;

public class Bazaar {

    private double cost;
    private final int score=5;
    private int level=1;
    private int unitId;
    private int blockId;



    private int dayExists=1;
    private long numberOfemployees=50L;
    private ArrayList<User> employees =new ArrayList<>();

    public int getDayExists() {
        return dayExists;
    }

    public void setDayExists(int dayExists) {
        this.dayExists = dayExists;
    }

    public void setEmployees(ArrayList<User> employees) {
        this.employees = employees;
    }



    public ArrayList<User> getEmployees() {
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



//    public void upgrade(int level, double score, int unitId, int numberOfplayers) {
//
//        if (level == 2) {
//            score = numberOfplayers*(score*1.4);
//        }
//        if(level == 3){
//            score=numberOfplayers*(score*1.6);
//        }
//
//
//    }
//    public void remove(int level,double score,int numberOfplayers){
//        if(level == 2){
//            score=score*numberOfplayers/1.4;
//        }
//        if(level == 3){
//            score=score*numberOfplayers/1.6;
//        }
//
//
//    }






}
