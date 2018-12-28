package controller;

public class WellController {
    model.Well well;

    public  WellController(model.Well well){
        this.well = well;
    }

    public void upgrade(){
        well.setLevel(well.getLevel() + 1);
        well.setCapacityUnit(well.getCapacityUnit() + 1);
        well.setCapacity(well.getCapacityUnit());
    }

    public void unload(){
        if (well.getCapacity() <= 0)
            System.out.println("well is empty!!");
        else
            well.setCapacity(well.getCapacity() - 1);
    }

    public void load(){
        if (well.getCapacity() == well.getCapacityUnit())
            System.out.println("well is full!!");
        else
            well.setCapacity(well.getCapacityUnit());
    }
}
