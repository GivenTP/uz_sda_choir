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

    PagerTitleStrip stripp;

    Toolbar mtoolbar;

    ActionBar actionBar;

    ImageButton FAB;

    FrameLayout frameLayout;

    EditText editText;

    CharSequence[] hymnWords;
    String[] hymnnames;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager1);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //add a fragment
        //HymnOne1  hymnOne1 = new HymnOne1();
        //fragmentTransaction.add(R.id.edit, hymnOne1);
        //fragmentTransaction.commit();
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        // mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        //PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);

        hymnPageAdapter = new HymnPageAdapter1(getSupportFragmentManager(), getApplicationContext());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager2);
        //mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setAdapter(hymnPageAdapter);

        //getMenuInflater().inflate(R.menu.main2, menu);

        Resources resources = getResources();
        hymnWords = resources.getTextArray(R.array.hymnWords);
        hymnnames = resources.getStringArray(R.array.hymns);

        /*WindowManager.LayoutParams params = getWindow().getAttributes();
        params.x = -50;
        params.height = 800;
        params.width = 800;
        params.y = -50;

        this.getWindow().setAttributes(params);
*/
//        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

//        final EditText textView = (EditText) findViewById(R.id.edittext);

        /*textView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "I am clicked", Toast.LENGTH_SHORT).show();
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        });*/
        /*textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(textView, InputMethodManager.SHOW_IMPLICIT);
                    }
                });
            }
        });*/


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

                /*if (position == 0) {
                    actionBar.setSubtitle("1. Gwayana");
                } else if (position == 1) {
                    actionBar.setSubtitle("2. Jesus Is The Bread");
                } else if (position == 2) {
                    actionBar.setSubtitle("3. Sanctuary");
                } else if (position == 3) {
                    actionBar.setSubtitle("4. Sweetest Song");
                } else if (position == 4) {
                    actionBar.setSubtitle("5. The Loud Cry");
                } else if (position == 5) {
                    actionBar.setSubtitle("6. Apo Tofamba");
                } else if (position == 6) {
                    actionBar.setSubtitle("7. Fairest");
                } else if (position == 7) {
                    actionBar.setSubtitle("8. Sunny Smile");
                } else if (position == 8) {
                    actionBar.setSubtitle("9. God is love");
                } else if (position == 9) {
                    actionBar.setSubtitle("10. God is not man");
                } else if (position == 10) {
                    actionBar.setSubtitle("11. Ngif’k ekhaya");
                } else if (position == 11) {
                    actionBar.setSubtitle("12. There is no sermon");
                }*/

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

  //      stripp = (PagerTitleStrip) findViewById(R.id.pager_title_strip);
  //      stripp.requestLayout();

        //mtoolbar = (Toolbar) findViewById(R.id.toolbar);

        actionBar = getSupportActionBar();
        //ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        //this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

//        int position = getIntent().getIntExtra("hymns", 4);
//        mViewPager.setCurrentItem(position);


        MainActivity mainActivity = new MainActivity();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            //mainActivity.actionBar.getSubtitle();
            actionBar.setSubtitle(bundle.getString("hymnName"));

            for(int y=0; y<hymnnames.length;y++){
                if (actionBar.getSubtitle().toString().equalsIgnoreCase(hymnnames[y])){
                    mViewPager.setCurrentItem(y);
                }
            }
            //mViewPager.setCurrentItem(4);
            /*if (actionBar.getSubtitle().toString().equalsIgnoreCase("1. Gwayana")){
                mViewPager.setCurrentItem(0);
            }
            else if (actionBar.getSubtitle().toString().equalsIgnoreCase("2. Jesus Is The Bread")){
                mViewPager.setCurrentItem(1);
            }
            else if (actionBar.getSubtitle().toString().equalsIgnoreCase("3. Sanctuary")){
                mViewPager.setCurrentItem(2);
            }
            else if (actionBar.getSubtitle().toString().equalsIgnoreCase("4. Sweetest Song")){
                mViewPager.setCurrentItem(3);
            }
            else if (actionBar.getSubtitle().toString().equalsIgnoreCase("5. The Loud Cry")){
                mViewPager.setCurrentItem(4);
            }
            else if (actionBar.getSubtitle().toString().equalsIgnoreCase("6. Apo Tofamba")){
                mViewPager.setCurrentItem(5);
            }
            else if (actionBar.getSubtitle().toString().equalsIgnoreCase("7. Fairest")){
                mViewPager.setCurrentItem(6);
            }
            else if (actionBar.getSubtitle().toString().equalsIgnoreCase("8. Sunny Smile")){
                mViewPager.setCurrentItem(7);
            }
            else if (actionBar.getSubtitle().toString().equalsIgnoreCase("9. God is love")){
                mViewPager.setCurrentItem(8);
            }
            else if (actionBar.getSubtitle().toString().equalsIgnoreCase("10. God is not man")){
                mViewPager.setCurrentItem(9);
            }
            else if (actionBar.getSubtitle().toString().equalsIgnoreCase("11. Ngif’k ekhaya")){
                mViewPager.setCurrentItem(10);
            }
            else if (actionBar.getSubtitle().toString().equalsIgnoreCase("12. There is no sermon")){
                mViewPager.setCurrentItem(11);
            }*/

