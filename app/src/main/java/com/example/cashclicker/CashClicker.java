package com.example.cashclicker;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
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
    String filename = "savedData.txt";

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



// TODO: 19.04.2018 - Implement saving (text file?)
// https://stackoverflow.com/questions/33740686/android-call-function-on-app-close

    public void saveToFile() throws IOException {
        /*FileOutputStream outputStream = getApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE);
        outputStream.write(Integer.toString(cash).getBytes());
        outputStream.close();*/
        /*File file = new File(filename);
        file.createNewFile();
        */
        PrintWriter writer = new PrintWriter(filename, "UTF-8");
        writer.println(cash);
        writer.close();

    }

    public void loadFromFile() throws IOException {

        Scanner sc = new Scanner(filename);
        while (sc.hasNextLine()) {
            int i = sc.nextInt();
            cash = i;
            System.out.println(i);
        }
        sc.close();
    }







// TODO: 19.04.2018 - Balance pricing (exponential?)


}