package rzeznika.ndtClient.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import rzeznika.ndtClient.R;


/**
 * Created by steppinski on 22.07.17.
 */

public class TradeView extends Activity {

    private ImageView pic;
    String slash = "/ ";

    int player_holz_give = 0;
    int player_lehm_give = 0;
    int player_schaf_give = 0;
    int player_stroh_give = 0;
    int player_stein_give = 0;

    int player_holz_got = 5;
    int player_lehm_got = 5;
    int player_schaf_got = 5;
    int player_stroh_got = 5;
    int player_stein_got = 5;

    int player_holz_want = 0;
    int player_lehm_want = 0;
    int player_schaf_want = 0;
    int player_stroh_want = 0;
    int player_stein_want = 0;

    int [] tradeDisplay = new int [10];
    int [] tradeMax = new int[5];

    int [] buttonNames  = new int[] {R.id.plus_holz_want, R.id.plus_lehm_want, R.id.plus_schaf_want, R.id.plus_stroh_want, R.id.plus_stein_want,
            R.id.plus_holz_own, R.id.plus_lehm_own, R.id.plus_schaf_own, R.id.plus_stroh_own, R.id.plus_stein_own,
            R.id.minus_holz_want, R.id.minus_lehm_want, R.id.minus_schaf_want, R.id.minus_stroh_want, R.id.minus_stein_want,
            R.id.minus_holz_own, R.id.minus_lehm_own, R.id.minus_schaf_own, R.id.minus_stroh_own, R.id.minus_stein_own};

    Button [] tradeButtons = new Button [20];


    int[] buttonID = new int[] {R.id.holz_anzeige_want, R.id.lehm_anzeige_want, R.id.schaf_anzeige_want, R.id.stroh_anzeige_want, R.id.stein_anzeige_want,
            R.id.holz_anzeige_give, R.id.lehm_anzeige_give, R.id.schaf_anzeige_give, R.id.stroh_anzeige_give, R.id.stein_anzeige_give};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.trade_menue);

        ImageView bg = (ImageView) findViewById(R.id.tradebg); // Sets the graphical Resource of a Landtile
        bg.setImageResource(R.drawable.menuetrade_blank); //BACKGROUND

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.8));



        tradeDisplay [0] = player_holz_want;
        tradeDisplay [1] = player_lehm_want;
        tradeDisplay [2] = player_schaf_want;
        tradeDisplay [3] = player_stroh_want;
        tradeDisplay [4] = player_stein_want;
        tradeDisplay [5] = player_holz_give;
        tradeDisplay [6] = player_lehm_give;
        tradeDisplay [7] = player_schaf_give;
        tradeDisplay [8] = player_stroh_give;
        tradeDisplay [9] = player_stein_give;

        tradeMax [0] = player_holz_got;
        tradeMax [1] = player_lehm_got;
        tradeMax [2] = player_schaf_got;
        tradeMax [3] = player_stroh_got;
        tradeMax [4] = player_stein_got;



        // Deklaration von allen Trade-Buttons
        for (int i=0; i<20; i++) {
            tradeButtons [i] = (Button) findViewById(buttonNames[i]);
        }

        // Accept-Button, um den Tradeanfrage zu erstellen
        Button offerTrade = (Button) findViewById(R.id.tradeOffer);


        // Increas & Decrease Bedingung für Trading
        for(int i=0; i<20; i++)
        {

            if(i<5)
            {
                final int index = i;
                tradeButtons [i].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        increaseInteger(v, index);
                    }
                });
            }
            else if (5<=i && i<10){
                final int index = i;
                tradeButtons [i].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        if (tradeDisplay[index] < tradeMax[index - 5]) {
                            increaseInteger(v, index);
                        }

                    }
                });
            }

            else {
                final int index = i-10;
                tradeButtons [i].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v){

                        decreaseInteger(v,index);
                    }

                });

            }

        }

        //////////////////
        //Trade-offer wird an den Server geschickt !!!! NOCH NICHT FERTIG !!!!!
        //////////////////



        offerTrade.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //TextView bigtest= (TextView) findViewById(R.id.justTest);

                //bigtest.setText();
                tradeRequest();
            }
        });