//            mViewPager.setCurrentItem(int 2);

            //mViewPager.setScrollX(0);
            //mViewPager.setScaleY(0);

        }
        mViewPager.beginFakeDrag();

        //frameLayout = (FrameLayout) findViewById(R.id.frameLayout);

    }


/*    public boolean onCreateOptionMenu(Menu menu) {

            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main2, menu);
            //restoreActionBar();
            //return true;

        return super.onCreateOptionsMenu(menu);
    }*/


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
        //return textView.getText();
    }

    //Editable editable = new Ed

    //Editable gettext = new Editable();
    Spanned gettext; //= new String();
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

            //Intent intent = new Intent(this,Savebuttons.class);
            //startActivity(intent);


            HymnOne1 hymnOne1 = new HymnOne1();
            ActionBar actionBar = getSupportActionBar();
            Resources resources = getApplicationContext().getResources();


            //final String[] hymnnames = resources.getStringArray(R.array.hymns);
            //hymnOne1.getView().findViewById(R.id.edittext);
            //editText = (EditText) view.findViewById(R.id.edittext);
            //editText = (EditText) findViewById(R.id.edittext);
            //editText.setText("Heyyyyyy");

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
                if(actionBar.getSubtitle().equals(hymnnames[i]))
                {

                    urlss[i]= getmyValues();
                    //a.put(urlss[i]);
                    //break;
                }

                //else {
                    //a.remove(i);
                    a.put(urlss[i]);
                //}
                    //  else{
                    //a.put(urlss[i]);
                   //  }
            }
            if(!(hymnnames.length==0)){
                editor.putString("key", a.toString());
            }else {
                editor.putString("key", null);
            }
            editor.apply();


            /*SharedPreferences prefs = getApplicationContext().getSharedPreferences("preferencename", 0);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(hymnWords +"_size", hymnWords.length);
            for(int i=0;i<hymnWords.length;i++) {
                if (actionBar.getSubtitle().toString().equalsIgnoreCase(hymnnames[i]))
                {
                    editor.putString(hymnWords + "_" + i, editText.getText().toString());
                }
            }
            editor.commit();*/


        //startActivity(new Intent(getApplicationContext(),Edit.class));
            for(int x = 0; x <hymnnames.length; x++){
                if(actionBar.getSubtitle().equals(hymnnames[x])){
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("hymnName", hymnnames[x]);
                    startActivity(intent);
                }
            }





            /*//HymnOne1 hymnOne1 = new HymnOne1();
            final EditText textView = (EditText) findViewById(R.id.edittext);
            //String hymn1key = ;
            //textView.setText(Html.fromHtml(hymn1key));

            //textView.getText().clear();

            CharSequence[] hymnWords;
            String[] hymnnames;
            Resources resources = getResources();
            hymnWords = resources.getTextArray(R.array.hymnWords);
            hymnnames = resources.getStringArray(R.array.hymns);


            //for(int x = 0; x <hymnnames.length; x++){
                //if(actionBar.getSubtitle().equals(hymnnames[x])){
                    //hymnWords[x] = "Given";
                //SharedPreferences sp = getSharedPreferences("key",0);
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = prefs.edit();

            JSONArray a = new JSONArray();

            for(int i=0; i< hymnWords.length; i++){

               if (actionBar.getSubtitle().toString().equalsIgnoreCase(hymnnames[i])) {
                   a.put(textView.getText().toString());
                   editor.putString("key", textView.getText().toString());
               }
            }
            //prefs.getString("key",null);
            //if(!(hymnWords.length==0)){
                //editor.putString("key", a.toString());
           // }else {
                //editor.putString("key", null);
           // }
            editor.apply();
                            //textView.getText().toString();
               // }
            //}

            this.finish();

            *//*SharedPreferences sp = getSharedPreferences("key",0);
            String tval = sp.getString("textvalue","");*/
        }

        return super.onOptionsItemSelected(item);


    }

    @Override
    public void onStop() {
        super.onStop();
        this.finish();
    }



}
