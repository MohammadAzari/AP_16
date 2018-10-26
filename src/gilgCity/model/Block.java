package gilgCity.model;

import gilgCity.controller.counter;

import java.util.ArrayList;
import gilgCity.model.Entity;

public class Block extends Entity{
    private int capacity = 15;
    private gilgCity.controller.counter counter = new counter();
    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId=unitId;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    private ArrayList<Entity> entities = new ArrayList<>();

    @Override
    public void update() {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public double calculateScore() {
        double totalScore = 0;
        for (Entity entity: entities) {
            totalScore += entity.calculateScore();
        }
        return totalScore;
    }

    public Entity getEntityByID(int unitId){
        for (Entity entity: entities){
            if (entity.getUnitId() == unitId){
                return entity;
            }
        }
        return null;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public counter getEntityCounter() {
        return counter;
    }

}
