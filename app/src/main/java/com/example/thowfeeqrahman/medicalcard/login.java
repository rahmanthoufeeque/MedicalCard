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
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class login extends AppCompatActivity implements View.OnClickListener {
    EditText ed1, ed2;
    Button bt1;
    TextView tv1;

    String namespace = "http://tempuri.org/";
    String method = "Login";
    String soapaction = "http://tempuri.org/Login";
    String  url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed1 = (EditText) findViewById(R.id.editText1);
        ed2 = (EditText) findViewById(R.id.editText2);
        bt1 = (Button) findViewById(R.id.button1);
        tv1 = (TextView) findViewById(R.id.textView1);
        bt1.setOnClickListener(this);
tv1.setOnClickListener(this);
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
            String n = ed1.getText().toString();
            String m = ed2.getText().toString();

              if(ed1.getText().length()==0)
            {
                ed1.setError("");
            }
            if(ed2.getText().length()==0)
            {
                ed2.setError("");
            }
                try {



                SoapObject soap = new SoapObject(namespace, method);
                soap.addProperty("username", n);
                soap.addProperty("password", m);

                SoapSerializationEnvelope snv = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                snv.dotNet = true;
                snv.setOutputSoapObject(soap);

                HttpTransportSE ht = new HttpTransportSE(url);
                ht.call(soapaction, snv);
                String result = snv.getResponse().toString();
                if (result.equalsIgnoreCase("no")) {
                    Toast.makeText(getApplicationContext(), "Invalid user", Toast.LENGTH_SHORT).show();

                } else {

                    String[] aa = result.split("\\#");

                   // Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

                    if (aa[2].equalsIgnoreCase("doctor")) {

                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor ed = sh.edit();
                        ed.putString("uid", aa[1]);

                        ed.putString("type", aa[2]);


                        ed.commit();
                        Intent p = new Intent(getApplicationContext(), Doctor.class);

                        startActivity(p);

                    } else if (aa[2].equalsIgnoreCase("medicalstore")) {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor ed = sh.edit();
                        ed.putString("uid", aa[1]);

                        ed.putString("type", aa[2]);

                        ed.commit();

                        Intent p = new Intent(getApplicationContext(),Medical_store.class);
                        startActivity(p);

                    } else if (aa[2].equalsIgnoreCase("user")) {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor ed = sh.edit();
                        ed.putString("uid", aa[1]);

                        ed.putString("type", aa[2]);

                        ed.commit();

                        Intent p = new Intent(getApplicationContext(), User.class);
                        startActivity(p);

                    }
                }

//
//
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), "error" + ex.getMessage(), Toast.LENGTH_SHORT).show();
            }


        }
        if(view == tv1)
        {
            Intent p = new Intent(getApplicationContext(), Choosereg.class);
            startActivity(p);

        }
    }
}
