package com.example.cashclicker;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class CashClicker {

    int cash;
    int cashPerSecond;
    int cashPerClick;
    int clicks;
    int pricePerAutoClicker;
    int priceForBetterClicks;


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
            cashPerSecond += 2;
            cash -= pricePerAutoClicker;
            pricePerAutoClicker += 3;
        }
        else {
            System.out.println("You can't afford that!");
        }

    }

    void buyBetterClicks() {
        if(cash>=priceForBetterClicks) {
            cashPerClick += 1;
            cash -= priceForBetterClicks;
            priceForBetterClicks +=8;
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


    /*protected void initialize(){
        Timer timer = new Timer();
        timer.schedule(, 0, 1000);
        //update();
    }


    protected void initializeTimerTask(){
        TimerTask timerTask = new TimerTask(){
            public void run() { //TimerTask
                cashClicker.cash += cashClicker.cashPerSecond;
                update();
            }

        };
    }*/

}


// TODO: 19.04.2018 - Implement saving (text file?)
// https://stackoverflow.com/questions/33740686/android-call-function-on-app-close
// TODO: 19.04.2018 - Balance pricing (exponential?) 