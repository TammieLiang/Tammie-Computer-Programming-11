import java.util.Date;

public class Main {
    public static void main (String args[]){
        Date date = new Date();

        Customer customer = new Customer("Billy Joe Bob", 1, 50, 50);
        customer.deposit(20, date, "Checking");
        customer.deposit(10, date, "Saving");

        customer.displayDeposits();
    }
}
