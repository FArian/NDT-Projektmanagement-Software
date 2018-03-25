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
import android.widget.Toast;

import com.androidjson.firebasegooglelogin_androidjsoncom.R;
import com.androidjson.firebasegooglelogin_androidjsoncom.client.Item;
import com.androidjson.firebasegooglelogin_androidjsoncom.client.RecyclerViewAdapter;
import com.androidjson.firebasegooglelogin_androidjsoncom.client.RecyclerViewItemClickListener;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.Personal;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class AlarmActivity extends Activity {
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
        setContentView(R.layout.activity_items);
        bindViews();

        //Create model list
        items = new ArrayList<>();
        final String EMERGENCY_MESSAGE =
                "1 : Remote Handling tongs " + "\n" +
                        "2: Shielded container" + "\n" +
                        "3: Hand tools -> wrench,pliers,croppers" + "\n" +
                        "4: Fist aid kit" + "\n" +
                        "5: Radiation warning labels & signs" + "\n" +
                        "6: Barriers/ropes" + "\n" +
                        "7: call HPS";

        items.add(new Item("ALARM", "RADIATION EMERGENCY MESSAGE", EMERGENCY_MESSAGE, R.drawable.emergency_equipment));


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Create an adapter
        adapter = new RecyclerViewAdapter(AlarmActivity.this, items);

        //Create custom interface object and send it to adapter
        //Adapter trigger it when any item view is clicked
        adapter.setOnItemClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showEditDialog(position);
                Toast.makeText(AlarmActivity.this, getResources().getString(R.string.clicked_item, items.get(position).getInfo()), Toast.LENGTH_SHORT).show();

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
        final Dialog dialog = new Dialog(AlarmActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.edit_dialog);


        //Bind dialog views
        final EditText value_edittext = (EditText) dialog.findViewById(R.id.edit_text_value);
        final Button edit_btn = (Button) dialog.findViewById(R.id.edit_btn);
        Button deleteButton = (Button) dialog.findViewById(R.id.delete_button);

        //Set clicked to value edittext
        value_edittext.setText(items.get(position).getValue());

        //When edit button is clicked, first edit edittext should be checked if it is empty
        //If it is not empty, data and listview item should be changed.
        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!value_edittext.getText().toString().equals("")) {

                    items.get(position).setValue(value_edittext.getText().toString());
                    //Notify adapter about changing of model list
                    adapter.notifyDataSetChanged();
                    //Close dialog
                    dialog.dismiss();
                } else {
                    Toast.makeText(AlarmActivity.this, getResources().getString(R.string.cant_be_empty), Toast.LENGTH_SHORT).show();
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



