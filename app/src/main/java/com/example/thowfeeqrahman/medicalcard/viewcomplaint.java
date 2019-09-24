package com.example.thowfeeqrahman.medicalcard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class viewcomplaint extends AppCompatActivity implements View.OnClickListener {
    ListView lv1;
    Button bt1;
String[]cid,cname,date,reply,status;

    String namespace = "http://tempuri.org/";
    String method = "viewcomplaint";
    String soapaction = "http://tempuri.org/viewcomplaint";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcomplaint);
lv1=(ListView)findViewById(R.id.lv1);
        bt1=(Button)findViewById(R.id.button26);
        bt1.setOnClickListener(this);
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip=sh.getString("ip", "");
     String   url="http://"+ip+"/Medical%20card/WebService.asmx";


        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {

                StrictMode.ThreadPolicy th = new StrictMode.ThreadPolicy.Builder().permitAll().build();

                android.os.StrictMode.setThreadPolicy(th);


                SoapObject soap = new SoapObject(namespace, method);
                soap.addProperty("source",sh.getString("uid",""));
                soap.addProperty("type",sh.getString("type",""));
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

                    cname = new String[res.length];
                    date = new String[res.length];
                    reply = new String[res.length];
status=new String[res.length];
                    for (int i = 0; i < res.length; i++) {
                        String[] d = res[i].split("\\#");
                        cid[i] = d[0];
                        status[i]=d[3];
                        date[i] = d[1];
                        cname[i] = d[2];
                        reply[i] = d[4];


                    }
                    lv1.setAdapter(new Custom_viewcomp(getApplicationContext(),  cname, date,status,reply));



    }}}
            catch(Exception ex)
                {
                    Toast.makeText(getApplicationContext(),ex.getMessage().toString(),Toast.LENGTH_SHORT).show();
                }}

    @Override
    public void onClick(View view) {
        if(view==bt1)
        {
            Intent i=new Intent(getApplicationContext(),complaint.class);
            startActivity(i);
        }

    }
}
