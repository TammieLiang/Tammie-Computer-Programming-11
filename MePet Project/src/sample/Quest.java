package sample;

import java.io.IOException;
import java.io.*;

public class Quest {

    private String name;
    private String info;
    private double prize;

    public Quest(String name, String info, double prize){
        this.name = name;
        this.info = info;
        this.prize = prize;
    }

    //Writes the quest's instance variables (name, info, prize) as separate lines in a text file
    // with an added "@" at the end so we can differentiate between the different instance variables
    // later on to make the quests. Then, we add an extra line with a semicolon so that it separates
    // each quest from each other.
    public void writeToFile() throws IOException{
        FileWriter fw = new FileWriter("Quests.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(this.name + "@\r");
        bw.write(this.info + "@\r");
        bw.write(this.prize + "@\r");
        bw.write (";\r");

        bw.close();
    }

    //If the quest's name is the same as the compared quest, then both the quests are equal.
    public boolean compareQuest(Quest compareTo){
        if (this.name.equalsIgnoreCase(compareTo.getName())){
            return true;
        }

        return false;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public double getPrize(){
        return prize;
    }

    public String toString(){
        return this.name;
    }



}
