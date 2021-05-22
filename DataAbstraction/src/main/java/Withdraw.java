import java.util.Date;

public class Withdraw {
    private double amount;
    private Date date;
    private String account;

    Withdraw(double amount, Date date, String account){
        this.amount = amount;
        this.date = date;
        this.account = account;
    }

    @Override
    public String toString(){
        //your code here
        return "Withdraw: $" + this.amount + " Date: " + this.date + " from account: " + this.account;
    }
}