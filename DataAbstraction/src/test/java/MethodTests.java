import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MethodTests {
    private Customer customer;
    private Deposit savingsDeposit;
    private Deposit checkingsDeposit;
    private Withdraw savingsWithdraw;
    private Withdraw checkingsWithdraw;
    private Date date;


    @Before
    public void setUp(){
        this.date = new Date();
        this.customer = new Customer("Billy Joe Bob", 1, 200, 200);

        this.checkingsDeposit = new Deposit(200, this.date, "Checking");
        this.savingsWithdraw = new Withdraw(200, this.date, "Saving");

        this.savingsDeposit = new Deposit(200, this.date, "Saving");
        this.checkingsWithdraw = new Withdraw(200, this.date, "Checking");
    }

    @Test
    public void testDepositPrinting(){
        assertEquals("Deposit: $200.0 Date: " + this.date + " into account: Checking", this.checkingsDeposit.toString());
    }

    @Test
    public void testWithdrawPrinting(){
        assertEquals("Withdraw: $200.0 Date: " + this.date + " from account: Saving", this.savingsWithdraw.toString());
    }

    @Test
    public void testCustomerDeposit(){

        //This tests if the deposit method will add a valid amount to the SAVING account, and
        //that the deposit event is recorded
        this.customer.deposit(10, date, "saving");
        assertEquals(210, this.customer.getSavingBalance(), 0);
        assertTrue(this.customer.getDeposits().size() == 1);

        //This tests if the deposit method will add a valid amount to the CHECKING account, and
        //that the deposit event is recorded
        this.customer.deposit(10, date, "checking");
        assertEquals(210, this.customer.getCheckingBalance(), 0);
        assertTrue(this.customer.getDeposits().size() == 2);

        //If the deposit amount is negative, the deposit won't go through
        //and no deposit event is recorded. This applies for both saving and checking accounts.
        this.customer.deposit(-1, date, "saving");
        assertEquals(210, this.customer.getSavingBalance(), 0);
        assertTrue(this.customer.getDeposits().size() == 2);

        this.customer.deposit(-1, date, "checking");
        assertEquals(210, this.customer.getCheckingBalance(), 0);
        assertTrue(this.customer.getDeposits().size() == 2);

        //If the deposit amount is 0, no deposits happen
        //and no deposit event is recorded. This applies for both saving and checking accounts.
        this.customer.deposit(0, date, "saving");
        assertEquals(210, this.customer.getSavingBalance(), 0);
        assertTrue(this.customer.getDeposits().size() == 2);

        this.customer.deposit(0, date, "checking");
        assertEquals(210, this.customer.getCheckingBalance(), 0);
        assertTrue(this.customer.getDeposits().size() == 2);

    }

    @Test
    public void testCustomerWithdraw(){

        //This tests if the withdraw method can take away a valid amount for SAVING account, and
        //that the withdraw event is recorded
        this.customer.withdraw(100, date, "saving");
        assertEquals(100, this.customer.getSavingBalance(), 0);
        assertTrue(this.customer.getWithdraws().size() == 1);

        //This tests if the SAVING balance can go negative, and that the withdraw
        //event is recorded
        this.customer.withdraw(110, date, "saving");
        assertEquals(-10, this.customer.getSavingBalance(), 0);
        assertTrue(this.customer.getWithdraws().size() == 2);

        //This tests if the withdraw method follows the overdraft rules.
        //If someone tries to withdraw an amount that will make their SAVING account balance go below -100,
        //then the withdraw won't go through, and 0 is withdrawn. No withdraw event will be recorded.
        this.customer.withdraw(91, date, "saving");
        assertEquals(-10, this.customer.getSavingBalance(), 0);
        assertTrue(this.customer.getWithdraws().size() == 2);

        //This tests if the withdraw method can take away a valid amount for CHECKING account, and
        //that the withdraw event is recorded
        this.customer.withdraw(100, date, "checking");
        assertEquals(100, this.customer.getCheckingBalance(), 0);
        assertTrue(this.customer.getWithdraws().size() == 3 );

        //This tests if the CHECKING balance can go negative, and that the withdraw
        //event is recorded
        this.customer.withdraw(110, date, "checking");
        assertEquals(-10, this.customer.getCheckingBalance(), 0);
        assertTrue(this.customer.getWithdraws().size() == 4);

        //This tests if the withdraw method follows the overdraft rules.
        //If someone tries to withdraw an amount that will make their CHECKING account balance go below -100,
        //then the withdraw won't go through, and 0 is withdrawn. No withdraw event will be recorded.
        this.customer.withdraw(91, date, "checking");
        assertEquals(-10, this.customer.getCheckingBalance(), 0);
        assertTrue(this.customer.getWithdraws().size() == 4);

        //This tests that the withdraw will NOT follow through if the amount that wants to be withdrawn
        //is negative, for saving OR checking account.
        this.customer.withdraw(-1, date, "checking");
        assertEquals(-10, this.customer.getCheckingBalance(), 0);
        assertTrue(this.customer.getWithdraws().size() == 4);

        this.customer.withdraw(-1, date, "saving");
        assertEquals(-10, this.customer.getSavingBalance(), 0);
        assertTrue(this.customer.getWithdraws().size() == 4);

        //This tests that the withdraw will NOT follow through if the amount that wants to be withdrawn
        //is 0, for saving OR checking account.
        this.customer.withdraw(0, date, "checking");
        assertEquals(-10, this.customer.getCheckingBalance(), 0);
        assertTrue(this.customer.getWithdraws().size() == 4);

        this.customer.withdraw(0, date, "saving");
        assertEquals(-10, this.customer.getSavingBalance(), 0);
        assertTrue(this.customer.getWithdraws().size() == 4);

        //For saving account
        //If someone tries to withdraw an amount that will make their SAVING account balance equal EXACTLY -100,
        //then the withdraw WILL go through and be recorded.
        this.customer.withdraw(90, date, "saving");
        assertEquals(-100, this.customer.getSavingBalance(), 0);
        assertTrue(this.customer.getWithdraws().size() == 5);

        //for checking account
        this.customer.withdraw(90, date, "checking");
        assertEquals(-100, this.customer.getSavingBalance(), 0);
        assertTrue(this.customer.getWithdraws().size() == 6);

    }
}
