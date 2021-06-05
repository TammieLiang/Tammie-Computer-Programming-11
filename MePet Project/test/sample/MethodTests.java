package sample;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.*;

public class MethodTests {
    private Food food;
    private Food compareFood;
    private Food anotherFoodToCompare;
    private Quest quest;
    private Quest questToCompare;
    private Quest anotherQuestToCompare;
    private Quest fourthQuestToCompare;
    private Dialogue dialogue;
    private Store store;


    @Before
    public void setUp() throws IOException {
        this.food = new Food("Food", 3.00, "Info", 5);
        this.compareFood = new Food("Food",  "Infoz", 10);
        this.anotherFoodToCompare = new Food ("foodie", 3.00, "Info", 5);

        this.quest = new Quest("Quest", "Hi", 0);
        this.questToCompare = new Quest("questoria", "Hi", 0);
        this.anotherQuestToCompare = new Quest("questoria", "hello", 100);
        this.fourthQuestToCompare = new Quest("qUesToRia", "h", 0);

        this.dialogue =  new Dialogue();


    }

    @Test
    public void testFoodFeed(){
        //1 is a valid feed number. The food quantity should decrease by 1 and become 4.
        this.food.feed(1);
        assertEquals(4, this.food.getQuantity());

        //5 in this case is an invalid number because it exceeds the current food quantity.
        //The food quantity should not change in this case.
        this.food.feed(5);
        assertEquals(4, this.food.getQuantity());

        //-1 is an invalid feed number. It doesn't make sense in the real world to feed a negative
        // amount of food. The food quantity therefore should not change in this case.
        this.food.feed(-1);
        assertEquals(4, this.food.getQuantity());
    }

    @Test
    public void testFoodIncreaseQuantity(){
        //Any number greater than or equal to zero is a valid increase number.
        //Therefore, the quantity of the food should increase by 1.
        this.food.increaseQuantity(1);
        assertEquals(6, this.food.getQuantity());

        //Any number greater than or equal to zero is a valid increase number.
        //In this case, the quantity doesn't change since we add 0 to the current quantity.
        this.food.increaseQuantity(0);
        assertEquals(6, this.food.getQuantity());

        //-10 is an invalid parameter since it is not normal convention to increase
        //food by a negative amount. That's the feed() method's job (to decrease qty).
        //Therefore, the quantity should not change since the increase of -10 doesn't happen.
        this.food.increaseQuantity(-10);
        assertEquals(6, this.food.getQuantity());

    }

    @Test
    public void testFoodCompareFood(){
        //this.food and this.compareFood have the same food name. Therefore,
        //both foods are the same and the compareFood method should return true,
        //even if their other instance variables are different from each other.
        assertTrue(this.food.compareFood(compareFood));

        //this.food and this.anotherFoodToCompare have different food names
        //even if their other instance variables are the same.
        //Therefore they are not the same food.
        assertFalse(this.food.compareFood(anotherFoodToCompare));

        //Different food names should return false.
        assertFalse(this.compareFood.compareFood(anotherFoodToCompare));

        this.compareFood.setName("foodie");

        //Now that compareFood's name changed to "foodie", it is exactly
        //the same as anotherFoodToCompare's name, "foodie".
        //Therefore, the compareFood() method should return true.
        assertTrue(this.compareFood.compareFood(anotherFoodToCompare));

        this.food.setName("fOoDie");

        //Now that this.food's name changed to "fOoDie", it is considered
        //the same name as this.compareFood's name, "foodie" even if the capitalization
        //is different. Therefore, the compareFood() method should return true.
        assertTrue(this.food.compareFood(this.compareFood));
    }

    @Test
    public void testQuestCompareQuest(){
        //this.quest's name differs from questToCompare's name.
        //Therefore, the quests are not the same and the compareQuest()
        // method should return false.
        assertFalse(this.quest.compareQuest(questToCompare));

        //this.questToCompare and this.anotherQuestToCompare have the same name. Therefore,
        // both quests are the same and the compareQuest method should return true,
        // even if their other instance variables are different from each other.
        assertTrue(this.questToCompare.compareQuest(anotherQuestToCompare));

        //this.anotherQuestToCompare and fourthQuestToCompare have the same name,
        // just in different capitalisation. Therefore, they should still
        // be considered the same quest regardless of their other instance variables.
        assertTrue(this.anotherQuestToCompare.compareQuest(fourthQuestToCompare));
    }

    @Test
    public void testDialogueCreations(){
        //Should create all 8 messages for the Heartoohart pet.
        //Therefore, the size of the list containing all her messages should be 8.
        this.dialogue.createAllMessagesForHeartoohart();
        assertEquals(8, this.dialogue.getHeartoohart().size());

        //Should create all 11 messages for the MePet talk button.
        //Therefore, the size of the list containing all the button's messages should be 11.
        this.dialogue.createAllMessagesForTalk();
        assertEquals(11, this.dialogue.getTalkButton().size());

        //Should create all 9 messages for the MePet pet.
        //Therefore, the size of the list containing all its messages should be 9.
        this.dialogue.createAllMessagesForClicked();
        assertEquals(9, this.dialogue.getWhenClicked().size());
    }

    @Test
    public void testStoreStock(){
        this.store = new Store();

        //Should create all 6 Food items for the store.
        //Therefore, the size of the list containing all the store items should be 6.
        store.createStock();
        assertEquals(6, this.store.getStock().size());
    }


}