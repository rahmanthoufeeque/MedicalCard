package com.example.thowfeeqrahman.medicalcard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class complaint extends AppCompatActivity implements View.OnClickListener {
EditText ed1;
    Button bt1;
    String namespace = "http://tempuri.org/";
    String method = "complaint";
    String soapaction = "http://tempuri.org/complaint";
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        ed1=(EditText)findViewById(R.id.editText40);
        bt1=(Button)findViewById(R.id.button21);
        bt1.setOnClickListener(this);
        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {

                StrictMode.ThreadPolicy th = new StrictMode.ThreadPolicy.Builder().permitAll().build();

                android.os.StrictMode.setThreadPolicy(th);

            }


        } catch (Exception ex) {

        }
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip = sh.getString("ip", "");
        url = "http://" + ip + "//Medical%20card/WebService.asmx";

    }

    @Override
    public void onClick(View view) {
        if (view == bt1) {
            try {

                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                String n = ed1.getText().toString();
                // String m = ed2.getText().toString();//
                String uid = sh.getString("uid", "");
                String type = sh.getString("type", "");


                SoapObject soap = new SoapObject(namespace, method);
                soap.addProperty("source_id", uid);
                soap.addProperty("complaint", ed1.getText().toString());
                soap.addProperty("type", type);

                SoapSerializationEnvelope snv = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                snv.dotNet = true;
                snv.setOutputSoapObject(soap);

                HttpTransportSE ht = new HttpTransportSE(url);
                ht.call(soapaction, snv);
                String result = snv.getResponse().toString();
                if (result.equalsIgnoreCase("no")) {
                    Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();

                    Intent p = new Intent(getApplicationContext(),viewcomplaint.class);
                    startActivity(p);

                }
            }
                     catch(Exception ex){
                    Toast.makeText(getApplicationContext(), "error" + ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
