package sample;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Wallet {
    public double amount;

    public Wallet(){
        this.amount = 10000;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public void loseMoney (double amount){
        this.amount -= round(amount,2);
    }

    public void gainMoney (double amount){
        this.amount += round(amount,2);
    }

    public String toString(){
        return "$" + round(this.amount, 2);
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


}
