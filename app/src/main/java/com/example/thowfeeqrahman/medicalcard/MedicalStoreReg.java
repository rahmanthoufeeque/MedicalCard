package com.example.thowfeeqrahman.medicalcard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class
MedicalStoreReg extends AppCompatActivity implements View.OnClickListener {
    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7;
    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_store_reg);
        ed1=(EditText)findViewById(R.id.editText3);
        ed2=(EditText)findViewById(R.id.editText4);
        ed3=(EditText)findViewById(R.id.editText5);
        ed4=(EditText)findViewById(R.id.editText6);
        ed5=(EditText)findViewById(R.id.editText7);
        ed6=(EditText)findViewById(R.id.editText8);
        ed7=(EditText)findViewById(R.id.editText9);
        bt1= (Button)findViewById(R.id.button5);
        bt1.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        int flg=0;
        if(view==bt1) {
           if(ed1.getText().length()==0)
           {flg++;
               ed1.setError("");
           }
           if(ed2.getText().length()==0)
           {flg++;
               ed2.setError("");
           }
            if(ed3.getText().length()==0)
            {flg++;
                ed3.setError("");
            }
            if(ed4.getText().length()==0)
            {flg++;
                ed4.setError("");
            }
            if(ed5.getText().length()==0)
            {flg++;
                ed5.setError("");
            }
            if(ed6.getText().length()==0)
            {flg++;
                ed6.setError("");
            }
            if(ed7.getText().length()==0)
            {flg++;
                ed7.setError("");
            }
            if (flg==0)
            {
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor ed=sh.edit();
            ed.putString("m_name",ed1.getText().toString());
            ed.putString("pharmacist_id",ed2.getText().toString());
            ed.putString("place",ed5.getText().toString());
            ed.putString("state",ed3.getText().toString());
            ed.putString("pincode",ed4.getText().toString());
            ed.putString("liscence_no",ed6.getText().toString());
            ed.putString("phone_no",ed7.getText().toString());
            ed.commit();
            Intent i=new Intent(getApplicationContext(), MedicalStoreReg2.class);
            startActivity(i);
        }}



    }
}
