package com.example.thowfeeqrahman.medicalcard;

import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class pres_detail extends AppCompatActivity {
TextView t1,t2,t3,t4,t5;
    String namespace = "http://tempuri.org/";
    String method = "detail_pres";
    String soapaction = "http://tempuri.org/detail_pres";
    String url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pres_detail);
        t1=(TextView)findViewById(R.id.textView13);
        t2=(TextView)findViewById(R.id.textView24);
        t3=(TextView)findViewById(R.id.textView33);
        t4=(TextView)findViewById(R.id.textView48);
        t5=(TextView)findViewById(R.id.textView49);
        try

        {
            if (android.os.Build.VERSION.SDK_INT > 9) {

                StrictMode.ThreadPolicy th = new StrictMode.ThreadPolicy.Builder().permitAll().build();

                android.os.StrictMode.setThreadPolicy(th);

            }


        }
        catch (Exception ex)

        {
            Toast.makeText(getApplicationContext(), "error"+ex.getMessage(), Toast.LENGTH_LONG).show();

        }
        SharedPreferences sh5= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip=sh5.getString("ip","");
        url = "http://" + ip + "//Medical%20card/WebService.asmx";

        String id=sh5.getString("uid","");

        try {
            SoapObject soap = new SoapObject(namespace, method);
            soap.addProperty("ir",sh5.getString("rid",""));


            SoapSerializationEnvelope snv = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            snv.dotNet = true;
            snv.setOutputSoapObject(soap);

            Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_SHORT).show();
            HttpTransportSE ht = new HttpTransportSE(url);
            ht.call(soapaction, snv);

            String result = snv.getResponse().toString();
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
            if (result.equalsIgnoreCase("no")) {
                Toast.makeText(getApplicationContext(), "No value", Toast.LENGTH_LONG).show();

            }
            else

            {
                String ar[]=result.split("\\#");

                String did=ar[0].toString();
                String dname=ar[1].toString();
                String gender=ar[2].toString();
                String qualif=ar[3].toString();


//                String u=ar[5].toString();
//                //   Toast.makeText(getApplicationContext(), url , Toast.LENGTH_SHORT).show();
//                String url1="http://" + ip + "//Medical%20card/user/"+u;
//                Picasso.with(getApplicationContext()).load(url).into(im);

                t1.setText(did);
                t2.setText(dname);
                t3.setText(gender);
                t4.setText(qualif);
                t5.setText(ar[4].toString());

            }



        } catch (Exception ex) {


            Toast.makeText(getApplicationContext(), "error" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    }
