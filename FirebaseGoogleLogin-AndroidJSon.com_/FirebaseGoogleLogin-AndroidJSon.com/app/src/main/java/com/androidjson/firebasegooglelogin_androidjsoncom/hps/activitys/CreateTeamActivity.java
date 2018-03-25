package com.androidjson.firebasegooglelogin_androidjsoncom.hps.activitys;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.androidjson.firebasegooglelogin_androidjsoncom.MainActivity;
import com.androidjson.firebasegooglelogin_androidjsoncom.R;
import com.androidjson.firebasegooglelogin_androidjsoncom.connection.Client;
import com.androidjson.firebasegooglelogin_androidjsoncom.json.ToJson;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.Personal;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.Team;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CreateTeamActivity extends Activity {

    // TAG is for show some tag logs in LOG screen.
    public static final String TAG = "CreateTeamActivity";
    private Gson gson;
    private Intent intent;
    private Personal person;
    private LinearLayout list_view_items_line1, list_view_items_line2;
    private android.support.v7.widget.RecyclerView recyclerView;
    private Client client;
    private Team team;
    //Items list
    // Array of strings items
    private String[] items = new String[]{"Personals", "Materials"};

    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] flags = new int[]{
            R.drawable.person,
            R.drawable.material
    };
    private String itemsMessage[] = {"add new Person to your Team", "add new Material to your Team"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        client = MainActivity.getClientCustom();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        recyclerView = (android.support.v7.widget.RecyclerView) findViewById(R.id.recycler_view);
        list_view_items_line1 = (LinearLayout) findViewById(R.id.line1);
        list_view_items_line2 = (LinearLayout) findViewById(R.id.line2);
        gson = new Gson();
        team = new Team();
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
                        //personal
                        goToNextPage(position);
                        break;
                    case 1:
                        //material
                        goToNextPage(position);
                        break;
                }

            }
        };
        listView.setOnItemClickListener(itemClickListener);

    }


    /**
     * go to next page , RtCameraSafety Page
     */
    @SuppressLint("ResourceAsColor")
    private void goToNextPage(int position) {
        String myJson = null;
        switch (position) {
            case 0:
                // select personals for Team
                client.getWebSocket().send(ToJson.message("Resource", "Personal").toString());

                break;
            case 1:
                // create material activity
                intent = new Intent(getApplicationContext(), CreateMaterialActivity.class);
                startActivity(intent);
                break;
        }


    }


}