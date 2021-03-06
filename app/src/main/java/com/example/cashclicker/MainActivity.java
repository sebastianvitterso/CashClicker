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
    //Button resetButton;
    String cashOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button clickButton = (Button) findViewById(R.id.clickButton);
        autoClickButton = (Button) findViewById(R.id.autoClickButton);
        betterClickButton = (Button) findViewById(R.id.betterClickButton);
        textView2 = (TextView) findViewById(R.id.textView2);
        //resetButton = (Button) findViewById(R.id.resetButton);
        messageTextView = (TextView) findViewById(R.id.messageTextView);
        clickButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                controllerClick();
            }

        });
        /*resetButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                resetSaveFile();
            }

        });*/
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
        saveToFile();
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
        cashOutput = longToString(cashClicker.cash);
        textView2.setText(cashOutput);
        autoClickButton.setText("Levle selvtrykking\n" + longToString(cashClicker.pricePerAutoClicker));
        betterClickButton.setText("Kjøp bedre trykk\n" + longToString(cashClicker.priceForBetterClicks));
    }

    public String longToString(long longNumber){
        String afterComma;
        String returnString;
        if(longNumber<1000){returnString = longNumber + " kr";
        }
        else if(longNumber<1000000) {
            afterComma = String.valueOf(longNumber).substring(String.valueOf(longNumber).length()-3);
            returnString = longNumber/1000 + "," + afterComma + " k";
        }
        else if(longNumber<1000000000) {
            afterComma = String.valueOf(longNumber).substring(String.valueOf(longNumber).length()-6, String.valueOf(longNumber).length()-3);
            returnString = longNumber/1000000 + "," + afterComma + " mil";
        }
        else if(cashClicker.cash<1000000000000L) {
            afterComma = String.valueOf(longNumber).substring(String.valueOf(longNumber).length()-9, String.valueOf(longNumber).length()-6);
            returnString = longNumber/1000000000 + "," + afterComma + " mrd";
        }
        else if(cashClicker.cash<1000000000000000L) {
            afterComma = String.valueOf(longNumber).substring(String.valueOf(longNumber).length()-12,String.valueOf(longNumber).length()-9);
            returnString = longNumber/1000000000000L + "," + afterComma + " bil";
        }
        else if(cashClicker.cash<1000000000000000000L) {
            afterComma = String.valueOf(longNumber).substring(String.valueOf(longNumber).length()-15,String.valueOf(longNumber).length()-12);
            returnString = longNumber/1000000000000000L + "," + afterComma + " brd";
        }
        else {
            afterComma = String.valueOf(longNumber).substring(String.valueOf(longNumber).length()-18,String.valueOf(longNumber).length()-15);
            returnString = "Nå nærmer vi oss overflow.\n" + longNumber/1000000000000000000L + "," + afterComma + " tri";
        }


        return returnString;
    }




    public static final String textFileLocation = "savedData.txt";

    public void saveToFile(){
        String saveString = "";
        saveString += cashClicker.cash + "\n";
        saveString += cashClicker.cashPerSecond + "\n";
        saveString += cashClicker.cashPerClick + "\n";
        saveString += cashClicker.clicks + "\n";
        saveString += cashClicker.pricePerAutoClicker + "\n";
        saveString += cashClicker.priceForBetterClicks + "";

        try{
            FileOutputStream fos = openFileOutput(textFileLocation, Context.MODE_PRIVATE);
            fos.write(saveString.getBytes());
            fos.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void resetSaveFile(){
        String saveString = "";
        saveString += 0 + "\n";
        saveString += 0 + "\n";
        saveString += 1 + "\n";
        saveString += 0 + "\n";
        saveString += 5 + "\n";
        saveString += 3;

        try{
            FileOutputStream fos = openFileOutput(textFileLocation, Context.MODE_PRIVATE);
            fos.write(saveString.getBytes());
            fos.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        loadFromFile();
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
