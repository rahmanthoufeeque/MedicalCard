package com.example.thowfeeqrahman.medicalcard;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by asus on 4/6/2018.
 */

public class custom_health_status extends BaseAdapter {
    String[]did,dname,gender;
    private Context context;
    public custom_health_status(Context ApplicaContext,String[]did1,String [] dname1,String[] gender1)
    {
        this.context=ApplicaContext;
        this.dname=dname1;
        this.gender=gender1;

        this.did=did1;

    }
    @Override
    public int getCount() {
        return did.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView = inflator.inflate(R.layout.custom_hstatus, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView3);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView4);


        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);




        tv1.setText(dname[i]);
        tv2.setText(gender[i]);


        return  gridView;
    }
}
