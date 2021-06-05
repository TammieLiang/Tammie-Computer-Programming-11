package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.*;
import java.util.ArrayList;

public class Controller {
    public Button btnPurchase;

    public TextField txtPetName;
    public TextField txtUsername;
    public TextField txtVent;
    public TextField txtQuestName;
    public TextField txtQuestInfo;

    public ImageView btnChangePetname;
    public ImageView imgClearBTN;
    public ImageView imgLoad;

    public Label lblPetName;
    public Label lblSetNameSuccess;
    public Label lblUsername;
    public Label lblPetDialogue;
    public Label lblStoreOwnerTalk;
    public Label lblHeartoohartTalk;
    public Label lblGladiatorTalk;
    public Label lblFoodName;
    public Label lblFoodInfo;
    public Label lblFoodPrice;
    public Label lblFoodNameInv;
    public Label lblFoodInfoInv;
    public Label lblFoodQuantityInv;
    public Label lblQuestName;
    public Label lblQuestInfo;
    public Label lblQuestReward;
    public Label lblPurchaseStatus;
    public Label lblBalance;
    public Label lblBalance1;
    public Label lblFeedStatus;
    public Label lblInventorySaveStatus;
    public Label lblStartMsg;

    public ListView <Food> listShopItems = new ListView();
    public ListView <Food> listInventoryItems = new ListView();
    public ListView <Quest> listQuests = new ListView();

    private double balance = 0;

    //Loads all the txt files saved. If the text file doesn't exist, then we don't read the non-existent
    // text file.
    public void loadProgress() throws IOException{

        File usernameFile = new File("Username.txt");
        File petnameFile = new File("Pet Name.txt");
        if (usernameFile.exists() && petnameFile.exists()) {
            FileReader fr = new FileReader("Username.txt");
            BufferedReader br = new BufferedReader(fr);
            loadNames();

            lblPetDialogue.setText("Welcome back, " + br.readLine() + "!");
        }
        loadStore();

        File questFile = new File("Quests.txt");
        if (questFile.exists()){
            loadQuests();
        }

        File balanceFile = new File("Balance.txt");
        if (balanceFile.exists()) {
            loadBalance();
        }

        File inventoryFile = new File("Inventory.txt");
        if (inventoryFile.exists()) {
            loadInventoryFood();
        }

        if (!usernameFile.exists() && !petnameFile.exists() && !questFile.exists() && !balanceFile.exists() && !inventoryFile.exists()){
            return;
        }
        lblStartMsg.setText("Loaded saved progress!");

    }

    //Updates the balance label in the application with the balance that was saved.
    public void loadBalance() throws IOException{
        FileReader fr = new FileReader("Balance.txt");
        BufferedReader br = new BufferedReader(fr);

        double savedBalance = Double.parseDouble(br.readLine());

        balance = savedBalance;

        updateBalance();
    }

    //Displays all the store items.
    public void loadStore(){
        listShopItems.getItems().clear();
        Store stock = new Store();
        stock.createStock();

        for (Food food: stock.getStock()){
            listShopItems.getItems().add(food);
        }
    }

    //Displays the names that the user chose for themselves and for the pet.
    public void loadNames() throws IOException{
        FileReader frPet = new FileReader("Pet Name.txt");
        BufferedReader brPet = new BufferedReader(frPet);

        FileReader fr = new FileReader("Username.txt");
        BufferedReader br = new BufferedReader(fr);

        String username = br.readLine();
        String petName = brPet.readLine();

        lblUsername.setText(username);
        lblPetName.setText(petName);
        txtUsername.setText(username);
        txtPetName.setText(petName);
    }

