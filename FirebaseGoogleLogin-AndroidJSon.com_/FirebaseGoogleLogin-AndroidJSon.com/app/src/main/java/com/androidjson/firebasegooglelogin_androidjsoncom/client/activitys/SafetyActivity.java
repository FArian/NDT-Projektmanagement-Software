package com.androidjson.firebasegooglelogin_androidjsoncom.client.activitys;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.CardView;
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

public class SafetyActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Item> items;
    private ImageView user_pic;
    private int RED, GREEN, BLACK, WHITE, CURRENTCOLOR;
    private Personal personal;
    private Button edit_btn, delete_btn;
    private TextView textView_info;
    private String unitValue;
    private double radiation_value = 0.0;
    private CardView cardView;
    private String warning;
    private boolean isWarning = false;
    private Client client;
    private Gson gson;
    private static String email;
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
        email = personal.getEmail();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        bindViews();
        //Create model list
        items = new ArrayList<>();
        items.add(new Item("person", " YOUR INFO", R.drawable.worker));
        //------Camera---------
        items.add(new Item("on_camera", " On camera" + "\n" + " (max 2 mSv/h)", R.drawable.rt_camera_min));
        items.add(new Item("around_camera", " 1m distance from camera" + "\n" + " (max 20 μSv/h)", R.drawable.rt_camera_min));
        //------bunker---------
        items.add(new Item("bunker_inside", " Inside bunker" + "\n" + " (max 7,5 μSv/h)", R.drawable.bunker1));
        items.add(new Item("bunker_outside", " Outside bunker" + "\n" + " (max 2,5 μSv/h)", R.drawable.bunker1));
        //-------Transport-----
        items.add(new Item("transport_inside", " Driver place" + "\n" + " (max 2,5 μSv/h)", R.drawable.transport));
        items.add(new Item("transport_outside", " Around the car" + "\n" + " (max7,5 μSv/h)", R.drawable.transport));
        //-------Area----------
        items.add(new Item("forbidden_area", " Forbidden area" + "\n" + " (D° >= 2 mSv/h)", R.drawable.area));
        items.add(new Item("control_area", " Control area" + "\n" + " (2 mμSv/h >D°>25 μSv/h)", R.drawable.area));
        items.add(new Item("free_area", " Free area" + "\n" + " (25 μSv/h > D°)", R.drawable.area));

        //----------------------------------Test---------------------------------------------------
        items.add(new Item("Radioactive", " Radioactive", "", R.drawable.radioactive));
        //--------------------------------------Test------------------------------------------------

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Create an adapter
        adapter = new RecyclerViewAdapter(SafetyActivity.this, items);

        //Create custom interface object and send it to adapter
        //Adapter trigger it when any item view is clicked
        adapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showEditDialog(position);
                Toast.makeText(SafetyActivity.this, getResources().getString(R.string.clicked_item, items.get(position).getInfo()), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemLongClick(View view, int position) {
                //showEditDialog(position);

            }
        });

        //Set adapter to recyclerview
        recyclerView.setAdapter(adapter);


    }

    public String getReceiveMessage() {
        return receiveMessage;
    }

    public void setReceiveMessage(String receiveMessage) {
        this.receiveMessage = receiveMessage;
    }


    /**
     * View initializing
     */
    private void bindViews() {

        //Bind recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        cardView = (CardView) findViewById(R.id.card_view);

    }
    //-------------------------------------edit_text---------------------------

    private void showEditDialog(final int position) {
        //dialog connected to edit_dialog layout
        final Dialog dialog = new Dialog(SafetyActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.edit_dialog);

        final EditText value_edittext = (EditText) dialog.findViewById(R.id.edit_text_value);
        final Spinner spinner = (Spinner) dialog.findViewById(R.id.sp_edit_dialog);
        //Bind dialog views
        edit_btn = (Button) dialog.findViewById(R.id.edit_btn);
        delete_btn = (Button) dialog.findViewById(R.id.delete_button);
        textView_info = (TextView) dialog.findViewById(R.id.edit_text_info);
        textView_info.setText(items.get(position).getInfo());


        //Set clicked to value edittext
        //value_edittext.setText(items.get(position).getValue().toString());

        //When edit button is clicked, first edit edittext should be checked if it is empty
        //If it is not empty, data and listview item should be changed.
        edit_btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                String value = value_edittext.getText().toString();
                if (value != null || !value.isEmpty()) {
                    unitValue = spinner.getSelectedItem().toString();
                    if (unitValue.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Radiation Unit is empty", Toast.LENGTH_LONG).show();
                    } else {
                        radiation_value = Double.parseDouble(value_edittext.getText().toString());
                        items.get(position).setValue(String.valueOf(radiation_value) + " " + unitValue);
                        switch (items.get(position).getType()) {
                            case "on_camera":
                                if (radiation_value > 2 && unitValue.equals("mSv/h")) {
                                    Toast.makeText(SafetyActivity.this, getResources().getString(R.string.warning_radiation, items.get(position).getValue()), Toast.LENGTH_SHORT).show();
                                    warning = "Warning: Radiation ON CAMERA is over 2 mSv/h ";
                                    items.get(position).setWarning(true);
                                    isWarning = true;
                                }
                                break;
                            case "around_camera":
                                if (radiation_value > 20 && unitValue.equals("μSv/h")) {
                                    Toast.makeText(SafetyActivity.this, getResources().getString(R.string.warning_radiation, items.get(position).getValue()), Toast.LENGTH_SHORT).show();
                                    warning = "Warning: Radiation with 1 meter from CAMERA is over 20 μSv/h ";
                                    items.get(position).setWarning(true);
                                    isWarning = true;
                                }
                                break;
                            case "bunker_inside":
                                if (radiation_value > 7.5 && unitValue.equals("μSv/h")) {
                                    Toast.makeText(SafetyActivity.this, getResources().getString(R.string.warning_radiation, items.get(position).getValue()), Toast.LENGTH_SHORT).show();
                                    warning = "Warning: Radiation inside of bunker is over 7,5 μSv/h ";
                                    items.get(position).setWarning(true);
                                    isWarning = true;
                                }
                                break;
                            case "bunker_outside":
                                if (radiation_value > 2.5 && unitValue.equals("μSv/h")) {
                                    Toast.makeText(SafetyActivity.this, getResources().getString(R.string.warning_radiation, items.get(position).getValue()), Toast.LENGTH_SHORT).show();
                                    warning = "Warning: Radiation outside of bunker is over 2,5 μSv/h ";
                                    items.get(position).setWarning(true);
                                    isWarning = true;
                                }

                                break;
                            case "transport_inside":
                                if (radiation_value > 2.5 && unitValue.equals("μSv/h")) {
                                    Toast.makeText(SafetyActivity.this, getResources().getString(R.string.warning_radiation, items.get(position).getValue()), Toast.LENGTH_SHORT).show();
                                    warning = "Warning: Radiation inside of car in driver place is over 2,5 μSv/h ";
                                    items.get(position).setWarning(true);
                                    isWarning = true;
                                }

                                break;
                            case "transport_outside":
                                if (radiation_value > 7.5 && unitValue.equals("μSv/h")) {
                                    Toast.makeText(SafetyActivity.this, getResources().getString(R.string.warning_radiation, items.get(position).getValue()), Toast.LENGTH_SHORT).show();
                                    warning = "Warning: Radiation outside of car is over 7,5 μSv/h ";
                                    items.get(position).setWarning(true);
                                    isWarning = true;
                                }
                                break;
                            case "forbidden_area":
                                if (radiation_value < 2 && unitValue.equals("mSv/h")) {
                                    Toast.makeText(SafetyActivity.this, getResources().getString(R.string.warning_radiation, items.get(position).getValue()), Toast.LENGTH_SHORT).show();
                                    warning = "Warning: Radiation Forbidden area  should be -> (D° >= 2 mSv/h) ";
                                    items.get(position).setWarning(true);
                                    isWarning = true;
                                }
                                break;
                            case "control_area":
                                if ((radiation_value < 2 && unitValue.equals("mSv/h")) || (radiation_value > 25 && unitValue.equals("μSv/h "))) {
                                    Toast.makeText(SafetyActivity.this, getResources().getString(R.string.warning_radiation, items.get(position).getValue()), Toast.LENGTH_SHORT).show();
                                    warning = "Warning: Radiation of Control area  should be -> (2 mμSv/h >D°>25 μSv/h) ";
                                    items.get(position).setWarning(true);
                                    isWarning = true;
                                }

                                break;
                            case "free_area":
                                if (radiation_value < 25 && unitValue.equals("μSv/h")) {
                                    Toast.makeText(SafetyActivity.this, getResources().getString(R.string.warning_radiation, items.get(position).getValue()), Toast.LENGTH_SHORT).show();
                                    warning = "Warning: Radiation of Free area  should be -> (25 μSv/h > D°) ";
                                    items.get(position).setWarning(true);
                                    isWarning = true;
                                }
                                break;
                            case "person":
                                break;

                        }
                        if (isWarning) {
                            client.getWebSocket().send(ToJson.message("Safety Warning", personal.getId(), warning).toString());
                            isWarning = false;
                            setReceiveMessage(client.getReceiveMessage());


                        }


                    }
                    //Notify adapter about changing of model list
                    adapter.notifyDataSetChanged();
                    //Close dialog
                    dialog.dismiss();

                } else {
                    Toast.makeText(SafetyActivity.this, getResources().getString(R.string.cant_be_empty), Toast.LENGTH_SHORT).show();
                }


            }
        });

        //When delete button is clicked, it should be deleted from
        //data list and adapter should be notified that data list change
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.remove(position);
                //Notify adapter about changing of model list
                adapter.notifyDataSetChanged();
                //Close dialog
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}



