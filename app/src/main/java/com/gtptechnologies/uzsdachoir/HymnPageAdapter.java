package com.gtptechnologies.uzsdachoir;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;
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
public class HymnPageAdapter extends FragmentPagerAdapter{

    String[] hymns;
    CharSequence[] hymnWords;
    private final Context ctx;
    //private Context context;
    //String choruss;

    public HymnPageAdapter(FragmentManager fm, Context context) {
        super(fm);

        this.ctx = context;
        Resources resources = context.getResources();
        /*SharedPreferences sp = getSharedPreferences("key",0);
        String tval = sp.getString("textvalue","");*/
        hymns = resources.getStringArray(R.array.hymns);
        hymnWords = resources.getTextArray(R.array.hymnWords);

        /*SharedPreferences prefs = context.getSharedPreferences("preferencename", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(hymnWords +"_size", hymnWords.length);
        for(int i=0;i<hymnWords.length;i++)
            editor.putString(hymnWords + "_" + i, hymnWords[i].toString());
            editor.commit();
*/
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();
        for(int i=0; i< hymnWords.length; i++){
            a.put(hymnWords[i]);
        }
        if(!(hymnWords.length==0)){
            editor.putString("key", a.toString());
        }else {
            editor.putString("key", null);
        }
        editor.apply();



        //SharedPreferences prefs = tgetClass().getSharedPreferences("key",0);
        //SharedPreferences.Editor editor = prefs.edit();

        //setStringArrayPref(context,"urls",hymnWords);
        //CharSequence hymn = Html.escapeHtml(hymnWords[1]);
        //choruss = resources.getStringArray(R.array.hymnWords).toString();


    }


    @Override
    public Fragment getItem(int position) {
        //Context context = this.context;
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

        /*SharedPreferences prefs = ctx.getSharedPreferences("preferencename", 0);
        int size = prefs.getInt(hymnWords + "_size", 0);
        String array[] = new String[size];
        for(int i=0;i<size;i++)
            array[i] = prefs.getString(hymnWords + "_" + i, null);*/
        //return array;

        Bundle bundle = new Bundle();
        bundle.putCharSequence(HymnOne.HymnOnekey,urlss[position]);//getStringArrayPref(context,"urls",position));

        HymnOne hymnOne = new HymnOne();
        hymnOne.setArguments(bundle);
        return hymnOne;
    }

    public CharSequence getPageTitle(int position){
        return hymns[position];
    }

    @Override
    public int getCount() {
        return hymns.length;
    }

    public static void setStringArrayPref(Context context, String key, CharSequence[] values){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();
        for(int i=0; i< values.length; i++){
            a.put(values[i]);
        }
        if(!(values.length==0)){
            editor.putString(key, a.toString());
        }else {
            editor.putString(key, null);
        }
        editor.commit();
    }

    public static CharSequence getStringArrayPref(Context context, String key, int position){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = prefs.getString(key, null);
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
        return urlss[position];
    }

    //public static CharSequence getStringArrayPrefi(Context context, String key){

    //}
}
