import javax.security.sasl.SaslClient;
import java.util.ArrayList;
import java.util.Date;

public class Customer {
    private int accountNumber;

    private ArrayList<Deposit> depositRecords;
    private ArrayList<Withdraw> withdrawRecords;

    private double checkingBalance;
    private double savingBalance;
    private double savingRate;
    private String name;
    public static final String CHECKING = "Checking";
    public static final String SAVING = "Saving";
    private final int OVERDRAFT = -100;

    Customer(){
        this.name = "";
        this.accountNumber++;
        this.checkingBalance = 0;
        this.savingBalance = 0;
    }

    Customer(String name, int accountNumber, double checkingDeposit, double savingDeposit){
        this.name = name;
        this.accountNumber = accountNumber;
        this.checkingBalance = checkingDeposit;
        this.savingBalance = savingDeposit;
        this.withdrawRecords = new ArrayList<>();
        this.depositRecords = new ArrayList<>();
    }

    //Requires: positive amount, date, checkings or savings account
    //Modifies: this
    //Effect: deposits the amount in the parameter to the corresponding account
    public double deposit(double amt, Date date, String account){

        if (amt <= 0 && account.equalsIgnoreCase(this.CHECKING)){
            return this.checkingBalance;
        }

        if (amt <= 0 && account.equalsIgnoreCase(this.SAVING)){
            return this.savingBalance;
        }

        this.depositRecords.add(new Deposit (amt, date, account));

        if (account.equalsIgnoreCase(this.CHECKING)){
            this.checkingBalance += amt;
        }

        if (account.equalsIgnoreCase(this.SAVING)){
            this.savingBalance += amt;
        }

        return 0;
    }


    //Requires: positive amount to be withdrawn, checking or saving account
    //Modifies: nothing
    //Effect: returns true if the amount withdrawn will cause the corresponding account balance to go below -100,
    //        returns false if the amount withdrawn will not cause the corresponding account balance to go below -100.
    private boolean checkOverdraft(double amt, String account){
        if (account.equalsIgnoreCase(this.CHECKING) && this.checkingBalance - amt < OVERDRAFT){
            return true;
        }

        if (account.equalsIgnoreCase(this.SAVING) && this.savingBalance - amt < OVERDRAFT){
            return true;
        }

        return false;
    }

    //Requires: positive amount that won't cause balance
    //          to go BELOW -100, date, and checking or saving account */
    //Modifies: this
    //Effect: withdraws the amount in the parameter to the corresponding account
    public double withdraw(double amt, Date date, String account){
        if (account.equalsIgnoreCase(this.SAVING) && this.checkOverdraft(amt, account) == true){
            return this.savingBalance;
        }

        if (account.equalsIgnoreCase(this.CHECKING) && this.checkOverdraft(amt, account) == true){
            return this.checkingBalance;
        }

        if (amt <= 0 && account.equalsIgnoreCase(this.CHECKING)){
            return this.checkingBalance;
        }

        if (amt <= 0 && account.equalsIgnoreCase(this.SAVING)){
            return this.savingBalance;
        }

        this.withdrawRecords.add(new Withdraw (amt, date, account));

        if (account.equalsIgnoreCase(this.CHECKING)){
            return this.checkingBalance -= amt;
        }

        if (account.equalsIgnoreCase(this.SAVING)){
            return this.savingBalance -= amt;
        }

        return 0;

    }

    //do not modify
    public void displayDeposits(){
        for(Deposit d : this.depositRecords){
            System.out.println(d);
        }
    }
    //do not modify
    public void displayWithdraws(){
        for(Withdraw w : this.withdrawRecords){
            System.out.println(w);
        }
    }

    //Requires: nothing
    //Modifies: nothing
    //Effects: returns the checking account balance
    public double getCheckingBalance() {
        return checkingBalance;
    }

    //Requires: nothing
    //Modifies: nothing
    //Effects: returns the saving account balance
    public double getSavingBalance() {
        return savingBalance;
    }

    //Requires: nothing
    //Modifies: nothing
    //Effects: returns the list of deposits made
    public ArrayList<Deposit> getDeposits() {
        return this.depositRecords;
    }

    //Requires: nothing
    //Modifies: nothing
    //Effects: returns the list of withdraws made
    public ArrayList<Withdraw> getWithdraws() {
        return this.withdrawRecords;
    }


}