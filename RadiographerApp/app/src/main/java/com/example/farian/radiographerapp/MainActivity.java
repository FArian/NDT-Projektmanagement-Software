package com.example.farian.radiographerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class MainActivity extends AppCompatActivity {

    private Button start;
    private TextView output;
    private OkHttpClient client;
    private EchoWebSocketListener echoWebSocketListener;

    private void output(final String txt) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                output.setText(output.getText().toString()+ "\n\n" + txt);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button)findViewById(R.id.start);
        output=(TextView)findViewById(R.id.output);
        client=new OkHttpClient();
        echoWebSocketListener = new EchoWebSocketListener();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });

    }

    private void start() {

        Request request = new Request.Builder().url("ws://192.168.178.50:9000/getsocket").build();
        EchoWebSocketListener listener = new EchoWebSocketListener();
        WebSocket ws=client.newWebSocket(request,listener);
        client.dispatcher().executorService().shutdown();
        //ws.send(ToJson.message("Hey , im Client").toString()+"\n");
    }
}