    //Displays all the quests that the user saved and added by creating quest objects
    // from the lines in the "Quests.txt" file. If the file doesn't exist,
    // the quest gladiator tells the user, and nothing is read or loaded.
    public void loadQuests() throws IOException{
        listQuests.getItems().clear();


        String username = "user";
        String petName = "MePet";
        FileReader frPet;
        BufferedReader brPet;
        FileReader fr;
        BufferedReader br;

        File petFile = new File ("Pet Name.txt");
        if (petFile.exists()){
            frPet = new FileReader("Pet Name.txt");
            brPet = new BufferedReader(frPet);
            petName = brPet.readLine();
        }

        File userFile = new File("Username.txt");
        if (userFile.exists()){
            fr = new FileReader("Username.txt");
            br = new BufferedReader(fr);
            username = br.readLine();
        }

        File questFile = new File("Quests.txt");
        if (!questFile.exists()){
            lblGladiatorTalk.setText("You didn't have any incomplete quests saved. Create some and conquer some goals today!!" +
                    " If you're done for the day, well done!");
            return;
        }

        ArrayList <Quest> quests = new ArrayList<>();
        quests = CreateQuestFromFile.createAllQuests();
        for (Quest questCreated: quests){
            listQuests.getItems().add(questCreated);
        }
        lblGladiatorTalk.setText("Hey " + username + "!! These are the quests you need to conquer in your life!" +
                " Once you complete them, reward yourself and buy " + petName + " some food or drinks! Go gettem!!");
    }

    //Displays all the inventory food that was saved.
    public void loadInventoryFood() throws IOException{

        File inventoryFile = new File("Inventory.txt");
        if (!inventoryFile.exists()) {
            return;
        }
        listInventoryItems.getItems().clear();

        ArrayList <Food> foodToLoad = CreateFoodFromFile.createAllFoodFromFile();
        for (Food foodCreated: foodToLoad){
            listInventoryItems.getItems().add(foodCreated);
        }
    }

    //Displays the quest name and the quest description for the selected quest.
    public void displayQuest(MouseEvent event){
        Quest questToDisplay = listQuests.getSelectionModel().getSelectedItem();

        lblQuestName.setText(questToDisplay.getName());
        lblQuestInfo.setText(questToDisplay.getInfo());
        lblQuestReward.setText("$"+ questToDisplay.getPrize());
        lblGladiatorTalk.setText("Good luck in completing " + questToDisplay.getName() + "! Although" +
                " I'm sure you won't need luck.");
    }

    //Displays the food/drink name and info and quantity for the selected item in the
    // inventory list.
    public void displayInventoryItem(){
        if (listInventoryItems.getItems().isEmpty()){
            return;
        }
        Food food;
        food = listInventoryItems.getSelectionModel().getSelectedItem();

        lblFoodNameInv.setText(food.getName());
        lblFoodInfoInv.setText(food.getInfo());
        lblFoodQuantityInv.setText(Integer.toString(food.getQuantity()));
    }

    //Displays name, description, and price of the store item selected in the
    // store's item list.
    public void displayStoreItem(){
        Food food;
        food = listShopItems.getSelectionModel().getSelectedItem();

        lblFoodName.setText(food.getName());
        lblFoodInfo.setText(food.getInfo());
        lblFoodPrice.setText("$" + food.getPrice());
    }

//    public void switchToLobby(MouseEvent event) throws IOException{
//        Parent lobbyViewParent = FXMLLoader.load(getClass().getResource("Lobby.fxml"));
//        Scene lobbyScene = new Scene(lobbyViewParent);
//
//        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        window.setScene(lobbyScene);
//        window.show();
//    }

    //Updates and saves the pet name to whatever the user wrote in the corresponding text field
    // when the "change" button is clicked. If the user doesn't enter any characters, nothing is updated.
    public void setPetName() throws IOException{
        FileWriter fw = new FileWriter("Pet Name.txt");
        BufferedWriter bw = new BufferedWriter(fw);

        String petName = txtPetName.getText();
        if (petName.isEmpty()){
            lblPetName.setText("You cannot set an empty pet name!");
            return;
        }

        bw.write(petName);
        bw.close();

        lblPetName.setText(petName);
        lblSetNameSuccess.setText("Successfully updated petname!");
        txtPetName.clear();

    }

    //Updates and saves the username to whatever the user wrote in the  corresponding
    // text field when the "change" button is clicked. If the user doesn't enter any characters, nothing is updated.
    public void changeUsername() throws IOException{
        FileWriter fw = new FileWriter("Username.txt");
        BufferedWriter bw = new BufferedWriter(fw);

        String username = txtUsername.getText();
        if (username.isEmpty()){
            lblSetNameSuccess.setText("You cannot set an empty username!");
            return;
        }

        bw.write(username);
        bw.close();

        lblUsername.setText(username);
        lblSetNameSuccess.setText("Successfully updated username!");
        txtUsername.clear();
    }

