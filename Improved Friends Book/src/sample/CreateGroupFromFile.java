package sample;

import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
public class CreateGroupFromFile {

    private static ArrayList<FriendGroup> groups = new ArrayList<>();

    public static ArrayList createGroupFromFile(String fileName) throws IOException{
        groups.clear();
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);

        String line;
        String groupLine = "";

        while ((line = br.readLine()) != null){

            if (!line.equals(";")) {
                groupLine += line;
            }
            else {
                parseGroup(groupLine);
                groupLine = "";
            }
        }

        return groups;
    }

    public static void parseGroup(String groupLine) throws IOException {
        ArrayList <String> friends = new ArrayList<>();

        String [] groupParts = groupLine.split(",");
        String name = groupParts[0];
        for (int i = 1; i < groupParts.length; i++){
            String friend = groupParts[i];
            friends.add(friend);
        }

        FriendGroup group = new FriendGroup(name);
        for (String f : friends){
            group.addFriend(f);
        }

        for (FriendGroup g : groups){
            if (g.compareGroup(group) == true){
                return;
            }
        }

        groups.add(group);
    }

}
