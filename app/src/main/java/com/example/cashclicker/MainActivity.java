package com.example.cashclicker;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {
    CashClicker cashClicker = new CashClicker();
    TextView textView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button clickButton = (Button) findViewById(R.id.clickButton);
        final Button autoClickButton = (Button) findViewById(R.id.autoClickButton);
        final Button betterClickButton = (Button) findViewById(R.id.betterClickButton);
        textView2 = (TextView) findViewById(R.id.textView2);
        clickButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                controllerClick();
            }

        });
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
    }





}
