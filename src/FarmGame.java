import java.util.ArrayList;

abstract class Animal {

    double price;
    //map

}

class Wild extends Animal{

    private ArrayList <Wild> wildAnimals; //game

    Wild() {
        wildAnimals = new ArrayList<>();
    }

    public void addToWildAnimals(){//game

        System.out.println("HI");
        wildAnimals.add(new Wild());
    }



}

class WildController{

    private Wild wild;

    public void collision(Product product , Wild wild){//wild controller

        //if(map)
        //remove product bgdcsbgb

    }
}

class Lion extends Wild{

}

class Bear extends Wild{

}

class Domestic extends Animal{

    int stomach;
    private ArrayList <Domestic> domesticAnimals = new ArrayList<>();//game

}

class DomesticController{

    private Domestic domestic;
    public void eatGrass(double grass) {//controler


    }

    public Product produce(){//con

        Product product = new Product();
        return product;
    }

    public void die(Domestic domestics){     //control

        domestic.domesticAnimals.remove(domestics);
    }
}

class Sheep extends Domestic{

    private ArrayList<Sheep> sheeps;//game

    Sheep() {
        sheeps = new ArrayList<>();
    }


}

class SheepController{

    public Product produce(){

        Wool wool = new Wool();
        return wool;
    }

    public void die(Domestic sheep){

        sheeps.remove(sheep);
    }
}

class Cow extends Domestic{

    ArrayList<Cow> cows;//game

    Cow() {
        cows = new ArrayList<>();
    }

}

class CowController{

    public Product produce(){

        Milk milk = new Milk();
        return milk;
    }

    public void die(Domestic cow){

        cows.remove(cow);
    }
}

class Chicken extends Domestic{

    private ArrayList<Chicken> chickens;

    {
        chickens = new ArrayList<>();
    }

}

class ChickenController{

    public Product produce(){

        Egg egg = new Egg();
        return egg;
    }

    public void die(Domestic chicken){

        chickens.remove(chicken);
    }
}

class Dog extends Animal{

}

class DogController{

    public void run(){

    }
}

class Cat extends Animal{

    int level;

}

class CatController{

    public void move(){
        if(level == 1)
            stupidMove();
        else if (level == 2)
            smartMove();
    }

    private void stupidMove(){

    }

    private void smartMove(){

    }

    public void pickUp(Product product){

    }

    public void upgrade(){
        level++;
    }
}

class Product{

    double price;
    private ArrayList<Product> products = new ArrayList<>();//game
}

class Feather extends Product{

}

class Egg extends Product{

}

class Wool extends Product{

}

class NewFlour extends Product{

}

class Milk extends Product{

}

class Powder extends Product{

}

class Cookie extends Product{

}

class Cake extends Product{

}

class Cotton extends Product{

}

class Cloth extends Product{

}

class Clothes extends Product{

}

//upgrade(where?)