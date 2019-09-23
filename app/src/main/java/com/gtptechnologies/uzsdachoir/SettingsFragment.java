package com.gtptechnologies.uzsdachoir;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Given Pfunguro on 16/10/2017.
 */
public class SettingsFragment extends Fragment{

    public SettingsFragment(){
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.settingsfragment,container,false);
        return v;
    }

}
