package com.example.thowfeeqrahman.medicalcard;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class medicalstorehome extends AppCompatActivity implements View.OnClickListener {
    TextView tv1,tv2,tv3;
    Button bt1;

    String namespace = "http://tempuri.org/";
    String method = "store_viewprofile";
    String soapaction = "http://tempuri.org/store_viewprofile";
    String url = "";
    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicalstorehome);
        tv1=(TextView)findViewById(R.id.textView18);
        tv2=(TextView)findViewById(R.id.textView21);
        tv3= (TextView)findViewById(R.id.textView23);
        bt1=(Button)findViewById(R.id.button13);


        bt1.setOnClickListener(this);


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
            soap.addProperty("id",id);


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

                tv1.setText(dname);
                tv2.setText(gender);
                tv3.setText(did);


            }



        } catch (Exception ex) {


            Toast.makeText(getApplicationContext(), "error" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    public void scanQR() {
        try {

            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);

        } catch (ActivityNotFoundException anfe) {
            // showDialog(Con, "No Scanner Found", "Download a scanner code activity?", "Yes", "No").show();
        }
    }
    private static AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(act);
        downloadDialog.setTitle(title);
        downloadDialog.setMessage(message);
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try {
                    act.startActivity(intent);
                } catch (ActivityNotFoundException anfe) {

                }
            }
        });
        downloadDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return downloadDialog.show();
    }
    Runnable rn= new Runnable() {
        @Override
        public void run() {
        }
    };
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");



                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("pid",contents);
                ed.commit();
                //	Toast.makeText(getApplicationContext(), "ss="+contents, Toast.LENGTH_LONG).show();

                Intent i=new Intent(getApplicationContext(), dcard.class);
                startActivity(i);
            }
        }}

    @Override
    public void onClick(View view) {

        if (view==bt1)
        {
            scanQR();
        }

    }
}
