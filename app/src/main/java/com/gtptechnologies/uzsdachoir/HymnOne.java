package com.gtptechnologies.uzsdachoir;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Given Pfunguro on 15/9/2017.
 */
public class HymnOne extends Fragment {
    public static final String HymnOnekey = "HymnOnekey";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hymn_one_layout, container, false);

        Bundle bundle = getArguments();
        if(bundle != null){

             String hymn1key = bundle.getString(HymnOnekey);

            setValues(view, hymn1key);
        }
        return view;

    }

    private void setValues(View view, String hymn1key) {
        TextView textView = (TextView) view.findViewById(R.id.hymn_one);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setTextIsSelectable(true);
        textView.setText(Html.fromHtml(hymn1key));
    }


}
