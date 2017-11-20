package com.example.farian.multipleactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * project activity
 */
public class ProjectActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        Button logOutBtn=(Button) findViewById(R.id.logout_button);
        Button safetyBtn= (Button) findViewById(R.id.safety_button);
        Button timeBtn=(Button) findViewById(R.id.time_button);
        Button reportBtn=(Button)findViewById(R.id.report_button);
        Button myinfoBtn=(Button) findViewById(R.id.my_info_button);
        Button materialBtn=(Button) findViewById(R.id.material_button);
        materialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MaterialActivity.class);
                startActivity(intent);
            }
        });
        myinfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MyInfoActivity.class);
                startActivity(intent);
            }
        });
        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ReportActivity.class);
                startActivity(intent);
            }
        });
        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),TimeActivity.class);
                startActivity(intent);
            }
        });
        safetyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SafetyActivity.class);
                startActivity(intent);

            }
        });
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);

            }
        });


    }
}

