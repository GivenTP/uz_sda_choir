package com.gtptechnologies.uzsdachoir;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.widget.TextView;
import android.widget.Toast;


public class SettingsActivity extends PreferenceActivity {

    public SettingsActivity(){

    }
    //private CheckBoxPreference checkBoxPreference;
    //final CheckBoxPreference checkboxPref = (CheckBoxPreference) getPreferenceManager().findPreference("day_night_check");

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);

        initViews();
        //initListeners();

        //CheckBoxPreference notifyPref=(CheckBoxPreference)getPreferenceManager().findPreference("day_night_check");
        //notifyPref.setOnPreferenceChangeListener((Preference.OnPreferenceChangeListener) this);
        //if (notifyPref.isChecked()) {
        //    notifyPref.setTitle("Notifications Enabled");
        //}
        //else if (!notifyPref.isChecked()){
        //    notifyPref.setTitle("Notifications Disabled");
        //}

    }

    private void initViews() {
        final CheckBoxPreference checkboxPref = (CheckBoxPreference) getPreferenceManager().findPreference("day_night_check");

        //modeType = AppCompatDelegate.getDefaultNightMode();

        if(checkboxPref.isChecked()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        checkboxPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if(newValue instanceof Boolean){
                    Boolean boolVal = (Boolean)newValue;
                    if(!checkboxPref.isChecked()) {
//                        Toast.makeText(getApplicationContext(), "CB: " + "true",
//                                Toast.LENGTH_SHORT).show();
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        //onStop();
                        //Main2Activity main2Activity = new Main2Activity();
                        //main2Activity.refresh();
                        //Intent intent = new Intent(this,Main2Activity.class);
                    }
                    else{
//                        Toast.makeText(getApplicationContext(), "CB: " + "false",
//                                Toast.LENGTH_SHORT).show();
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    }
                }
                return true;
            }

        });

        /*final ListPreference listPreference = (ListPreference) getPreferenceManager().findPreference("example_list");

        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue){
                if(newValue instanceof Boolean){
                    Boolean boolVal = (Boolean)newValue;
                 if(listPreference.getEntry().equals("Small")){
                     TextView textView = (TextView) findViewById(R.id.hymn_one);
                     textView.setTextSize(10);
                 }
                 else if(listPreference.getEntry().equals("Normal")){
                     TextView textView = (TextView) findViewById(R.id.hymn_one);
                     textView.setTextSize(25);
                 }

                }
                return true;
            }
        });*/
    }

    private void initListeners() {

    }

    @Override
    public void onStop() {
        //Main2Activity.sampleActivity.finish();
        //((Activity) Class.forName(Main2Activity).newInstance()).finish();
        //Intent intent = new Intent(this,Main2Activity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        //intent.putExtra("flag", "modify");
        //startActivity(intent);

        //this.finish();
        super.onStop();
        //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_in);
    }

}
