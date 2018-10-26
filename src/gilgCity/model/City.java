package gilgCity.model;

import java.util.ArrayList;

public class City {
    private ArrayList<Block> blocks=new ArrayList<>();

    public void setBlocks(ArrayList<Block> blocks) {

        this.blocks = blocks;
    }

    public ArrayList<Block> getBlocks() {

        return blocks;
    }


    public double calculateScore(){
        double totalScore = 0;
        for (Block block: blocks) {
            totalScore += block.calculateScore();
        }
        return totalScore;
    }

//    public void remove(int blockId){
//        Block block = this.getBlocks(blockId);
//        this.getBlocks().remove(block);
//    }



}
