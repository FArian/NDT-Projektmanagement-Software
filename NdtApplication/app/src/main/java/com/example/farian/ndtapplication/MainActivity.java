package com.example.farian.ndtapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.farian.ndtapplication.json.TOJSON;
import com.example.farian.ndtapplication.network.Client;
import com.example.farian.ndtapplication.network.RgClient;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    private Button start;
    private TextView output;

    private RgClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new RgClient("192.168.178.50");


        start = (Button) findViewById(R.id.start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                client.webSocket.send(TOJSON.helloServer().toString());

            }
        });

    }

}
