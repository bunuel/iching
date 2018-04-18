package com.lenhunt.iching;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;



public class MainActivity extends AppCompatActivity {

    public TextView resultTextView;
    public ImageView coin1;
    public ImageView coin2;
    public ImageView coin3;

    public ImageView line1;
    public ImageView line2;
    public ImageView line3;
    public ImageView line4;
    public ImageView line5;
    public ImageView line6;

    //public String newline = System.lineSeparator();

    public Button throwCoinsButton;

    public boolean[] hexagramResultArray = new boolean[6];

    public String hexagramResultString = "";

    public int throwNumber = 0;

    public int thisThrowResult = 0;

    public int totalThrowResult = 0;

    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // globally
        // public TextView resultTextView = (TextView)findViewById(R.id.final_result);
        //public TextView resultTextView = (TextView)findViewById(R.id.final_result);
        //final TextView resultTextView = (TextView) findViewById(R.id.final_result);
        resultTextView = (TextView) findViewById(R.id.final_result);
        coin1 = findViewById(R.id.coin1);
        coin2 = findViewById(R.id.coin2);
        coin3 = findViewById(R.id.coin3);

        // reverse the line numbers here because hexagram is built from the bottom
        line1 = findViewById(R.id.line6_1);
        line2 = findViewById(R.id.line5_1);
        line3 = findViewById(R.id.line4_1);
        line4 = findViewById(R.id.line3_1);
        line5 = findViewById(R.id.line2_1);
        line6 = findViewById(R.id.line1_1);

