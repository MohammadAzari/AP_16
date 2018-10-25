package gilgCity.model;

import java.util.ArrayList;

public class City {
    private ArrayList<Block> blocks;

    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }
}
