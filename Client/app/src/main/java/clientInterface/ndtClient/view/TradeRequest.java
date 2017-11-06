package rzeznika.ndtClient.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import rzeznika.ndtClient.R;

/**
 * Created by steppinski on 26.07.17.
 */

public class TradeRequest extends Activity {

    private final int [] angebote = {R.id.angebot_holz, R.id.angebot_lehm, R.id.angebot_schaf, R.id.angebot_stroh, R.id.angebot_stein,
                                        R.id.nachfrage_holz, R.id.nachfrage_lehm, R.id.nachfrage_schaf, R.id.nachfrage_stroh, R.id.nachfrage_stein};

    private final int [] ressourceIV = {R.id.wood1, R.id.brick1, R.id.wool1, R.id.grain1, R.id.ore1,
                                        R.id.wood2, R.id.brick2, R.id.wool2, R.id.grain2, R.id.ore2};

    private final int [] ressourceImage = {R.drawable.harborlumber, R.drawable.harborbrick, R.drawable.harborwool, R.drawable.harborgrain, R.drawable.harborore};


    TextView angebotAnzeige [] = new TextView[10];
    int [] anzeige = new int[10];
    String[] getNames = {"player_holz_give", "player_lehm_give", "player_schaf_give", "player_stroh_give", "player_stein_give",
                        "player_holz_own", "player_lehm_own", "player_schaf_own", "player_stroh_own", "player_stein_own"};

    int [] tradeDisplay = new int [10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.traderequest);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        ImageView holz1 = (ImageView) findViewById(R.id.wood1); // Sets the graphical Resource of a Landtile
        holz1.setImageResource(R.drawable.harborlumber);

        ImageView[] bilderResource = new ImageView[10];

        for(int i = 0; i<10; i++) {
            if(i<5) {
                bilderResource[i] = (ImageView) findViewById(ressourceIV[i]);
                bilderResource[i].setImageResource(ressourceImage[i]);
            }
            else {
                bilderResource[i] = (ImageView) findViewById(ressourceIV[i]);
                bilderResource[i].setImageResource(ressourceImage[i-5]);
            }
        }



        getWindow().setLayout((int)(width*.8), (int)(height*.8));

        for(int i = 0;i<10; i++){
            angebotAnzeige[i] = (TextView) findViewById(angebote[i]);
            angebotAnzeige[i].setVisibility(View.INVISIBLE);
        }

        Bundle angebotData = getIntent().getExtras();
/*
        for (int i = 0; i>10;i++){
            anzeige[i] = angebotData.getInt(getNames[i]);
        }
        */

       for(int i = 0;i<10; i++){
            String name = "tradeDisplay";
            String number = String.valueOf(i);
            tradeDisplay[i] = angebotData.getInt(name.concat(number));
        }

        for(int i=0;i<10;i++){
            angebotAnzeige[i].setText(String.valueOf(tradeDisplay[i]));
            angebotAnzeige[i].setVisibility(View.VISIBLE);
        }








    }
}
