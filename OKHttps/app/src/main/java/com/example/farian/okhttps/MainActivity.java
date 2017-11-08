package com.example.farian.okhttps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.farian.okhttps.json.ToJson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class MainActivity extends AppCompatActivity {
    private Button start;
    private TextView output;
    private OkHttpClient client;

    private final class EchoWebSocketListener extends WebSocketListener{

    private static final int NORMAL_CLOSURE_STATUS = 1000;
    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        webSocket.send(ToJson.helloServer().toString()+ "\n");
       //webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");
    }
    @Override
    public void onMessage(WebSocket webSocket, String text) {
        output("Receiving : " + text);

    }
    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        output("Receiving bytes : " + bytes.hex());
    }
    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(NORMAL_CLOSURE_STATUS, null);
        webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");
        output("Closing : " + code + " / " + reason);
    }
    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        output("Error : " + t.getMessage());
        
    }
}

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

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });
    }

    private void start() {

        Request request = new Request.Builder().url("ws://10.180.51.53:9000/getsocket").build();
        EchoWebSocketListener listener = new EchoWebSocketListener();
        WebSocket ws=client.newWebSocket(request,listener);
        client.dispatcher().executorService().shutdown();
    }
}
