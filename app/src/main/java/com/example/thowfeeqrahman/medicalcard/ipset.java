package com.example.thowfeeqrahman.medicalcard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ipset extends AppCompatActivity implements View.OnClickListener {
EditText ed1;
    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipset);
        ed1=(EditText)findViewById(R.id.editText37);
        bt1=(Button)findViewById(R.id.button8);
        bt1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {


        if(view==bt1)
        {
            String k=ed1.getText().toString();
            SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor ed=sh.edit();
            ed.putString("ip",k);
            ed.commit();

            Intent i=new Intent(getApplicationContext(),login.class);
            startActivity(i);
        }

    }
}
