package com.example.farian.multipleactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.farian.multipleactivities.connection.Client;
import com.example.farian.multipleactivities.json.ToJson;


import okhttp3.OkHttpClient;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/**
 * project activity
 */
public class SafetyActivity extends Activity {
    private OkHttpClient okHttpClient;
    private Client client;
    private WebSocketListener listener;
    private WebSocket webSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety);
        okHttpClient=new OkHttpClient();
        client = new Client();
        webSocket = client.getWebSocket();
        Button logoutBtn=(Button)findViewById(R.id.saftey_logout_button);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                client.getClient();
                client.getWebSocket().send(ToJson.message("Safety Activity" ,"Safety").toString());
            }
        });


    }
}

