import java.io.IOException;
import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main (String args[]) throws IOException {
        System.out.println("The program shows the index position of the line(s) that a word appears in.");
        System.out.println("-----------------For example...-----------------------");

        System.out.print("'1206' appears in the line with index position(s) ");
        //should print out 0 since 1206 is in the FIRST line of the txt file, aka index 0
        System.out.println(returnIndexOfWord("1206"));

        System.out.print("'1843' appears in the line with index position(s) ");
        //should print out 1 since '1843' appears in the SECOND line of the txt file excluding empty lines, aka index 1
        System.out.println(returnIndexOfWord("1843"));

        System.out.print("'programs' appears in the line with index position(s) ");
        //should print out 3, 4, 6, and 7 since the word appears in the 4th, 5th, 7th, and 8th line
        // (excluding empty lines) of the file at least once.
        System.out.println(returnIndexOfWord("programs"));
    }

    public static String returnIndexOfWord(String word) throws IOException {
        //This removes any empty spaces in the word and any periods or commas so we can better look for it
        //later
        word.trim().replace(",", "").replace(".", "");

        ArrayList<String> lines = new ArrayList<>();
        ArrayList<Integer> indexPositions = new ArrayList<>();


        FileReader fr = new FileReader("ProgrammingHistory.txt");
        BufferedReader br = new BufferedReader(fr);

        String line;

        //This while loop reads every line in the file until the file ends
        //Then, each line it reads, it adds the line as an element in an ArrayList.
        //If the line does not contain any text and is just an empty line, the line does NOT get added to the ArrayList.
        while ((line = br.readLine()) != null) {
            if (!line.equals("")) {
                lines.add(line);
            }
        }

        //This iterates through every line that was stored in the ArrayList of lines.
        //The line is then split up into separate words, stored in an array.
        //If the array of words has the word we are looking for, it means that line contains that word.
        //Therefore, we add the index position of the line to an ArrayList of valid index positions.
        String [] parts = null;
        for (int i = 0; i < lines.size(); i++) {
            String l = lines.get(i);
            parts = l.split(" ");


            for (int j = 0; j < parts.length; j++){
                String s = parts[j].replace(",", "");
                if (s.equalsIgnoreCase(word)){
                    indexPositions.add(i);
                    break;
                }
            }

        }
        String toBeReturned = "";

        for (int i = 0; i < indexPositions.size(); i++){
            int pos = indexPositions.get(i);
            if (i == indexPositions.size() - 1 && indexPositions.size() > 1){
                toBeReturned += "and " + pos;
                break;
            }

            if (i == indexPositions.size() - 1 && indexPositions.size() == 1) {
                toBeReturned += pos;
                break;
            }
            toBeReturned += pos + ", ";
        }

        return toBeReturned;
    }
}
