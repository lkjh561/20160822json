package com.example.user.a20160822json;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamCorruptedException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button insert,select,delete,update,all;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert=(Button)findViewById(R.id.button2);
        select=(Button)findViewById(R.id.button3);
        delete=(Button)findViewById(R.id.button4);
        update=(Button)findViewById(R.id.button5);
        all=(Button)findViewById(R.id.button9);
        insert.setOnClickListener(bt1);
        select.setOnClickListener(bt2);
        delete.setOnClickListener(bt3);
       update.setOnClickListener(bt4);
       all.setOnClickListener(bt5);


    }
    View.OnClickListener bt1=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i  = new Intent();
            i.setClass(MainActivity.this,insert1.class);
            startActivity(i);
            finish();
        }
    };
    View.OnClickListener bt2=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i  = new Intent();
            i.setClass(MainActivity.this,select.class);
            startActivity(i);
            finish();
        }
    };
    View.OnClickListener bt3=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i  = new Intent();
            i.setClass(MainActivity.this,delete2.class);
            startActivity(i);
            finish();

        }
    };
    View.OnClickListener bt4=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i  = new Intent();
            i.setClass(MainActivity.this,update.class);
            startActivity(i);
            finish();
        }
    };
    View.OnClickListener bt5=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i  = new Intent();
            i.setClass(MainActivity.this,ALL.class);
            startActivity(i);
            finish();
        }
    };
}
