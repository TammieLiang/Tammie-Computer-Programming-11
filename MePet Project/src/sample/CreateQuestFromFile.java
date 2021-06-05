package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CreateQuestFromFile {

    private static FileReader fr;
    private static BufferedReader br;
    private static ArrayList <Quest> quests = new ArrayList<>();

    //Each line of the file is read, and added to a string. If the we read a line that's a semicolon,
    // that means we read all the lines that represent the instance variables for ONE quest. We then send the string
    // that had all our lines for the one quest to the method "parseQuest". After "parseQuest" finishes creating a quest
    // from those lines and adding it to the list of quests, we return the list of quests so it can be displayed.
    public static ArrayList createAllQuests() throws IOException {

        //We clear the list of quests so that deleted quests that were previously added into the quest list
        // are not considered. This is okay to do because we add all the quests that aren't deleted back into the list
        // anyways as we read ALL the lines of the txt file, and the txt file DOES NOT contain any lines or info of the deleted quest
        // (as long the user saved the correct list.
        quests.clear();
        fr = new FileReader("Quests.txt");
        br = new BufferedReader(fr);

        String line;
        String questString = "";

        while ((line = br.readLine()) != null) {
            if (!line.equals(";")) {
                questString += line;
            } else {
                parseQuest(questString);
                questString = "";
            }
        }
        return quests;
    }

    //The method splits the string it received as a parameter by the character "@" (so every "@" it sees
    // in the string, it removes the "@" and stores the text before and afte the @ as separate strings). For
    // example, if the string was "Questname@questinfo@questreward", then the split up parts would be "Questname",
    //"questinfo", and "questreward". The split up parts are then stored in an array list of strings.

    //The first split up part is set as the name of the quest. The second split up part is the quest description.
    //The third split up part is the quest's prize.
    //We then create a quest with those parts. Then, we go through the list of quests we already have. If the name
    // of the quest we created is the same as a quest that already exists in our list of quests, then we don't add
    // the quest we created into the list. If our list of quest does not have a quest that has the same name as the
    // quest we created, then we add it to the list of quests. We go back to the previous method now.

    public static void parseQuest(String questString){
        String [] questParts = questString.split("@");

        String name = questParts[0];
        String info = questParts[1];
        double prize = Double.parseDouble(questParts[2]);

        Quest quest = new Quest(name, info, prize);


        for (Quest q: quests){
            if (q.compareQuest(quest) == true){
                return;
            }
        }

        quests.add(quest);
    }
}
