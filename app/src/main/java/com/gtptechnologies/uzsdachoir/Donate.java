package com.gtptechnologies.uzsdachoir;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

public class Donate extends AppCompatActivity {

    EditText editText;
    Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donate);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.x = -50;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.y = -50;
        this.getWindow().setAttributes(params);

        editText = (EditText) findViewById(R.id.donedit);
        button = (Button) findViewById(R.id.donbutton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editText.getText().toString().length()!=0) {
                    dailNumber(Integer.parseInt(editText.getText().toString()));
                }
                else {
                    editText.setText("");
                }
            }

        });
    }

    private void dailNumber(int amt) {
        String ussdCode = "*151*2*2*177589*" + amt + Uri.encode("#");
        startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + ussdCode)));
    }
}
