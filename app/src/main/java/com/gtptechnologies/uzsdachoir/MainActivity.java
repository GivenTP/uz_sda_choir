package com.gtptechnologies.uzsdachoir;

import java.util.ArrayList;
import java.util.Locale;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerTitleStrip;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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

    HymnPageAdapter hymnPageAdapter;

    PagerTitleStrip stripp;

    Toolbar mtoolbar;

    ActionBar actionBar;

    ImageButton FAB;

    FrameLayout frameLayout;

    ArrayList<String> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
       // mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        //PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);

        hymnPageAdapter = new HymnPageAdapter(getSupportFragmentManager(), getApplicationContext());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        //mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setAdapter(hymnPageAdapter);

        //getMenuInflater().inflate(R.menu.main2, menu);
        //EditText textView = (EditText) findViewById(R.id.edittext);
        //textView.setCursorVisible(false);

        CharSequence[] hymnWords;
        final String[] hymnnames;
        Resources resources = getResources();
        hymnWords = resources.getTextArray(R.array.hymnWords);
        hymnnames = getMyData(1).toArray(new String[listItem.size()]);

        //mViewPager.animate();
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

        stripp = (PagerTitleStrip) findViewById(R.id.pager_title_strip);
        stripp.requestLayout();

        mtoolbar = (Toolbar) findViewById(R.id.toolbar);

        actionBar = getSupportActionBar();
        //ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

//        int position = getIntent().getIntExtra("hymns", 4);
//        mViewPager.setCurrentItem(position);



        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            actionBar.setSubtitle(bundle.getString("hymnName"));

            for(int y=0; y<hymnnames.length;y++){
                if (actionBar.getSubtitle().toString().equalsIgnoreCase(hymnnames[y])){
                    mViewPager.setCurrentItem(y);
                }
            }

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
            }
