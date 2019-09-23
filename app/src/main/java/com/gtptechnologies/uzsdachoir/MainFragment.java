package com.gtptechnologies.uzsdachoir;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Given Pfunguro on 16/10/2017.
 */
public class MainFragment extends Fragment {
    private int contentView;

    public MainFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void setContentView(int contentView) {
        this.contentView = contentView;
    }
}

