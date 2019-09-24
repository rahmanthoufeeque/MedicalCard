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

public class MedicalStoreReg2 extends AppCompatActivity implements View.OnClickListener {
    EditText ed8, ed9, ed10, ed11;
    Button bt2;
    String namespace = "http://tempuri.org/";
    String method = "medicalstorereg";
    String soapaction = "http://tempuri.org/medicalstorereg";
  String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_store_reg2);
        ed8 = (EditText) findViewById(R.id.editText12);
        ed9 = (EditText) findViewById(R.id.editText16);
        ed10 = (EditText) findViewById(R.id.editText22);
        ed11 = (EditText) findViewById(R.id.editText23);
        bt2 = (Button) findViewById(R.id.button4);

        bt2.setOnClickListener(this);
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip = sh.getString("ip", "");
        url = "http://"+ip+"/Medical%20card/WebService.asmx";
    }

    @Override
    public void onClick(View view) {
        if (view == bt2) {
            int flg=0;
            if(ed8.getText().length()==0)
            {
               flg++;
                ed8.setError("");
            }
            if(ed9.getText().length()==0)
            {
                flg++;
                ed9.setError("");
            }
            if(ed10.getText().length()==0)
            {
                flg++;
                ed10.setError("");
            }
            if(flg==0)
            {
            try
        { SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

            SoapObject soap = new SoapObject(namespace, method);
            soap.addProperty("m_name",sh.getString("m_name","") );
            soap.addProperty("pharmacist_id",sh.getString("pharmacist_id","") );
            soap.addProperty("place", sh.getString("place",""));
            soap.addProperty("state", sh.getString("state",""));
            soap.addProperty("pincode", sh.getString("pincode",""));
            soap.addProperty("liscence_no", sh.getString("liscence_no",""));
            soap.addProperty("email_id", ed8.getText().toString());
            soap.addProperty("username", ed9.getText().toString());
            soap.addProperty("password", ed10.getText().toString());
soap.addProperty("phone_no",sh.getString("phone_no",""));

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

                Intent p = new Intent(getApplicationContext(), login.class);
                startActivity(p);
            }
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "error" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }}

        }}


