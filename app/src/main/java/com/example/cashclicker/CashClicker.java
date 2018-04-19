package com.example.cashclicker;

public class CashClicker {

    long cash;
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
            if(cashPerSecond > 0) {
                cashPerSecond = (int) Math.ceil(cashPerSecond * 1.05);
            }else{cashPerSecond = 1;}
            cash -= pricePerAutoClicker;
            pricePerAutoClicker = (int) Math.ceil(pricePerAutoClicker * 1.2);
        }
        else {
            System.out.println("You can't afford that!");
        }

    }

    void buyBetterClicks() {
        if(cash>=priceForBetterClicks) {
            cashPerClick = (int) Math.ceil(cashPerClick*1.05);
            cash -= priceForBetterClicks;
            priceForBetterClicks = (int) Math.ceil(priceForBetterClicks * 1.25);
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


// TODO: 19.04.2018 - Implement saving (text file?)



// TODO: 19.04.2018 - Balance pricing (exponential?)


}