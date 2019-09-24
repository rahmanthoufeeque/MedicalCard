package com.example.thowfeeqrahman.medicalcard;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class Addprescription extends AppCompatActivity implements View.OnClickListener {
Button bt;
    EditText e1,e2,e3,e4;
    String namespace = "http://tempuri.org/";
    String method = "prescription";
    String soapaction = "http://tempuri.org/prescription";
    String url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addprescription);
        bt=(Button)findViewById(R.id.button10);
        e1=(EditText)findViewById(R.id.editText33);
        e2=(EditText)findViewById(R.id.editText18);
        e3=(EditText)findViewById(R.id.editText15);
        e4=(EditText)findViewById(R.id.editText33);
        bt.setOnClickListener(this);
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip = sh.getString("ip", "");
        url = "http://" + ip + "//Medical%20card/WebService.asmx";

    }

    @Override
    public void onClick(View view) {
        if(view==bt)
        {int flg=0;
            if (e1.getText().length() == 0) {
                flg++;
                e1.setError("");
            }
            if (e2.getText().length() == 0) {
                flg++;
                e2.setError("");
            }
            if (e3.getText().length() == 0) {
                flg++;
                e3.setError("");
            }
            if (e4.getText().length() == 0) {
                flg++;
                e4.setError("");
            }
            if(flg==0)
            {
            try {

                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


                // String m = ed2.getText().toString();//
                String uid = sh.getString("uid", "");
                String type = sh.getString("type", "");


                SoapObject soap = new SoapObject(namespace, method);
                soap.addProperty("user_id", uid);
                soap.addProperty("medicine_name", e1.getText().toString());
                soap.addProperty("dosage",e2.getText().toString());
                soap.addProperty("description",e3.getText().toString());
                soap.addProperty("other_precriptions",e4.getText().toString());

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

            catch(Exception ex) {
                Toast.makeText(getApplicationContext(), "error" + ex.getMessage(), Toast.LENGTH_SHORT).show();
            }  }
        }
    }
}
