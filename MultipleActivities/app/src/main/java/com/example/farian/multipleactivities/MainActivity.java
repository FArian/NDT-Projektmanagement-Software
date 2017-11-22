package com.example.farian.multipleactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.farian.multipleactivities.connection.Client;


import okhttp3.OkHttpClient;

/**
 *
 */
public class MainActivity extends Activity {
    private Client client;
    private OkHttpClient okHttpClient= new OkHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        client= new Client();
        Button gotoProjectButton = (Button) findViewById(R.id.sign_in_button);
        gotoProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ProjectActivity.class);
                startActivity(intent);
            }
        });


    }
}

