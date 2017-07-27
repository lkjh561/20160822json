package com.example.user.a20160822json;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ALL extends AppCompatActivity {


    TextView tv;
    Button bt1,bt;
    JSONObject jsonObject = new JSONObject();
    HttpURLConnection urlConnection = null; //可以跟網站做溝通的物件
    URL url = null;
    StringBuilder sb;
    String z;
  //  String line;
    // urlConnection = (HttpURLConnection) url.openConnection();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        tv=(TextView)findViewById(R.id.textView13);
        bt1 = (Button) findViewById(R.id.button10);
        bt1.setOnClickListener(btn);
        bt= (Button) findViewById(R.id.button11);
        bt.setOnClickListener(btn1);
    }

    View.OnClickListener btn1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            new Thread() {

                @Override
                public void run() {
                    super.run();
                    try {
                        url = new URL("http://192.168.43.180/all.php");
                        urlConnection = (HttpURLConnection) url.openConnection();
                        urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                        urlConnection.setRequestProperty("Accept", "application/json");
                        urlConnection.setRequestMethod("POST");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (ProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    urlConnection.setDoOutput(true);
                    urlConnection.setDoInput(true);







                    try {
                        OutputStreamWriter out = null;
                        out=new OutputStreamWriter(urlConnection.getOutputStream());
                        Log.e("HTTP",jsonObject.toString());
                        out.write(jsonObject.toString());
                        out.close();

                        int  HttpResult=urlConnection.getResponseCode();
                        sb=new StringBuilder();
                        Log.e("HTTP",HttpResult+"-------");
                        if(HttpResult==HttpURLConnection.HTTP_OK){
                            BufferedReader br=new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"utf-8"));

                            while ((z=br.readLine())!=null){
                                tv.setText(z);

                                //sb.append(line);
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }.start();
        }




    };
    View.OnClickListener btn=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i  = new Intent();
            i.setClass(ALL.this,MainActivity.class);
            startActivity(i);
            finish();
        }
    };
}