    //Adds the item into the list of inventories, and deducts the cost of the item from the
    // user's balance. If the item already exists in the inventory list, its quantity is increased by 1
    // and the item is not added as a separate item.
    public void purchaseItem(ActionEvent event) throws IOException{
        Food foodToPurchase = listShopItems.getSelectionModel().getSelectedItem();
        if (foodToPurchase.getPrice() > balance){
            lblPurchaseStatus.setText("You do not have enough money! Complete quests to earn money.");
            return;
        }

        balance -= foodToPurchase.getPrice();

        lblStoreOwnerTalk.setText("Thank you! Please come again :D");

        String name = foodToPurchase.getName();
        String info = foodToPurchase.getInfo();

        updateBalance();

        //Checking if the inventory already has the item. If it does, increase its quantity by 1.
        for (Food inventoryItem : listInventoryItems.getItems()){
            if (foodToPurchase.compareFood(inventoryItem) == true){
                inventoryItem.increaseQuantity(1);
                return;
            }
        }

        Food inventoryRevisedFood = new Food(name, info, 1);
        listInventoryItems.getItems().add(inventoryRevisedFood);
        inventoryRevisedFood.writeToFile();
        lblPurchaseStatus.setText("Purchase successful!");
    }

    //Decreases the quantity of item in the inventory by 1 and updates inventory list
    public void feedPet() throws IOException{
        if (listInventoryItems.getItems().isEmpty() || listInventoryItems.getSelectionModel().isEmpty()){
            return;
        }
        Food food;
        food = listInventoryItems.getSelectionModel().getSelectedItem();

        if (food.getQuantity() == 0){
            lblFeedStatus.setText("You ran out of " + food.getName() + "! Go to the store to get some more.");
            return;
        }

        lblPetDialogue.setText("Thank you so much for feeding me " + food.getName() + " !");
        lblFeedStatus.setText("Fed " + lblPetName.getText() + "!");

        food.feed(1);
        displayInventoryItem();
        saveCurrentInventoryForFeed();
    }

    //Adds and saves a quest with $3 reward.
    public void addAndSaveQuest(ActionEvent event) throws IOException{
        String name = txtQuestName.getText();
        String info = txtQuestInfo.getText();
        if (name.isEmpty()){
            lblGladiatorTalk.setText("Ey bro!!! You gotta give your quest a name!");
            return;
        }
        if (info.isEmpty()){
            info = name;
        }

        Quest quest = new Quest(name, info, 3.00);
        listQuests.getItems().add(quest);
        quest.writeToFile();

        lblGladiatorTalk.setText("Another challenge to conquer. Nice, hero!");
        txtQuestName.clear();
        txtQuestInfo.clear();
    }

    //Adds and saves a quest with $8 reward.
    public void addAndSaveBigQuest(ActionEvent event) throws IOException{
        String name = txtQuestName.getText();
        String info = txtQuestInfo.getText();
        if (name.isEmpty()){
            lblGladiatorTalk.setText("Ey bro!!! You gotta give your big quest a name!");
            return;
        }
        if (info.isEmpty()){
            info = name;
        }

        Quest bigQuest = new Quest(name, info, 8.00);
        listQuests.getItems().add(bigQuest);
        bigQuest.writeToFile();

        lblGladiatorTalk.setText("Another harder challenge to conquer. YOU CAN DO IT, HERO!!");
        txtQuestName.clear();
        txtQuestInfo.clear();
    }

    //This deletes the current quest file and writes a new quest file with all the current quests displayed
    //in the list. This is so the deleted/completed quests don't get saved.
    public void saveCurrentListOfQuests() throws IOException{
        File oldQuestFile = new File("Quests.txt");
        oldQuestFile.delete();

        ObservableList <Quest> questList = listQuests.getItems();

        for (Quest q: questList){
            q.writeToFile();
        }

        listQuests.getItems().clear();
        lblGladiatorTalk.setText("Save all quests that were displayed! If you need to see them again," +
                " load it up!!");
    }

