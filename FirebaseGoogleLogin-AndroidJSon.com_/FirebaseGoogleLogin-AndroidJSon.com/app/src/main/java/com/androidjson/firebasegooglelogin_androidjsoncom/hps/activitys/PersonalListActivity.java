package com.androidjson.firebasegooglelogin_androidjsoncom.hps.activitys;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.androidjson.firebasegooglelogin_androidjsoncom.MainActivity;
import com.androidjson.firebasegooglelogin_androidjsoncom.R;
import com.androidjson.firebasegooglelogin_androidjsoncom.client.activitys.AlarmActivity;
import com.androidjson.firebasegooglelogin_androidjsoncom.client.activitys.MaterialActivity;
import com.androidjson.firebasegooglelogin_androidjsoncom.client.activitys.ReportActivity;
import com.androidjson.firebasegooglelogin_androidjsoncom.client.activitys.SafetyActivity;
import com.androidjson.firebasegooglelogin_androidjsoncom.client.activitys.TimeActivity;
import com.androidjson.firebasegooglelogin_androidjsoncom.connection.Client;
import com.androidjson.firebasegooglelogin_androidjsoncom.json.ToJson;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.Personal;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.enums.PERSONALTYPE;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PersonalListActivity extends Activity {

    // TAG is for show some tag logs in LOG screen.
    public static final String TAG = "PersonalListActivity";
    private Gson gson;
    private Client client;
    private List<Personal> personals = new ArrayList<>();
    private List<ImageView> imageViews=new ArrayList<>();
    private Intent intent;
    private Personal person;
    private LinearLayout list_view_items_line1, list_view_items_line2;
    private android.support.v7.widget.RecyclerView recyclerView;
    //Items list
    // Array of strings items
    //private String[] items = new String[]{"Safety", "Report", "Time", "Material", "Alarm"};
    private ArrayList<String> items = new ArrayList<>();

    // Array of integers points to images stored in /res/drawable-ldpi/
    private ArrayList<Integer> arrayList = new ArrayList<>();
    //int[] flags = new int[]{ R.drawable.person,};

    //private String itemsMessage[] = {"Safety is first", "Make report", "Calculator radiation time", "Current list of materials in project", "Radiation emergency message"};
    private ArrayList<String> itemsMessage = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        recyclerView = (android.support.v7.widget.RecyclerView) findViewById(R.id.recycler_view);
        list_view_items_line1 = (LinearLayout) findViewById(R.id.line1);
        list_view_items_line2 = (LinearLayout) findViewById(R.id.line2);
        gson = new Gson();
        client=MainActivity.getClientCustom();

        this.personals = client.getPersonals();
        if(personals!=null){
            for (int i = 0; i <personals.size() ; i++) {
                System.out.println(TAG);
                System.out.println(personals.get(i).toString());

            }

        }

        person = gson.fromJson(getIntent().getStringExtra("Personal"), Personal.class);
        this.updatePersonalList();
        this.itemInitialization();


        // Each row in the list stores country name, currency and flag
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < items.size(); i++) {

            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("txt", "ITEM:" + items.get(i));
            hm.put("cur", "Message : " + itemsMessage.get(i));
            hm.put("flag", Integer.toString(arrayList.get(i)));
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

    private void itemInitialization() {
        if (personals != null) {

            for (int i = 0; i < personals.size(); i++) {
                items.add(personals.get(i).getFirstName().toUpperCase() + " " + personals.get(i).getLastName());
                if(personals.get(i).getPersonalType().equals(PERSONALTYPE.HPS)){
                    arrayList.add(R.drawable.person1);
                }else {
                    arrayList.add(R.drawable.person);
                }
                itemsMessage.add(personals.get(i).getPersonalType().name().toString());
                System.out.println("Personal List in: "+ TAG );
                System.out.println(personals.get(i).toString());
            }

        } else {
            items.add("List is empty");
            arrayList.add(R.drawable.person);
            itemsMessage.add("you have nobody in List of Personals");
            items.add("---------------------");
            arrayList.add(R.drawable.person1);
            itemsMessage.add("--------------");
        }

    }

    private void updatePersonalList() {
        List<Personal> personalList = new ArrayList<>();


        if (client.getPersonals() != null) {
            personalList = client.getPersonals();

            personals = personalList;
            for (Personal p : personals) {
                int n = 0;
                System.out.println("----------------------------------->: Nr :" + n++);
                System.out.println("Personals list in " + TAG + p.toString());

            }
        } else {
            /**
             * send request to receive list of Personals
             */
            client.getWebSocket().send(ToJson.message("PERSONALSLIST", "I NEED PERSONAL LIST").toString());
            personalList = client.getPersonals();
            personals = personalList;
            if (personals != null) {
                for (Personal p : personals) {
                    int n = 0;
                    System.out.println("----------------------------------->: Nr :" + n++);
                    System.out.println("Personals list in " + TAG + p.toString());

                }

            }


        }
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
        String myJson = null;
        switch (position) {
            case 0:
                // safety activity
                intent = new Intent(getApplicationContext(), SafetyActivity.class);
                myJson = gson.toJson(person);
                intent.putExtra("Personal", myJson);
                startActivity(intent);
                break;
            case 1:
                // report activity
                intent = new Intent(getApplicationContext(), ReportActivity.class);
                myJson = gson.toJson(person);
                intent.putExtra("Personal", myJson);
                startActivity(intent);
                break;
            case 2:
                // time activity
                intent = new Intent(getApplicationContext(), TimeActivity.class);
                myJson = gson.toJson(person);
                intent.putExtra("Personal", myJson);
                startActivity(intent);
                break;
            case 3:
                // material activity
                intent = new Intent(getApplicationContext(), MaterialActivity.class);
                myJson = gson.toJson(person);
                intent.putExtra("Personal", myJson);
                startActivity(intent);
                break;
            case 4:
                // alarm activity
                intent = new Intent(getApplicationContext(), AlarmActivity.class);
                myJson = gson.toJson(person);
                intent.putExtra("Personal", myJson);
                startActivity(intent);
                break;
            case 5:
                // main activity
                intent = new Intent(getApplicationContext(), MainActivity.class);
                //myJson = gson.toJson(person);
                //intent.putExtra("Personal", myJson);
                startActivity(intent);
                break;


        }


    }


}