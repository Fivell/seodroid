package com.seodroid.seodroid.ui;

import com.seodroid.seodroid.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.InputStream;

import android.content.Context;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemArrayAdapter extends ArrayAdapter<ResultItem> {

    private Context context;

    private List<ResultItem> results = new ArrayList<ResultItem>();
    private int rowResourceId;
    private int maxResultLength = 16;
    private int minFontSize  =10;
    private int defaultFontSize = 12;

    public ItemArrayAdapter(Context context, int textViewResourceId,
                            List<ResultItem> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.results = objects;
        this.rowResourceId = textViewResourceId;
    }



    public int getCount() {
        return this.results.size();
    }

    public ResultItem getItem(int index) {
        return this.results.get(index);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(rowResourceId,parent,false);
        }

        ImageView iconView = (ImageView) rowView.findViewById(R.id.jobIcon);
        TextView  nameView  = (TextView) rowView.findViewById(R.id.jobName);
        TextView valueView =  (TextView) rowView.findViewById(R.id.jobValue);
        ResultItem item =  getItem(position);
        nameView.setText(item.name);
        valueView.setText(item.value);
        if(item.value.length() > maxResultLength){
            valueView.setTextSize(minFontSize);
        }else{
            valueView.setTextSize(defaultFontSize);
        }
        // get input stream
        InputStream ims = null;
        try {
            ims = context.getAssets().open(item.resourceFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // load image as Drawable
        Drawable d = Drawable.createFromStream(ims, null);
        // set image to ImageView
        iconView.setImageDrawable(d);

        return rowView;
    }


}