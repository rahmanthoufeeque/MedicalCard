package com.example.thowfeeqrahman.medicalcard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.os.health.HealthStats;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class dcard extends AppCompatActivity implements View.OnClickListener {
    TextView t1,t2,t3,t4,t5;
    String namespace = "http://tempuri.org/";
    String method = "patient_pro";
    String soapaction = "http://tempuri.org/patient_pro";
    String url = "";
    ImageView im;

Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dcard);
        t1=(TextView)findViewById(R.id.textView43);
        t2=(TextView)findViewById(R.id.textView44);
        t3=(TextView)findViewById(R.id.textView45);
        t4=(TextView)findViewById(R.id.textView46);
        t5=(TextView)findViewById(R.id.textView47);
        im=(ImageView)findViewById(R.id.imageView4);
b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button9);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
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

        String id=sh5.getString("pid","");

        try {
            SoapObject soap = new SoapObject(namespace, method);
            soap.addProperty("uid",id);


            SoapSerializationEnvelope snv = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            snv.dotNet = true;
            snv.setOutputSoapObject(soap);

            Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_SHORT).show();
            HttpTransportSE ht = new HttpTransportSE(url);
            ht.call(soapaction, snv);

            String result = snv.getResponse().toString();
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
            if (result.equalsIgnoreCase("no")) {
                Toast.makeText(getApplicationContext(), "No value", Toast.LENGTH_LONG).show();

            }
            else

            {
                Toast.makeText(getApplicationContext(), result , Toast.LENGTH_SHORT).show();
                String ar[]=result.split("\\#");

                String did=ar[0].toString();
                String dname=ar[1].toString();
                String gender=ar[2].toString();
                String qualif=ar[3].toString();


                String u=ar[6].toString();

//                String url1="http://" + ip + "//Medical%20card/user/"+u;
//                Picasso.with(getApplicationContext()).load(url1).into(im);
//                Toast.makeText(getApplicationContext(), url1 , Toast.LENGTH_SHORT).show();
                t1.setText(did);
                t2.setText(dname);
                t3.setText(gender);
                t4.setText(qualif);
                t5.setText(ar[4]);

            }



        } catch (Exception ex) {


            Toast.makeText(getApplicationContext(), "error" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onClick(View view) {
        if(view==b2)
        {
            Intent i= new Intent(getApplicationContext(),healthstatus.class);
            startActivity(i);
        }
        if(view==b1)
        {
            Intent i= new Intent(getApplicationContext(),prescription.class);
            startActivity(i);
        }
    }
}

