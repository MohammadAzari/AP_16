package gilgCity.model;


public class Defender{
    private int level;
    private int blockId;
    private int unitId;
    private double score;
    private static int dayLives;
    private int employees;
    private static double cost;

    public void setLevel(int level) {
        this.level = level;
        }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
        }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
        }

    public void setScore(double score) {
        this.score = score;
        }

    public static void setDayLives(int dayLives) {
        Defender.dayLives = dayLives;
        }

    public void setEmployees(int employees) {
        this.employees = employees;
        }

    public static void setCost(double cost) {
        Defender.cost = cost;
        }

    public int getLevel() {
        return level;
        }

    public int getBlockId() {
        return blockId;
        }

    public int getUnitId() {
        return unitId;
        }

    public double getScore() {
        return score;
        }

    public static int getDayLives() {
        return dayLives;
        }

    public int getEmployees() {
        return employees;
        }

    public static double getCost() {
        return cost;
        }


    public Defender(){
        dayLives = 0;
        }

    public static void upgrade () {
        cost = cost - 5000;
    }

    public static void remove () {
        cost += 10000;
    }
}