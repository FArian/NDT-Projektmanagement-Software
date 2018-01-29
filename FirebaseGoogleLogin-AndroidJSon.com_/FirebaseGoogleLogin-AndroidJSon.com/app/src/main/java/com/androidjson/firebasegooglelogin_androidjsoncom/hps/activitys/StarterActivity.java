package com.androidjson.firebasegooglelogin_androidjsoncom.hps.activitys;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.androidjson.firebasegooglelogin_androidjsoncom.R;
import com.androidjson.firebasegooglelogin_androidjsoncom.client.activitys.AlarmActivity;
import com.androidjson.firebasegooglelogin_androidjsoncom.client.activitys.ReportActivity;
import com.androidjson.firebasegooglelogin_androidjsoncom.client.activitys.SafetyActivity;
import com.androidjson.firebasegooglelogin_androidjsoncom.client.activitys.TimeActivity;
import com.androidjson.firebasegooglelogin_androidjsoncom.connection.Client;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.Personal;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class StarterActivity extends Activity {

    // TAG is for show some tag logs in LOG screen.
    public static final String TAG = "StarterActivity";
    private Personal person;
    private LinearLayout list_view_items_line1, list_view_items_line2;
    private android.support.v7.widget.RecyclerView recyclerView;
    private Client client;
    private Gson gson;
    //Items list
    // Array of strings items
    private String[] items = new String[]{"PROJECTS", " TEAMS ", " PERSONALS", " MATERIALS", " MESSAGES", " ALARM"};

    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] flags = new int[]{
            R.drawable.project,
            R.drawable.team,
            R.drawable.person1,
            R.drawable.material1,
            R.drawable.message,
            R.drawable.alarm1,
    };
    private String itemsMessage[] = {"Projects", " Teams ", " Personals ", " Materials", " Messages from Client", " Alarm from Client"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        list_view_items_line1 = (LinearLayout) findViewById(R.id.line1);
        list_view_items_line2 = (LinearLayout) findViewById(R.id.line2);
        client = new Client();
        gson = new Gson();

        person = gson.fromJson(getIntent().getStringExtra("Personal"), Personal.class);
        // Each row in the list stores country name, currency and flag
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < items.length; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("txt", "ITEM:" + items[i]);
            hm.put("cur", "Message : " + itemsMessage[i]);
            hm.put("flag", Integer.toString(flags[i]));
            aList.add(hm);
        }
        // Keys used in Hashmap
        String[] from = {"flag", "txt", "cur"};
        // Ids of views in listview_layout
        int[] to = {R.id.flag, R.id.txt, R.id.cur};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_layout_start, from, to);
        // Getting a reference to listview of main_login.xmlin.xml layout file
        ListView listView = (ListView) findViewById(R.id.list_view_start);
        // Setting the adapter to the listView
        listView.setAdapter(adapter);

        // Item Click Listener for the listview
        OnItemClickListener itemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Getting the Container Layout of the ListView
                LinearLayout linearLayoutParent = (LinearLayout) view;

                // Getting the inner Linear Layout
                LinearLayout linearLayoutChild = (LinearLayout) linearLayoutParent.getChildAt(1);

                // Getting the item TextView
                TextView item = (TextView) linearLayoutChild.getChildAt(0);


                Toast.makeText(getBaseContext(), item.getText().toString(), Toast.LENGTH_SHORT).show();

                switch (position) {
                    case 0:
                        //safety
                        goToNextPage(position);
                        break;
                    case 1:
                        //report
                        goToNextPage(position);
                        break;
                    case 2:
                        //time
                        goToNextPage(position);
                        break;
                    case 3:
                        //material
                        goToNextPage(position);

                        break;
                    case 4:
                        //alarm
                        goToNextPage(position);
                        break;
                    case 5:
                        //logout
                        goToNextPage(position);
                        break;


                }

            }
        };
        // Setting the item click listener for the listview
        listView.setOnItemClickListener(itemClickListener);

        //safety_btn.setBackgroundColor(R.color.easyfei_black);
        //safety_btn.setTextColor(R.color.easyfei_green);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    /**
     * go to next page , RtCameraSafety Page
     */
    @SuppressLint("ResourceAsColor")
    private void goToNextPage(int position) {
        Gson gson = new Gson();
        String myJson = null;
        Intent intent;
        switch (position) {
            case 0:
                // projects activity
                intent = new Intent(getApplicationContext(), SafetyActivity.class);
                myJson = gson.toJson(person);
                intent.putExtra("Personal", myJson);
                //startActivity(intent);
                break;
            case 1:
                // teams activity
                intent = new Intent(getApplicationContext(), ReportActivity.class);
                myJson = gson.toJson(person);
                intent.putExtra("Personal", myJson);
                startActivity(intent);
                break;
            case 2:
                // personal activity
                intent = new Intent(getApplicationContext(), TimeActivity.class);
                myJson = gson.toJson(person);
                intent.putExtra("Personal", myJson);
                //startActivity(intent);
                break;
            case 3:
                // material activity
                intent = new Intent(getApplicationContext(), CreateMaterialActivity.class);
                startActivity(intent);
                break;
            case 4:
                //message activity
                //TODO
            case 5:
                // alarm activity
                intent = new Intent(getApplicationContext(), AlarmActivity.class);
                startActivity(intent);
                break;


        }


    }


}