package sample;

import java.util.ArrayList;

public class Store {
    public ArrayList <Food> stock = new ArrayList<>();

    //Creates a list of store items and adds it to a list of Food objects.
    public void createStock(){
        Food energyDrink = new Food("Energy Drink", 5.00,
                "A refreshing drink to boost your energy for the day!",10000);
        Food hapHam = new Food("Happy Hamburger", 3.50,
                "A healthy hamburger that will guarantee you happiness right when it enters your mouth!", 10000);
        Food funFruit = new Food ("FunFruit", 1.50, "Healthy fruit so you can have some fun later!", 10000);

        Food ultWater = new Food ("Ultimate Water", 1.00,
                "Ultimate water is unlike any water. It hydrates the consumer for the entire day.", 10000);
        Food sweetSundee = new Food ("Sweet Sundee", 2.50,
                "The sweetness will feel like heaven down your throat!", 10000);
        Food motivMars = new Food ("MotivMars Bars", 2.00,
                "Definitely not inspired by Mars Bars. Let the caramel and chocolate melt" +
                        " inside your mouth for extra motivation!", 10000);

        stock.add(energyDrink);
        stock.add(hapHam);
        stock.add(funFruit);
        stock.add(ultWater);
        stock.add(sweetSundee);
        stock.add(motivMars);
    }

    //Returns the list of store items.
    public ArrayList <Food> getStock(){
        return this.stock;
    }
}
