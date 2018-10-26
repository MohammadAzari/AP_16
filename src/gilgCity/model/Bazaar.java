package gilgCity.model;

public class Bazaar {

    public double score;
    public double cost;
    public int level;
    public int unitId;
    public int blockId;
    private int dayExists=1;



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

    public void addDayslive(){
        dayExists++;

    }




}
