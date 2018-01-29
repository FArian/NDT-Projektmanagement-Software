package com.androidjson.firebasegooglelogin_androidjsoncom.client.activitys;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidjson.firebasegooglelogin_androidjsoncom.R;
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
    private double thickness = 0;
    private double distance_film_from_source = 0;
    private double film_factor = 0;
    private double sourceActivity = 0;
    private double rhm = 0;
    private double hvl_mm = 0;
    private double realTime, time;
    private String half_life_time, optimum_thickness_range;
    private Spinner spinner_isotope, spinner_material, spinner_thickness, spinner_distance;
    private EditText editText_thickness, editText_distance, editText_activity, editText_film_factor;
    private Button calculate_btn, start_btn, stop_btn;
    private TextView textView_result_value, textView_result_view, text_View_result_time, text_view_isotope_info, text_View_Ft, text_view_Ft_warning;
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
        text_View_Ft.setOnClickListener(this);
        text_view_Ft_warning.setOnClickListener(this);
        isotopeInfoUpdate();
        spinner_isotope.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                isotopeInfoUpdate();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
        text_view_isotope_info = (TextView) findViewById(R.id.text_view_isotope_info);
        text_View_Ft = (TextView) findViewById(R.id.text_view_ft);
        text_view_Ft_warning = (TextView) findViewById(R.id.text_view_ft_warning);

    }

    /**
     * isotope info for title update
     */
    private void isotopeInfoUpdate() {
        isotope_Info(spinner_isotope.getSelectedItem().toString());
        text_view_isotope_info.setText(" Source=" + spinner_isotope.getSelectedItem() + "  (RHM = " + getRhm() + ") " + " (HVL,mm = " + getHvl_mm() + ")" + "\n" + " (Half life Time = " + getHalf_life_time() + ")" + "(Optimum thickness= " + getOptimum_thickness_range() + ")");

    }


    /**
     * @param isotope
     * @return pos[0]=RHM ,pos[1]=HVM,mm
     */
    public void isotope_Info(String isotope) {

        switch (isotope) {
            case "Ir-192":
                setRhm(0.49);
                setHvl_mm(11);
                setHalf_life_time("74 day");
                setOptimum_thickness_range("10-70 mm");
                break;
            case "Co-60":
                setRhm(1.33);
                setHvl_mm(22);
                setHalf_life_time("5.3 year");
                setOptimum_thickness_range("15-150 mm");
                break;
            case "Se-75":
                setRhm(0.22);
                setHvl_mm(10);
                setHalf_life_time("121 day");
                setOptimum_thickness_range("5-40 mm");
                break;
            case "Cs-137":
                setRhm(0.33);
                setHvl_mm(17);
                setHalf_life_time("3 year");
                setOptimum_thickness_range("15-100 mm");
                break;


        }

    }

    public double getRhm() {
        return rhm;
    }

    public void setRhm(double rhm) {
        this.rhm = rhm;
    }

    public double getHvl_mm() {
        return hvl_mm;
    }

    public void setHvl_mm(double hvl_mm) {
        this.hvl_mm = hvl_mm;
    }

    public String getHalf_life_time() {
        return half_life_time;
    }

    public void setHalf_life_time(String half_life_time) {
        this.half_life_time = half_life_time;
    }

    public String getOptimum_thickness_range() {
        return optimum_thickness_range;
    }

    public void setOptimum_thickness_range(String optimum_thickness_range) {
        this.optimum_thickness_range = optimum_thickness_range;
    }

    public double getThickness() {
        return thickness;
    }

    public void setThickness(double thickness) {
        this.thickness = thickness;
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
                    thickness = convert_Inch_to_mm(thickness);
                    metal_Thickness_Factor((int) thickness);
                    break;
                case "mm":
                    metal_Thickness_Factor((int) thickness);
                    break;

            }

        }
        if (getThickness() < 5) {
            setThickness(5);
            text_view_Ft_warning.setText("Thickness=5");
            Toast.makeText(TimeActivity.this, getResources().getString(R.string.value_is_false, text_view_Ft_warning.getText()), Toast.LENGTH_LONG).show();
            text_view_Ft_warning.setText("5mm<= t >=74mm");
        }
        if (getThickness() > 74) {
            setThickness(74);
            text_view_Ft_warning.setText("Thickness=74");
            Toast.makeText(TimeActivity.this, getResources().getString(R.string.value_is_false, text_view_Ft_warning.getText()), Toast.LENGTH_LONG).show();
            text_view_Ft_warning.setText("5mm<= t >=74mm");

        }
    }

    private void updateDistance() {
        String value = editText_distance.getText().toString();
        if (value == null || value.isEmpty()) {
            distance_film_from_source = 0.0;
            Toast.makeText(TimeActivity.this, getResources().getString(R.string.clicked_item, editText_distance.getText()), Toast.LENGTH_SHORT).show();

        } else {
            distance_film_from_source = Double.parseDouble(editText_distance.getText().toString());

            switch (spinner_distance.getSelectedItem().toString()) {
                case "Inch":
                    distance_film_from_source = convert_Inch_to_mm(distance_film_from_source);
                    break;
                case "m":
                    distance_film_from_source = convert_m_to_mm(distance_film_from_source);
                    break;
                case "cm":
                    distance_film_from_source = convert_cm_to_mm(distance_film_from_source);
                    break;

            }

        }

    }

    private void update_FilmFactor_SourceActivity() {
        String value1 = editText_activity.getText().toString();
        String value2 = editText_film_factor.getText().toString();
        if (value1 == null || value1.isEmpty()) {
            distance_film_from_source = 0.0;
        } else {
            sourceActivity = Double.parseDouble(editText_activity.getText().toString());
            //Toast.makeText(TimeActivity.this, getResources().getString(R.string.clicked_item, editText_activity.getText()), Toast.LENGTH_SHORT).show();

        }
        if (value2 == null || value2.isEmpty()) {
            distance_film_from_source = 0.0;
        } else {
            film_factor = Double.parseDouble(editText_film_factor.getText().toString());
            //Toast.makeText(TimeActivity.this, getResources().getString(R.string.clicked_item, editText_film_factor.getText()), Toast.LENGTH_SHORT).show();

        }
    }


    private double convert_Inch_to_mm(double inch) {
        double mm = 0;
        mm = inch * 2.54 * 10;
        return mm;
    }

    private double convert_cm_to_mm(double cm) {
        double mm = 0;
        mm = cm * 10;
        return mm;
    }

    private double convert_m_to_mm(double m) {
        double mm = 0;
        mm = m * 100 * 10;
        return mm;
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
                radiationTime(film_factor, thickness, distance_film_from_source, sourceActivity);
                radiationRealTime(film_factor, (int) hvl_mm, distance_film_from_source, rhm, sourceActivity);
                textView_result_view.setText("Time " + time + "\n RealTime " + realTime);
                break;
            case R.id.start_btn:
                long value = chronometer.getBase();
                value = SystemClock.elapsedRealtime() - value;
                chronometer.setBase(value);
                chronometer.setFormat("MM:SS");
                chronometer.setCountDown(true);
                chronometer.start();
                break;
            case R.id.stop_btn:
                chronometer.stop();
                break;
            case R.id.simpleChronometer:
                chronometer.setBase(SystemClock.elapsedRealtime());
                break;
            case R.id.text_view_ft:
                metal_thickness_factorUpdate();
                break;
            case R.id.text_view_ft_warning:
                text_view_Ft_warning.setText("");
                metal_thickness_factorUpdate();
                break;
        }

    }

    /**
     * update Metal thickness and set it in text view ft
     */
    private void metal_thickness_factorUpdate() {
        updateThickness();
        double var = metal_Thickness_Factor((int) getThickness());
        text_View_Ft.setText("F(" + getThickness() + ") = " + var);
    }

    private void setChronometer(long start, long stop) {
        CountDownTimer cT = new CountDownTimer(start, stop) {
            @Override
            public void onTick(long millisUntilFinished) {
                String v = String.format("%02d", millisUntilFinished / 60000);
                int va = (int) ((millisUntilFinished % 60000) / 1000);
                textView_result_value.setText("seconds remaining: " + v + ":" + String.format("%02d", va));
                System.out.println("seconds remaining: " + v + ":" + String.format("%02d", va));

            }

            @Override
            public void onFinish() {
                textView_result_value.setText("done!");
            }
        };
        cT.start();

    }

    /**
     * @param film_factor
     * @param half_value_layer
     * @param distance_film_from_source
     * @param gamma_Factor_RHM
     * @param sourceActivity
     * @return realTime
     */
    public double radiationRealTime(double film_factor, int half_value_layer, double distance_film_from_source, double gamma_Factor_RHM, double sourceActivity) {
        double res = 0;
        res = ((film_factor * Math.pow(2, 1 / half_value_layer) * Math.pow(distance_film_from_source, 2) * 60) / (gamma_Factor_RHM * sourceActivity * Math.pow(100, 2)));
        time = res;
        return res;
    }

    /**
     * @param ff  Film Factor
     * @param t   Thickness
     * @param sfd Distance of Film from Source
     * @param A   Source Activity
     * @return real radiation time
     */
    public double radiationTime(double ff, double t, double sfd, double A) {
        double res = 0;
        t = metal_Thickness_Factor((int) t);
        res = ff * t * Math.pow(sfd, 2) / A * Math.pow(100, 2);
        realTime = res;
        return res;
    }

    /**
     * Thickness factor
     *
     * @param thickness_in_mm
     * @return f(t)
     */
    private double metal_Thickness_Factor(int thickness_in_mm) {
        int x = thickness_in_mm;
        double res = 0;
        switch (x) {
            case 5:
                res = 5.3;
                break;
            case 6:
                res = 5.5;
                break;
            case 7:
                res = 5.8;
                break;
            case 8:
                res = 6;
                break;
            case 9:
                res = 6.3;
                break;
            case 10:
                res = 6.6;
                break;
            case 11:
                res = 6.9;
                break;
            case 12:
                res = 7.3;
                break;
            case 13:
                res = 7.6;
                break;
            case 14:
                res = 7.9;
                break;
            case 15:
                res = 8.3;
                break;
            case 16:
                res = 8.3;
                break;
            case 17:
                res = 9.1;
                break;
            case 18:
                res = 9.5;
                break;
            case 19:
                res = 10.0;
                break;
            case 20:
                res = 10.4;
                break;
            case 21:
                res = 10.9;
                break;
            case 22:
                res = 11.4;
                break;
            case 23:
                res = 12;
                break;
            case 24:
                res = 12.5;
                break;
            case 25:
                res = 13.1;
                break;
            case 26:
                res = 13.7;
                break;
            case 27:
                res = 14.4;
                break;
            case 28:
                res = 15.0;
                break;
            case 29:
                res = 15.7;
                break;
            case 30:
                res = 16.5;
                break;
            case 31:
                res = 17.2;
                break;
            case 32:
                res = 18;
                break;
            case 33:
                res = 18.9;
                break;
            case 34:
                res = 19.7;
                break;
            case 35:
                res = 20.7;
                break;
            case 36:
                res = 21.6;
                break;
            case 37:
                res = 22.6;
                break;
            case 38:
                res = 23.7;
                break;
            case 39:
                res = 24.8;
                break;
            case 40:
                res = 25.9;
                break;
            case 41:
                res = 27.1;
                break;
            case 42:
                res = 28.4;
                break;
            case 43:
                res = 29.7;
                break;
            case 44:
                res = 31.1;
                break;
            case 45:
                res = 32.6;
                break;
            case 46:
                res = 34.1;
                break;
            case 47:
                res = 35.7;
                break;
            case 48:
                res = 37.3;
                break;
            case 49:
                res = 39.1;
                break;
            case 50:
                res = 40.9;
                break;
            case 51:
                res = 42.8;
                break;
            case 52:
                res = 44.8;
                break;
            case 53:
                res = 46.9;
                break;
            case 54:
                res = 49.0;
                break;
            case 55:
                res = 51.3;
                break;
            case 56:
                res = 53.7;
                break;
            case 57:
                res = 56.2;
                break;
            case 58:
                res = 58.8;
                break;
            case 59:
                res = 61.6;
                break;
            case 60:
                res = 64.4;
                break;

            case 61:
                res = 67.4;
                break;
            case 62:
                res = 70.6;
                break;
            case 63:
                res = 73.8;
                break;
            case 64:
                res = 77.3;
                break;
            case 65:
                res = 80.9;
                break;
            case 66:
                res = 84.6;
                break;
            case 67:
                res = 88.6;
                break;
            case 68:
                res = 92.7;
                break;
            case 69:
                res = 97.0;
                break;
            case 70:
                res = 101.5;
                break;
            case 71:
                res = 106.3;
                break;
            case 72:
                res = 111.2;
                break;
            case 73:
                res = 116.4;
                break;
            case 74:
                res = 121.8;
                break;
            default:
                res = 0.0;
                break;
        }
        return res;
    }


}



