package com.example.thowfeeqrahman.medicalcard;

import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class View_doctors extends AppCompatActivity {
ListView lv2;
    String[]did,dname,gender,qualification,image;

    String namespace = "http://tempuri.org/";
    String method = "viewdoctors";
    String soapaction = "http://tempuri.org/viewdoctors";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doctors);
        lv2=(ListView)findViewById(R.id.lv2);
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip=sh.getString("ip", "");
        String   url="http://" + ip + "//Medical%20card/WebService.asmx";


        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {

                StrictMode.ThreadPolicy th = new StrictMode.ThreadPolicy.Builder().permitAll().build();

                android.os.StrictMode.setThreadPolicy(th);


                SoapObject soap = new SoapObject(namespace, method);

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

                    did = new String[res.length];

                    dname = new String[res.length];
                    image = new String[res.length];
                    gender = new String[res.length];
                            qualification=new String[res.length];
                    for (int i = 0; i < res.length; i++) {
                        String[] d = res[i].split("\\#");
                        did[i] = d[0];
                        dname[i]=d[1];
                        image[i] = d[4];
                        gender[i] = d[2];
                        qualification[i] = d[3];


                    }
                    lv2.setAdapter(new Custom_view_doctor(getApplicationContext(),  did,dname,image,gender,qualification));



                }}}
        catch(Exception ex)
        {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }

    }
}
