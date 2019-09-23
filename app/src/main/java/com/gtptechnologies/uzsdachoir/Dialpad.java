package com.gtptechnologies.uzsdachoir;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.inputmethodservice.Keyboard;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class Dialpad extends AppCompatActivity {

    List<RowItem> rowItems;
    String[] hymn_name;
    ViewPager mViewPager;

    Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonC, buttonEqual;
    EditText crunchifyEditText;

    float mValueOne, mValueTwo;

    boolean crunchifyAddition, mSubtract, crunchifyMultiplication, crunchifyDivision;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialpad);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonEqual = (Button) findViewById(R.id.buttoneql);
        crunchifyEditText = (EditText) findViewById(R.id.edt1);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            crunchifyEditText.setShowSoftInputOnFocus(false);
        }else {
            crunchifyEditText.setTextIsSelectable(true);
        }

        //crunchifyEditText.setText("0");
        //crunchifyEditText.append("");
        //crunchifyEditText.setSelection(crunchifyEditText.getText().length());
        //crunchifyEditText.setText('a');
        //crunchifyEditText.setPaintFlags(0);



        WindowManager.LayoutParams params = getWindow().getAttributes();
        //params.x = -50;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        //params.y = -50;

        this.getWindow().setAttributes(params);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.append("1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.append("2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.append("3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.append("4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.append("5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.append("6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.append("7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.append("8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.append("9");
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crunchifyEditText.append("0");
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(crunchifyEditText.getText().toString().length()!=0){
                    mValueTwo = Integer.parseInt(crunchifyEditText.getText() + "");
                }else {
                    crunchifyEditText.setText("0");
                    //crunchifyEditText.append(Integer.toString(hymnnames.length));
                }


                CharSequence[] hymnWords;
                String[] hymnnames;
                Resources resources = getResources();
                hymnWords = resources.getTextArray(R.array.hymnWords);
                hymnnames = resources.getStringArray(R.array.hymns);


                if(mValueTwo<=hymnnames.length&&mValueTwo>0){
                    for (int x = 0; x <= hymnnames.length; x++) {
                        if (mValueTwo == x) {
                            Intent intent = new Intent(Dialpad.this, MainActivity.class);
                            intent.putExtra("hymnName", hymnnames[x - 1]);
                            startActivity(intent);
                        }
                    }
                }
                else
                    {
                        //crunchifyEditText.setSelection(crunchifyEditText.getText().length());
                        crunchifyEditText.setText("");
                        crunchifyEditText.append(Integer.toString(hymnnames.length));
                        //crunchifyEditText.append("2");
                    }



                /*if(mValueTwo==1){
                    Intent intent = new Intent(Dialpad.this, MainActivity.class);
                    intent.putExtra("hymnName","1. Gwayana");
                    startActivity(intent);
                }

                else if(mValueTwo==2){
                    Intent intent = new Intent(Dialpad.this, MainActivity.class);
                    intent.putExtra("hymnName","2. Jesus Is The Bread");
                    startActivity(intent);
                }

                else if(mValueTwo==3){
                    Intent intent = new Intent(Dialpad.this, MainActivity.class);
                    intent.putExtra("hymnName","3. Sanctuary");
                    startActivity(intent);
                }

                else if(mValueTwo==4){
                    Intent intent = new Intent(Dialpad.this, MainActivity.class);
                    intent.putExtra("hymnName","4. Sweetest Song");
                    startActivity(intent);
                }

                else if(mValueTwo==5){
                    Intent intent = new Intent(Dialpad.this, MainActivity.class);
                    intent.putExtra("hymnName","5. The Loud Cry");
                    startActivity(intent);
                }

                else if(mValueTwo==6){
                    Intent intent = new Intent(Dialpad.this, MainActivity.class);
                    intent.putExtra("hymnName","6. Apo Tofamba");
                    startActivity(intent);
                }

                else if(mValueTwo==7){
                    Intent intent = new Intent(Dialpad.this, MainActivity.class);
                    intent.putExtra("hymnName","7. Fairest");
                    startActivity(intent);
                }

                else if(mValueTwo==8){
                    Intent intent = new Intent(Dialpad.this, MainActivity.class);
                    intent.putExtra("hymnName","8. Sunny Smile");
                    startActivity(intent);
                }

                else if(mValueTwo==9){
                    Intent intent = new Intent(Dialpad.this, MainActivity.class);
                    intent.putExtra("hymnName","9. God is love");
                    startActivity(intent);
                }

                else if(mValueTwo==10){
                    Intent intent = new Intent(Dialpad.this, MainActivity.class);
                    intent.putExtra("hymnName","10. God is not man");
                    startActivity(intent);
                }

                else if(mValueTwo==11){
                    Intent intent = new Intent(Dialpad.this, MainActivity.class);
                    intent.putExtra("hymnName","11. Ngif’k ekhaya");
                    startActivity(intent);
                }

                else if(mValueTwo==12){
                    Intent intent = new Intent(Dialpad.this, MainActivity.class);
                    intent.putExtra("hymnName","12. There is no sermon");
                    startActivity(intent);
                }

                else if(mValueTwo>12||mValueTwo<=0){
                    //crunchifyEditText.setSelection(crunchifyEditText.getText().length());
                    crunchifyEditText.setText("");
                    crunchifyEditText.append("1");
                    crunchifyEditText.append("2");
                }

                else if(crunchifyEditText.getText().equals(null)){
                    crunchifyEditText.setText("");
                }*/

                /*if (crunchifyAddition == true) {
                    crunchifyEditText.setText(mValueOne + mValueTwo + "");
                    crunchifyAddition = false;
                }

                if (mSubtract == true) {
                    crunchifyEditText.setText(mValueOne - mValueTwo + "");
                    mSubtract = false;
                }

                if (crunchifyMultiplication == true) {
                    crunchifyEditText.setText(mValueOne * mValueTwo + "");
                    crunchifyMultiplication = false;
                }

                if (crunchifyDivision == true) {
                    crunchifyEditText.setText(mValueOne / mValueTwo + "");
                    crunchifyDivision = false;
                }*/
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = crunchifyEditText.getText().length();
                if (length > 0) {
                    crunchifyEditText.getText().delete(length - 1, length);
                }
                //crunchifyEditText.setText("");
            }
        });


    }

    @Override
    public void onStop() {
        super.onStop();
        this.finish();
    }
}