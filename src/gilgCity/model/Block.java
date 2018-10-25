package gilgCity.model;

import java.util.ArrayList;

public class Block {
    private ArrayList<Defender> defenders;
    private ArrayList<Attacker> attackeras;
    private ArrayList<Home> homes;
    private ArrayList<Bazaar> bazaars;
    private int employeesOfBlock;
    private int nonEmplyeesOfBlock;
    private double incomeOfEmployees;

    public Block(){

    }

    public double getIncomeOfEmployees() {
        return incomeOfEmployees;
    }

    public int getEmployeesOfBlock() {
        return employeesOfBlock;
    }

    public int getNonEmplyeesOfBlock() {
        return nonEmplyeesOfBlock;
    }


}
