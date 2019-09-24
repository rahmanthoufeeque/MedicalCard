package com.example.thowfeeqrahman.medicalcard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
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

public class UserReg2 extends AppCompatActivity implements View.OnClickListener {
    EditText ed7,ed8,ed9,ed10,ed11;
    Button bt2;
    public static String attach="";
    private static final int FILE_SELECT_CODE = 0;
    String path,sz="0",fname;
    String namespace = "http://tempuri.org/";
    String method = "userreg";
    String soapaction = "http://tempuri.org/userreg";
    String url = "http://localhost/Medical%20card/WebService.asmx";

String name,dob,place,state,pincode,gender;
    ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg2);
        ed7=(EditText)findViewById(R.id.editText);
        ed8=(EditText)findViewById(R.id.editText34);
        ed9=(EditText)findViewById(R.id.editText35);
        ed10=(EditText)findViewById(R.id.editText38);
        ed11=(EditText)findViewById(R.id.ed111);
        bt2= (Button)findViewById(R.id.button6);
        bt2.setOnClickListener(this);
       im=(ImageView)findViewById(R.id.imageView2);
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
        url = "http://"+ip+"/Medical%20card/WebService.asmx";

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
                  //  Log.d("File Uri", "File Uri: " + uri.toString());



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
    @Override
    public void onClick(View view) {
        int flg = 0;

        if (view == bt2) {
            if (ed7.getText().length() == 0) {
                flg++;
                ed7.setError("");
            }
            if (ed8.getText().length() == 0) {
                flg++;
                ed8.setError("");
            }
            if (ed9.getText().length() == 0) {
                flg++;
                ed9.setError("");
            }
            if (ed10.getText().length() == 0) {
                flg++;
                ed10.setError("");
            }
            if (flg == 0) {
                try {
                    SharedPreferences sh1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());



                    SoapObject soap = new SoapObject(namespace, method);
                    soap.addProperty("name", sh1.getString("name1", ""));
                    soap.addProperty("dob",sh1.getString("dob1", ""));
                    soap.addProperty("place",sh1.getString("place1", ""));
                    soap.addProperty("state", sh1.getString("state1", ""));

                    soap.addProperty("pincode", sh1.getString("pincode1", ""));
                    soap.addProperty("gender", sh1.getString("gender1", ""));
                    soap.addProperty("emailid", ed11.getText().toString());
                    soap.addProperty("username", ed8.getText().toString()
                    );
                    soap.addProperty("password", ed9.getText().toString());
                    soap.addProperty("photo", attach);
                    soap.addProperty("phoneno", ed7.getText().toString());
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

                        Intent p = new Intent(getApplicationContext(),login.class);
                        startActivity(p);
                    }
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), "error" + ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        }
        if(view==im)
        {
            showfilechooser(1);
        }

    }

}
