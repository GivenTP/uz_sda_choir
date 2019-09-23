package com.gtptechnologies.uzsdachoir;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.prefs.Preferences;

/**
 * Created by Given Pfunguro on 15/9/2017.
 */
public class HymnOne extends Fragment {
    public static final String HymnOnekey = "HymnOnekey";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hymn_one_layout, container, false);

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);

        Bundle bundle = getArguments();
        //SharedPreferences sp = getActivity().getSharedPreferences("key",0);
        //String hymn1key = sp.getString("textvalue","");
        if(bundle != null){

             String hymn1key = bundle.getString(HymnOnekey);

            setValues(view, hymn1key);
        }
        return view;

    }

    private void setValues(View view, String hymn1key) {
        TextView textView = (TextView) view.findViewById(R.id.hymn_one);
        //SharedPreferences sp = getString(textView,"");
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setTextIsSelectable(true);
        textView.setText(Html.fromHtml(hymn1key));
        //textView.setTextAppearance(android.R.style.TextAppearance_Large);
    }


}
