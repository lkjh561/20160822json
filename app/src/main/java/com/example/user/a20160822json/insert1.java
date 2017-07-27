package com.example.user.a20160822json;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class insert1 extends AppCompatActivity {

    EditText et1;
    EditText et2;
    EditText et3;
    EditText et4;
    Button bt1,bt2;
    JSONObject jsonObject = new JSONObject();
    HttpURLConnection urlConnection = null; //可以跟網站做溝通的物件
    URL url = null;
    StringBuilder sb;
    int a=0;

    // urlConnection = (HttpURLConnection) url.openConnection();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert1);
        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4=(EditText) findViewById(R.id.editText4);
        bt1 = (Button) findViewById(R.id.button);
        bt2=(Button)findViewById(R.id.button7) ;
        bt1.setOnClickListener(btn);
        bt2.setOnClickListener(btn2);

    }

    View.OnClickListener btn = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            new Thread() {

                @Override
                public void run() {
                    super.run();
                    try {
                        url = new URL("http://192.168.43.180/insert.php");
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

                    String name= String.valueOf(et1.getText());
                    String studentID=String.valueOf(et2.getText());
                    String Class=String.valueOf(et3.getText());
                    String password=String.valueOf(et4.getText());

                    try {
                        jsonObject.put("name",name);
                        jsonObject.put("studentID",studentID);
                        jsonObject.put("class",Class);
                        jsonObject.put("password",password);
                        Log.e("5555",name+studentID+Class);
                        a++;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


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
                            String line=null;
                            while ((line=br.readLine())!=null){
                                Log.e("Line",line);
                                sb.append(line);
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }.start();
            if(a==0){  Toast.makeText(view.getContext(), "新增失敗", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(view.getContext(), "新增成功!!", Toast.LENGTH_SHORT).show();
            }
        }





    };
    View.OnClickListener btn2=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i  = new Intent();
            i.setClass(insert1.this,MainActivity.class);
            startActivity(i);
            finish();
        }
    };
}
