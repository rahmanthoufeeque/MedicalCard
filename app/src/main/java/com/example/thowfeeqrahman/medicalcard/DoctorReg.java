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

public class DoctorReg extends AppCompatActivity implements View.OnClickListener {
    EditText ed1, ed2, ed3, ed4, ed5, ed6, ed7, ed8;
    Button bt1;
    RadioButton rd1, rd2;
    String gen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_reg);
        ed1 = (EditText) findViewById(R.id.editText13);
        ed2 = (EditText) findViewById(R.id.editText29);
        ed3 = (EditText) findViewById(R.id.editText14);
        ed4 = (EditText) findViewById(R.id.editText2);
        ed5 = (EditText) findViewById(R.id.editText17);
        ed6 = (EditText) findViewById(R.id.editText26);
        ed7 = (EditText) findViewById(R.id.editText27);
        ed8 = (EditText) findViewById(R.id.editText28);
        rd1 = (RadioButton) findViewById(R.id.radioButton3);
        rd2 = (RadioButton) findViewById(R.id.radioButton4);

        bt1 = (Button) findViewById(R.id.button3);
        bt1.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view == bt1) {
            if (rd1.isChecked()) {
                gen = "Male";
            } else if (rd2.isChecked()) {
                gen = "Female";

            }
            int flg = 0;
            if (view == bt1) {
                if (ed1.getText().length() == 0) {
                    flg++;
                    ed1.setError("");
                }
                if (ed2.getText().length() == 0) {
                    flg++;
                    ed2.setError("");
                }
                if (ed3.getText().length() == 0) {
                    flg++;
                    ed3.setError("");
                }
                if (ed4.getText().length() == 0) {
                    flg++;
                    ed4.setError("");
                }
                if (ed5.getText().length() == 0) {
                    flg++;
                    ed5.setError("");
                }
                if (ed6.getText().length() == 0) {
                    flg++;
                    ed6.setError("");
                }
                if (ed7.getText().length() == 0) {
                    flg++;
                    ed7.setError("");
                }
                if (ed8.getText().length() == 0) {
                    flg++;
                    ed8.setError("");
                }
                if (flg == 0) {

                    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor ed = sh.edit();
                    ed.putString("name", ed1.getText().toString());
                    ed.putString("dob", ed2.getText().toString());
                    ed.putString("qualification", ed3.getText().toString());
                    ed.putString("specialisation", ed4.getText().toString());
                    ed.putString("place", ed6.getText().toString());
                    ed.putString("state", ed7.getText().toString());
                    ed.putString("pincode", ed8.getText().toString());
                    ed.putString("gender", gen.toString());
ed.putString("drid",ed5.getText().toString());
ed.commit();
                    Intent i = new Intent(getApplicationContext(), DoctorReg2.class);
                    startActivity(i);
                }
            }
        }
    }
}