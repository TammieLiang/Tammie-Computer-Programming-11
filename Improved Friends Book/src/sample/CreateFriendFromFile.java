package sample;

import java.util.ArrayList;
import java.io.*;

public class CreateFriendFromFile {
    private static String name;
    private static String gender;
    private static int age;
    private static String hobby;
    private static FileReader fr;
    private static BufferedReader br;

    private static ArrayList <Friend> friends = new ArrayList<>();

    public static ArrayList createFriendsFromFile(String fileName) throws IOException{
        friends.clear();
        fr = new FileReader(fileName);
        br = new BufferedReader(fr);

        String line;
        String friendString = "";

        while ((line = br.readLine()) != null){
            if (!line.equals(";")){
                friendString += line;
            } else {
                parseFriend(friendString);
                friendString = "";
            }
        }
        return friends;
    }

    public static void parseFriend(String friendString){

        String [] friendParts = friendString.split(",");
        String name = friendParts[0];
        String gender = friendParts[1];
        int age = Integer.valueOf(friendParts[2]);
        String hobby = friendParts[3];

        Friend friend = new Friend(name, gender, age, hobby);

        for (Friend h : friends){
            if (h.compareFriend(friend) == true){
                return;
            }
        }
        friends.add(friend);
    }

}
