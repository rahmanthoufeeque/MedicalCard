package com.example.thowfeeqrahman.medicalcard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Userhealth extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[]cid,date,status;
ListView lv;
    String namespace = "http://tempuri.org/";
    String method = "viewhealthstatus";
    String soapaction = "http://tempuri.org/viewhealthstatus";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhealth);
        lv=(ListView)findViewById(R.id.lv1);
        lv.setOnItemClickListener(this);
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip=sh.getString("ip", "");
        String   url="http://"+ip+"/Medical%20card/WebService.asmx";


        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {

                StrictMode.ThreadPolicy th = new StrictMode.ThreadPolicy.Builder().permitAll().build();

                android.os.StrictMode.setThreadPolicy(th);


                SoapObject soap = new SoapObject(namespace, method);
soap.addProperty("uid",sh.getString("uid",""));
                SoapSerializationEnvelope snv = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                snv.dotNet = true;
                snv.setOutputSoapObject(soap);
                HttpTransportSE ht = new HttpTransportSE(url);
                ht.call(soapaction, snv);
                String result = snv.getResponse().toString();
                if (result.equalsIgnoreCase("no")) {
                    Toast.makeText(getApplicationContext(), "no value", Toast.LENGTH_SHORT).show();

                } else {
                    String[] res = result.split("\\@");

                    cid = new String[res.length];

                    status = new String[res.length];
                    date = new String[res.length];

                    for (int i = 0; i < res.length; i++) {
                        String[] d = res[i].split("\\#");
                        cid[i] = d[0];
                        status[i]=d[5];
                        date[i] = d[7];


                    }
                    lv.setAdapter(new custom_health_status(getApplicationContext(),  cid, date,status));



                }}}
        catch(Exception ex)
        {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor ed=sh.edit();
        ed.putString("hid",cid[i]);
        ed.commit();
        Intent h=new Intent(getApplicationContext(),detailhealth.class);
        startActivity(h);
    }
}
