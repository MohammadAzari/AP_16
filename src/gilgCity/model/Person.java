package gilgCity.model;

public class Person extends Entity{
    private double satisfactory = 1;
    private int daysOfWorking = 0;
    private boolean isEmployer = false;

    public boolean isEmployer() {
        return isEmployer;
    }

    public void setIsEmployer(boolean employer) {
        isEmployer = employer;
    }

    public void employe(){
        isEmployer = true;
    }

    public void getFired(){
        isEmployer = false;
        daysOfWorking = 0;
    }

    public int getDaysOfWorking() {
        return daysOfWorking;
    }

    public void setDaysOfWorking(int daysOfWorking) {
        this.daysOfWorking = daysOfWorking;
    }

    @Override
    public void update() {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public double calculateScore() {
        return 1;
    }

    public double getSatisfactory() {
        return satisfactory;
    }

    public void setSatisfactory(double satisfactory) {
        this.satisfactory = satisfactory;
    }
}