        throwCoinsButton = findViewById(R.id.throw_coins);


    }

    public void startNew(View view) {
        line1.setVisibility(View.INVISIBLE);
        line2.setVisibility(View.INVISIBLE);
        line3.setVisibility(View.INVISIBLE);
        line4.setVisibility(View.INVISIBLE);
        line5.setVisibility(View.INVISIBLE);
        line6.setVisibility(View.INVISIBLE);

        resultTextView.setText(getResources().getString(R.string.welcome_text));
        totalThrowResult = 0;
        throwNumber = 0;

        throwCoinsButton.setVisibility(View.VISIBLE);

        coin1.setVisibility(View.VISIBLE);
        coin2.setVisibility(View.VISIBLE);
        coin3.setVisibility(View.VISIBLE);

        coin1.setImageDrawable(getResources().getDrawable(R.drawable.circle));
        coin2.setImageDrawable(getResources().getDrawable(R.drawable.circle));
        coin3.setImageDrawable(getResources().getDrawable(R.drawable.circle));

        hexagramResultString = "";

    }

    public void throwCoins(View view) {
        // generate 3 random binary numbers, heads or tails
        boolean[] coinResultArray = new boolean[3];

        for (int i = 0; i < coinResultArray.length; i++) {
            coinResultArray[i] = toBoolean(rand.nextInt(2));
            //resultTextView.append(Integer.toString(rand.nextInt(2)));
            //resultTextView.append(Boolean.toString(coinResultArray[i]));
            // change the 3 coin graphics to reflect the generated numbers
            getBackgroundColor(coinResultArray[i], i);


        }

        //resultTextView.append(Integer.toString(totalThrowResult));

        // increment throwNumber
        throwNumber++;

        // draw the line that reflects the numbers, based on the global throwNumber
        drawLine(throwNumber, thisThrowResult);

        thisThrowResult = 0;

        // if there have been six throws, show the corresponding text for the generated lines

        if (throwNumber == 6) {
            //setResultText(totalThrowResult);
            totalThrowResult = 0;
            throwNumber = 0;
            throwCoinsButton.setVisibility(View.INVISIBLE);
            coin1.setVisibility(View.INVISIBLE);
            coin2.setVisibility(View.INVISIBLE);
            coin3.setVisibility(View.INVISIBLE);
            giveFinalResult();
        }
    }

    private void drawLine(int throwNumber, int thisThrowResult) {
        /*
        3 heads = 6 = a broken line ( ) which changes to a firm line ( ).

        3 tails = 9 = a firm line ( ) which changes to a broken line ( ).

        2 heads and 1 tail = 7 a firm line ( ) that does not change.

        2 tails and 1 head = 8 a broken line ( ) that does not change.
        */
        // true for firm line, false for broken
        int line = 1;

        Drawable thisImage = getResources().getDrawable(R.drawable.blank);

        //resultTextView.append(Integer.toString(thisThrowResult));

        if (thisThrowResult == 9 || thisThrowResult == 8) {
            line = 0; // broken line
            thisImage = getResources().getDrawable(R.drawable.square);
            //resultTextView.append("broken line");
        }

        //hexagramResultArray[throwNumber - 1] = line;

        hexagramResultString += Integer.toString(line);

        //set the background color of the line based on throw #
        // TODO: put these all into an array and loop through them
        if (throwNumber == 1) {
            line1.setVisibility(View.VISIBLE);
            line1.setImageDrawable(thisImage);
            //line1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if (throwNumber == 2){
            line2.setVisibility(View.VISIBLE);
            line2.setImageDrawable(thisImage);
            //line2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if (throwNumber == 3){
            line3.setVisibility(View.VISIBLE);
            line3.setImageDrawable(thisImage);
            //line3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if (throwNumber == 4){
            line4.setVisibility(View.VISIBLE);
            line4.setImageDrawable(thisImage);
            //line4.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if (throwNumber == 5){
            line5.setVisibility(View.VISIBLE);
            line5.setImageDrawable(thisImage);
            //line5.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if (throwNumber == 6){
            line6.setVisibility(View.VISIBLE);
            line6.setImageDrawable(thisImage);
            //line6.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }

    }

    private boolean toBoolean(int i) {
        return (i == 1);
    }

    private void getBackgroundColor(boolean b, int i) {

        Drawable thisImage = getResources().getDrawable(R.drawable.circle);

        int background_color = getResources().getColor(R.color.colorPrimaryDark);
        int throwResult = 3;
        if (b) {
            background_color = getResources().getColor(R.color.colorPrimary);
            throwResult--;
            thisImage = getResources().getDrawable(R.drawable.circle_heads);
        }

        totalThrowResult += throwResult;

        thisThrowResult += throwResult;

        // TODO: loop through these
        if (i == 0) {
            //coin1.setBackgroundColor(background_color);
            coin1.setImageDrawable(thisImage);
        } else if (i == 1) {
            //coin2.setBackgroundColor(background_color);
            coin2.setImageDrawable(thisImage);
        } else if (i == 2) {
            //coin3.setBackgroundColor(background_color);
            coin3.setImageDrawable(thisImage);
        }

    }

    private void setResultText(int i) {
        resultTextView.setText(Integer.toString(totalThrowResult));
    }

    private void giveFinalResult() {
        String finalResultText = hexagramResultString;

        if (hexagramResultString.equals("111111")) {
            finalResultText = getResources().getString(R.string.hex_1);
        } else if (hexagramResultString.equals("000000")) {
            finalResultText = getResources().getString(R.string.hex_2);
        } else if (hexagramResultString.equals("100010")) {
            finalResultText = getResources().getString(R.string.hex_3);
        } else if (hexagramResultString.equals("010001")) {
            finalResultText = getResources().getString(R.string.hex_4);
        } else if (hexagramResultString.equals("111010")) {
            finalResultText = getResources().getString(R.string.hex_5);
        } else if (hexagramResultString.equals("010111")) {
            finalResultText = getResources().getString(R.string.hex_6);
        } else if (hexagramResultString.equals("010000")) {
            finalResultText = getResources().getString(R.string.hex_7);
        } else if (hexagramResultString.equals("000010")) {
            finalResultText = getResources().getString(R.string.hex_8);
        } else if (hexagramResultString.equals("111011")) {
            finalResultText = getResources().getString(R.string.hex_9);
        } else if (hexagramResultString.equals("110111")) {
            finalResultText = getResources().getString(R.string.hex_10);
        } else if (hexagramResultString.equals("111000")) {
            finalResultText = getResources().getString(R.string.hex_11);
        } else if (hexagramResultString.equals("000111")) {
            finalResultText = getResources().getString(R.string.hex_12);
        } else if (hexagramResultString.equals("101111")) {
            finalResultText = getResources().getString(R.string.hex_13);
        } else if (hexagramResultString.equals("111101")) {
            finalResultText = getResources().getString(R.string.hex_14);
        } else if (hexagramResultString.equals("001000")) {
            finalResultText = getResources().getString(R.string.hex_15);
        } else if (hexagramResultString.equals("000100")) {
            finalResultText = getResources().getString(R.string.hex_16);
        } else if (hexagramResultString.equals("100110")) {
            finalResultText = getResources().getString(R.string.hex_17);
        } else if (hexagramResultString.equals("011001")) {
            finalResultText = getResources().getString(R.string.hex_18);
        } else if (hexagramResultString.equals("110000")) {
            finalResultText = getResources().getString(R.string.hex_19);
        } else if (hexagramResultString.equals("000011")) {
            finalResultText = getResources().getString(R.string.hex_20);
        } else if (hexagramResultString.equals("100101")) {
            finalResultText = getResources().getString(R.string.hex_21);
        } else if (hexagramResultString.equals("101001")) {
            finalResultText = getResources().getString(R.string.hex_22);
        } else if (hexagramResultString.equals("000001")) {
            finalResultText = getResources().getString(R.string.hex_23);
        } else if (hexagramResultString.equals("100000")) {
            finalResultText = getResources().getString(R.string.hex_24);
        } else if (hexagramResultString.equals("100111")) {
            finalResultText = getResources().getString(R.string.hex_25);
        } else if (hexagramResultString.equals("111001")) {
            finalResultText = getResources().getString(R.string.hex_26);
        } else if (hexagramResultString.equals("100001")) {
            finalResultText = getResources().getString(R.string.hex_27);
        } else if (hexagramResultString.equals("011110")) {
            finalResultText = getResources().getString(R.string.hex_28);
        } else if (hexagramResultString.equals("010010")) {
            finalResultText = getResources().getString(R.string.hex_29);
        } else if (hexagramResultString.equals("101101")) {
            finalResultText = getResources().getString(R.string.hex_30);
        } else if (hexagramResultString.equals("001110")) {
            finalResultText = getResources().getString(R.string.hex_31);
        } else if (hexagramResultString.equals("011100")) {
            finalResultText = getResources().getString(R.string.hex_32);
        } else if (hexagramResultString.equals("001111")) {
            finalResultText = getResources().getString(R.string.hex_33);
        } else if (hexagramResultString.equals("111100")) {
            finalResultText = getResources().getString(R.string.hex_34);
        } else if (hexagramResultString.equals("000101")) {
            finalResultText = getResources().getString(R.string.hex_35);
        } else if (hexagramResultString.equals("101000")) {
            finalResultText = getResources().getString(R.string.hex_36);
        } else if (hexagramResultString.equals("101011")) {
            finalResultText = getResources().getString(R.string.hex_37);
        } else if (hexagramResultString.equals("110101")) {
            finalResultText = getResources().getString(R.string.hex_38);
        } else if (hexagramResultString.equals("001010")) {
            finalResultText = getResources().getString(R.string.hex_39);
        } else if (hexagramResultString.equals("010100")) {
            finalResultText = getResources().getString(R.string.hex_40);
        } else if (hexagramResultString.equals("110001")) {
            finalResultText = getResources().getString(R.string.hex_41);
        } else if (hexagramResultString.equals("100011")) {
            finalResultText = getResources().getString(R.string.hex_42);
        } else if (hexagramResultString.equals("111110")) {
            finalResultText = getResources().getString(R.string.hex_43);
        } else if (hexagramResultString.equals("011111")) {
            finalResultText = getResources().getString(R.string.hex_44);
        } else if (hexagramResultString.equals("000110")) {
            finalResultText = getResources().getString(R.string.hex_45);
        } else if (hexagramResultString.equals("011000")) {
            finalResultText = getResources().getString(R.string.hex_46);
        } else if (hexagramResultString.equals("010110")) {
            finalResultText = getResources().getString(R.string.hex_47);
        } else if (hexagramResultString.equals("011010")) {
            finalResultText = getResources().getString(R.string.hex_48);
        } else if (hexagramResultString.equals("101110")) {
            finalResultText = getResources().getString(R.string.hex_49);
        } else if (hexagramResultString.equals("011101")) {
            finalResultText = getResources().getString(R.string.hex_50);
        } else if (hexagramResultString.equals("100100")) {
            finalResultText = getResources().getString(R.string.hex_51);
        } else if (hexagramResultString.equals("001001")) {
            finalResultText = getResources().getString(R.string.hex_52);
        } else if (hexagramResultString.equals("001011")) {
            finalResultText = getResources().getString(R.string.hex_53);
        } else if (hexagramResultString.equals("110100")) {
            finalResultText = getResources().getString(R.string.hex_54);
        } else if (hexagramResultString.equals("101100")) {
            finalResultText = getResources().getString(R.string.hex_55);
        } else if (hexagramResultString.equals("001101")) {
            finalResultText = getResources().getString(R.string.hex_56);
        } else if (hexagramResultString.equals("011011")) {
            finalResultText = getResources().getString(R.string.hex_57);
        } else if (hexagramResultString.equals("110110")) {
            finalResultText = getResources().getString(R.string.hex_58);
        } else if (hexagramResultString.equals("010011")) {
            finalResultText = getResources().getString(R.string.hex_59);
        } else if (hexagramResultString.equals("110010")) {
            finalResultText = getResources().getString(R.string.hex_60);
        } else if (hexagramResultString.equals("110011")) {
            finalResultText = getResources().getString(R.string.hex_61);
        } else if (hexagramResultString.equals("001100")) {
            finalResultText = getResources().getString(R.string.hex_62);
        } else if (hexagramResultString.equals("101010")) {
            finalResultText = getResources().getString(R.string.hex_63);


        } else if (hexagramResultString.equals("010101")) {
            finalResultText = getResources().getString(R.string.hex_64);
        }

        resultTextView.setText(finalResultText);
    }
}
