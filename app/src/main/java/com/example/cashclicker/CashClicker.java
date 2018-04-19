package com.example.cashclicker;

import android.content.Context;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
        *//*
        PrintWriter writer = new PrintWriter(filename, "UTF-8");
        writer.println(cash);
        writer.println(cashPerSecond);
        writer.println(cashPerClick);
        writer.println(clicks);
        writer.println(pricePerAutoClicker);
        writer.println(priceForBetterClicks);
        writer.close();*/
        File file = new File(filename);
        file.createNewFile();

        DataOutputStream stream = new DataOutputStream(new FileOutputStream(file));
        stream.writeInt(cash);
        stream.writeInt(cashPerSecond);
        stream.writeInt(cashPerClick);
        stream.writeInt(clicks);
        stream.writeInt(pricePerAutoClicker);
        stream.writeInt(priceForBetterClicks);
        stream.close();
    }

    public void loadFromFile() throws IOException {

        /*Scanner sc = new Scanner(filename);
        if (sc.hasNextLine()) {
            cash = Integer.valueOf((sc.nextLine()));
            cashPerSecond = Integer.valueOf(sc.nextLine());
            cashPerClick = Integer.valueOf(sc.nextLine());
            clicks = Integer.valueOf(sc.nextLine());
            pricePerAutoClicker = Integer.valueOf(sc.nextLine());
            priceForBetterClicks = Integer.valueOf(sc.nextLine());
        }
        sc.close();*/
        File file = new File(filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        while (reader.ready()) {
            cash = Integer.parseInt((reader.readLine()));
            cashPerSecond = Integer.parseInt(reader.readLine());
            cashPerClick = Integer.parseInt(reader.readLine());
            clicks = Integer.parseInt(reader.readLine());
            pricePerAutoClicker = Integer.parseInt(reader.readLine());
            priceForBetterClicks = Integer.parseInt(reader.readLine());
        }
        reader.close();
    }

    public void setFilename(String filename){
        this.filename = filename;
    }





// TODO: 19.04.2018 - Balance pricing (exponential?)


}