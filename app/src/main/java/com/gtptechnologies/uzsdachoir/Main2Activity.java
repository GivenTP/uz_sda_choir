package com.gtptechnologies.uzsdachoir;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.multidex.MultiDex;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class Main2Activity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, NavigationView.OnNavigationItemSelectedListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    String[] hymn_name;
    //ArrayList<String> hymn_name;
    TypedArray pics;
    String[] author;

    List<RowItem> rowItems;
    ListView mylistview;

    boolean  started;
    ActionBar actionBar;

    ImageButton FAB;

    static Main2Activity sampleActivity;

    //For Database items
    ArrayList<String> listItem;
    String[] myArray;
    ArrayAdapter adapter;
    ListView userList;


    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    /**public Button but1;

    public void init(){
        but1 = (Button)findViewById(R.id.button);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(Main2Activity.this,MainActivity.class);

                startActivity(toy);
            }
        });
    }**/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        /*if (InitApplication.getInstance().isNightModeEnabled()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }*/

        //MultiDex.install(this);
        setContentView(R.layout.activity_main2);

        //PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);


        sampleActivity = this;

        started = false;

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        /**init(); **/

        //ListView
        rowItems = new ArrayList<RowItem>();
        myArray = new String[0];

        //hymn_name = getResources().getStringArray(R.array.hymns);
        //hymn_name = new String[]{"Ndini", "Ndiwe", "Ndiye"};
        //hymn_name =  new String[0];
        hymn_name = getMyData(1).toArray(new String[listItem.size()]);

        pics = getResources().obtainTypedArray(R.array.pics);

        author = getMyData(3).toArray(new String[listItem.size()]);

        for (int i = 0; i < hymn_name.length; i++){

            RowItem item = new RowItem(hymn_name[i],
                    pics.getResourceId(i, -1), author[i]);
            rowItems.add(item);
        }

 //       toolbarr = (Toolbar) findViewById(R.id.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        mylistview = (ListView) findViewById(R.id.list);
        CustomAdapter adapter = new CustomAdapter(Main2Activity.this, rowItems);
        mylistview.setAdapter(adapter);

/**        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(Main2Activity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.hymns));
        mylistview.setAdapter(mAdapter);**/

        mylistview.setCacheColorHint(Color.TRANSPARENT);
        mylistview.setFastScrollEnabled(true);
        mylistview.setScrollingCacheEnabled(false);

        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mylistview = (ListView) findViewById(R.id.list);
                String hymn_name = rowItems.get(position).getHymn_name();
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                intent.putExtra("hymnName", hymn_name);

                startActivity(intent);


                /**Toast.makeText(getApplicationContext(), "" + hymn_name,
                 Toast.LENGTH_SHORT).show();**/
            }
        });

        FAB = (ImageButton) findViewById(R.id.imageButton);
        FAB.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, Dialpad.class);
                startActivity(intent);
                //Toast.makeText(Main2Activity.this,"I am clicked", Toast.LENGTH_SHORT).show();
            }
        });

        mylistview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {


            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                int lastItem = firstVisibleItem + visibleItemCount;
                if (lastItem == totalItemCount) {

                    FAB.setVisibility(View.INVISIBLE);
                }else {
                    FAB.setVisibility(View.VISIBLE);
                }
            }
        });

        NavigationView navy = (NavigationView) findViewById(R.id.navigation_drawer);
        navy.setNavigationItemSelectedListener(this);

 /*       NavigationDrawerFragment frag = new NavigationDrawerFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, frag, "hymnal");
        fragmentTransaction.commit();*/

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.closeDrawer(GravityCompat.START);
  /*      FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();*/
        //onNavigationItemSelected();
    }

 /*   public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = "UZ SDA Choir";
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }*/

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menu3, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            //return true;
        }

        else if(id==R.id.edit){
            TextView textView = (TextView) findViewById(R.id.hymn_one);
            textView.setFocusable(true);
            textView.setEnabled(true);
            textView.setClickable(true);
            textView.setFocusableInTouchMode(true);
        }

        if(id==R.id.share){
            PackageManager pm = getPackageManager();
            ApplicationInfo appInfo;
            try {
                appInfo = pm.getApplicationInfo("org.fdroid.fdroid",
                        PackageManager.GET_META_DATA);
                Intent sendBt = new Intent(Intent.ACTION_SEND);
                // NOT THIS! sendBt.setType("application/vnd.android.package-archive");
                sendBt.setType("application/zip");
                sendBt.putExtra(Intent.EXTRA_STREAM,
                        Uri.parse("file://" + appInfo.publicSourceDir));
                sendBt.setClassName("com.android.bluetooth",
                        "com.android.bluetooth.opp.BluetoothOppLauncherActivity");
                startActivity(sendBt);
            } catch (PackageManager.NameNotFoundException e1) {
                e1.printStackTrace();
            }


            /*CharSequence[] hymnWords;
            Resources resources = getResources();
            hymnWords = resources.getTextArray(R.array.hymnWords);


            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            //CharSequence sharebody = hymnWords[1];
            String sharesub = "Your subject here";
            //myIntent.putExtra("hymnName","1. Gwayana");
            myIntent.putExtra(Intent.EXTRA_HTML_TEXT,sharesub);
            startActivity(Intent.createChooser(myIntent,"Share using"));*/
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.nav_hymnal){
            setTitle("Settings");
            MainFragment frag = new MainFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container,frag,"hymnal");
            fragmentTransaction.commit();
        }

        else if (id == R.id.nav_settings){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        else if (id == R.id.nav_exit){
            finish();
        }

        else if(id==R.id.nav_about){
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
        }

        else if (id==R.id.nav_feedback){
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto: giventp1@gmail.com"));
            startActivity(Intent.createChooser(emailIntent, "Send feedback"));
        }

        else if (id==R.id.nav_donate){
            Intent intent = new Intent(this, Donate.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main2, container, false);
            return rootView;
        }

        public static Main2Activity getInstance(){
            return   sampleActivity;
        }
    //    @Override
  /*      public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((Main2Activity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }*/
    }

//    public void refresh(){
//        this.finish();
//        Intent intent = new Intent(this,Main2Activity.class);
//        startActivity(intent);
//    }

    @Override
        public void onResume() {
            super.onResume();

            if(started){
                this.recreate();
            }
            else {
                started = true;
                //finish();
            }
            //this.recreate();
        //finish();
        //Intent refresh = new Intent(this, Main2Activity.class);
        //startActivity(refresh);//Start the same Activity
        // mylistview.invalidate();
        //CustomAdapter adapter = new CustomAdapter(Main2Activity.this, rowItems);
        //mylistview.setAdapter(adapter);
        //mylistview.setAdapter();
        //mylistview.refreshDrawableState();

        //When BACK BUTTON is pressed, the activity on the stack is restarted
        //Do what you want on the refresh procedure here
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
    public void onBackPressed(){
        super.onBackPressed();
        if(started) {
            this.finish();
        }
        else
            this.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //replaces the default 'Back' button action
        if(mNavigationDrawerFragment.isDrawerOpen())
        {
            DrawerLayout mDrawerLayout;
            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            mDrawerLayout.closeDrawers();
        }else {
        if(keyCode== KeyEvent.KEYCODE_BACK)   {
            // something here
            finish();
        }}
        return true;
    }

}
