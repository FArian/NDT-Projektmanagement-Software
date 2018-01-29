package com.androidjson.firebasegooglelogin_androidjsoncom.client.activitys;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidjson.firebasegooglelogin_androidjsoncom.R;
import com.androidjson.firebasegooglelogin_androidjsoncom.client.Item;
import com.androidjson.firebasegooglelogin_androidjsoncom.client.RecyclerViewAdapter;
import com.androidjson.firebasegooglelogin_androidjsoncom.client.RecyclerViewItemClickListener;
import com.androidjson.firebasegooglelogin_androidjsoncom.connection.Client;
import com.androidjson.firebasegooglelogin_androidjsoncom.json.ToJson;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.Personal;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MaterialActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Item> items;
    private ImageView user_pic;
    private int RED, GREEN, BLACK, WHITE, CURRENTCOLOR;
    private Personal personal;
    private Button SEND_BTN;
    private Spinner SPINNER;
    private EditText EDITTEXT1, EDITTEXT2;
    private TextView TEXTVIEW_title, textView_spinner;
    private String GETTEXT, reason_text, need_text, message_text;
    private Gson gson;
    private Client client;
    private String receiveMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RED = R.color.colorRed;
        GREEN = R.color.easyfei_green;
        BLACK = R.color.album_text_color;
        WHITE = R.color.white;
        gson = new Gson();
        client = new Client();
        personal = gson.fromJson(getIntent().getStringExtra("Personal"), Personal.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        bindViews();

        //Create model list
        items = new ArrayList<>();

        items.add(new Item("FILMBADGE", " FILMBADGE", GETTEXT, R.drawable.film_badge_min));
        items.add(new Item("TLD", " TLD", GETTEXT, R.drawable.tld_badge_min));
        items.add(new Item("DOSIMETER", " POCKET DOSIMETER ", GETTEXT, R.drawable.dosimeters_min));
        items.add(new Item("GEIGER", " GEIGER ALARM", GETTEXT, R.drawable.geiger_min));

        items.add(new Item("RADIOMETER", " RADIOMETER", GETTEXT, R.drawable.radiometer_min));
        items.add(new Item("LEAD_ARON", " LEAD ARON", GETTEXT, R.drawable.lead_aron2));
        items.add(new Item("RADIATION_SINGS", " RADIATION SINGS", GETTEXT, R.drawable.radiation_sings2));

        items.add(new Item("HANDLING_TONGS", " HANDLING TONGS", GETTEXT, R.drawable.handling_tongs));
        items.add(new Item("EMERGENCY_STORAGE_CONTAINER", " EMERGENCY STORAGE CONTAINER", GETTEXT, R.drawable.emergency_storage_container_min));

        items.add(new Item("RT_CAMERA", " RT CAMERA ", GETTEXT, R.drawable.rt_camera_min));
        items.add(new Item("RT_FILM", " RT FILM ", GETTEXT, R.drawable.agfa_film_min));
        items.add(new Item("IQI", " IQI", GETTEXT, R.drawable.iqi));
        items.add(new Item("FILM_DEVELOPMENT_CHEMICALS", " DEVELOPMENT", GETTEXT, R.drawable.developer_min));
        items.add(new Item("FILM_FIXER_CHEMICALS", " FIXER", GETTEXT, R.drawable.fixer_min));




        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Create an adapter
        adapter = new RecyclerViewAdapter(MaterialActivity.this, items);

        //Create custom interface object and send it to adapter
        //Adapter trigger it when any item view is clicked
        adapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showEditDialog(position);
                TEXTVIEW_title.setText(items.get(position).getInfo());
                Toast.makeText(MaterialActivity.this, getResources().getString(R.string.clicked_item, items.get(position).getInfo()), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onItemLongClick(View view, int position) {
                //showEditDialog(position);

            }
        });

        //Set adapter to recyclerview
        recyclerView.setAdapter(adapter);


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
        final Dialog dialog = new Dialog(MaterialActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.edit_dialog_material);

        SPINNER = (Spinner) dialog.findViewById(R.id.spinner_reason);
        SEND_BTN = (Button) dialog.findViewById(R.id.send_need_material);
        EDITTEXT1 = (EditText) dialog.findViewById(R.id.editText1);
        EDITTEXT2 = (EditText) dialog.findViewById(R.id.editText2);
        TEXTVIEW_title=(TextView)dialog.findViewById(R.id.text_material_title);



        //Set clicked to value edittext
        //value_edittext.setText(items.get(position).getValue());
        SEND_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = SPINNER.getSelectedItem().toString();
                if (value.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Item is empty", Toast.LENGTH_LONG).show();
                } else {
                    reason_text = value;
                    String value_need = EDITTEXT1.getText().toString();
                    String value_message = EDITTEXT2.getText().toString();
                    if (!value_need.isEmpty() || !value_need.equals(null)) {
                        need_text = value_need;
                    }
                    if (!value_message.isEmpty() || !value_message.equals(null)) {
                        message_text = value_message;
                    }
                    GETTEXT="Reason = " + reason_text + "\n" + "Need =" + need_text + "\n" + "Message=" + message_text;
                    items.get(position).setValue(GETTEXT);
                    //items.get(position).setValue("Reason = " + reason_text + "\n" + "Need =" + need_text + "\n" + "Message=" + message_text);
                    client.getWebSocket().send(ToJson.message("Material", personal.getId(), items.get(position).toString()).toString());

                    Toast.makeText(getApplicationContext(), "Send to HPS", Toast.LENGTH_LONG).show();
                    //Notify adapter about changing of model list
                    adapter.notifyDataSetChanged();
                    //Close dialog
                    dialog.dismiss();
                }


            }
        });


        dialog.show();
    }


}



