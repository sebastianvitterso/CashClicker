package com.example.cashclicker;

import android.content.Context;
import java.io.IOException;
import java.util.ArrayList;

public class CashClicker {

    int cash;
    int cashPerSecond;
    int cashPerClick;
    int clicks;
    int pricePerAutoClicker;
    int priceForBetterClicks;
    Context context;
    String filename;

    public CashClicker() {
        cash = 0;
        cashPerSecond = 0;
        cashPerClick = 1;
        clicks = 0;
        pricePerAutoClicker = 5;
        priceForBetterClicks = 3;
    }

    void click() {
        clicks ++;
        cash += cashPerClick;
        System.out.println("click!");
    }

    void autoClick() {
        cash += cashPerSecond;
    }

    void buyAutoClicker() {
        if(cash>=pricePerAutoClicker) {
            if(cashPerSecond > 0) {
                cashPerSecond = (int) Math.round(cashPerSecond * 1.5);
            }else{cashPerSecond = 1;}
            cash -= pricePerAutoClicker;
            pricePerAutoClicker = (int) Math.round(pricePerAutoClicker * 1.5);
        }
        else {
            System.out.println("You can't afford that!");
        }

    }

    void buyBetterClicks() {
        if(cash>=priceForBetterClicks) {
            cashPerClick = (int) Math.round(cashPerClick*1.5);
            cash -= priceForBetterClicks;
            priceForBetterClicks = (int) Math.round(priceForBetterClicks * 1.5);
        }
        else {
            System.out.println("You can't afford that!");
        }

    }
 


    
    
    int getCash() {
        return cash;
    }

    public String toString() {
        return("Cash: " + cash + "$. \nCash per second: " + cashPerSecond + ". \nCash per click: "
                + cashPerClick + ". \nClicks since start: " + clicks + ".");
    }


// TODO: 19.04.2018 - Implement saving (text file?)
// https://stackoverflow.com/questions/33740686/android-call-function-on-app-close





    /*
    DataOutputStream stream = new DataOutputStream(new FileOutputStream(file));
        stream.writeInt(cash);
        stream.writeInt(cashPerSecond);
        stream.writeInt(cashPerClick);
        stream.writeInt(clicks);
        stream.writeInt(pricePerAutoClicker);
        stream.writeInt(priceForBetterClicks);
        stream.close();
     */







// TODO: 19.04.2018 - Balance pricing (exponential?)


}