package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.util.ArrayList;

import java.io.IOException;


public class Controller {
    public Button btnAddFriend;
    public Button btnUnfriend;
    public Button btnMakeGroup;
    public Button btnSaveFriends;
    public Button btnLoadFriends;
    public Button btnDeleteGroup;
    public Button btnSaveGroup;
    public Button btnSaveIndividualGroup;

    public TextField txtName;
    public TextField txtGender;
    public TextField txtAge;
    public TextField txtHobby;
    public TextField txtGroupName;
    public TextField txtGroupToLoad;

    public Label lblName;
    public Label lblGender;
    public Label lblAge;
    public Label lblHobby;
    public Label lblNumOfFriends;
    public Label lblInvalid;
    public Label lblSuccess;
    public Label lblFriendsInGroup;
    public Label lblNumOfFriendsInGroup;
    public Label lblNumOfGroups;
    public Label lblSavedGroup;
    public Label lblDeletedGroupMsg;

    public ListView <Friend> listFriends;
    public ListView <FriendGroup> listGroups;

    private int numOfFriends = 0;
    private int numOfGroups = 0;

    public void ifEmptySetDefaults(){

        if(txtName.getText().isEmpty()){
            txtName.setText("(No name)");

        }

        if (txtGender.getText().isEmpty()){
            txtGender.setText("Undefined");
        }

        if (txtAge.getText().isEmpty() || Integer.valueOf(txtAge.getText()) < 0){
            txtAge.setText("0");
        }

        if (txtHobby.getText().isEmpty()){
            txtHobby.setText("N/A");
        }
    }

    public void addFriend(ActionEvent event){

        if (txtHobby.getText().isEmpty() && txtAge.getText().isEmpty()
                && txtGender.getText().isEmpty() && txtName.getText().isEmpty()){
            lblInvalid.setText("You cannot add an empty friend!");
            lblSuccess.setText("");
            return;
        }
        lblInvalid.setText("");
        lblSuccess.setText("Added friend to friends list!");
        ifEmptySetDefaults();

        String name = txtName.getText();
        String gender = txtGender.getText();
        int age = Integer.valueOf(txtAge.getText());
        String hobby = txtHobby.getText();
        Friend friend = new Friend(name, gender, age, hobby);

        listFriends.getItems().add(friend);
        this.numOfFriends++;

        updateFriendNumbers();
        clearTextFields();

        listFriends.setDisable(false);
        btnMakeGroup.setDisable(false);

    }

    public void saveFriends(ActionEvent event) throws IOException{
        File oldFriendsFile = new File("Friends.txt");
        oldFriendsFile.delete();
        ObservableList<Friend> friends = listFriends.getItems();

        for (Friend friend : friends){
            friend.writeToFile();
        }

        listFriends.getItems().clear();
        listFriends.setDisable(true);
        btnMakeGroup.setDisable(true);
    }

    public void saveGroups(ActionEvent event) throws IOException{
        File oldGroupsFile = new File("Groups.txt");
        oldGroupsFile.delete();

        ObservableList <FriendGroup> groups = listGroups.getItems();

        for (FriendGroup group: groups){
            group.writeToAllGroupFile();
        }

        listGroups.getItems().clear();
        listGroups.setDisable(true);

        btnSaveIndividualGroup.setDisable(true);
        btnSaveGroup.setDisable(true);
        btnDeleteGroup.setDisable(true);

        lblSavedGroup.setText("Successfully saved all displayed groups!");
        lblDeletedGroupMsg.setText("");
    }

    public void saveIndividualGroup(ActionEvent event) throws IOException{

        FriendGroup group;
        group = listGroups.getSelectionModel().getSelectedItem();

        group.writeToIndividualFile();

        lblSavedGroup.setText("Successfully saved " + group.getName() + " to its own file!");
        lblDeletedGroupMsg.setText("");

    }

    // Method to choose which group file to load
    public void loadFile(String fileName) throws IOException{
        listGroups.getItems().clear();

        ArrayList<FriendGroup> group = CreateGroupFromFile.createGroupFromFile(fileName);

        for (FriendGroup groupInFile: group){
            listGroups.getItems().add(groupInFile);
        }
        numOfGroups = listGroups.getItems().size();

        listGroups.setDisable(false);
        updateGroupNumbers();

    }

    public void loadFriends(ActionEvent event) throws IOException{
        listFriends.setDisable(false);
        listFriends.getItems().clear();

        ArrayList <Friend> friendz = CreateFriendFromFile.createFriendsFromFile("Friends.txt");
        int numOfTotalFriends = 0;
        for (Friend friend : friendz){
            listFriends.getItems().add(friend);
            numOfTotalFriends++;
        }
        lblNumOfFriends.setText(Integer.toString(numOfTotalFriends));
        numOfFriends = numOfTotalFriends;
        updateFriendNumbers();
        btnMakeGroup.setDisable(false);
    }

    //Loads a specific searched group onto the groups list
    public void loadIndividualGroup(ActionEvent event) throws IOException{
        String groupToLoad = txtGroupToLoad.getText() + ".txt";

        loadFile(groupToLoad);

        txtGroupToLoad.clear();
        lblDeletedGroupMsg.setText("");

        btnSaveGroup.setDisable(false);
        btnDeleteGroup.setDisable(false);

    }

