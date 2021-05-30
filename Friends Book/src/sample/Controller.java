package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;


public class Controller {
    public Button btnAddFriend;
    public Button btnUnfriend;

    public TextField txtName;
    public TextField txtGender;
    public TextField txtAge;
    public TextField txtHobby;

    public Label lblName;
    public Label lblGender;
    public Label lblAge;
    public Label lblHobby;
    public Label lblNumOfFriends;
    public Label lblInvalid;
    public Label lblSuccess;

    public ListView <Friend> listFriends;

    private int numOfFriends = 0;

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
        Friend friend = new Friend(name, age, gender, hobby);

        listFriends.getItems().add(friend);
        this.numOfFriends++;

        lblNumOfFriends.setText(Integer.toString(this.numOfFriends));
        clearTextFields();
        listFriends.setDisable(false);

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

    public void unfriend(ActionEvent event){
        Friend friend;
        friend = listFriends.getSelectionModel().getSelectedItem();

        listFriends.getItems().remove(friend);
        listFriends.getSelectionModel().clearSelection();
        btnUnfriend.setDisable(true);

        this.numOfFriends--;
        lblNumOfFriends.setText(Integer.toString(this.numOfFriends));
        clearLabels();

        if (listFriends.getItems().size() == 0){
            listFriends.setDisable(true);
            btnUnfriend.setDisable(true);
        }
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
}
