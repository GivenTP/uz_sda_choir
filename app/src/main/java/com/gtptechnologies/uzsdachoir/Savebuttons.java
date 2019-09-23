package com.gtptechnologies.uzsdachoir;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Savebuttons extends AppCompatActivity{
    TextView textView;
    Button cancel, save;
    EditText editText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.savebuttons);

        textView = (TextView) findViewById(R.id.save);
        cancel = (Button) findViewById(R.id.cancelbutton);
        save = (Button) findViewById(R.id.savebutton);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.x = -50;
        params.height = 350;
        params.width = 800;
        params.y = -50;
        this.getWindow().setAttributes(params);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStop();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //HymnOne1 hymnOne1 = new HymnOne1();
                //hymnOne1.sendMessage();
                ActionBar actionBar = getSupportActionBar();
                Resources resources = getApplicationContext().getResources();
                final String[] hymnnames = resources.getStringArray(R.array.hymns);
                editText = (EditText) findViewById(R.id.edittext);

                //getstring
                //*************************************************
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String json = prefs.getString("key", null);
                ArrayList<String> urls = new ArrayList<String>();
                if(json != null){
                    try{
                        JSONArray a = new JSONArray(json);
                        for(int i=0; i< a.length(); i++){
                            String url = a.optString(i);
                            urls.add(url);
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                CharSequence[] urlss = new CharSequence[urls.size()];
                urlss = urls.toArray(urlss);
                //*************************************************************


                //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                //MainActivity mainActivity = new MainActivity();
                SharedPreferences.Editor editor = prefs.edit();
                JSONArray a = new JSONArray();
                for(int i=0; i< hymnnames.length; i++){
                    if (actionBar.getSubtitle().toString().equalsIgnoreCase(hymnnames[i])) {
                        // a.put(editText.getText());//}
                        //  else{
                        a.put(urlss[i]);
                        // }
                    }
                }
                if(!(hymnnames.length==0)){
                    editor.putString("key", a.toString());
                }else {
                    editor.putString("key", null);
                }
                editor.commit();
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        this.finish();
    }
}
