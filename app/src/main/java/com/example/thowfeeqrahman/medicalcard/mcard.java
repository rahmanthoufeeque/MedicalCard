package com.example.thowfeeqrahman.medicalcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class mcard extends AppCompatActivity implements View.OnClickListener {
    TextView tv1,tv2,tv3,tv4;
    Button bt1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcard);
        tv1=(TextView)findViewById(R.id.textView26);
        tv2=(TextView)findViewById(R.id.textView28);
        tv3=(TextView)findViewById(R.id.textView30);
        tv4=(TextView)findViewById(R.id.textView32);
        bt1=(Button)findViewById(R.id.button18);
        bt1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view==bt1){
//        {
//            Intent i=new Intent(getApplicationContext(),mprescription.class);
//            startActivity(i);
        }

    }
}
