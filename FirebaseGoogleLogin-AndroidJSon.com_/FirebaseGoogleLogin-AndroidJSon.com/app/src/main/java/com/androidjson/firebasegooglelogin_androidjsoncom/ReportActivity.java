package com.androidjson.firebasegooglelogin_androidjsoncom;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.androidjson.firebasegooglelogin_androidjsoncom.client.Report;
import com.androidjson.firebasegooglelogin_androidjsoncom.connection.Client;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.Personal;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.google.api.client.json.Json;
import com.fasterxml.jackson.core.JsonFactory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.fasterxml.jackson.core.JsonParser;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import okhttp3.OkHttpClient;
import okhttp3.WebSocket;

public class ReportActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener {
    // TAG is for show some tag logs in LOG screen.
    public static final String TAG = "ReportActivity";
    private Personal personal;
    private  Gson gson;
    private JsonNode reportJson;
    private WebSocket webSocket;
    private OkHttpClient okHttpClient;
    private Client client;
    private Report report;
    private Spinner spinner_film_name, spinner_film_type, spinner_film_model, spinner_film_length,
            spinner_measureUp1, spinner_measureUp2, spinner_measureUp3, spinner_measureUp4, spinner_measureUp5;
    private EditText editText_size1, editText_size2, editText_size3, editText_size4, editText_size5,
            edit_text_counter1, edit_text_counter2, edit_text_counter3, edit_text_counter4, edit_text_counter5;
    private DatePicker datePicker;
    private Button send_btn;
    private TextView text_date_of_report,text_view_message;
    private double[] size_value;
    private String[] unit_value;
    private double[] counter_value;
    private String reportDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        report = new Report();
        gson = new Gson();
        personal = gson.fromJson(getIntent().getStringExtra("Personal"), Personal.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_layout);
        okHttpClient = new OkHttpClient();
        client = new Client();
        //create connection with Websocket
        webSocket = client.getWebSocket();
        client.getClient();
        bindViews();
        send_btn.setOnClickListener(this);
        datePicker.setOnClickListener(this);


    }


    /**
     * View initializing
     */
    @SuppressLint("ResourceAsColor")
    private void bindViews() {
        //Spinner
        spinner_film_name = (Spinner) findViewById(R.id.spinner_film_name);
        spinner_film_type = (Spinner) findViewById(R.id.spinner_film_type);
        spinner_film_model = (Spinner) findViewById(R.id.spinner_film_model);
        spinner_film_length = (Spinner) findViewById(R.id.spinner_film_length);

        spinner_measureUp1 = (Spinner) findViewById(R.id.spinner_measureUp1);
        spinner_measureUp2 = (Spinner) findViewById(R.id.spinner_measureUp2);
        spinner_measureUp3 = (Spinner) findViewById(R.id.spinner_measureUp3);
        spinner_measureUp4 = (Spinner) findViewById(R.id.spinner_measureUp4);
        spinner_measureUp5 = (Spinner) findViewById(R.id.spinner_measureUp5);
        //Edittext
        editText_size1 = (EditText) findViewById(R.id.size1);
        editText_size2 = (EditText) findViewById(R.id.size2);
        editText_size3 = (EditText) findViewById(R.id.size3);
        editText_size4 = (EditText) findViewById(R.id.size4);
        editText_size5 = (EditText) findViewById(R.id.size5);

        edit_text_counter1 = (EditText) findViewById(R.id.edit_text_counter1);
        edit_text_counter2 = (EditText) findViewById(R.id.edit_text_counter2);
        edit_text_counter3 = (EditText) findViewById(R.id.edit_text_counter3);
        edit_text_counter4 = (EditText) findViewById(R.id.edit_text_counter4);
        edit_text_counter5 = (EditText) findViewById(R.id.edit_text_counter5);
        //DatePicker
        datePicker = (DatePicker) findViewById(R.id.date_picker);
        //Button
        send_btn = (Button) findViewById(R.id.send_btn);
        //TextView
        text_date_of_report = (TextView) findViewById(R.id.text_date_of_report);
        text_view_message=(TextView)findViewById(R.id.text_view_message);


    }

    public void updateSize() {
        size_value = new double[5];
        unit_value = new String[5];
        counter_value = new double[5];

        for (int i = 0; i < size_value.length; i++) {

            switch (i) {
                case 0:
                    String size0 = editText_size1.getText().toString();
                    String counter0 = edit_text_counter1.getText().toString();
                    if (size0 == null || size0.isEmpty() && counter0 == null || counter0.isEmpty()) {
                        size_value[0] = 0.0;
                        unit_value[0] = "?";
                        counter_value[0] = 0.0;

                    } else {
                        size_value[0] = Double.parseDouble(editText_size1.getText().toString());
                        unit_value[0] = spinner_measureUp1.getSelectedItem().toString();
                        counter_value[0] = Double.parseDouble(edit_text_counter1.getText().toString());
                        if (Double.parseDouble(editText_size1.getText().toString()) < 0 || Double.parseDouble(edit_text_counter1.getText().toString()) < 0) {
                            size_value[0] = 0.0;
                            counter_value[0] = 0.0;
                        }

                    }
                    break;
                case 1:
                    String size1 = editText_size2.getText().toString();
                    String counter1 = edit_text_counter2.getText().toString();
                    if (size1 == null || size1.isEmpty() && counter1 == null || counter1.isEmpty()) {
                        size_value[1] = 0.0;
                        unit_value[1] = "?";
                        counter_value[1] = 0.0;

                    } else {
                        size_value[1] = Double.parseDouble(editText_size2.getText().toString());
                        unit_value[1] = spinner_measureUp2.getSelectedItem().toString();
                        counter_value[1] = Double.parseDouble(edit_text_counter2.getText().toString());
                        if (Double.parseDouble(editText_size2.getText().toString()) < 0 || Double.parseDouble(edit_text_counter2.getText().toString()) < 0) {
                            size_value[1] = 0.0;
                            counter_value[1] = 0.0;
                        }
                    }
                    break;
                case 2:
                    String size2 = editText_size3.getText().toString();
                    String counter2 = edit_text_counter3.getText().toString();
                    if (size2 == null || size2.isEmpty() && counter2 == null || counter2.isEmpty()) {
                        size_value[2] = 0.0;
                        unit_value[2] = "?";
                        counter_value[2] = 0.0;
                    } else {
                        size_value[2] = Double.parseDouble(editText_size3.getText().toString());
                        unit_value[2] = spinner_measureUp3.getSelectedItem().toString();
                        counter_value[2] = Double.parseDouble(edit_text_counter3.getText().toString());
                        if (Double.parseDouble(editText_size3.getText().toString()) < 0 || Double.parseDouble(edit_text_counter3.getText().toString()) < 0) {
                            size_value[2] = 0.0;
                            counter_value[2] = 0.0;
                        }
                    }
                    break;
                case 3:
                    String size3 = editText_size4.getText().toString();
                    String counter3 = edit_text_counter4.getText().toString();
                    if (size3 == null || size3.isEmpty() && counter3 == null || counter3.isEmpty()) {
                        size_value[3] = 0.0;
                        unit_value[3] = "?";
                        counter_value[3] = 0.0;
                    } else {
                        size_value[3] = Double.parseDouble(editText_size4.getText().toString());
                        unit_value[3] = spinner_measureUp4.getSelectedItem().toString();
                        counter_value[3] = Double.parseDouble(edit_text_counter4.getText().toString());
                        if (Double.parseDouble(editText_size4.getText().toString()) < 0 || Double.parseDouble(edit_text_counter4.getText().toString()) < 0) {
                            size_value[3] = 0.0;
                            counter_value[3] = 0.0;
                        }
                    }
                    break;
                case 4:
                    String size4 = editText_size5.getText().toString();
                    String counter4 = edit_text_counter5.getText().toString();
                    if (size4 == null || size4.isEmpty() && counter4 == null || counter4.isEmpty()) {
                        size_value[4] = 0.0;
                        unit_value[4] = "?";
                        counter_value[4] = 0.0;
                    } else {
                        size_value[4] = Double.parseDouble(editText_size5.getText().toString());
                        unit_value[4] = spinner_measureUp5.getSelectedItem().toString();
                        counter_value[4] = Double.parseDouble(edit_text_counter5.getText().toString());
                        if (Double.parseDouble(editText_size5.getText().toString()) < 0 || Double.parseDouble(edit_text_counter5.getText().toString()) < 0) {
                            size_value[4] = 0.0;
                            counter_value[4] = 0.0;
                            Toast.makeText(ReportActivity.this, getResources().getString(R.string.value_is_false, edit_text_counter5.getText()), Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
            }

        }
    }


    /**
     * @param view       the picker associated with the dialog
     * @param year       the selected year
     * @param month      the selected month (0-11 for compatibility with
     *                   {@link Calendar#MONTH})
     * @param dayOfMonth th selected day of the month (1-31, depending on
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, final int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.DEFAULT).format(calendar.getTime());
        text_date_of_report.setText(" Date of Report : " + currentDateString);
        reportDate = currentDateString;
        text_date_of_report.setTextSize(20);
        datePicker.setVisibility(View.GONE);
        text_date_of_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.setVisibility(View.VISIBLE);
                send_btn.setVisibility(View.VISIBLE);
                text_view_message.setVisibility(View.GONE);
            }
        });
    }

    public void updateReport() {
        Report report_value = new Report();
        updateSize();
        // Array of integers points to images stored in /res/drawable-ldpi/
        String[] film_info = new String[]{
                spinner_film_name.getSelectedItem().toString(),
                spinner_film_type.getSelectedItem().toString(),
                spinner_film_model.getSelectedItem().toString(),
                spinner_film_length.getSelectedItem().toString(),
        };

        for (int i = 0; i < 5; i++) {
            report_value.setSizes(i, (int) size_value[i]);
            report_value.setSizeUnits(i, unit_value[i]);
            report_value.setCounter(i, (int) counter_value[i]);
            if(i!=4){
                report_value.setFilmInfos(i,film_info[i]);
            }

        }
        report_value.setDateOfReport(reportDate);
        this.report = report_value;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_btn:
                updateReport();
                report.creatId();
                System.out.println(report);
                send_btn.setVisibility(View.GONE);
                text_view_message.setVisibility(View.VISIBLE);
                text_view_message.setText("The following message sent to server"+ "\n" + report.toString() +"");
                sendMessageToServer();
                break;
            case R.id.date_picker:
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "DatePicker");
                break;

        }

    }

    /**
     * send report Message to Server
     */
    public void sendMessageToServer(){
        String myJson=gson.toJson(report);
        client.getWebSocket().send(myJson);

        //just for Test
        ObjectMapper mapper=new ObjectMapper();
        JsonFactory factory=mapper.getJsonFactory();
        try {
            JsonParser jp=factory.createJsonParser(gson.toJson(report.toString()));
            JsonNode jsonNode=mapper.readTree(jp);
            //client.getWebSocket().send(jsonNode.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}



