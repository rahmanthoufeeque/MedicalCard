package com.example.thowfeeqrahman.medicalcard;

import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class detailhealth extends AppCompatActivity {
TextView tv1,tv2,tv3,tv4,tv5;
    String namespace = "http://tempuri.org/";
    String method = "detailhealth";
    String soapaction = "http://tempuri.org/detailhealth";
    String url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailhealth);
        tv1=(TextView)findViewById(R.id.textView34);
        tv2=(TextView)findViewById(R.id.textView36);
        tv3=(TextView)findViewById(R.id.textView38);
        tv4=(TextView)findViewById(R.id.textView40);
        tv5=(TextView)findViewById(R.id.textView42);
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
            soap.addProperty("id",sh5.getString("hid",""));


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



                //String u=ar[12].toString();
                //  Toast.makeText(getApplicationContext(), url , Toast.LENGTH_SHORT).show();
                // Picasso.with(getApplicationContext()).load(url).into(userphoto);

                tv1.setText(did);
                tv2.setText(dname);
                tv3.setText(gender);
                tv4.setText(ar[3].toString());
                tv5.setText(ar[4].toString());


            }



        } catch (Exception ex) {


            Toast.makeText(getApplicationContext(), "error" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
