package com.example.thowfeeqrahman.medicalcard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import static com.example.thowfeeqrahman.medicalcard.R.styleable.View;

public class DoctorReg2 extends AppCompatActivity implements View.OnClickListener {
    EditText ed10,ed11,ed12,ed13,ed14,ed15;
    Button bt2;
    public static String attach="";
    private static final int FILE_SELECT_CODE = 0;
    String path,sz="0",fname;
ImageView im;
    String namespace = "http://tempuri.org/";
    String method = "doctorreg";
    String soapaction = "http://tempuri.org/doctorreg";
    String url = "http://localhost/Medical%20card/WebService.asmx";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_reg2);
        ed10=(EditText)findViewById(R.id.editText10);
        ed11=(EditText)findViewById(R.id.editText11);
        ed12=(EditText)findViewById(R.id.editText19);
        ed13=(EditText)findViewById(R.id.editText20);
        ed14=(EditText)findViewById(R.id.editText21);
        ed15=(EditText)findViewById(R.id.editText111);
        bt2= (Button)findViewById(R.id.button2);
        bt2.setOnClickListener(this);
        im=(ImageView)findViewById(R.id.imageView);
        im.setOnClickListener(this);
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
        if(view==bt2)
        {

            int flg=0;
            if (ed10.getText().length() == 0) {
                flg++;
                ed10.setError("");
            }
            if (ed11.getText().length() == 0) {
                flg++;
                ed11.setError("");
            }
            if (ed12.getText().length() == 0) {
                flg++;
                ed13.setError("");
            }
            if (ed14.getText().length() == 0) {
                flg++;
                ed14.setError("");
            }
            if (ed15.getText().length() == 0) {
                flg++;
                ed15.setError("");
            }
           if(flg==0)
           {
            try
        {
          SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SoapObject soap = new SoapObject(namespace, method);
            soap.addProperty("Dr_liscense_no",sh.getString("drid",""));
            soap.addProperty("Dr_Name",sh.getString("name","") );
            soap.addProperty("dob", sh.getString("dob",""));
            soap.addProperty("qualification",sh.getString("qualification",""));
            soap.addProperty("specialisation",sh.getString("specialisation",""));
            soap.addProperty("working_hospital", ed15.getText().toString());
            soap.addProperty("state", sh.getString("state",""));
            soap.addProperty("place", sh.getString("place",""));
            soap.addProperty("pincode", sh.getString("pincode",""));
            soap.addProperty("phone_no", ed10.getText().toString());
            soap.addProperty("email_id", ed11.getText().toString() );
            soap.addProperty("gender", sh.getString("gender",""));
            soap.addProperty("username",  ed12.getText().toString());
            soap.addProperty("image", attach);
            soap.addProperty("password",  ed13.getText().toString());


            SoapSerializationEnvelope snv = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            snv.dotNet = true;
            snv.setOutputSoapObject(soap);

            HttpTransportSE ht = new HttpTransportSE(url);
            ht.call(soapaction, snv);
            String result = snv.getResponse().toString();
            if (result.equalsIgnoreCase("ok")) {
                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
Intent i=new Intent(getApplicationContext(),login.class);
startActivity(i);
            } else {

            }}
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }


            Intent i=new Intent(getApplicationContext(),DoctorReg.class);
            startActivity(i);
        }}
        if(view==im)
        {
           showfilechooser(1);
        }


    }
    private void showfilechooser(int FILE_SELECT_CODE)
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT); //getting all types of files
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {

            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"),FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(getApplicationContext(), "Please install a File Manager.",Toast.LENGTH_SHORT).show();
        }

    }

    Uri uri;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        switch (requestCode) {

            case 1:
                if (resultCode == RESULT_OK) {
//

                    // Get the Uri of the selected file
                    uri = data.getData();
                    //   im.setImageURI(uri);
                    Picasso.with(getApplicationContext()).load(uri).into(im);
                    Log.d("File Uri", "File Uri: " + uri.toString());



                    // Get the path
                    //String path = null;
                    try {
                        path = FileUtils.getPath(this, uri);
                        fname = "";

                        //  img.setImageURI(uri);


                        // ed10.setText(path);

                        File fil = new File(path);
                        float fln = (float) (fil.length() / 1024);
                        //		atype=path.substring(path.lastIndexOf(".")+1);
                        sz = fln + "";
                        // ed2.setText(sz+"");
                    } catch (URISyntaxException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    Toast.makeText(this, "File Name & PATH are:" + path, Toast.LENGTH_LONG).show();

                    // send image to server

                    File file = new File(path);

                    byte[] byteArray = null;
                    try {
                        InputStream inputStream = new FileInputStream(file);
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        byte[] b = new byte[2048 * 8];
                        int bytesRead = 0;

                        while ((bytesRead = inputStream.read(b)) != -1) {
                            bos.write(b, 0, bytesRead);
                        }

                        byteArray = bos.toByteArray();
                        String str = Base64.encodeToString(byteArray, Base64.DEFAULT);
                        attach = str;
                    } catch (IOException e) {
                        Toast.makeText(this, "String :" + e.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }

                }
        }



    }
}
