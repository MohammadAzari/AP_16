package model;

public class Well {
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

    public int getCapacity() {
        return capacity;
    }

    public int getCapacityUnit() {
        return capacityUnit;
    }

    public int getLevel() {
        return level;
    }

    public Well getSingleton() {
        return singleton;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setCapacityUnit(int capacityUnit) {
        this.capacityUnit = capacityUnit;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setSingleton(Well singleton) {
        this.singleton = singleton;
    }
}
