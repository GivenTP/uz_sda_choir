package com.gtptechnologies.uzsdachoir;

import java.util.ArrayList;
import java.util.Locale;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerTitleStrip;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

public class Edit extends AppCompatActivity implements Communicator {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    //SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    HymnPageAdapter1 hymnPageAdapter;
    ActionBar actionBar;
    CharSequence[] hymnWords;
    String[] hymnnames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager1);

        hymnPageAdapter = new HymnPageAdapter1(getSupportFragmentManager(), getApplicationContext());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager2);
        mViewPager.setAdapter(hymnPageAdapter);

        Resources resources = getResources();
        hymnWords = resources.getTextArray(R.array.hymnWords);
        hymnnames = resources.getStringArray(R.array.hymns);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int x =0; x<hymnnames.length;x++){
                    if (position == x) {
                        actionBar.setSubtitle(hymnnames[x]);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            actionBar.setSubtitle(bundle.getString("hymnName"));

            for(int y=0; y<hymnnames.length;y++){
                if (actionBar.getSubtitle().toString().equalsIgnoreCase(hymnnames[y])){
                    mViewPager.setCurrentItem(y);
                }
            }

        }
        mViewPager.beginFakeDrag();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu4, menu);

        return true;
    }

    @Override
    public void setValues(View view, String hymn1key) {
        final EditText textView = (EditText) findViewById(R.id.edittext);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setTextIsSelectable(true);
        textView.setText(Html.fromHtml(hymn1key));
        textView.isInEditMode();
        textView.setActivated(true);
    }

    Spanned gettext;
    public void setmyValues(Spanned text){
        gettext = text;
    }

    public Spanned getmyValues(){
        return gettext;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
            return true;
        }

        if(id == R.id.save){
            ActionBar actionBar = getSupportActionBar();
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
            SharedPreferences.Editor editor = prefs.edit();
            JSONArray a = new JSONArray();
            for(int i=0; i< hymnnames.length; i++){
                if(actionBar.getSubtitle().equals(hymnnames[i]))
                {
                    urlss[i]= getmyValues();
                }
                    a.put(urlss[i]);
            }
            if(!(hymnnames.length==0)){
                editor.putString("key", a.toString());
            }else {
                editor.putString("key", null);
            }
            editor.apply();

            for(int x = 0; x <hymnnames.length; x++){
                if(actionBar.getSubtitle().equals(hymnnames[x])){
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("hymnName", hymnnames[x]);
                    startActivity(intent);
                }
            }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStop() {
        super.onStop();
        this.finish();
    }



}
