package com.androidjson.firebasegooglelogin_androidjsoncom;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidjson.firebasegooglelogin_androidjsoncom.client.RecyclerViewAdapter;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.Personal;
import com.google.gson.Gson;
import java.text.DecimalFormat;


public class TimeActivity extends Activity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private int RED, GREEN, BLACK, WHITE, CURRENTCOLOR;
    private Personal personal;
    private double thickness = 0, distance = 0, film_factor = 0, sourceActivity = 0;
    private Spinner spinner_isotope, spinner_material, spinner_thickness, spinner_distance;
    private EditText editText_thickness, editText_distance, editText_activity, editText_film_factor;
    private Button calculate_btn, start_btn, stop_btn;
    private TextView textView_result_value, textView_result_view, text_View_result_time;
    private Chronometer chronometer;
    private static DecimalFormat REAL_FORMATTER = new DecimalFormat("00.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RED = R.color.colorRed;
        GREEN = R.color.easyfei_green;
        BLACK = R.color.album_text_color;
        WHITE = R.color.white;
        Gson gson = new Gson();
        personal = gson.fromJson(getIntent().getStringExtra("Personal"), Personal.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_layout);
        bindViews();
        calculate_btn.setOnClickListener(this);
        start_btn.setOnClickListener(this);
        stop_btn.setOnClickListener(this);
        chronometer.setOnClickListener(this);
        onChronometerTick();
    }

    private void updateThickness() {
        String value = editText_thickness.getText().toString();
        if (value == null || value.isEmpty()) {
            thickness = 0.0;
            Toast.makeText(TimeActivity.this, getResources().getString(R.string.clicked_item, editText_thickness.getText()), Toast.LENGTH_SHORT).show();
        } else {
            thickness = Double.parseDouble(editText_thickness.getText().toString());

            switch (spinner_thickness.getSelectedItem().toString()) {
                case "Inch":
                    thickness = convert_Inch_to_Cm(thickness);
                    break;
                case "mm":
                    thickness = convert_mm_to_Cm(thickness);
                    break;

            }

        }
    }

    private void updateDistance() {
        String value = editText_distance.getText().toString();
        if (value == null || value.isEmpty()) {
            distance = 0.0;
            Toast.makeText(TimeActivity.this, getResources().getString(R.string.clicked_item, editText_distance.getText()), Toast.LENGTH_SHORT).show();

        } else {
            distance = Double.parseDouble(editText_distance.getText().toString());

            switch (spinner_distance.getSelectedItem().toString()) {
                case "Inch":
                    distance = convert_Inch_to_Cm(distance);

                    break;
                case "m":
                    distance = convert_m_to_Cm(distance);

                    break;

            }

        }
    }

    private void update_FilmFactor_SourceActivity() {
        String value1 = editText_activity.getText().toString();
        String value2 = editText_film_factor.getText().toString();
        if (value1 == null || value1.isEmpty()) {
            distance = 0.0;
        } else {
            sourceActivity = Double.parseDouble(editText_activity.getText().toString());
            //Toast.makeText(TimeActivity.this, getResources().getString(R.string.clicked_item, editText_activity.getText()), Toast.LENGTH_SHORT).show();

        }
        if (value2 == null || value2.isEmpty()) {
            distance = 0.0;
        } else {
            film_factor = Double.parseDouble(editText_film_factor.getText().toString());
            //Toast.makeText(TimeActivity.this, getResources().getString(R.string.clicked_item, editText_film_factor.getText()), Toast.LENGTH_SHORT).show();

        }
    }


    /**
     * View initializing
     */
    private void bindViews() {
        //Bind recyclerView
        spinner_isotope = (Spinner) findViewById(R.id.spinner1);
        spinner_material = (Spinner) findViewById(R.id.spinner2);
        spinner_thickness = (Spinner) findViewById(R.id.spinner3);
        spinner_distance = (Spinner) findViewById(R.id.spinner4);
        chronometer = (Chronometer) findViewById(R.id.simpleChronometer);
        editText_thickness = (EditText) findViewById(R.id.edit_text1);
        editText_distance = (EditText) findViewById(R.id.edit_text2);
        editText_activity = (EditText) findViewById(R.id.edit_text3);
        editText_film_factor = (EditText) findViewById(R.id.edit_text4);
        calculate_btn = (Button) findViewById(R.id.calculate_btn);
        start_btn = (Button) findViewById(R.id.start_btn);
        stop_btn = (Button) findViewById(R.id.stop_btn);
        textView_result_value = (TextView) findViewById(R.id.text_view_calculate_result);
        textView_result_view = (TextView) findViewById(R.id.text_View_result);
        text_View_result_time = (TextView) findViewById(R.id.text_View_result_time);

    }

    private double convert_Inch_to_Cm(double inch) {
        double cm = 0;
        cm = inch * 2.54;
        return cm;
    }

    private double convert_mm_to_Cm(double mm) {
        double cm = 0;
        cm = mm / 10;
        return cm;
    }

    private double convert_m_to_Cm(double m) {
        double cm = 0;
        cm = m * 100;
        return cm;
    }

    private double time() {
        double time = 0;
        time = ((Math.pow(distance, 2) * thickness * film_factor) * 60 / (sourceActivity * 32000));
        //Toast.makeText(TimeActivity.this, getResources().getString(R.string.clicked_item, "dis*dis*thickness*ff/A*10000"), Toast.LENGTH_SHORT).show();
        return time;
    }

    @SuppressLint("ResourceAsColor")
    private void onChronometerTick() {
        while (chronometer.getBase()==(long)time()){
            chronometer.setTextColor(R.color.colorRed);
        }

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.calculate_btn:
                updateThickness();
                updateDistance();
                update_FilmFactor_SourceActivity();
                textView_result_value.setText(REAL_FORMATTER.format(time()));
                textView_result_value.setVisibility(View.VISIBLE);
                textView_result_view.setVisibility(View.VISIBLE);
                //chronometer.setBase((long)time());
                chronometer.setVisibility(View.VISIBLE);
                start_btn.setVisibility(View.VISIBLE);
                break;
            case R.id.start_btn:
                long baseTime;
                long stopTime=(long) time();
                long elapsedTime;
                //chronometer.setCountDown(true);
                long dayInMilli = 60*60*24*1000;
                chronometer.setBase(SystemClock.elapsedRealtime()-dayInMilli);
                chronometer.start();
                stop_btn.setVisibility(View.VISIBLE);
                baseTime=SystemClock.elapsedRealtime()-stopTime;
                break;
            case R.id.stop_btn:
                chronometer.stop();
                chronometer.setVisibility(View.VISIBLE);
                break;
            case R.id.simpleChronometer:
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.setVisibility(View.VISIBLE);
                break;
        }

    }
}



