package com.example.cashclicker;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {
    CashClicker cashClicker = new CashClicker();
    TextView textView2;
    Button autoClickButton;
    Button betterClickButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            final Button clickButton = (Button) findViewById(R.id.clickButton);
            autoClickButton = (Button) findViewById(R.id.autoClickButton);
            betterClickButton = (Button) findViewById(R.id.betterClickButton);
            textView2 = (TextView) findViewById(R.id.textView2);

            clickButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    controllerClick();
                }

            });
            //load();
            update();
            autoClickButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    getAutoClicks();
                }

            });
            betterClickButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    getBetterClicks();
                }

            });

            new CountDownTimer(2000000000, 1000)
            {
                public void onTick(long millisUntilFinished)
                {
                    cashClicker.autoClick();
                    update();
                }

                public void onFinish()
                {
                    // finish off when we're all dead !
                }
            }.start();
        }
        catch (Exception e){

        }

    }

    @Override
    protected void onPause() {
        try {
            super.onPause();
            save();
        }
        catch (Exception e){

        }

    }





    String cashOutput;

    void controllerClick() {
        cashClicker.click();
        update();
    }

    void getBetterClicks() {
        cashClicker.buyBetterClicks();
        update();
    }

    void getAutoClicks() {
        cashClicker.buyAutoClicker();
        update();
    }

    void update() {
        cashOutput = "Kr " + cashClicker.cash;
        textView2.setText(cashOutput);
        autoClickButton.setText("Levle selvtrykking\n" + cashClicker.pricePerAutoClicker + " kr");
        betterClickButton.setText("Kjøp bedre trykk\n" + cashClicker.priceForBetterClicks + " kr");
    }

    void save() throws IOException{
        cashClicker.saveToFile();
    }

    void load () throws IOException {
        cashClicker.loadFromFile();
        update();
    }






}
