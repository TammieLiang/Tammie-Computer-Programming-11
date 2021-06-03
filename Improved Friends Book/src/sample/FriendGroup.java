package sample;
import com.sun.codemodel.internal.fmt.JTextFile;

import javax.swing.*;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;

public class FriendGroup {
    private ArrayList <String> friends;
    private String name;

    public FriendGroup(String name) throws IOException {
        this.name = name;
        this.friends = new ArrayList<>();
    }

    public void addFriend(String friendName){
        this.friends.add(friendName);
    }

    public ArrayList <String> getFriends(){
        return this.friends;
    }

    public void writeToAllGroupFile () throws IOException {
        FileWriter fw = new FileWriter(new File("Groups.txt"), true);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(this.name + ",\r");
        for (String f: friends){
            bw.write(f + ",\r");
        }
        bw.write(";\r");
        bw.close();
    }

    public void writeToIndividualFile () throws IOException {

        FileWriter fw = new FileWriter(new File(this.name + ".txt"), true);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(this.name + ",\r");
        for (String f: friends){
            bw.write(f + ",\r");
        }
        bw.write(";\r");
        bw.close();
    }

    public boolean compareGroup(FriendGroup comparedGroup){
        if (this.name.equals(comparedGroup.name)){
            return true;
        }
        return false;
    }

    public String getName(){
        return this.name;
    }

    public String toString(){
        return this.name;
    }
}
