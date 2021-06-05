package sample;

import java.io.IOException;
import java.io.*;

public class Food {
    private String name;
    private double price;
    private String info;
    private int quantity;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getInfo() {
        return info;
    }

    public int getQuantity(){
        return this.quantity;
    }

    //We increase the quantity of the food by the change in quantity.
    //If the change in quantity is a negative number, we don't change
    //the food's quantity.
    public int increaseQuantity(int qty){

        if (qty < 0){
            return this.quantity;
        }

        this.quantity += qty;
        return this.quantity;
    }

    //We decrease the quantity of the food by the change in quantity.
    //If the change in quantity is greater than our current quantity, nothing
    // is changed and we just return the current quantity we have.
    //If the change in quantity is a negative number, that doesn't really make
    // sense in the real world to feed negative amount of food, so nothing is
    // changed and we just return the current quantity we have.
    public int feed(int qty){
        if (qty > this.quantity){
            return this.quantity;
        }

        if (qty < 0){
            return this.quantity;
        }
        this.quantity -= qty;
        return this.quantity;
    }

    public Food(String name, double price, String info, int quantity){
        this.name = name;
        this.price = price;
        this.info = info;
        this.quantity = quantity;
    }

    //This is revised constructor for inventory food
    public Food(String name, String info, int quantity){
        this.name = name;
        this.info = info;
        this.quantity = quantity;
    }

    //This writes all the saved food instance variable strings into separated lines with an "@" at the end
    // so that later on we can separate the lines and set them as a food instance variable. Then, we add an
    // extra ";" line so we can separate each food later.
    public void writeToFile() throws IOException{
        FileWriter fw = new FileWriter("Inventory.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(this.name + "@\r");
        bw.write(this.price + "@\r");
        bw.write(this.info + "@\r");
        bw.write(this.quantity + "\r");
        bw.write(";\r");

        bw.close();
        fw.close();
    }

    //If the food name is the same as the compared food's name, then both the foods are equal.
    public boolean compareFood(Food comparedTo){
        if (this.name.equalsIgnoreCase(comparedTo.name)){
            return true;
        }
        return false;
    }

    //Displays the food name and the cost. If it's in an inventory, we ignore the cost and just
    //say it's 0.
    public String toString(){
        return this.name + "\t Cost: $" + this.price;
    }

}
