package com.gtptechnologies.uzsdachoir;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Given Pfunguro on 3/10/2017.
 */
public class CustomAdapter extends BaseAdapter{

    Context context;
    List<RowItem> rowItems;

    CustomAdapter(Context context, List<RowItem> rowItems){
        this.context = context;
        this.rowItems = rowItems;
    }

    @Override
    public int getCount() {return rowItems.size();}

    @Override
    public Object getItem(int position) {return rowItems.get(position);}

    @Override
    public long getItemId(int position) {return rowItems.indexOf(getItem(position));}

    public Context getContext() {
        return context;
    }

    /*private view holder class*/
    private class ViewHolder{
        ImageView pics;
        TextView hymn_name;
        TextView author;
    }

    ViewHolder holder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();

            holder.hymn_name = (TextView) convertView
                    .findViewById(R.id.hymn_name);
            holder.pics = (ImageView) convertView
                    .findViewById(R.id.pics);
            holder.author = (TextView) convertView
                    .findViewById(R.id.author);
            convertView.setTag(holder);

        }
        else{holder = (ViewHolder)convertView.getTag();
        }
            RowItem row_pos = rowItems.get(position);

            holder.pics.setImageResource(row_pos.getPics_id());
            //holder.pics.setImageDrawable(ContextCompat.getDrawable(getContext(), row_pos.getPics_id()));
            holder.hymn_name.setText(row_pos.getHymn_name());
            holder.author.setText(row_pos.getAuthor());
        return convertView;
    }
}
