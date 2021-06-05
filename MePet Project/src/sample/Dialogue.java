package sample;

import java.io.IOException;
import java.io.*;
import java.util.ArrayList;

public class Dialogue {
    ArrayList<String> whenClicked;
    ArrayList<String> talkButton;
    ArrayList<String> heartoohart;
    String username;


    public Dialogue() throws IOException{
        this.whenClicked = new ArrayList<>();
        this.talkButton = new ArrayList<>();
        this.heartoohart = new ArrayList<>();
        FileReader fr = new FileReader("Username.txt");
        BufferedReader br = new BufferedReader(fr);
        username = br.readLine();
    }

    //Creates a list of messages for the pet when it's clicked, and adds them to
    // the corresponding list.
    public void createAllMessagesForClicked(){

        String msg1 = "What's up, " + username + "?";
        String msg2 = "You like clicking me, don't ya?";
        String msg3 = "You clicked me. :O";
        String msg4 = "Instead of poking me, just talk to me normally!";
        String msg5 = "Hey fidgety clicker, hope you're doing well! :)";
        String msg6 = "You know what they say... one poke is one joke! Here's a joke: me!";
        String msg7 = "Idk what you poked me for, " + username + ", but hi! :D";
        String msg8 = "Hullo, " + username + "!";
        String msg9 = "Boop!";

        whenClicked.add(msg1);
        whenClicked.add(msg2);
        whenClicked.add(msg3);
        whenClicked.add(msg4);
        whenClicked.add(msg5);
        whenClicked.add(msg6);
        whenClicked.add(msg7);
        whenClicked.add(msg8);
        whenClicked.add(msg9);

    }

    //Creates all the messages for the pet when the talk button is pushed, and adds them to
    // the corresponding list.
    public void createAllMessagesForTalk() {
        String msg1 = "You're not alone! You got me, friends, family, and people that think your presence is a gift <3";
        String msg2 = "If you had a hard day, no worries. Hard days are part of the challenge, the more challenging" +
                " things get, the more rewarding it is when we get out of the challenge! :)";
        String msg3 = "No one can ever ever replace you in the world. Know that!";
        String msg4 = "Hey " + username + ", did you know bottling up your feelings will let those feelings consume you from the inside?" +
                "Let it out! Talk to me, talk to a friend or family! You can also vent to Heartoohart.";
        String msg5 = "Some people say I'm a bun. Some people say I'm a plant. I say I'm me, because that's all that" +
                " matters. No matter your identity, you're you and that's the best thing about you <3";
        String msg6 = "One has to be lost in order to be found. It's okay if you're feeling lost right now, you'll find" +
                " it soon :)";
        String msg7 = "I'm thankful for a wonderful owner like you. What are you thankful for?";
        String msg8 = "Did you know? Ultimate water was discovered by Chef Bao when he went mining. Crazy, huh?";
        String msg9 = "Heartoohart is the sweetest MePet you'll ever meet!";
        String msg10 = "Yknow, if we aim for the impossible, we will hit the all possibles that we may have never expected to hit" +
                " if we never tried to aim high!";
        String msg11 = "If opportunity knocks, OPEN THE DOOR!! With that being said, don't open your door to strangers.";

        talkButton.add(msg1);
        talkButton.add(msg2);
        talkButton.add(msg3);
        talkButton.add(msg4);
        talkButton.add(msg5);
        talkButton.add(msg6);
        talkButton.add(msg7);
        talkButton.add(msg8);
        talkButton.add(msg9);
        talkButton.add(msg10);
        talkButton.add(msg11);
    }

    //Creates all the messages Heartoohart can say when the talk button is pushed in the
    // vent tab. The messages are added to the corresponding list.
    public void createAllMessagesForHeartoohart(){
        String msg1 = "If you think talking to someone about your problems bothers them," +
                " let me tell you this. Your friends, family, peers, or anyone will never regret talking" +
                " or listening to you. They will only regret losing you.";
        String msg2 = "There is a place for everyone in the world. Your existence fills up a hole in someone's life <3";
        String msg3 = "A person cannot be a failure. Failure is an event. You still have a long way ahead of you. You may" +
                " ENCOUNTER failure, but you are not a failure yourself. In fact, failure are steps to success.";
        String msg4 = "Go ahead, I'm listening :) You're not alone!";
        String msg5 = "I may be a program... but I represent the programmer behind the screen, the very same human who" +
                " made me so you can feel better. There's always someone who cares for you, even if you may not" +
                " know it :)";
        String msg6 = "All feelings are valid. It's okay to not be okay :) We hit rough spots in life, that's okay." +
                " We'll get out of it!";
        String msg7 = "You're your own protagonist in your own story. Sometimes your story goes in unexpected paths, " +
                "but in the end the protagonist always comes out of the path stronger, no matter how long it takes.";
        String msg8 = "Healing isn't impossible. It takes effort and time, and that's ok!";

        heartoohart.add(msg1);
        heartoohart.add(msg2);
        heartoohart.add(msg3);
        heartoohart.add(msg4);
        heartoohart.add(msg5);
        heartoohart.add(msg6);
        heartoohart.add(msg7);
        heartoohart.add(msg8);
    }

    //Returns the list of messages created for the pet when it is clicked.
    public ArrayList<String> getWhenClicked(){
        return whenClicked;
    }

    //Returns the list of messages created for the pet when its talk button is clicked.
    public ArrayList<String> getTalkButton(){
        return talkButton;
    }

    //Returns the list of messages created for Heartoohart when its talk button is clicked.
    public ArrayList<String> getHeartoohart(){
        return heartoohart;
    }
}
