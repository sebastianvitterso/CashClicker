package com.example.cashclicker;

public class CashClicker {

    long cash;
    long cashPerSecond;
    long cashPerClick;
    int clicks;
    long pricePerAutoClicker;
    long priceForBetterClicks;

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
                cashPerSecond = (long) Math.ceil(cashPerSecond * 1.15);
            }else{cashPerSecond = 1;}
            cash -= pricePerAutoClicker;
            pricePerAutoClicker = (long) Math.ceil(pricePerAutoClicker * 1.18);
        }
        else {
            System.out.println("You can't afford that!");
        }

    }

    void buyBetterClicks() {
        if(cash>=priceForBetterClicks) {
            cashPerClick = (long) Math.ceil(cashPerClick*1.15);
            cash -= priceForBetterClicks;
            priceForBetterClicks = (long) Math.ceil(priceForBetterClicks * 1.18);
        }
        else {
            System.out.println("You can't afford that!");
        }

    }
 


    
    
    long getCash() {
        return cash;
    }

    public String toString() {
        return("Cash: " + cash + "$. \nCash per second: " + cashPerSecond + ". \nCash per click: "
                + cashPerClick + ". \nClicks since start: " + clicks + ".");
    }


// DONE: 19.04.2018 - Implement saving (text file?)
// TODO: 20.04.2018 - Balance pricing better (exponential now works, but not perfect.)


}