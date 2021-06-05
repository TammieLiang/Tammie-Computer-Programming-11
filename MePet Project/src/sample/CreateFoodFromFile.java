package sample;

import java.util.ArrayList;
import java.io.*;
//import java.util.Iterator;

public class CreateFoodFromFile {

    private static FileReader fr;
    private static BufferedReader br;

    private static ArrayList<Food> inventoryFood =  new ArrayList<>();

    //Each line of the file is read, and added to a string. If the we read a line that's a semicolon,
    // that means we read all the lines that represent the instance variables for ONE Food object. We then send the string
    // that had all our lines for the one Food object to the method "parseFood". After "parseFood" finishes creating a Food
    // from those lines and adding it to the list of Food, we return the list of Foods so it can be displayed.
    public static ArrayList<Food> createAllFoodFromFile() throws IOException{

        //We clear the list of Foods so that deleted food that were previously added into the Foods list
        // are not considered. This is okay to do because we add all the Food that aren't deleted back into the list
        // anyways as we read ALL the lines of the txt file, and the txt file DOES NOT contain any lines or info of the deleted Food
        // (as long the user saved the correct list).
        inventoryFood.clear();

        fr = new FileReader("Inventory.txt");
        br = new BufferedReader(fr);

        String line;
        String foodString = "";

        while ((line = br.readLine()) != null){
            if (!line.equals(";")){
                foodString += line;
            }
            else {
                parseFood(foodString);
                foodString = "";
            }
        }
        br.close();

        return inventoryFood;
    }

    //The method splits the string it received as a parameter by the character "@" (so every "@" it sees
    // in the string, it removes the "@" and stores the text before and afte the @ as separate strings). For
    // example, if the string was "Food name@food cost@food info@food qty", then the split up parts would be "Food name",
    //"food cost", "food info", and "food qty". The split up parts are then stored in an array list of strings.

    //The first split up part is set as the name of the Food item. The second split up part is the Food price.
    // We don't care about the price of the Food since we already bought it and is now an inventory item. So
    // we don't make a variable for it.
    //The third split up part is the Food's info.
    //The fourth split up part is the Food's quantity
    //We then create a Food object with those parts, and add it to our list of Foods. We go back to the previous method now.
    public static void parseFood(String foodString){
        String [] foodParts = foodString.split("@");

        String name = foodParts[0];
        String info = foodParts[2];
        int quantity = Integer.valueOf(foodParts[3]);

        Food inventoryFud = new Food(name, info, quantity);

//        Iterator<Food> itr = inventoryFood.iterator();
//        while (itr.hasNext()) {
//            Food foodToRemove = itr.next();
//            if (foodToRemove.compareFood(inventoryFud) == true){
//                inventoryFud.increaseQuantity(foodToRemove.getQuantity());
//                itr.remove();
//            }
//        }

        inventoryFood.add(inventoryFud);
    }
}
