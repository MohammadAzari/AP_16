package gilgCity.controller;

public class counter {
    private int blockNumber = 0;
    private int entityNumber = 0;
    private int personNumber;

    public void setBlockNumber(int blockNumber) {
        this.blockNumber = blockNumber;
    }

    public void setEntityNumber(int entityNumber) {
        this.entityNumber = entityNumber;
    }

    public counter() {
    }

    public int getHomeNumber() {
        entityNumber++;
        return entityNumber;
    }


    public int getFloorNumber() {
        entityNumber++;
        return entityNumber;
    }


    public int getUnitNumber() {
        entityNumber++;
        return entityNumber;
    }


    public int getBlockNumber() {
        blockNumber++;
        return blockNumber;
    }


    public int getAttackerNumber() {
        entityNumber++;
        return entityNumber;
    }


    public int getDefenderNumber() {
        entityNumber++;
        return entityNumber;
    }


    public int getBazaarNumber() {
        entityNumber++;
        return entityNumber;
    }



    public int getPersonNumber() {
        personNumber++;
        return personNumber;
    }

    public void setPersonNumber(int personNumber) {
        this.personNumber = personNumber;
    }

    public int getEntityNumber() {
        return entityNumber;
    }
}