    //Saves all the currently displayed items in the inventory.
    public void saveCurrentInventory() throws IOException{
        File oldInventoryFile = new File("Inventory.txt");
        oldInventoryFile.delete();

        ObservableList <Food> inventoryList = listInventoryItems.getItems();

        for (Food itemDisplayed: inventoryList){
            itemDisplayed.writeToFile();
        }

        listInventoryItems.getItems().clear();
    }
    //Saves all the currently displayed items in the inventory WITHOUT clearing.
    //This is used when the feed button is used. That way, the quantity of the item gets updated,
    // but the list still displays all the items so it doesn't look like the user fed the whole
    // list of items.
    public void saveCurrentInventoryForFeed() throws IOException{
        File oldInventoryFile = new File("Inventory.txt");
        oldInventoryFile.delete();

        ObservableList <Food> inventoryList = listInventoryItems.getItems();

        for (Food itemDisplayed: inventoryList){
            itemDisplayed.writeToFile();
        }

        lblInventorySaveStatus.setText("Saved inventory items!");

    }

    //This removes a quest from the list of quests displayed.
    public void deleteQuest() {
        if (listQuests.getItems().isEmpty() || listQuests.getSelectionModel().isEmpty()){
            return;
        }
        Quest questToDelete;
        questToDelete = listQuests.getSelectionModel().getSelectedItem();

        listQuests.getItems().remove(questToDelete);
        lblGladiatorTalk.setText("Not the right challenge, eh? All good! It's been trashed!");
    }

    //This removes a quest from the list of quest displayed, and adds the quests' prize
    // to the user's balance.
    public void completeQuest() throws IOException{
        if (listQuests.getItems().isEmpty() || listQuests.getSelectionModel().isEmpty()){
            return;
        }

        Quest completedQuest;
        completedQuest = listQuests.getSelectionModel().getSelectedItem();
        listQuests.getItems().remove(completedQuest);

        balance += completedQuest.getPrize();

        lblGladiatorTalk.setText("NICE WORK!! Knew you could do it. Don't forget to save the list so it" +
                " knows which one you completed already! KEEP UP THE GREAT WORK! Do another one >:)");

        updateBalance();
    }

    //This clears whatever the user wrote inside the text field for venting.
    public void clearVent(){
        txtVent.clear();
    }

    //This generates a random dialogue message from the corresponding list of messages,
    // and updates the corresponding label with that message.
    public void generateRandomMessage(ArrayList<String> messageList, Label label){
        int i = (int) (Math.random()*messageList.size());

        String message = messageList.get(i);
        label.setText(message);
    }

    //Changes the dialogue of the pet to a random message when the pet is clicked.
    public void changeDialogue() throws IOException{
        Dialogue dialogue = new Dialogue();
        dialogue.createAllMessagesForClicked();

        ArrayList<String> messages = dialogue.getWhenClicked();

        generateRandomMessage(messages, lblPetDialogue);

    }

    //Changes the dialogue of the pet to a random message when its talk button is clicked.
    public void talk() throws IOException{
        Dialogue dialogue = new Dialogue();
        dialogue.createAllMessagesForTalk();

        ArrayList <String> messages = dialogue.getTalkButton();

        generateRandomMessage(messages, lblPetDialogue);
    }

    //Changes the dialogue of Heartoohart to a random message when its talk button is clicked.
    public void talkToHeartoohart() throws IOException{
        Dialogue heartMsgs = new Dialogue();
        heartMsgs.createAllMessagesForHeartoohart();

        ArrayList <String> messages = heartMsgs.getHeartoohart();

        generateRandomMessage(messages, lblHeartoohartTalk);
    }

    //Saves the current balance the user has as well as sets the balance label
    // in the application to that balance.
    public void updateBalance() throws IOException{
        FileWriter fw = new FileWriter("Balance.txt");
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(Double.toString(balance));
        bw.close();
        fw.close();
        lblBalance.setText(Double.toString(balance));
        lblBalance1.setText(Double.toString(balance));
    }
}