*/
//            mViewPager.setCurrentItem(int 2);
        }

        FAB = (ImageButton) findViewById(R.id.imageButton);
        FAB.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "I am clicked", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, Dialpad.class);
                startActivity(intent);
            }
        });

        /*Bundle bundle1 = getIntent().getExtras();
        bundle1.getString("given");
        String name = "Myname";*/

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

        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    //Database Access
    public ArrayList<String> getMyData(int x){

        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        listItem = new ArrayList<String>();
        String infor = "data";
        Cursor res = databaseAccess.getData(infor);
        if(res.moveToFirst()){
            do{
                listItem.add(res.getString(x)/*+" "+res.getString(1)*/);
            }while (res.moveToNext());
        }
        databaseAccess.close();
        return listItem;
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            //return true;
        }

        if(id==R.id.share){
            CharSequence[] hymnWords;
            String[] hymnnames;
            Resources resources = getResources();
            hymnWords = resources.getTextArray(R.array.hymnWords);
            hymnnames = resources.getStringArray(R.array.hymns);
            //About about = new About();
            //Share share = new Share();

            //about.setIntent("Given!");
            //Intent intent = new Intent(this, Share.class);
            //startActivity(intent);
            for(int z = 0; z<hymnnames.length;z++){
                if(actionBar.getSubtitle().equals(hymnnames[z])) {
                    Intent myIntent = new Intent();
                    myIntent.setAction(Intent.ACTION_SEND);
                    myIntent.setType("text/plain");
                    String s = (String) hymnWords[z];
                    String r =  Html.fromHtml(s).toString();
                    //String n = hymnnames[0];
                    String m = hymnnames[z];
                    //String n = (String) r;
                    //myIntent.setPackage("com.whatsapp");
                    String d = m +"\n"+ r + "\n" + "Send by the UZSDA Choir app." + "\n";
                    //myIntent.putExtra(Intent.EXTRA_TEXT, m);
                    myIntent.putExtra(Intent.EXTRA_TEXT, d);
                    //myIntent.putExtra("hymnName", "1. Gwayana");
                    startActivity(Intent.createChooser(myIntent, "Share using"));
                    //try{
                    //    startActivity(myIntent);
                    //}catch (android.content.ActivityNotFoundException ex){
                    //ToastHelper.makeShortText("Whatsapp not installed");
                    //}
                }
            }

            /*if(actionBar.getSubtitle().equals("1. Gwayana")) {
                Intent myIntent = new Intent();
                myIntent.setAction(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String s = (String) hymnWords[0];
                String r =  Html.fromHtml(s).toString();
                //String n = hymnnames[0];
                String m = hymnnames[0];
                //String n = (String) r;
                //myIntent.setPackage("com.whatsapp");
                String d = m +"\n"+ r + "\n";
                //myIntent.putExtra(Intent.EXTRA_TEXT, m);
                myIntent.putExtra(Intent.EXTRA_TEXT, d);
                //myIntent.putExtra("hymnName", "1. Gwayana");
                startActivity(Intent.createChooser(myIntent, "Share using"));
                //try{
                //    startActivity(myIntent);
                //}catch (android.content.ActivityNotFoundException ex){
                    //ToastHelper.makeShortText("Whatsapp not installed");
                //}
            }

            if(actionBar.getSubtitle().equals("2. Jesus Is The Bread")){
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                //myIntent.setPackage("com.whatsapp");
                String s = (String) hymnWords[1];
                String r = Html.fromHtml(s).toString();
                String m = hymnnames[1];;
                String d = m +"\n"+ r + "\n";
                myIntent.putExtra(Intent.EXTRA_TEXT, d);
                //myIntent.putExtra("hymnName", "1. Gwayana");
                startActivity(Intent.createChooser(myIntent, "Share using"));
            }

            if(actionBar.getSubtitle().equals("3. Sanctuary")){
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                //myIntent.setPackage("com.whatsapp");
                String s = (String) hymnWords[2];
                String r = Html.fromHtml(s).toString();
                String m = hymnnames[2];
                String d = m +"\n"+ r + "\n";
                myIntent.putExtra(Intent.EXTRA_TEXT, d);
                //myIntent.putExtra("hymnName", "1. Gwayana");
                startActivity(Intent.createChooser(myIntent, "Share using"));
            }

            if(actionBar.getSubtitle().equals("4. Sweetest Song")){
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                //myIntent.setPackage("com.whatsapp");
                String s = (String) hymnWords[3];
                String r = Html.fromHtml(s).toString();
                String m = hymnnames[3];
                String d = m +"\n"+ r + "\n";
                myIntent.putExtra(Intent.EXTRA_TEXT, d);
                //myIntent.putExtra("hymnName", "1. Gwayana");
                startActivity(Intent.createChooser(myIntent, "Share using"));
            }

            if(actionBar.getSubtitle().equals("5. The Loud Cry")){
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                //myIntent.setPackage("com.whatsapp");
                String s = (String) hymnWords[4];
                String r = Html.fromHtml(s).toString();
                String m = hymnnames[4];
                String d = m +"\n"+ r + "\n";
                myIntent.putExtra(Intent.EXTRA_TEXT, d);
                //myIntent.putExtra("hymnName", "1. Gwayana");
                startActivity(Intent.createChooser(myIntent, "Share using"));
            }

            if(actionBar.getSubtitle().equals("6. Apo Tofamba")){
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                //myIntent.setPackage("com.whatsapp");
                String s = (String) hymnWords[5];
                String r = Html.fromHtml(s).toString();
                String m = hymnnames[5];
                String d = m +"\n"+ r + "\n";
                myIntent.putExtra(Intent.EXTRA_TEXT, d);
                //myIntent.putExtra("hymnName", "1. Gwayana");
                startActivity(Intent.createChooser(myIntent, "Share using"));
            }

            if(actionBar.getSubtitle().equals("7. Fairest")){
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                //myIntent.setPackage("com.whatsapp");
                String s = (String) hymnWords[6];
                String r = Html.fromHtml(s).toString();
                String m = hymnnames[6];
                String d = m +"\n"+ r + "\n";
                myIntent.putExtra(Intent.EXTRA_TEXT, d);
                myIntent.putExtra("hymnName", "1. Gwayana");
                startActivity(Intent.createChooser(myIntent, "Share using"));
            }

            if(actionBar.getSubtitle().equals("8. Sunny Smile")){
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                //myIntent.setPackage("com.whatsapp");
                String s = (String) hymnWords[7];
                String r = Html.fromHtml(s).toString();
                String m = hymnnames[7];
                String d = m +"\n"+ r + "\n";
                myIntent.putExtra(Intent.EXTRA_TEXT, d);
                myIntent.putExtra("hymnName", "1. Gwayana");
                startActivity(Intent.createChooser(myIntent, "Share using"));
            }

            if(actionBar.getSubtitle().equals("9. God is love")){
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                //myIntent.setPackage("com.whatsapp");
                String s = (String) hymnWords[8];
                String r = Html.fromHtml(s).toString();
                String m = hymnnames[8];
                String d = m +"\n"+ r + "\n";
                myIntent.putExtra(Intent.EXTRA_TEXT, d);
                myIntent.putExtra("hymnName", "1. Gwayana");
                startActivity(Intent.createChooser(myIntent, "Share using"));
            }

            if(actionBar.getSubtitle().equals("10. God is not man")){
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                //myIntent.setPackage("com.whatsapp");
                String s = (String) hymnWords[9];
                String r = Html.fromHtml(s).toString();
                String m = hymnnames[9];
                String d = m +"\n"+ r + "\n";
                myIntent.putExtra(Intent.EXTRA_TEXT, d);
                myIntent.putExtra("hymnName", "1. Gwayana");
                startActivity(Intent.createChooser(myIntent, "Share using"));
            }

            if(actionBar.getSubtitle().equals("11. Ngif’k ekhaya")){
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                //myIntent.setPackage("com.whatsapp");
                String s = (String) hymnWords[10];
                String r = Html.fromHtml(s).toString();
                String m = hymnnames[10];
                String d = m +"\n"+ r + "\n";
                myIntent.putExtra(Intent.EXTRA_TEXT, d);
                myIntent.putExtra("hymnName", "1. Gwayana");
                startActivity(Intent.createChooser(myIntent, "Share using"));
            }

            if(actionBar.getSubtitle().equals("12. There is no sermon")){
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                //myIntent.setPackage("com.whatsapp");
                String s = (String) hymnWords[11];
                String r = Html.fromHtml(s).toString();
                String m = hymnnames[11];
                String d = m +"\n"+ r + "\n";
                myIntent.putExtra(Intent.EXTRA_TEXT, d);
                myIntent.putExtra("hymnName", "1. Gwayana");
                startActivity(Intent.createChooser(myIntent, "Share using"));
            }
*/

        }

        if (id == R.id.edit){

            CharSequence[] hymnWords;
            String[] hymnnames;
            Resources resources = getResources();
            hymnWords = resources.getTextArray(R.array.hymnWords);
            hymnnames = resources.getStringArray(R.array.hymns);

            for(int x = 0; x <hymnnames.length; x++){
                if(actionBar.getSubtitle().equals(hymnnames[x])){
                    Intent intent = new Intent(MainActivity.this, Edit.class);
                    intent.putExtra("hymnName", hymnnames[x]);
                    startActivity(intent);
                }
            }

            /*if(actionBar.getSubtitle().equals("1. Gwayana")) {
                Intent intent = new Intent(MainActivity.this, Edit.class);
                intent.putExtra("hymnName", "1. Gwayana");
                startActivity(intent);
            }
            if(actionBar.getSubtitle().equals("2. Jesus Is The Bread")) {
                Intent intent = new Intent(MainActivity.this, Edit.class);
                intent.putExtra("hymnName", "2. Jesus Is The Bread");
                startActivity(intent);
            }
            else if(actionBar.getSubtitle().equals("3. Sanctuary")){
                Intent intent = new Intent(MainActivity.this, Edit.class);
                intent.putExtra("hymnName","3. Sanctuary");
                startActivity(intent);
            }

            else if(actionBar.getSubtitle().equals("4. Sweetest Song")){
                Intent intent = new Intent(MainActivity.this, Edit.class);
                intent.putExtra("hymnName","4. Sweetest Song");
                startActivity(intent);
            }

            else if(actionBar.getSubtitle().equals("5. The Loud Cry")){
                Intent intent = new Intent(MainActivity.this, Edit.class);
                intent.putExtra("hymnName","5. The Loud Cry");
                startActivity(intent);
            }

            else if(actionBar.getSubtitle().equals("6. Apo Tofamba")){
                Intent intent = new Intent(MainActivity.this, Edit.class);
                intent.putExtra("hymnName","6. Apo Tofamba");
                startActivity(intent);
            }

            else if(actionBar.getSubtitle().equals("7. Fairest")){
                Intent intent = new Intent(MainActivity.this, Edit.class);
                intent.putExtra("hymnName","7. Fairest");
                startActivity(intent);
            }

            else if(actionBar.getSubtitle().equals("8. Sunny Smile")){
                Intent intent = new Intent(MainActivity.this, Edit.class);
                intent.putExtra("hymnName","8. Sunny Smile");
                startActivity(intent);
            }

            else if(actionBar.getSubtitle().equals("9. God is love")){
                Intent intent = new Intent(MainActivity.this, Edit.class);
                intent.putExtra("hymnName","9. God is love");
                startActivity(intent);
            }

            else if(actionBar.getSubtitle().equals("10. God is not man")){
                Intent intent = new Intent(MainActivity.this, Edit.class);
                intent.putExtra("hymnName","10. God is not man");
                startActivity(intent);
            }

            else if(actionBar.getSubtitle().equals("11. Ngif’k ekhaya")){
                Intent intent = new Intent(MainActivity.this, Edit.class);
                intent.putExtra("hymnName","11. Ngif’k ekhaya");
                startActivity(intent);
            }

            else if(actionBar.getSubtitle().equals("12. There is no sermon")){
                Intent intent = new Intent(MainActivity.this, Edit.class);
                intent.putExtra("hymnName","12. There is no sermon");
                startActivity(intent);
            }
*/
        }
        return super.onOptionsItemSelected(item);


    }

    /*public void onEdit(){
        ViewPager mViewPager1;
        HymnPageAdapter1 hymnPageAdapter1 = new HymnPageAdapter1(getSupportFragmentManager(), getApplicationContext());
        // Set up the ViewPager with the sections adapter.
        mViewPager1 = (ViewPager) findViewById(R.id.pager);
        //mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager1.setAdapter(hymnPageAdapter1);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            actionBar.setSubtitle(bundle.getString("hymnName"));
            if (actionBar.getSubtitle().toString().equalsIgnoreCase("1. Gwayana")) {
                mViewPager1.setCurrentItem(0);
            } else if (actionBar.getSubtitle().toString().equalsIgnoreCase("2. Jesus Is The Bread")) {
                mViewPager1.setCurrentItem(1);
            }}*/


        //mViewPager1.setCurrentItem();
        /*Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null) {
            actionBar.setSubtitle(bundle1.getString("hymnName"));
            if (actionBar.getSubtitle().toString().equalsIgnoreCase("1. Gwayana")){
                mViewPager1.setCurrentItem(0);
            }
            else if (actionBar.getSubtitle().toString().equalsIgnoreCase("2. Jesus Is The Bread")){
                mViewPager1.setCurrentItem(1);
            }
            else if (actionBar.getSubtitle().toString().equalsIgnoreCase("3. Sanctuary")){
                mViewPager1.setCurrentItem(2);
            }
            else if (actionBar.getSubtitle().toString().equalsIgnoreCase("4. Sweetest Song")){
                mViewPager1.setCurrentItem(3);
            }
            else if (actionBar.getSubtitle().toString().equalsIgnoreCase("5. The Loud Cry")){
                mViewPager1.setCurrentItem(4);
            }
            else if (actionBar.getSubtitle().toString().equalsIgnoreCase("6. Apo Tofamba")){
                mViewPager1.setCurrentItem(5);
            }
            else if (actionBar.getSubtitle().toString().equalsIgnoreCase("7. Fairest")){
                mViewPager1.setCurrentItem(6);
            }
            else if (actionBar.getSubtitle().toString().equalsIgnoreCase("8. Sunny Smile")){
                mViewPager1.setCurrentItem(7);
            }
            else if (actionBar.getSubtitle().toString().equalsIgnoreCase("9. God is love")){
                mViewPager.setCurrentItem(8);
            }
            else if (actionBar.getSubtitle().toString().equalsIgnoreCase("10. God is not man")){
                mViewPager1.setCurrentItem(9);
            }
            else if (actionBar.getSubtitle().toString().equalsIgnoreCase("11. Ngif’k ekhaya")){
                mViewPager1.setCurrentItem(10);
            }
            else if (actionBar.getSubtitle().toString().equalsIgnoreCase("12. There is no sermon")){
                mViewPager1.setCurrentItem(11);
            }
        }*/
    //}

    @Override
    public void onStop() {
        super.onStop();
        this.finish();
    }


}
