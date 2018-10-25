package gilgCity.model;

public class Attacker {

    private int block_id;
    private int unit_id;
    private int level;
    private int dayslives;
    private double cost;
    private int employees;
    private double score;

    public void setBlock_id(int block_id) {
        this.block_id = block_id;
    }

    public void setUnit_id(int unit_id) {
        this.unit_id = unit_id;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setDayslives(int dayslives) {
        this.dayslives = dayslives;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getBlock_id() {
        return block_id;
    }

    public int getUnit_id() {
        return unit_id;
    }

    public int getLevel() {
        return level;
    }

    public int getDayslives() {
        return dayslives;
    }

    public double getCost() {
        return cost;
    }

    public int getEmployees() {
        return employees;
    }

    public double getScore() {
        return score;
    }



    public void upgrade(){

        cost = cost - 20000;
        employees = employees + 10;

    }

    public void remove(){

        cost += 10000;

    }

    public void attack(){

    }

    public void loose(){

    }

    public void win(){

    }

    public void addDaysLives(){

        dayslives++;

    }

    public void loot(){


    }




}
