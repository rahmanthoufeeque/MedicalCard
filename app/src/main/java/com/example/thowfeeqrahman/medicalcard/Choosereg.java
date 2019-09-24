package com.example.thowfeeqrahman.medicalcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Choosereg extends AppCompatActivity implements View.OnClickListener {
ImageView im1,im2,im3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosereg);
        im1=(ImageView)findViewById(R.id.doc);
        im2=(ImageView)findViewById(R.id.med);
        im3=(ImageView)findViewById(R.id.user);
        im1.setOnClickListener(this);
        im2.setOnClickListener(this);
        im3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==im1)
        {
            Intent i=new Intent(getApplicationContext(),DoctorReg.class);
            startActivity(i);
        }
        if(view==im2)
        {
            Intent i=new Intent(getApplicationContext(),MedicalStoreReg.class);
            startActivity(i);
        }
        if(view==im3)
        {
            Intent i=new Intent(getApplicationContext(),UserReg.class);
            startActivity(i);
        }
    }
}