/*

        offerTrade.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,TradeRequest.class));
            }
        });


*/


        TextView holz_own= (TextView) findViewById(R.id.holz_anzeige_own);
        holz_own.setText(slash.concat(String.valueOf(player_holz_got)));

        TextView lehm_own= (TextView) findViewById(R.id.lehm_anzeige_own);
        lehm_own.setText(slash.concat(String.valueOf(player_lehm_got)));

        TextView schaf_own= (TextView) findViewById(R.id.schaf_anzeige_own);
        schaf_own.setText(slash.concat(String.valueOf(player_schaf_got)));

        TextView stroh_own= (TextView) findViewById(R.id.stroh_anzeige_own);
        stroh_own.setText(slash.concat(String.valueOf(player_stroh_got)));

        TextView stein_own= (TextView) findViewById(R.id.stein_anzeige_own);
        stein_own.setText(slash.concat(String.valueOf(player_stein_got)));

        TextView holz_want= (TextView) findViewById(R.id.holz_anzeige_want);
        holz_want.setText(String.valueOf(player_holz_want));

        TextView lehm_want= (TextView) findViewById(R.id.lehm_anzeige_want);
        lehm_want.setText(String.valueOf(player_lehm_want));

        TextView schaf_want= (TextView) findViewById(R.id.schaf_anzeige_want);
        schaf_want.setText(String.valueOf(player_schaf_want));

        TextView stroh_want= (TextView) findViewById(R.id.stroh_anzeige_want);
        stroh_want.setText(String.valueOf(player_stroh_want));

        TextView stein_want= (TextView) findViewById(R.id.stein_anzeige_want);
        stein_want.setText(String.valueOf(player_stein_want));

        TextView holz_give= (TextView) findViewById(R.id.holz_anzeige_give);
        holz_give.setText(String.valueOf(player_holz_give));

        TextView lehm_give= (TextView) findViewById(R.id.lehm_anzeige_give);
        lehm_give.setText(String.valueOf(player_lehm_give));

        TextView schaf_give= (TextView) findViewById(R.id.schaf_anzeige_give);
        schaf_give.setText(String.valueOf(player_schaf_give));

        TextView stroh_give= (TextView) findViewById(R.id.stroh_anzeige_give);
        stroh_give.setText(String.valueOf(player_stroh_give));

        TextView stein_give= (TextView) findViewById(R.id.stein_anzeige_give);
        stein_give.setText(String.valueOf(player_stein_give));





    }

    //Increase der Ressource Anzeige
    public void increaseInteger(View view, int i) {
        tradeDisplay [i]+=1;
        display(tradeDisplay [i], i);
    }
    //Decrease der Ressource Anzeige
    public void decreaseInteger(View view, int i) {
        if(!(tradeDisplay[i]==0)) {
            tradeDisplay[i]-=1;

            display(tradeDisplay[i], i);
        }
    }
    // Update der Anzeige
    private void display(int number, int btnID) {
        TextView displayInteger = (TextView) findViewById(buttonID[btnID]);
        displayInteger.setText(String.valueOf(number));
    }

    // Send Trade-Offer : CURRENTLY ONLY SENDING OFFER TO THE CLIENT WHO MADE THE OFFER (LOCAL-SEND)!!
    public void tradeRequest (){
        //startActivity(new Intent(MainActivity.this,TradeRequest.class));
        Intent passdata_intent = new Intent(this,TradeRequest.class);


        for(int i = 0; i < 10; i++){

            String name = "tradeDisplay";
            String number = String.valueOf(i);

            passdata_intent.putExtra("tradeDisplay".concat(number), tradeDisplay[i]);
        }

        startActivity(passdata_intent);


    }

}




