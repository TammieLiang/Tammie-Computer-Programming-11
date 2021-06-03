package sample;

import java.io.IOException;
import java.io.*;

public class Friend {
    private String name;
    private String gender;
    private int age;
    private String hobby;

    public Friend(String name, String gender, int age, String hobby){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.hobby = hobby;
    }

    public void writeToFile()throws IOException{
        FileWriter fw = new FileWriter("Friends.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(this.name + ",\r");
        bw.write(this.gender + ",\r");
        bw.write(Integer.toString(this.age) + ",\r");
        bw.write(this.hobby + "\r");
        bw.write(";\r");

        bw.close();
    }

    public boolean compareFriend (Friend comparedFriend){
        if (this.name.equals(comparedFriend.getName()) && this.gender.equals(comparedFriend.getGender()) &&
        this.age == comparedFriend.getAge() && this.hobby.equals(comparedFriend.getHobby())){
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getHobby() {
        return hobby;
    }

    public String toString(){
        return this.name;
    }
}