    //Loads ALL saved groups
    public void loadAllGroups(ActionEvent event) throws IOException{

        loadFile("Groups.txt");
        updateGroupNumbers();
        lblSavedGroup.setText("Loaded all groups!");
        lblDeletedGroupMsg.setText("");

        btnSaveIndividualGroup.setDisable(false);
        btnSaveGroup.setDisable(false);

    }
    public void displayFriend(MouseEvent event){
        Friend friend;
        friend = listFriends.getSelectionModel().getSelectedItem();

        lblName.setText(friend.getName());
        lblGender.setText(friend.getGender());
        lblAge.setText(Integer.toString(friend.getAge()));
        lblHobby.setText(friend.getHobby());

        btnUnfriend.setDisable(false);
    }

    public void displayFriendsInGroup(MouseEvent event){
        FriendGroup group;
        group = listGroups.getSelectionModel().getSelectedItem();

        String listOfFriends = "";
        int numOfFriendz = 0;
        for (String friend: group.getFriends()){
            listOfFriends += friend + "\n";
            numOfFriendz++;
        }

        lblNumOfFriendsInGroup.setText(Integer.toString(numOfFriendz));
        lblFriendsInGroup.setText(listOfFriends);
        btnDeleteGroup.setDisable(false);
        updateFriendNumbers();

        lblDeletedGroupMsg.setText("");
    }

    //Removes friend from display list but not from file. The user will have to click
    //save again to update the list with removal
    public void unfriend(ActionEvent event) throws IOException{
        Friend friend;
        friend = listFriends.getSelectionModel().getSelectedItem();

        listFriends.getItems().remove(friend);
        listFriends.getSelectionModel().clearSelection();
        btnUnfriend.setDisable(true);

        this.numOfFriends--;
        updateFriendNumbers();

//        FileReader fr = new FileReader("Friends.txt");
//        BufferedReader br = new BufferedReader(fr);
//
//        String friendString = "";
//        String line;
//        while ((line = br.readLine()) != null){
//            friendString += line;
//        }
//
//        String [] friendParts = friendString.split(",");
//
//        ArrayList <String> toKeep = new ArrayList<>();
//
//        for (String part : friendParts){
//            toKeep.add(part);
//        }
//
//        for (int i = 0; i < toKeep.size(); i++){
//            String name = toKeep.get(i);
//            String gender = toKeep.get(i + 1);
//            String age = toKeep.get(i + 2);
//            String hobby = toKeep.get(i + 3);
//            System.out.println(name + gender + age);
//            if (name.equals(friend.getName() + ",")){
//                if (gender.equals(friend.getGender() + ",")){
//                    if (age.equals(friend.getAge() + ",")){
//                        if (hobby.equals(friend.getHobby() + ",;")){
//                            toKeep.remove(name);
//                            toKeep.remove(gender);
//                            toKeep.remove(age);
//                            toKeep.remove(hobby);
//                            File friendFile = new File("Friends.txt");
//                            friendFile.delete();
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//        FileWriter fw = new FileWriter("Friends.txt");
//        BufferedWriter bw = new BufferedWriter(fw);
//
//        for (String friendPart : toKeep){
//            bw.write(friendPart);
//        }
//        bw.close();


        clearLabels();

        if (listFriends.getItems().size() == 0){
            listFriends.setDisable(true);
            btnUnfriend.setDisable(true);
        }
    }

    //Makes a group, adds it to the displayed list, but doesn't save it
    public void makeGroup(ActionEvent event) throws IOException {
        String groupName = txtGroupName.getText();
        FriendGroup group = new FriendGroup(groupName);

        for (Friend friend: listFriends.getItems()){
            group.addFriend(friend.getName());
        }

        listFriends.getItems().clear();
        listGroups.getItems().add(group);

        listGroups.setDisable(false);
        btnDeleteGroup.setDisable(false);
        btnSaveGroup.setDisable(false);

        txtGroupName.clear();

        numOfGroups++;
        updateGroupNumbers();
        lblDeletedGroupMsg.setText("");
    }

    //Removes group from displayed group list, but not from saved file list.
    //The user will have to save again.
    //This removes the inidividual group file, though. So the group can no longer be searched
    //or loaded individually.
    public void deleteGroup(ActionEvent event) throws IOException{
        FriendGroup group = listGroups.getSelectionModel().getSelectedItem();

        File groupToRemoveFile = new File (group.getName() + ".txt");
        groupToRemoveFile.delete();

        listGroups.getItems().remove(group);
        numOfGroups = listGroups.getItems().size();

        lblSavedGroup.setText("");
        lblDeletedGroupMsg.setText("Deleted group from DISPLAYED list. Click save again to save current displayed list.");
        updateGroupNumbers();
    }

    public void clearTextFields(){
        txtName.clear();
        txtGender.clear();
        txtAge.clear();
        txtHobby.clear();
    }

    public void clearLabels(){
        lblName.setText("");
        lblGender.setText("");
        lblAge.setText("");
        lblHobby.setText("");
    }

    public void updateGroupNumbers(){
        lblNumOfGroups.setText(Integer.toString(numOfGroups));
    }

    public void updateFriendNumbers(){
        lblNumOfFriends.setText(Integer.toString(this.numOfFriends));
    }
}
