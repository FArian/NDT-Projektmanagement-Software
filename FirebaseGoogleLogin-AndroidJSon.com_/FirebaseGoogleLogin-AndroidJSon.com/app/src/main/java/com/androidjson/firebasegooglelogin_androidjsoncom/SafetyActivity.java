package com.androidjson.firebasegooglelogin_androidjsoncom;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.androidjson.firebasegooglelogin_androidjsoncom.client.Item;
import com.androidjson.firebasegooglelogin_androidjsoncom.client.RecyclerViewAdapter;
import com.androidjson.firebasegooglelogin_androidjsoncom.client.RecyclerViewItemClickListener;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.Personal;
import com.google.gson.Gson;

public class SafetyActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Item> items;
    private ImageView user_pic;
    private int RED, GREEN, BLACK, WHITE, CURRENTCOLOR;
    private Personal personal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RED = R.color.colorRed;
        GREEN = R.color.easyfei_green;
        BLACK = R.color.album_text_color;
        WHITE = R.color.white;
        Gson gson = new Gson();
        personal = gson.fromJson(getIntent().getStringExtra("Personal"), Personal.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety);
        bindViews();

        //Create model list
        items = new ArrayList<>();
        items.add(new Item("person", "YOUR INFO", "value",R.drawable.person));
        //------Camera---------
        items.add(new Item("on_camera", "On camera /n (max 2 mSv/h)", "value " + "mSv/h", R.drawable.rt_camera));
        items.add(new Item("around_camera", "With 1m distance from camera(max 20 μSv/h)", "value " + "μSv/h", R.drawable.rt_camera));
        //------bunker---------
        items.add(new Item("bunker_inside", "Inside bunker(max 7,5 μSv/h)", "value " + "μSv/h", R.drawable.bunker));
        items.add(new Item("bunker_outside", "Outside bunker(max 2,5 μSv/h)", "value " + "μSv/h", R.drawable.bunker));
        //-------Transport-----
        items.add(new Item("transport_inside", "Driver place(max 2,5 μSv/h)", "value " + "μSv/h", R.drawable.transport_car));
        items.add(new Item("transport_outside", "Around the car(max7,5 μSv/h)", "value " + "μSv/h", R.drawable.transport_car));
        //-------Area----------
        items.add(new Item("forbidden_area", "Forbidden area(D° >= 2 mSv/h)", "value " + "mSv/h", R.drawable.radiation_area));
        items.add(new Item("control_area", "Control area(2 mμSv/h >D°>25 μSv/h)", "value " + "μSv/h", R.drawable.radiation_area));
        items.add(new Item("free_area", "Free area(25 μSv/h > D°)", "value " + "μSv/h", R.drawable.radiation_area));

        //----------------------------------Test---------------------------------------------------
        items.add(new Item("", "Radioactive", "", R.drawable.radioactive));
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


    /**
     * View initializing
     */
    private void bindViews() {
        //Bind recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

    }
    //-------------------------------------edit_text---------------------------

    private void showEditDialog(final int position) {
        final Dialog dialog = new Dialog(SafetyActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.edit_dialog);


        //Bind dialog views
        final EditText value_edittext = (EditText) dialog.findViewById(R.id.edit_text_value);
        final Button submit_Button = (Button) dialog.findViewById(R.id.submit_button);
        Button deleteButton = (Button) dialog.findViewById(R.id.delete_button);

        //Set clicked to value edittext
        value_edittext.setText(items.get(position).getValue());

        //When edit button is clicked, first edit edittext should be checked if it is empty
        //If it is not empty, data and listview item should be changed.
        submit_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!value_edittext.getText().toString().equals("")) {

                    items.get(position).setValue(value_edittext.getText().toString());
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
        deleteButton.setOnClickListener(new View.OnClickListener() {
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



