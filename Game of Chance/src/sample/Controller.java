package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import static java.lang.Double.parseDouble;

public class Controller {


    public Label lblYourNumber;
    public Label lblCPUNumber;
    public Label lblYourWallet;
    public Label lblCPUWallet;
    public Label lblUserBet;
    public Label lblCPUbet;
    public Label lblMoneyPool;
    public Label lblLosses;
    public Label lblWins;
    public Label lblResult;
    public Label lblMoneyWon;
    public Label lblMoneyLost;

    public Button btnRoll;
    public Button btnBet;
    public Button btnHigher;
    public Button btnLower;

    public TextField txtAmount;

    public ListView <Log> listLogs;

    public ArrayList<Integer> cpuRolls = new ArrayList<>();
    public ArrayList<Integer> userRolls = new ArrayList<>();

    public Wallet user = new Wallet();
    public Wallet cpu = new Wallet();
    private int wins = 0;
    private int losses = 0;
    private double totalWon = 0;
    private double totalLost = 0;

    public void betMoney(ActionEvent event){

        double cpuBet = round((Math.random() * cpu.amount), 2);
        double userBet = round(parseDouble(txtAmount.getText()), 2);

        if (userBet < 0) {
            userBet = 0;
        }

        if (userBet > user.amount){
            userBet = user.amount;
        }

        user.loseMoney(userBet);
        cpu.loseMoney(cpuBet);

        txtAmount.clear();

        lblUserBet.setText("$" + Double.toString(userBet));
        lblCPUbet.setText("$" + Double.toString(cpuBet));
        updateLabels();

        lblMoneyPool.setText(Double.toString(round((cpuBet + userBet), 2)));
        this.lblResult.setText("");
        this.lblCPUNumber.setText("");
        btnBet.setDisable(true);
        btnRoll.setDisable(false);
    }

    public void rollDice(ActionEvent event){
        int cpuRoll = (int)(Math.random() * 6) + 1;
        this.cpuRolls.add(cpuRoll);

        int userRoll = (int)(Math.random() * 6) + 1;
        this.userRolls.add(userRoll);
        lblYourNumber.setText(Integer.toString(userRoll));

        btnHigher.setDisable(false);
        btnLower.setDisable(false);
        btnRoll.setDisable(true);
    }

    public void isHigher(ActionEvent event){
        int mostRecentCPURoll = this.cpuRolls.get(this.cpuRolls.size() - 1);
        int mostRecentUserRoll = this.userRolls.get(this.userRolls.size() - 1);

        lblCPUNumber.setText(Integer.toString(mostRecentCPURoll));
        btnHigher.setDisable(true);
        btnLower.setDisable(true);
        btnBet.setDisable(false);

        if (mostRecentCPURoll > mostRecentUserRoll){
            double moneyGained = parseDouble(lblMoneyPool.getText());
            user.gainMoney(moneyGained);
            Log win = new Log("Win", moneyGained);
            this.listLogs.getItems().add(win);
            this.wins++;
            this.lblResult.setText("You won!");
            this.totalWon += moneyGained;
        }

        if (mostRecentCPURoll <= mostRecentUserRoll){
            double moneyLost = parseDouble(lblMoneyPool.getText());
            cpu.gainMoney(moneyLost);
            Log loss = new Log("Loss", moneyLost);
            this.listLogs.getItems().add(loss);
            this.losses++;
            this.lblResult.setText("You lost :(");
            this.totalLost += moneyLost;
        }

        lblUserBet.setText("$0.00");
        lblCPUbet.setText("$0.00");
        updateLabels();
    }

    public void isLower(ActionEvent event){
        int mostRecentCPURoll = this.cpuRolls.get(this.cpuRolls.size() - 1);
        int mostRecentUserRoll = this.userRolls.get(this.userRolls.size() - 1);

        lblCPUNumber.setText(Integer.toString(mostRecentCPURoll));
        btnHigher.setDisable(true);
        btnLower.setDisable(true);
        btnBet.setDisable(false);

        if (mostRecentCPURoll < mostRecentUserRoll){
            double moneyGained = parseDouble(lblMoneyPool.getText());
            user.gainMoney(moneyGained);
            Log win = new Log("Win", moneyGained);
            this.listLogs.getItems().add(win);
            this.wins++;
            this.totalWon += moneyGained;
            this.lblResult.setText("You won!");
        }

        if (mostRecentCPURoll >= mostRecentUserRoll){
            double moneyLost = parseDouble(lblMoneyPool.getText());
            cpu.gainMoney(moneyLost);
            Log loss = new Log("Loss", moneyLost);
            this.listLogs.getItems().add(loss);
            this.losses++;
            this.totalLost += moneyLost;
            this.lblResult.setText("You lost :(");
        }

        lblUserBet.setText("$0.00");
        lblCPUbet.setText("$0.00");
        updateLabels();
    }


    public void updateLabels(){
        lblCPUWallet.setText(Double.toString(round((cpu.amount), 2)));
        lblYourWallet.setText(Double.toString(round((user.amount), 2)));
        lblMoneyPool.setText(Double.toString(round(0.00, 2)));
        lblWins.setText(Integer.toString(this.wins));
        lblLosses.setText(Integer.toString(this.losses));
        lblMoneyLost.setText("$" + Double.toString(round(this.totalLost, 2)));
        lblMoneyWon.setText("$" + Double.toString(round(this.totalWon, 2)));
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


}
