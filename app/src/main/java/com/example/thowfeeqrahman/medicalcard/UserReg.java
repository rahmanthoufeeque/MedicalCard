package com.example.thowfeeqrahman.medicalcard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class UserReg extends AppCompatActivity implements View.OnClickListener {
    EditText ed1,ed2,ed3,ed4,ed6;
    RadioButton rd1,rd2;
    Button bt1;
    String gen="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg);
        ed1=(EditText)findViewById(R.id.editText24);
        ed2=(EditText)findViewById(R.id.editText25);
        ed3=(EditText)findViewById(R.id.editText30);
        ed4=(EditText)findViewById(R.id.editText31);

        ed6=(EditText)findViewById(R.id.editText36);
        rd1=(RadioButton)findViewById(R.id.radioButton1);
        rd2=(RadioButton)findViewById(R.id.radioButton2);

        bt1= (Button)findViewById(R.id.button7);
        bt1.setOnClickListener(this);
        if(rd1.isChecked())
        {
            gen="Male";
        }
        else if(rd2.isChecked())
        {
            gen="Female";

        }

        int flg=0;
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

        if(ed6.getText().length()==0)
        {flg++;
            ed6.setError("");
        }

        if (flg==0) {

            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor ed = sh.edit();
            ed.putString("name1", ed4.getText().toString());
            ed.putString("dob1", ed6.getText().toString());
            ed.putString("place1", ed1.getText().toString());
            ed.putString("state1", ed2.getText().toString());
            ed.putString("pincode1", ed3.getText().toString());
            ed.putString("gender1", gen.toString());
            ed.commit();


        }



    }

    @Override
    public void onClick(View view) {
        if(view==bt1)
        {
            Intent i=new Intent(getApplicationContext(),UserReg2.class);
            startActivity(i);
        }

    }
}

