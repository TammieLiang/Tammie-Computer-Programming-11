package sample;

import java.util.Date;

public class Log {
    private String result;
    private Date date = new Date();
    private double moneyLostOrGained;

    public Log(String result, double moneyLostOrGained){
        this.result = result;
        this.moneyLostOrGained = moneyLostOrGained;
    }

    public String toString(){
        if (this.result.equals("Win")) {
            return "Result: " + this.result + ", " + this.date + ", " + "Won: $" + this.moneyLostOrGained;
        }
        if (this.result.equals("Loss")){
            return "Result: " + this.result + ", " + this.date + ", " + "Lost: $" + moneyLostOrGained;
        }

        return "Result: " + this.result + ", " + this.date + ", No money lost or gained.";
    }
}
