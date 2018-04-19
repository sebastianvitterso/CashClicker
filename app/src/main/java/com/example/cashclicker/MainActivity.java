package com.example.cashclicker;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    CashClicker cashClicker = new CashClicker();
    TextView textView2;
    TextView messageTextView;
    Button autoClickButton;
    Button betterClickButton;
    Button saveButton;
    Button loadButton;
    String cashOutput;

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
                saveToFile();
            }
        });
        loadButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                loadFromFile();
                update();
            }
        });


        loadFromFile();
        update();

        new CountDownTimer(2000000000, 1000)
        {
            public void onTick(long millisUntilFinished)
            {
                cashClicker.autoClick();
                update();
            }

            public void onFinish(){/* kode som skal skje etter 46 dager ellerno*/}
        }.start();



    }

    @Override
    protected void onPause() {
        super.onPause();
        // Put save method here
    }



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






    public static final String textFileLocation = "savedData.txt";

    public void saveToFile(){
        String saveString = "";
        saveString += cashClicker.cash + "\n";
        saveString += cashClicker.cashPerSecond + "\n";
        saveString += cashClicker.cashPerClick + "\n";
        saveString += cashClicker.clicks + "\n";
        saveString += cashClicker.pricePerAutoClicker + "\n";
        saveString += cashClicker.priceForBetterClicks;

        try{
            FileOutputStream fos = openFileOutput(textFileLocation, Context.MODE_PRIVATE);
            fos.write(saveString.getBytes());
            fos.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(){
        try{
            FileInputStream fis = openFileInput(textFileLocation);

            BufferedReader reader = new BufferedReader(new InputStreamReader(new DataInputStream(fis)));

            cashClicker.cash = Integer.valueOf(reader.readLine());
            cashClicker.cashPerSecond = Integer.valueOf(reader.readLine());
            cashClicker.cashPerClick = Integer.valueOf(reader.readLine());
            cashClicker.clicks = Integer.valueOf(reader.readLine());
            cashClicker.pricePerAutoClicker = Integer.valueOf(reader.readLine());
            cashClicker.priceForBetterClicks = Integer.valueOf(reader.readLine());

            fis.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}
