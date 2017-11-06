package rzeznika.ndtClient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import rzeznika.ndtClient.controller.Login;
import rzeznika.ndtClient.model.Player;
import rzeznika.ndtClient.network.Client;
import rzeznika.ndtClient.network.TOJSON;
import rzeznika.ndtClient.network.TCPController;

public class MainActivity extends AppCompatActivity {

    private TextView textViewOutput;
    private Client client;
    private Login log;



    /**
     * Server Ip Address for Client connection !
     * Please enter your current server IP address below in SERVERIP ?
     */
    private static String SERVERIP = "10.180.60.111";
    private final String SERVERADDRESS = TCPController.webAddress(SERVERIP);




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.connectiontest); //Switched to work with GUI 07.07.17 by Stephan


        setContentView(R.layout.activity_main);
        client = new Client(SERVERADDRESS);
        textViewOutput = (TextView) findViewById(R.id.output);
        this.log=new Login("MainActivty");
        final String extraname;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                extraname = null;
            } else {
                extraname = extras.getString("STR_NAME");
            }
        } else {
            extraname = (String) savedInstanceState.getSerializable("STR_NAME");
        }
        final String extracolor;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                extracolor = null;
            } else {
                extracolor = extras.getString("STR_COLOR");
            }
        } else {
            extracolor = (String) savedInstanceState.getSerializable("STR_COLOR");
        }

        start();
        client.setColorName(extraname, extracolor);
    }

    @Override
    protected void onDestroy() {
        client.gameWebSocket.close(9999, "Closed Websocket - On Destroy");
        super.onDestroy();
    }

    public void output(final String txt) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                textViewOutput.setText(textViewOutput.getText().toString() + "\n\n" + txt);
            }
        });

    }

    private void start() {

        client.gameWebSocket.send(TOJSON.helloVersion().toString() + "\n");
        log.getLogin().info("Hallo Version send to Server" + TOJSON.helloVersion().toString()+"\n");

    }

    // set Robber (Räuber an beliebiges Feld setzen)
    @SuppressWarnings("ResourceType")
    public static void setPositionRobber (ImageView robber, int position) {

        int row_1 = -175;
        int row_2 = -90;
        int row_3 = 0;
        int row_4 = 70;
        int row_5 = 150;

        if      (position == 1)     {robber.setX(-90); robber.setY(row_1);}
        else if (position == 2)     {robber.setX(0); robber.setY(row_1);}
        else if (position == 3)     {robber.setX(90); robber.setY(row_1);}
        else if (position == 4)     {robber.setX(-135); robber.setY(row_2);}
        else if (position == 5)     {robber.setX(-45); robber.setY(row_2);}
        else if (position == 6)     {robber.setX(45); robber.setY(row_2);}
        else if (position == 7)      {robber.setX(135); robber.setY(row_2);}
        else if (position == 8)      {robber.setX(-180); robber.setY(row_3);}
        else if (position == 9)      {robber.setX(-90); robber.setY(row_3);}
        else if (position == 10)     {robber.setX(0); robber.setY(row_3);}
        else if (position == 11)     {robber.setX(90); robber.setY(row_3);}
        else if (position == 12)     {robber.setX(180); robber.setY(row_3);}
        else if (position == 13)     {robber.setX(-135); robber.setY(row_4);}
        else if (position == 14)     {robber.setX(-45); robber.setY(row_4);}
        else if (position == 15)     {robber.setX(45); robber.setY(row_4);}
        else if (position == 16)     {robber.setX(135); robber.setY(row_4);}
        else if (position == 17)     {robber.setX(-90); robber.setY(row_5);}
        else if (position == 18)     {robber.setX(0); robber.setY(row_5);}
        else if (position == 19)     {robber.setX(90); robber.setY(row_5);}


    }
    // set Settlement (Spieler setzt Siedlung mit richtiger Farbe)
    // WICHTIG : Methode soll mit einer Location Instanz kommen : setSettlement (Player spieler, String location)
    public void setBuilding (Player spieler, String buildingType, int buildPosition)
    {

        ImageView building = (ImageView) findViewById(buildPosition);
        if (spieler.getColor()=="Rot")
        {
            if(buildingType == "Settlement") {
                building.setImageResource(R.drawable.settlementrot);
            }
            else if(buildingType == "City"){
                building.setImageResource(R.drawable.cityrot);
            }

        }
        if (spieler.getColor()=="Blau")
        {

            if(buildingType == "Settlement") {
                building.setImageResource(R.drawable.settlementblau);
            }
            else if(buildingType == "City") {
                building.setImageResource(R.drawable.cityblau);
            }
        }
        if (spieler.getColor()=="Orange")
        {

            if(buildingType == "Settlement") {
                building.setImageResource(R.drawable.settlementorange);
            }
            else if(buildingType == "City") {
                building.setImageResource(R.drawable.cityorange);
            }
        }
        if (spieler.getColor()=="Weiss")
        {
            if(buildingType == "Settlement") {
                building.setImageResource(R.drawable.settlementweiss);
            }
            else if(buildingType == "City") {
                building.setImageResource(R.drawable.cityweiss);
            }
        }
    }

    //setStreet : Straße wird an angegebener Location gesetzt und erkennt welche Straße (gerade, rechtsHoch, rechtsRunter)

    public void setStreet(Player spieler, int streetPosition)

    {
        ImageView street = (ImageView) findViewById(streetPosition);
        if (spieler.getColor()=="Rot"){
            if(((String)street.getTag()).equals("gerade"))
            {
                street.setImageResource(R.drawable.rot_gerade);
            }
            else if(((String)street.getTag()).equals("rechtsHoch"))
            {
                street.setImageResource(R.drawable.rot_rechtshoch);
            }
            else if(((String)street.getTag()).equals("rechtsRunter"))
            {
                street.setImageResource(R.drawable.rot_rechtsrunter);
            }
        }
        else if (spieler.getColor()=="Blau"){
            if(((String)street.getTag()).equals("gerade"))
            {
                street.setImageResource(R.drawable.blau_gerade);
            }
            else if(((String)street.getTag()).equals("rechtsHoch"))
            {
                street.setImageResource(R.drawable.blau_rechtshoch);
            }
            else if(((String)street.getTag()).equals("rechtsRunter"))
            {
                street.setImageResource(R.drawable.blau_rechtsrunter);
            }
        }
        else if (spieler.getColor()=="Orange"){
            if(((String)street.getTag()).equals("gerade"))
            {
                street.setImageResource(R.drawable.orange_gerade);
            }
            else if(((String)street.getTag()).equals("gerade"))
            {
                street.setImageResource(R.drawable.orange_rechtshoch);
            }
            else if(((String)street.getTag()).equals("gerade"))
            {
                street.setImageResource(R.drawable.orange_rechtsrunter);
            }
        }
        else if (spieler.getColor()=="Weiss"){
            if(((String)street.getTag()).equals("gerade"))
            {
                street.setImageResource(R.drawable.weiss_gerade);
            }
            else if(((String)street.getTag()).equals("gerade"))
            {
                street.setImageResource(R.drawable.weiss_rechtshoch);
            }
            else if(((String)street.getTag()).equals("gerade"))
            {
                street.setImageResource(R.drawable.weiss_rechtsrunter);
            }
        }
    }


}
