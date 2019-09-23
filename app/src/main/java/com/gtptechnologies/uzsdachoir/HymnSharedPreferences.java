package com.gtptechnologies.uzsdachoir;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

public class HymnSharedPreferences {

    //CharSequence[] array;

    //Resources resources = context.getResources();
    //array = resources.getTextArray(R.array.hymnWords);

    public boolean saveArrayData(CharSequence[] array, String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName + "_size", array.length);
        for (int i = 0; i < array.length; i++) {
            editor.putString(arrayName + "_" + i, array[i].toString());
        }
        return editor.commit();

    }

    /*public CharSequence[] getArrayData(String arrayName, Context mContext){
        SharedPreferences prefs = mContext.getSharedPreferences("preferencename",0);
        //SharedPreferences.Editor editor = prefs.edit();
        int size = prefs.getInt(arrayName + "_", 0);
        for(int i=0;i<array.length; i++){
            editor.getString(arrayName + "_" + array[i].toString());
        }
        return editor.commit();
    }*/
}
