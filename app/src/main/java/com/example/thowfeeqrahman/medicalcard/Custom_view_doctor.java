package com.example.thowfeeqrahman.medicalcard;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by asus on 4/5/2018.
 */

public class Custom_view_doctor extends BaseAdapter
{
    String[]did,dname,gender,qualification,image;
    private Context context;
    public Custom_view_doctor(Context ApplicaContext,String[]did1,String [] dname1,String[] gender1,String[]qualification1,String[] image1)
    {
        this.context=ApplicaContext;
        this.dname=dname1;
        this.gender=gender1;
        this.qualification=qualification1;
        this.did=did1;
        this.image=image1;
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
            gridView = inflator.inflate(R.layout.custom_view_doctor, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView3);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView4);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView5);
        ImageView im=(ImageView)gridView.findViewById(R.id.imageView3);

        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);



        tv1.setText(did[i]);
        tv2.setText(dname[i]);
        tv3.setText(qualification[i]);
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
        String ip = sh.getString("ip", "");
        String url = "http://" + ip + "//Medical%20card//"+gender[i];
        Picasso.with(context).load(url).into(im);
        return  gridView;
    }
}
