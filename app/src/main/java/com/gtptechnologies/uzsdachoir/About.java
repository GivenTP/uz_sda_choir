package com.gtptechnologies.uzsdachoir;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class About extends AppCompatActivity{

    TextView textView;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

     textView = (TextView) findViewById(R.id.about1);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    public void setIntent(String s){
        Intent myIntent = new Intent(Intent.ACTION_SEND);
        myIntent.setType("text/plain");
        myIntent.putExtra(Intent.EXTRA_TEXT,s);
        startActivity(Intent.createChooser(myIntent,"Share using"));
    }

    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        if(id==android.R.id.home){
            finish();
        }
        return true;
    }

}
