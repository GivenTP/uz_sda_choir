package com.gtptechnologies.uzsdachoir;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by GivenPfunguro on 15/9/2017.
 */
public class HymnPageAdapter1 extends FragmentPagerAdapter{

    String[] hymns;
    CharSequence[] hymnWords;
    private final Context ctx;
    //String choruss;

    public HymnPageAdapter1(FragmentManager fm, Context context) {
        super(fm);

        this.ctx = context;

        Resources resources = context.getResources();

        hymns = resources.getStringArray(R.array.hymns);
        hymnWords = resources.getTextArray(R.array.hymnWords);
        //CharSequence hymn = Html.escapeHtml(hymnWords[1]);
        //choruss = resources.getStringArray(R.array.hymnWords).toString();

    }

    @Override
    public Fragment getItem(int position) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
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

        Bundle bundle = new Bundle();
        bundle.putCharSequence(HymnOne1.HymnOnekey,urlss[position]);

        HymnOne1 hymnOne1 = new HymnOne1();
        hymnOne1.setArguments(bundle);
        return hymnOne1;
    }

    /*public CharSequence getPageTitle(int position){
        return hymns[position];
    }*/

    @Override
    public int getCount() {
        return hymns.length;
    }
}
