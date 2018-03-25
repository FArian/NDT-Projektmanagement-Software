package com.androidjson.firebasegooglelogin_androidjsoncom.hps.activitys;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.androidjson.firebasegooglelogin_androidjsoncom.MainActivity;
import com.androidjson.firebasegooglelogin_androidjsoncom.R;
import com.androidjson.firebasegooglelogin_androidjsoncom.client.Item;
import com.androidjson.firebasegooglelogin_androidjsoncom.client.RecyclerViewAdapter;
import com.androidjson.firebasegooglelogin_androidjsoncom.client.RecyclerViewItemClickListener;
import com.androidjson.firebasegooglelogin_androidjsoncom.connection.Client;
import com.androidjson.firebasegooglelogin_androidjsoncom.json.ToJson;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.DatePickerFragment;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.DateSettings;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.Personal;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.RT.Film;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.RT.Isotope;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.RT.RT_Camera;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.Team;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.dosimeter.FilmBadge;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.dosimeter.GeigerAlarm;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.dosimeter.PocketDosimeter;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.dosimeter.Radiometer;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.dosimeter.TLD;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.enums.ISOTOPETYPE;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.enums.MODEL;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.enums.NAME;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.enums.SIZE;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.enums.TYPE;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.material.EmergencyStorageContainer;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.material.HandlingTongs;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.material.IQI;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.material.LeadAron;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.material.RadiationSigns;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.processing.Developer;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.processing.Fixer;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateMaterialActivity extends FragmentActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Item> items;
    private ImageView user_pic;
    private int RED, GREEN, BLACK, WHITE, CURRENTCOLOR, day, month, year;
    private Personal personal;
    private Button create_btn, calibration_date_btn, expire_date_btn, set_date;
    private Spinner spinner_name, spinner_type, spinner_model, spinner_size, spinner_activity_units, spinner_isotope,
            spinner_dose_units, spinner_length_units, spinner_iqi_pos1, spinner_iqi_pos2;
    private EditText editText_value_dose, editText_value_activity, edit_text_name,
            edit_text_serial_number, edit_text_length, editText_iqi_pos0, editText_iqi_pos3, editText_iqi_pos4;
    private TextView TEXTVIEW_title, textView_date, textView_expire_date;
    private TableRow row_name, row_type, row_model, row_size, row_activity,
            row_calibration_date, row_expire_date, row_dose, row_isotope, row_set_date, row_info, row_length, row_iqi;
    private String GETTEXT, reason_text, need_text, message_text;
    private Gson gson;
    private Client client;
    private String receiveMessage;
    private String currentDate;
    private String expireDate;
    private DatePicker datePicker;
    private FilmBadge filmBadge = new FilmBadge();
    private TLD tld = new TLD();
    private PocketDosimeter dosimeter = new PocketDosimeter();
    private GeigerAlarm geiger = new GeigerAlarm();
    private Radiometer radiometer = new Radiometer();
    private LeadAron leadAron = new LeadAron();
    private RadiationSigns radiationSigns = new RadiationSigns();
    private HandlingTongs handlingTongs = new HandlingTongs();
    private EmergencyStorageContainer container = new EmergencyStorageContainer();
    private Isotope isotope_none = new Isotope(ISOTOPETYPE.OTHER, -1);
    private RT_Camera camera = new RT_Camera(NAME.OTHER, MODEL.OTHER, isotope_none);
    private Film film = new Film(NAME.OTHER, TYPE.OTHER, MODEL.OTHER, SIZE.OTHER);
    private IQI iqi = new IQI();
    private Developer developer = new Developer(NAME.OTHER, MODEL.OTHER, SIZE.OTHER);
    private Fixer fixer = new Fixer(NAME.OTHER, MODEL.OTHER, SIZE.OTHER);
    // TAG is for show some tag logs in LOG screen.
    private static final String TAG = "CreateMaterialActivity";
    private DateSettings dateSettings;
    static final int DATE_DIALOG_ID = 999;
    //JsonNode personalJson = Json.toJson(rt_camera_logo.toString());
    private JsonNode jsonNode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RED = R.color.colorRed;
        GREEN = R.color.easyfei_green;
        BLACK = R.color.album_text_color;
        WHITE = R.color.white;
        dateSettings = new DateSettings(getApplicationContext());
        gson = new Gson();
        client = MainActivity.getClientCustom();
        personal = gson.fromJson(getIntent().getStringExtra("Personal"), Personal.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        bindViews();
        //Create model list
        items = new ArrayList<>();

        items.add(new Item("FILMBADGE", " FILMBADGE,S", GETTEXT, R.drawable.film_badge_min));
        items.add(new Item("TLD", " TLD,S", GETTEXT, R.drawable.tld_badge_min));
        items.add(new Item("DOSIMETER", " POCKET DOSIMETER,S ", GETTEXT, R.drawable.dosimeters_min));
        items.add(new Item("GEIGER", " GEIGER ALARMS", GETTEXT, R.drawable.geiger_min));

        items.add(new Item("RADIOMETER", " RADIOMETER,S", GETTEXT, R.drawable.radiometer_min));
        items.add(new Item("LEAD ARON", " LEAD ARON,S", GETTEXT, R.drawable.lead_aron2));
        items.add(new Item("RADIATION SINGS", " RADIATION SINGS,S", GETTEXT, R.drawable.radiation_sings2));

        items.add(new Item("HANDLING TONGS", " HANDLING TONGS,S", GETTEXT, R.drawable.handling_tongs));
        items.add(new Item("EMERGENCY STORAGE CONTAINER", " EMERGENCY STORAGE CONTAINER,S", GETTEXT, R.drawable.emergency_storage_container_min));

        items.add(new Item("RT CAMERA", " RT CAMERA,S", GETTEXT, R.drawable.rt_camera_min));
        items.add(new Item("RT FILM", " RT FILM,S ", GETTEXT, R.drawable.agfa_film_min));
        items.add(new Item("IQI", " IQI,S", GETTEXT, R.drawable.iqi));
        items.add(new Item("FILM DEVELOPER", " FILM DEVELOPER,S", GETTEXT, R.drawable.developer_min));
        items.add(new Item("FILM FIXER", " FILM FIXER,S", GETTEXT, R.drawable.fixer_min));


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Create an adapter
        adapter = new RecyclerViewAdapter(CreateMaterialActivity.this, items);

        //Create custom interface object and send it to adapter
        //Adapter trigger it when any item view is clicked
        adapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showEditDialog(position);
                TEXTVIEW_title.setText(items.get(position).getInfo());
                Toast.makeText(CreateMaterialActivity.this, getResources().getString(R.string.clicked_item, items.get(position).getInfo()), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                showEditDialog(position);

            }
        });

        //Set adapter to recyclerview
        recyclerView.setAdapter(adapter);


    }

    public void setDate(View view) {
        DatePickerFragment pickerFragment = new DatePickerFragment();
        pickerFragment.show(getSupportFragmentManager(), "Date_Picker");
    }


    /**
     * View initializing
     */
    private void bindViews() {
        //Bind recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


    }
    //-------------------------------------edit_text---------------------------

    private void showEditDialog(final int position) {
        final Dialog dialog = new Dialog(CreateMaterialActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.edit_dialog_material_create);

        spinner_name = (Spinner) dialog.findViewById(R.id.spinner_name);
        spinner_type = (Spinner) dialog.findViewById(R.id.spinner_type);
        spinner_model = (Spinner) dialog.findViewById(R.id.spinner_model);
        spinner_size = (Spinner) dialog.findViewById(R.id.spinner_size);
        spinner_dose_units = (Spinner) dialog.findViewById(R.id.spinner_dose_units);
        spinner_length_units = (Spinner) dialog.findViewById(R.id.spinner_film_length);
        spinner_iqi_pos1 = (Spinner) dialog.findViewById(R.id.spinner_iqi_pos1);
        spinner_iqi_pos2 = (Spinner) dialog.findViewById(R.id.spinner_iqi_pos2);

        spinner_activity_units = (Spinner) dialog.findViewById(R.id.spinner_activity_units);
        spinner_isotope = (Spinner) dialog.findViewById(R.id.spinner_isotope);

        create_btn = (Button) dialog.findViewById(R.id.creat_btn);
        calibration_date_btn = (Button) dialog.findViewById(R.id.set_date_info1_btn);
        expire_date_btn = (Button) dialog.findViewById(R.id.expire_date_info2_btn);
        set_date = (Button) dialog.findViewById(R.id.set_date_btn);

        TEXTVIEW_title = (TextView) dialog.findViewById(R.id.title);
        textView_date = (TextView) dialog.findViewById(R.id.text_date);
        textView_expire_date = (TextView) dialog.findViewById(R.id.text_expire_date);

        row_dose = (TableRow) dialog.findViewById(R.id.row_dose);
        row_name = (TableRow) dialog.findViewById(R.id.row_name);
        row_type = (TableRow) dialog.findViewById(R.id.row_type);
        row_model = (TableRow) dialog.findViewById(R.id.row_model);
        row_size = (TableRow) dialog.findViewById(R.id.row_size);
        row_activity = (TableRow) dialog.findViewById(R.id.row_activity);
        row_set_date = (TableRow) dialog.findViewById(R.id.date);
        row_calibration_date = (TableRow) dialog.findViewById(R.id.row_calibration_date);
        row_expire_date = (TableRow) dialog.findViewById(R.id.row_expire_date);
        row_isotope = (TableRow) dialog.findViewById(R.id.row_isotope);
        row_info = (TableRow) dialog.findViewById(R.id.row_info);
        row_length = (TableRow) dialog.findViewById(R.id.row_length);
        row_iqi = (TableRow) dialog.findViewById(R.id.row_iqi);


        editText_value_dose = (EditText) dialog.findViewById(R.id.value_dose);
        editText_value_activity = (EditText) dialog.findViewById(R.id.value_activity);
        edit_text_name = (EditText) dialog.findViewById(R.id.edit_text_name);
        edit_text_serial_number = (EditText) dialog.findViewById(R.id.edit_text_serial_number);
        edit_text_length = (EditText) dialog.findViewById(R.id.edit_value_length);
        editText_iqi_pos0 = (EditText) dialog.findViewById(R.id.edit_text_iqi_pos0);
        editText_iqi_pos3 = (EditText) dialog.findViewById(R.id.edit_text_iqi_pos3);
        editText_iqi_pos4 = (EditText) dialog.findViewById(R.id.edit_text_iqi_pos4);


        datePicker = (DatePicker) dialog.findViewById(R.id.date_picker);
        spinner_name.setAdapter(new ArrayAdapter<NAME>(this, android.R.layout.simple_spinner_item, NAME.values()));
        spinner_type.setAdapter(new ArrayAdapter<TYPE>(this, android.R.layout.simple_spinner_item, TYPE.values()));
        spinner_model.setAdapter(new ArrayAdapter<MODEL>(this, android.R.layout.simple_spinner_item, MODEL.values()));
        spinner_size.setAdapter(new ArrayAdapter<SIZE>(this, android.R.layout.simple_spinner_item, SIZE.values()));
        spinner_isotope.setAdapter(new ArrayAdapter<ISOTOPETYPE>(this, android.R.layout.simple_spinner_item, ISOTOPETYPE.values()));
        changeVisibilityOfObject(items.get(position).getType());

        //Set clicked to value edittext
        //value_edittext.setText(items.get(position).getValue());
        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (items.get(position).getType()) {

                    case "FILMBADGE":

                        String filmBadge_name = edit_text_name.getText().toString();
                        String filmBadge_serialNumber = edit_text_serial_number.getText().toString();

                        if (filmBadge_name == null || filmBadge_name.isEmpty() || filmBadge_serialNumber == null || filmBadge_serialNumber.isEmpty()) {
                            Toast.makeText(CreateMaterialActivity.this, "value is empty", Toast.LENGTH_SHORT).show();
                        } else {
                            filmBadge.setName(filmBadge_name);
                            filmBadge.setSerialNumber(filmBadge_serialNumber);
                        }
                        adapter.notifyDataSetChanged();
                        //Close dialog
                        dialog.dismiss();
                        /**
                         * send object to Server
                         */
                        String message = ToJson.sendObjectWithRoot("Create FILMBADGE", filmBadge);
                        client.getWebSocket().send(message);
                        break;
                    case "TLD":
                        String tld_name = edit_text_name.getText().toString();
                        String tld_serialNumber = edit_text_serial_number.getText().toString();

                        if (tld_name == null || tld_name.isEmpty() || tld_serialNumber == null || tld_serialNumber.isEmpty()) {
                            Toast.makeText(CreateMaterialActivity.this, "value is empty", Toast.LENGTH_SHORT).show();
                        } else {
                            //String spinnerValue = spinner_dose_units.getSelectedItem().toString();
                            //tld_value = String.valueOf(Double.parseDouble(editText_value_dose.getText().toString()));
                            //tld.setValue(tld_value + " " + spinnerValue);
                            tld.setName(tld_name);
                            tld.setSerialNumber(tld_serialNumber);
                        }
                        adapter.notifyDataSetChanged();
                        //Close dialog
                        dialog.dismiss();

                        /**
                         * send object to Server
                         */
                        client.getWebSocket().send(ToJson.sendObjectWithRoot("Create TLD", tld));
                        break;
                    case "DOSIMETER":
                        String dosimeter_name = edit_text_name.getText().toString();
                        String dosimeter_serialNumber = edit_text_serial_number.getText().toString();
                        if (dosimeter_name == null || dosimeter_name.isEmpty() || dosimeter_serialNumber == null || dosimeter_serialNumber.isEmpty()) {
                            Toast.makeText(CreateMaterialActivity.this, "value is empty", Toast.LENGTH_SHORT).show();
                        } else {
                            dosimeter.setName(dosimeter_name);
                            dosimeter.setSerialNumber(dosimeter_serialNumber);
                        }
                        adapter.notifyDataSetChanged();
                        //Close dialog
                        dialog.dismiss();
                        /**
                         * send object to Server
                         */
                        client.getWebSocket().send(ToJson.sendObjectWithRoot("Create DOSIMETER", dosimeter));
                        break;
                    case "GEIGER":
                        String geiger_name = edit_text_name.getText().toString();
                        String geiger_serialNumber = edit_text_serial_number.getText().toString();
                        if (geiger_name == null || geiger_name.isEmpty() || geiger_serialNumber == null || geiger_serialNumber.isEmpty()) {
                            Toast.makeText(CreateMaterialActivity.this, "value is empty", Toast.LENGTH_SHORT).show();
                        } else {
                            geiger.setName(geiger_name);
                            geiger.setSerialNumber(geiger_serialNumber);
                        }
                        adapter.notifyDataSetChanged();
                        //Close dialog
                        dialog.dismiss();
                        /**
                         * send object to Server
                         */
                        client.getWebSocket().send(ToJson.sendObjectWithRoot("Create GEIGER", geiger));
                        break;
                    case "RADIOMETER":
                        String radiometer_name = edit_text_name.getText().toString();
                        String radiometer_serialNumber = edit_text_serial_number.getText().toString();
                        if (radiometer_name == null || radiometer_name.isEmpty() || radiometer_serialNumber == null || radiometer_serialNumber.isEmpty()) {
                            Toast.makeText(CreateMaterialActivity.this, "value is empty", Toast.LENGTH_SHORT).show();
                        } else {
                            radiometer.setName(radiometer_name);
                            radiometer.setSerialNumber(radiometer_serialNumber);
                        }
                        adapter.notifyDataSetChanged();
                        //Close dialog
                        dialog.dismiss();
                        /**
                         * send object to Server
                         */
                        client.getWebSocket().send(ToJson.sendObjectWithRoot("Create RADIOMETER", radiometer));

                        break;
                    case "LEAD ARON":
                        String leadAron_name = edit_text_name.getText().toString();

                        if (leadAron_name == null || leadAron_name.isEmpty()) {
                            Toast.makeText(CreateMaterialActivity.this, "value is empty", Toast.LENGTH_SHORT).show();
                        } else {
                            leadAron.setName(leadAron_name);
                        }
                        adapter.notifyDataSetChanged();
                        //Close dialog
                        dialog.dismiss();
                        /**
                         * send object to Server
                         */
                        client.getWebSocket().send(ToJson.sendObjectWithRoot("Create LEAD ARON", leadAron));
                        break;
                    case "RADIATION SINGS":
                        String radiationSigns_name = edit_text_name.getText().toString();

                        if (radiationSigns_name == null || radiationSigns_name.isEmpty()) {
                            Toast.makeText(CreateMaterialActivity.this, "value is empty", Toast.LENGTH_SHORT).show();
                        } else {
                            radiationSigns.setName(radiationSigns_name);
                        }
                        adapter.notifyDataSetChanged();
                        //Close dialog
                        dialog.dismiss();
                        /**
                         * send object to Server
                         */
                        client.getWebSocket().send(ToJson.sendObjectWithRoot("Create RADIATION SINGS", radiationSigns));
                        break;
                    case "HANDLING TONGS":
                        String handlingTongs_name = edit_text_name.getText().toString();
                        String handlingTongs_length = edit_text_length.getText().toString();
                        if (handlingTongs_length == null || handlingTongs_length.isEmpty()) {
                            Toast.makeText(CreateMaterialActivity.this, "value is empty", Toast.LENGTH_SHORT).show();
                        } else {
                            double var = Double.parseDouble(edit_text_length.getText().toString());
                            String varUnti = spinner_length_units.getSelectedItem().toString();
                            handlingTongs.setLength(var);
                            handlingTongs.setModel(var + " " + varUnti);
                        }

                        if (handlingTongs_name == null || handlingTongs_name.isEmpty()) {
                            Toast.makeText(CreateMaterialActivity.this, "value is empty", Toast.LENGTH_SHORT).show();
                        } else {
                            handlingTongs.setName(handlingTongs_name);
                            String model = handlingTongs.getModel();
                            handlingTongs.setModel(handlingTongs.getName() + " " + model);

                        }
                        adapter.notifyDataSetChanged();
                        //Close dialog
                        dialog.dismiss();
                        /**
                         * send object to Server
                         */
                        client.getWebSocket().send(ToJson.sendObjectWithRoot("Create HANDLING TONGS", handlingTongs));
                        break;
                    case "EMERGENCY STORAGE CONTAINER":
                        String container_name = edit_text_name.getText().toString();

                        if (container_name == null || container_name.isEmpty()) {
                            Toast.makeText(CreateMaterialActivity.this, "value is empty", Toast.LENGTH_SHORT).show();
                        } else {
                            container.setName(container_name);
                        }
                        adapter.notifyDataSetChanged();
                        //Close dialog
                        dialog.dismiss();
                        /**
                         * send object to Server
                         */
                        client.getWebSocket().send(ToJson.sendObjectWithRoot("Create EMERGENCY STORAGE CONTAINER", container));
                        break;
                    case "RT CAMERA":
                        String camera_name = spinner_name.getSelectedItem().toString();
                        String camera_model = spinner_model.getSelectedItem().toString();
                        String isotope_type = spinner_isotope.getSelectedItem().toString();
                        String activityText = editText_value_activity.getText().toString();

                        if (activityText == null || activityText.isEmpty()) {
                            Toast.makeText(CreateMaterialActivity.this, "value is empty", Toast.LENGTH_SHORT).show();
                        } else {
                            double activity = Double.parseDouble(editText_value_activity.getText().toString());
                            isotope_none.setActivity(activity);
                        }
                        camera.setName(NAME.valueOf(camera_name));
                        camera.setModel(MODEL.valueOf(camera_model));
                        isotope_none.setIsotopetype(ISOTOPETYPE.valueOf(isotope_type));
                        camera.setIsotope(isotope_none);
                        /**
                         * update camera data and isotope
                         */
                        isotope_none.updateIsotope();
                        camera.updateCamera();
                        adapter.notifyDataSetChanged();
                        //Close dialog
                        dialog.dismiss();
                        /**
                         * send object to Server
                         */
                        client.getWebSocket().send(ToJson.sendObjectWithRoot("Create RT CAMERA", camera));
                        break;
                    case "RT FILM":
                        String film_name = spinner_name.getSelectedItem().toString();
                        String film_type = spinner_type.getSelectedItem().toString();
                        String film_model = spinner_model.getSelectedItem().toString();
                        String film_size = spinner_size.getSelectedItem().toString();
                        String film_isotope = spinner_isotope.getSelectedItem().toString();
                        film.setFilmName(NAME.valueOf(film_name));
                        film.setType(TYPE.valueOf(film_type));
                        film.setModel(MODEL.valueOf(film_model));
                        film.setSize(SIZE.valueOf(film_size));
                        film.setIsotopetype(ISOTOPETYPE.valueOf(film_isotope));
                        film.updateFilm();
                        adapter.notifyDataSetChanged();
                        //Close dialog
                        dialog.dismiss();
                        client.getWebSocket().send(ToJson.sendObjectWithRoot("Create RT FILM", film));
                        break;
                    case "IQI":
                        final String iqi_pos0 = editText_iqi_pos0.getText().toString();
                        final String iqi_pos1 = spinner_iqi_pos1.getSelectedItem().toString();
                        final String iqi_pos2 = spinner_iqi_pos2.getSelectedItem().toString();
                        final String iqi_pos3 = editText_iqi_pos3.getText().toString();
                        final String iqi_pos4 = editText_iqi_pos4.getText().toString();
                        if (iqi_pos0 == null || iqi_pos0.isEmpty() || iqi_pos3 == null || iqi_pos3.isEmpty() || iqi_pos4 == null || iqi_pos4.isEmpty()) {
                            Toast.makeText(CreateMaterialActivity.this, "value is empty", Toast.LENGTH_SHORT).show();
                        } else {
                            int pos0 = Integer.parseInt(editText_iqi_pos0.getText().toString());
                            int pos3 = Integer.parseInt(editText_iqi_pos3.getText().toString());
                            int pos4 = Integer.parseInt(editText_iqi_pos4.getText().toString());
                            iqi.set_IQI_Model(pos0, iqi_pos1, iqi_pos2, pos3);
                            iqi.setCounterOfIqi(pos4);
                        }
                        String iqi_name = edit_text_name.getText().toString();

                        if (iqi_name == null || iqi_name.isEmpty()) {
                            Toast.makeText(CreateMaterialActivity.this, "value is empty", Toast.LENGTH_SHORT).show();
                        } else {
                            iqi.setName(iqi_name);
                        }

                        adapter.notifyDataSetChanged();
                        //Close dialog
                        dialog.dismiss();

                        client.getWebSocket().send(ToJson.sendObjectWithRoot("Create IQI", iqi));
                        break;
                    case "FILM DEVELOPER":
                        String developer_name = spinner_name.getSelectedItem().toString();
                        String developer_model = spinner_model.getSelectedItem().toString();
                        String developer_size = spinner_size.getSelectedItem().toString();
                        developer.setName(NAME.valueOf(developer_name));
                        developer.setModel(MODEL.valueOf(developer_model));
                        developer.setSize(SIZE.valueOf(developer_size));
                        adapter.notifyDataSetChanged();
                        //Close dialog
                        dialog.dismiss();
                        client.getWebSocket().send(ToJson.sendObjectWithRoot("Create FILM DEVELOPER", developer));
                        break;
                    case "FILM FIXER":
                        String fixer_name = spinner_name.getSelectedItem().toString();
                        String fixer_model = spinner_model.getSelectedItem().toString();
                        String fixer_size = spinner_size.getSelectedItem().toString();
                        fixer.setName(NAME.valueOf(fixer_name));
                        fixer.setModel(MODEL.valueOf(fixer_model));
                        fixer.setSize(SIZE.valueOf(fixer_size));
                        adapter.notifyDataSetChanged();
                        //Close dialog
                        dialog.dismiss();
                        client.getWebSocket().send(ToJson.sendObjectWithRoot("Create FILM FIXER", fixer));
                        break;
                }
            }
        });
        dialog.show();
    }

    public void changeVisibilityOfObject(String type) {
        /**
         * change visibility edit_dialog and set some Text for object
         */
        switch (type) {
            case "FILMBADGE":
                setCurrentDateOnView();
                final FilmBadge filmBadge_values = new FilmBadge();
                row_info.setVisibility(View.VISIBLE);
                row_calibration_date.setVisibility(View.VISIBLE);
                row_expire_date.setVisibility(View.VISIBLE);
                row_set_date.setVisibility(View.VISIBLE);
                set_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DATE_DIALOG_ID);
                    }
                });
                /**
                 * calibration date to set
                 */
                calibration_date_btn.setText("SET CALIBRATION DATE");
                calibration_date_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView_date.setText(getCurrentDate());
                        adapter.notifyDataSetChanged();
                        filmBadge_values.setCalibrationDate(getCurrentDate());
                        filmBadge_values.setCalibration(true);
                        calibration_date_btn.setText(" CALIBRATION DATE");
                        calibration_date_btn.setTextColor(WHITE);

                    }
                });
                /**
                 * calibration expire date to set
                 */
                expire_date_btn.setText(" SET EXPIRE CALIBRATION DATE ");
                expire_date_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView_expire_date.setText(getCurrentDate());
                        filmBadge_values.setCalibrationExpire(getCurrentDate());
                        expire_date_btn.setTextColor(WHITE);
                        expire_date_btn.setText(" EXPIRE CALIBRATION DATE ");
                        adapter.notifyDataSetChanged();

                    }
                });
                this.filmBadge = filmBadge_values;
                break;
            case "TLD":
                setCurrentDateOnView();
                final TLD tld = new TLD();
                row_info.setVisibility(View.VISIBLE);
                row_calibration_date.setVisibility(View.VISIBLE);
                row_expire_date.setVisibility(View.VISIBLE);
                row_set_date.setVisibility(View.VISIBLE);
                set_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DATE_DIALOG_ID);
                    }
                });
                calibration_date_btn.setText(" SET CALIBRATION DATE ");
                calibration_date_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView_date.setText(getCurrentDate());
                        adapter.notifyDataSetChanged();
                        tld.setCalibrationDate(getCurrentDate());
                        tld.setCalibration(true);
                        calibration_date_btn.setText("CALIBRATION DATE ");
                        calibration_date_btn.setTextColor(WHITE);

                    }
                });
                expire_date_btn.setText("SET EXPIRE CALIBRATION DATE ");
                expire_date_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView_expire_date.setText(getCurrentDate());
                        tld.setCalibrationExpire(getCurrentDate());
                        expire_date_btn.setTextColor(WHITE);
                        expire_date_btn.setText("EXPIRE CALIBRATION DATE ");
                        adapter.notifyDataSetChanged();
                    }
                });

                this.tld = tld;
                break;
            case "DOSIMETER":
                final PocketDosimeter dosimeter = new PocketDosimeter();
                setCurrentDateOnView();
                row_info.setVisibility(View.VISIBLE);
                row_calibration_date.setVisibility(View.VISIBLE);
                row_expire_date.setVisibility(View.VISIBLE);
                row_set_date.setVisibility(View.VISIBLE);
                set_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DATE_DIALOG_ID);
                    }
                });
                calibration_date_btn.setText(" SET CALIBRATION DATE ");
                calibration_date_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView_date.setText(getCurrentDate());
                        adapter.notifyDataSetChanged();
                        dosimeter.setCalibrationDate(getCurrentDate());
                        dosimeter.setCalibration(true);
                        calibration_date_btn.setText("CALIBRATION DATE ");
                        calibration_date_btn.setTextColor(WHITE);
                    }
                });
                expire_date_btn.setText("SET EXPIRE CALIBRATION DATE ");
                expire_date_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView_expire_date.setText(getCurrentDate());
                        dosimeter.setCalibrationExpire(getCurrentDate());
                        expire_date_btn.setTextColor(WHITE);
                        expire_date_btn.setText("EXPIRE CALIBRATION DATE ");
                        adapter.notifyDataSetChanged();
                    }
                });
                this.dosimeter = dosimeter;
                break;
            case "GEIGER":
                final GeigerAlarm geiger = new GeigerAlarm();
                setCurrentDateOnView();
                row_info.setVisibility(View.VISIBLE);
                row_calibration_date.setVisibility(View.VISIBLE);
                row_expire_date.setVisibility(View.VISIBLE);
                row_set_date.setVisibility(View.VISIBLE);
                set_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DATE_DIALOG_ID);
                    }
                });
                calibration_date_btn.setText(" SET CALIBRATION DATE ");
                calibration_date_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView_date.setText(getCurrentDate());
                        adapter.notifyDataSetChanged();
                        geiger.setCalibrationDate(getCurrentDate());
                        geiger.setCalibration(true);
                        calibration_date_btn.setText("CALIBRATION DATE ");
                        calibration_date_btn.setTextColor(WHITE);
                    }
                });
                expire_date_btn.setText("SET EXPIRE CALIBRATION DATE ");
                expire_date_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView_expire_date.setText(getCurrentDate());
                        geiger.setCalibrationExpire(getCurrentDate());
                        expire_date_btn.setTextColor(WHITE);
                        expire_date_btn.setText("EXPIRE CALIBRATION DATE ");
                        adapter.notifyDataSetChanged();
                    }
                });
                this.geiger = geiger;
                break;
            case "RADIOMETER":
                final Radiometer radiometer = new Radiometer();
                setCurrentDateOnView();
                row_info.setVisibility(View.VISIBLE);
                row_calibration_date.setVisibility(View.VISIBLE);
                row_expire_date.setVisibility(View.VISIBLE);
                row_set_date.setVisibility(View.VISIBLE);
                set_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DATE_DIALOG_ID);
                    }
                });
                calibration_date_btn.setText(" SET CALIBRATION DATE ");
                calibration_date_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView_date.setText(getCurrentDate());
                        adapter.notifyDataSetChanged();
                        radiometer.setCalibrationDate(getCurrentDate());
                        radiometer.setCalibration(true);
                        calibration_date_btn.setText("CALIBRATION DATE ");
                        calibration_date_btn.setTextColor(WHITE);
                    }
                });
                expire_date_btn.setText("SET EXPIRE CALIBRATION DATE ");
                expire_date_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView_expire_date.setText(getCurrentDate());
                        radiometer.setCalibrationExpire(getCurrentDate());
                        expire_date_btn.setTextColor(WHITE);
                        expire_date_btn.setText("EXPIRE CALIBRATION DATE ");
                        adapter.notifyDataSetChanged();
                    }
                });
                this.radiometer = radiometer;
                break;
            case "LEAD ARON":
                row_info.setVisibility(View.VISIBLE);
                edit_text_serial_number.setVisibility(View.GONE);
                break;
            case "RADIATION SINGS":
                row_info.setVisibility(View.VISIBLE);
                edit_text_serial_number.setVisibility(View.GONE);
                break;
            case "HANDLING TONGS":
                row_info.setVisibility(View.VISIBLE);
                row_length.setVisibility(View.VISIBLE);
                edit_text_serial_number.setVisibility(View.GONE);
                break;
            case "EMERGENCY STORAGE CONTAINER":
                row_info.setVisibility(View.VISIBLE);
                break;
            case "RT CAMERA":
                setCurrentDateOnView();
                row_name.setVisibility(View.VISIBLE);
                row_model.setVisibility(View.VISIBLE);
                row_isotope.setVisibility(View.VISIBLE);
                row_activity.setVisibility(View.VISIBLE);
                row_calibration_date.setVisibility(View.VISIBLE);
                row_set_date.setVisibility(View.VISIBLE);
                set_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DATE_DIALOG_ID);
                    }
                });
                calibration_date_btn.setText("ISOTOPE INSTALLATION DATE");
                calibration_date_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView_date.setText(getCurrentDate());
                        adapter.notifyDataSetChanged();
                        isotope_none.setDate(getCurrentDate());
                        calibration_date_btn.setText("INSTALLATION DATE");
                        calibration_date_btn.setTextColor(WHITE);
                    }
                });
                break;
            case "RT FILM":
                setCurrentDateOnView();
                final Film film = new Film();
                row_name.setVisibility(View.VISIBLE);
                row_type.setVisibility(View.VISIBLE);
                row_model.setVisibility(View.VISIBLE);
                row_size.setVisibility(View.VISIBLE);
                row_isotope.setVisibility(View.VISIBLE);
                row_calibration_date.setVisibility(View.VISIBLE);
                row_expire_date.setVisibility(View.VISIBLE);
                row_set_date.setVisibility(View.VISIBLE);
                set_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DATE_DIALOG_ID);
                    }
                });
                calibration_date_btn.setText(" SET PRODUCTION DATE ");
                calibration_date_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView_date.setText(getCurrentDate());
                        adapter.notifyDataSetChanged();
                        film.setDate(getCurrentDate());
                        film.setFilmIsexpirt(false);
                        calibration_date_btn.setText("PRODUCTION DATE ");
                        calibration_date_btn.setTextColor(WHITE);

                    }
                });
                expire_date_btn.setText("SET EXPIRE DATE ");
                expire_date_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView_expire_date.setText(getCurrentDate());
                        film.setExpiryDate(getCurrentDate());
                        expire_date_btn.setTextColor(WHITE);
                        expire_date_btn.setText("EXPIRE DATE ");
                        adapter.notifyDataSetChanged();
                    }
                });

                this.film = film;

                break;
            case "IQI":
                row_info.setVisibility(View.VISIBLE);
                edit_text_serial_number.setVisibility(View.GONE);
                row_iqi.setVisibility(View.VISIBLE);

                break;
            case "FILM DEVELOPER":
                setCurrentDateOnView();
                row_name.setVisibility(View.VISIBLE);
                row_model.setVisibility(View.VISIBLE);
                row_size.setVisibility(View.VISIBLE);
                row_calibration_date.setVisibility(View.VISIBLE);
                row_expire_date.setVisibility(View.VISIBLE);
                row_set_date.setVisibility(View.VISIBLE);
                set_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DATE_DIALOG_ID);
                    }
                });
                /**
                 * PRODUCTION date to set
                 */
                calibration_date_btn.setText("SET PRODUCTION DATE");
                calibration_date_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView_date.setText(getCurrentDate());
                        adapter.notifyDataSetChanged();
                        developer.setProductionDate(getCurrentDate());
                        calibration_date_btn.setText(" PRODUCTION DATE ");
                        calibration_date_btn.setTextColor(WHITE);

                    }
                });
                /**
                 *  EXPIRE date to set
                 */
                expire_date_btn.setText(" SET EXPIRE DATE ");
                expire_date_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView_expire_date.setText(getCurrentDate());
                        developer.setExpireDate(getCurrentDate());
                        expire_date_btn.setTextColor(WHITE);
                        expire_date_btn.setText(" EXPIRE DATE ");
                        adapter.notifyDataSetChanged();

                    }
                });
                break;
            case "FILM FIXER":
                setCurrentDateOnView();
                row_name.setVisibility(View.VISIBLE);
                row_model.setVisibility(View.VISIBLE);
                row_size.setVisibility(View.VISIBLE);
                row_calibration_date.setVisibility(View.VISIBLE);
                row_expire_date.setVisibility(View.VISIBLE);
                row_set_date.setVisibility(View.VISIBLE);
                set_date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DATE_DIALOG_ID);
                    }
                });
                /**
                 * PRODUCTION date to set
                 */
                calibration_date_btn.setText("SET PRODUCTION DATE");
                calibration_date_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView_date.setText(getCurrentDate());
                        adapter.notifyDataSetChanged();
                        fixer.setProductionDate(getCurrentDate());
                        calibration_date_btn.setText(" PRODUCTION DATE ");
                        calibration_date_btn.setTextColor(WHITE);

                    }
                });
                /**
                 *  EXPIRE date to set
                 */
                expire_date_btn.setText(" SET EXPIRE DATE ");
                expire_date_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView_expire_date.setText(getCurrentDate());
                        fixer.setExpireDate(getCurrentDate());
                        expire_date_btn.setTextColor(WHITE);
                        expire_date_btn.setText(" EXPIRE DATE ");
                        adapter.notifyDataSetChanged();

                    }
                });
                break;

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == item.getItemId()) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setCurrentDateOnView() {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        datePicker.init(year, month, day, null);
        setCurrentDate(new StringBuilder().append(day).append(".").append(month + 1).append(".").append(year).append("").toString());


    }


    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, datePickerListener, year, month, day);
                return new DatePickerDialog(this, datePickerListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;
            // set selected date into textview
            setCurrentDate(new StringBuilder().append(day)
                    .append(".").append(month + 1).append(".").append(year)
                    .append(" ").toString());
            // set selected date into datepicker also
            datePicker.init(year, month, day, null);


        }
    };
}



