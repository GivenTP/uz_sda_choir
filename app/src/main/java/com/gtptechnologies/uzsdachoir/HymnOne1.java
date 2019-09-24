package com.gtptechnologies.uzsdachoir;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;

/**
 * Created by Given Pfunguro on 15/9/2017.
 */
public class HymnOne1 extends Fragment {
    public static final String HymnOnekey = "HymnOnekey";
    private final Context context;

    public HymnOne1(){
        this.context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit, container, false);

        Bundle bundle = getArguments();

        if(bundle != null){
            String hymn1key = bundle.getString(HymnOnekey);

            setValues(view, hymn1key);
        }
        return view;


    }

    public void setValues(final View view, final String hymn1key) {
        final EditText textView = (EditText) view.findViewById(R.id.edittext);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setTextIsSelectable(true);
        textView.setText(Html.fromHtml(hymn1key));
        textView.isInEditMode();
        textView.setActivated(true);
        Edit edit = (Edit)getActivity();
        edit.setmyValues(textView.getText());
        textView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(textView, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        textView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                    sendMessage();
                handled = true;
                return handled;
            }
        });

    }

    public void sendMessage(){

        EditText textView = (EditText) getView().findViewById(R.id.edittext);

        android.app.ActionBar actionBar = getActivity().getActionBar();
        Resources resources = context.getResources();
        final String[] hymnnames = resources.getStringArray(R.array.hymns);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();

        for(int i=0; i< hymnnames.length; i++){
            if (actionBar.getSubtitle().toString().equalsIgnoreCase(hymnnames[i]))
                a.put(textView.getText().toString());
        }
        editor.commit();
    }

}
