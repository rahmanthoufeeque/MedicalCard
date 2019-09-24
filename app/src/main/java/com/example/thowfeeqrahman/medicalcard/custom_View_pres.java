package com.example.thowfeeqrahman.medicalcard;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by asus on 4/6/2018.
 */

public class custom_View_pres extends BaseAdapter {
    String[]status,cname,reply;
    private Context context;

    public custom_View_pres(Context ApplicaContext,String [] cmp1,String[]reply1,String[] status1)
    {this.context=ApplicaContext;


        this.cname=cmp1;
        this.status=status1;
        this.reply=reply1;
    }
    @Override
    public int getCount() {
        return cname.length;
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
            gridView = inflator.inflate(R.layout.custom_view_pres, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView3);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView4);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView5);


        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);



        tv1.setText(cname[i]);
        tv2.setText(status[i]);
        tv3.setText(reply[i]);

        return gridView;
    }
}
