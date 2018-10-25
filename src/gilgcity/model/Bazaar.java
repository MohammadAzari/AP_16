package gilgCity.model;

public class Bazaar {

    public static double score;
    public double cost;
    public int level;
    public int numberOfplayers;
    public int blockId;
    public int unitId;
    public int dayExist;



    public Bazaar(int level, int numberOfplayers) {
        this.level = level;
        this.numberOfplayers=numberOfplayers;
    }

//    public int cost_calculate(double cost,int level){
//        cost=(level+1)*5000;
//        return cost;
//    }

    public void upgrade(int level,double score,int unitId,int numberOfplayers) {

        if (level == 2) {
            score = numberOfplayers*(score*1.4);
        }
        if(level == 3){
            score=numberOfplayers*(score*1.6);
        }


    }

    public void remove(int level,double score,int numberOfplayers){
        if(level == 2){
            score=score*numberOfplayers/1.4;
        }
        if(level == 3){
            score=score*numberOfplayers/1.6;
        }


    }

    public void addDaysLive(){

    }









}