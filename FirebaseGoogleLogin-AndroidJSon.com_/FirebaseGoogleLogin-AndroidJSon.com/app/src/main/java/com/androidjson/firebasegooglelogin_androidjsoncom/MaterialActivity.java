package com.androidjson.firebasegooglelogin_androidjsoncom;

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
import android.widget.Toast;

import com.androidjson.firebasegooglelogin_androidjsoncom.client.Item;
import com.androidjson.firebasegooglelogin_androidjsoncom.client.RecyclerViewAdapter;
import com.androidjson.firebasegooglelogin_androidjsoncom.client.RecyclerViewItemClickListener;
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

        items.add(new Item("FILMBADGE", "YOUR FILMBADGE", "value " + " mSv/h", R.drawable.film_badge));
        items.add(new Item("TLD", "YOUR TLD", "value " + " mSv/h", R.drawable.tld_badge));
        items.add(new Item("DOSIMETER", "YOUR POCKET DOSIMETER ", "value", R.drawable.dosimeters));
        items.add(new Item("GEIGER", "YOUR GEIGER ALARM", "value", R.drawable.geiger));

        items.add(new Item("RADIOMETER", "TEAMS RADIOMETER", "value", R.drawable.radiometer));
        items.add(new Item("LEAD_ARON", "TEAMS LEAD ARON", "value", R.drawable.lead_aron2));
        items.add(new Item("RADIATION_SINGS", "TEAMS RADIATION SINGS", "value", R.drawable.radiation_sings2));

        items.add(new Item("HANDLING_TONGS", "TEAMS HANDLING TONGS", "value", R.drawable.handling_tongs));
        items.add(new Item("EMERGENCY_STORAGE_CONTAINER", "Teams EMERGENCY STORAGE CONTAINER", "value", R.drawable.emergency_storage_container));

        items.add(new Item("RT_CAMERA", "TEAMS RT CAMERA ", "value", R.drawable.rt_camera));
        items.add(new Item("RT_FILM", "TEAMS RT FILM ", "value", R.drawable.agfa_film));
        items.add(new Item("IQI", "INPATIENT QUALITY INDICATORS -> IQI", "value", R.drawable.iqi));
        items.add(new Item("FILM_DEVELOPMENT_CHEMICALS", "FILM DEVELOPMENT CHEMICALS", "value", R.drawable.developer));
        items.add(new Item("FILM_FIXER_CHEMICALS", "FILM FIXER CHEMICALS", "value", R.drawable.fixer));


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
                Toast.makeText(MaterialActivity.this, getResources().getString(R.string.clicked_item, items.get(position).getInfo()), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemLongClick(View view, int position) {
                showEditDialog(position);

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
                    Toast.makeText(MaterialActivity.this, getResources().getString(R.string.cant_be_empty), Toast.LENGTH_SHORT).show();
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



