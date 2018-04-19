package com.example.cashclicker;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    CashClicker cashClicker = new CashClicker();
    TextView textView2;
    TextView messageTextView;
    Button autoClickButton;
    Button betterClickButton;
    Button saveButton;
    Button loadButton;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button clickButton = (Button) findViewById(R.id.clickButton);
        autoClickButton = (Button) findViewById(R.id.autoClickButton);
        betterClickButton = (Button) findViewById(R.id.betterClickButton);
        saveButton = (Button) findViewById(R.id.saveButton);
        loadButton = (Button) findViewById(R.id.loadButton);
        textView2 = (TextView) findViewById(R.id.textView2);
        messageTextView = (TextView) findViewById(R.id.messageTextView);
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
        saveButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try{
                    cashClicker.saveToFile();
                }catch(IOException e){
                    messageTextView.setText("Save failed on saveButton \n " + e);
                    System.out.println(e);
                }
            }

        });
        loadButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try{
                    cashClicker.loadFromFile();
                }catch(IOException e){
                    messageTextView.setText("Load failed on saveButton \n " + e);
                    System.out.println(e);
                }
            }

        });

        try {
            String filename = context.getFilesDir().getPath().toString() + "/savedData.txt";
            cashClicker.setFilename(filename);
            load();}
        catch (Exception e){
            messageTextView.setText("Load failed in onCreate \n " + e);
            System.out.println(e);
        }
            update();

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

    @Override
    protected void onPause() {
        try {
            super.onPause();
            save();
        }
        catch (Exception e){
            messageTextView.setText("Save failed in onCreate \n " + e);
            System.out.println(e);
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
        if(cashClicker.cash<1000){cashOutput = cashClicker.cash + "Kr ";}
        else if(cashClicker.cash<1000000) {cashOutput = cashClicker.getCash()/1000 + "," + cashClicker.getCash()%1000 + "k";}
        else if(cashClicker.cash<1000000000) {cashOutput = cashClicker.getCash()/1000000 + "," + cashClicker.getCash()%1000000 + "mil";}
        else {cashOutput = "På tide å fikse dette ja.\n" + cashClicker.getCash()/1000000 + "," + cashClicker.getCash()%1000000 + "mil";}
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
