package rzeznika.ndtClient.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import rzeznika.ndtClient.R;
import rzeznika.ndtClient.model.Cross;
import rzeznika.ndtClient.model.Edge;
import rzeznika.ndtClient.model.Game;
import rzeznika.ndtClient.model.GameBoard;
import rzeznika.ndtClient.model.LandTile;
import rzeznika.ndtClient.model.Resource;


/**
 * Created by Lena on 28.06.2017.
 */

public class GameBoardView extends Activity{


    /**
     * the gameboard on which we play
     */
    private GameBoard gameboard;
    /** actual game */
    private Game game;

    private ArrayList<ImageView> imgStreet = new ArrayList<ImageView>();
    /** all imageson the pane_buildings */
    private ArrayList<ImageView> imgBuilding = new ArrayList<ImageView>();
    /** all edges on which the user could build a street */
    private Edge[] edgesToShow = new Edge[0];

    /**
     * Constructor for ViewIsland
     * @param gameboard actual gameboard
     * @param game actual game
     */
    public GameBoardView(GameBoard gameboard, Game game) {
        super();

        this.gameboard = gameboard;
        this.game = game;
    }

    public void buildIsland(ArrayList<ImageView> fieldsLand, ArrayList<ImageView> fieldsSea, ArrayList<ImageView> chips) {

        LandTile[] land = gameboard.getLand();
        LandTile[] sea = gameboard.getSea();

        for (int i = 0; i < land.length; i++) {

            LandTile field = land[i];

            if (field.getType() == Resource.BRICK) {
                fieldsLand.get(i).setImageResource(brick);
            } else if (field.getType() == Resource.GRAIN) {
                fieldsLand.get(i).setImageResource(grain);
            } else if (field.getType() == Resource.LUMBER) {
                fieldsLand.get(i).setImageResource(lumber);
            } else if (field.getType() == Resource.WOOL) {
                fieldsLand.get(i).setImageResource(wool);
            } else if (field.getType() == Resource.ORE) {
                fieldsLand.get(i).setImageResource(ore);
            } else {
                fieldsLand.get(i).setImageResource(desert);
            }
        }

        for (int i = 0; i < sea.length; i++) {
            LandTile field = sea[i];

            if (field.getType() == Resource.SEA) {
                fieldsSea.get(i).setImageResource(water);
            }
        }

    }

    public void buildChipNr(ArrayList<ImageView> numbers){

        LandTile[] land = gameboard.getLand();

        for (int i = 0; i < land.length; i++) {

            LandTile field = land[i];
            int nr = field.getChipNr();

            if (nr == 3) {
                numbers.get(i).setImageResource(chip3);
            } else if (nr == 4) {
                numbers.get(i).setImageResource(chip4);
            } else if (nr == 4) {
                numbers.get(i).setImageResource(chip4);
            }else if (nr == 5) {
                numbers.get(i).setImageResource(chip5);
            }else if (nr == 6) {
                numbers.get(i).setImageResource(chip6);
            }else if (nr == 7) {
                numbers.get(i).setImageResource(chip7);
            }else if (nr == 8) {
                numbers.get(i).setImageResource(chip8);
            }else if (nr == 9) {
                numbers.get(i).setImageResource(chip9);
            }else if (nr == 10) {
                numbers.get(i).setImageResource(chip10);
            }else if (nr == 11) {
                numbers.get(i).setImageResource(chip11);
            } else if (nr == 12) {
                numbers.get(i).setImageResource(chip12);
            } else {
                numbers.get(i).setImageResource(chip0);
            }

        }
    }

    public void buildCross(Cross cross){

        if (cross.getSettlement() != null){
            if(cross.isPossibleCity()){

                //TODO

            }



            cross.setPossibleCity(false);

        } else {

            //TODO
        }




        Cross[] crosses = gameboard.getCrosses();








    }

    public void setUpSettlements(){

        //TODO

    }

    public void setUpCities(){

        //TODO

    }







    int grain = R.drawable.polygonlandgrain;//weizen
    int brick = R.drawable.polygonlandbrick;//ziegel
    int lumber = R.drawable.polygonlandlumber;//holz
    int desert = R.drawable.polygonlanddesert;//wüste
    int wool = R.drawable.polygonlandwool;//wolle
    int ore = R.drawable.polygonlandore;//erz

    int water = R.drawable.polygonsea;//wasser

    int cityblue= R.drawable.cityblau;
    int cityred= R.drawable.cityrot;
    int cityorange= R.drawable.cityorange;
    int citywhite= R.drawable.cityweiss;
    int citygrey = R.drawable.citygrau;

    int settlementblue= R.drawable.settlementblau;
    int settlementred = R.drawable.settlementrot;
    int settlementwhite = R.drawable.settlementweiss;
    int settlementorange = R.drawable.settlementorange;
    int settlementgrey = R.drawable.settlementgrau;


    int chip3 = R.drawable.number3;
    int chip4 = R.drawable.number4;
    int chip5 = R.drawable.number5;
    int chip6 = R.drawable.number6;
    int chip7 = R.drawable.number7;
    int chip8 = R.drawable.number8;
    int chip9 = R.drawable.number9;
    int chip10 = R.drawable.number10;
    int chip11 = R.drawable.number11;
    int chip12 = R.drawable.number12;
    int chip0 = R.drawable.number0;

    int crossempty = R.drawable.crossempty;


    //x=0;
    private ImageView cross02,cross03,cross04,cross05,cross06,cross07,cross08;
    //x= 1
    private ImageView cross11,cross12,cross13,cross14,cross15,cross16,cross17,cross18,cross19;
    //x= 2
    private ImageView cross20,cross21,cross22,cross23,cross24,cross25,cross26,cross27,cross28,cross29,cross210;
    //x= 3
    private ImageView cross30,cross31,cross32,cross33,cross34,cross35,cross36,cross37,cross38,cross39,cross310;
    //x= 4
    private ImageView cross41,cross42,cross43,cross44,cross45,cross46,cross47,cross48,cross49;
    //x=5
    private ImageView cross52,cross53,cross54,cross55,cross56,cross57,cross58;

    //streets gerade
    private ImageView streeteA,streetAB,streetBC,streetCf,streetgD,streetDE,streetEF,streetFG,streetGh,streetiH,streetHI,streetIJ,streetJK,streetKL,streetLj,streetkM,streetMN,streetNO,streetOP,streetPl,streetmQ,streetQR,streetRS,streetSn;
    //streets rechtsrunter
    private ImageView streetAb,streetBc,streetCd,streetDA,streetEB,streetFC,streetGf,streetHD,streetIE,streetJF,streetKG,streetLh,streetkH,streetMI,streetNJ,streetOK,streetPL,streetmM,streetQN,streetRO,streetSP,streetoQ,streetPR,streetqS;
    //Streets rechtshoch
    private ImageView streetaA,streetbB,streetcC,streeteD,streetAE,streetBF,streetCG,streetgH,streetDI,streetEJ,streetFK,streetGL,streetHM,streetIN,streetJO,streetKP,streetLl,streetMQ,streetNR,streetOS,streetPn,streetQp,streetRq,streetSr;





    ArrayList<ImageView> crosses;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gameboardview, container, false);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameboardview);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        ImageView field_A =(ImageView) findViewById(R.id.field_A);
        ImageView field_B =(ImageView) findViewById(R.id.field_B);
        ImageView field_C =(ImageView) findViewById(R.id.field_C);
        ImageView field_D =(ImageView) findViewById(R.id.field_D);
        ImageView field_E =(ImageView) findViewById(R.id.field_E);
        ImageView field_F =(ImageView) findViewById(R.id.field_F);
        ImageView field_G =(ImageView) findViewById(R.id.field_G);
        ImageView field_H =(ImageView) findViewById(R.id.field_H);
        ImageView field_I =(ImageView) findViewById(R.id.field_I);
        ImageView field_J =(ImageView) findViewById(R.id.field_J);
        ImageView field_K =(ImageView) findViewById(R.id.field_K);
        ImageView field_L =(ImageView) findViewById(R.id.field_L);
        ImageView field_M =(ImageView) findViewById(R.id.field_M);
        ImageView field_N =(ImageView) findViewById(R.id.field_N);
        ImageView field_O =(ImageView) findViewById(R.id.field_O);
        ImageView field_P =(ImageView) findViewById(R.id.field_P);
        ImageView field_Q =(ImageView) findViewById(R.id.field_Q);
        ImageView field_Rf =(ImageView) findViewById(R.id.field_R);
        ImageView field_S =(ImageView) findViewById(R.id.field_S);
        ImageView field_a =(ImageView) findViewById(R.id.field_a);
        ImageView field_b =(ImageView) findViewById(R.id.field_b);
        ImageView field_c =(ImageView) findViewById(R.id.field_c);
        ImageView field_d =(ImageView) findViewById(R.id.field_d);
        ImageView field_e =(ImageView) findViewById(R.id.field_e);
        ImageView field_f =(ImageView) findViewById(R.id.field_f);
        ImageView field_g =(ImageView) findViewById(R.id.field_g);
        ImageView field_h =(ImageView) findViewById(R.id.field_h);
        ImageView field_i =(ImageView) findViewById(R.id.field_i);
        ImageView field_j =(ImageView) findViewById(R.id.field_j);
        ImageView field_k =(ImageView) findViewById(R.id.field_k);
        ImageView field_l =(ImageView) findViewById(R.id.field_l);
        ImageView field_m =(ImageView) findViewById(R.id.field_m);
        ImageView field_n =(ImageView) findViewById(R.id.field_n);
        ImageView field_o =(ImageView) findViewById(R.id.field_o);
        ImageView field_p =(ImageView) findViewById(R.id.field_p);
        ImageView field_q =(ImageView) findViewById(R.id.field_q);
        ImageView field_r =(ImageView) findViewById(R.id.field_r);

        field_A.setImageResource(R.drawable.leerland);

        ArrayList<ImageView> fieldsLand = new ArrayList<>();
        fieldsLand.add(field_A);
        fieldsLand.add(field_B);
        fieldsLand.add(field_C);
        fieldsLand.add(field_D);
        fieldsLand.add(field_E);
        fieldsLand.add(field_F);
        fieldsLand.add(field_G);
        fieldsLand.add(field_H);
        fieldsLand.add(field_I);
        fieldsLand.add(field_J);
        fieldsLand.add(field_K);
        fieldsLand.add(field_L);
        fieldsLand.add(field_M);
        fieldsLand.add(field_N);
        fieldsLand.add(field_O);
        fieldsLand.add(field_P);
        fieldsLand.add(field_Q);
        fieldsLand.add(field_Rf);
        fieldsLand.add(field_S);

        ArrayList<ImageView> fieldsSea = new ArrayList<>();
        fieldsSea.add(field_a);
        fieldsSea.add(field_b);
        fieldsSea.add(field_c);
        fieldsSea.add(field_d);
        fieldsSea.add(field_e);
        fieldsSea.add(field_f);
        fieldsSea.add(field_g);
        fieldsSea.add(field_h);
        fieldsSea.add(field_i);
        fieldsSea.add(field_j);
        fieldsSea.add(field_k);
        fieldsSea.add(field_l);
        fieldsSea.add(field_m);
        fieldsSea.add(field_n);
        fieldsSea.add(field_o);
        fieldsSea.add(field_p);
        fieldsSea.add(field_q);
        fieldsSea.add(field_r);


        ImageView numberA =(ImageView) findViewById(R.id.numberA);
        ImageView numberB =(ImageView) findViewById(R.id.numberB);
        ImageView numberC =(ImageView) findViewById(R.id.numberC);
        ImageView numberD =(ImageView) findViewById(R.id.numberD);
        ImageView numberE =(ImageView) findViewById(R.id.numberE);
        ImageView numberF =(ImageView) findViewById(R.id.numberF);
        ImageView numberG =(ImageView) findViewById(R.id.numberG);
        ImageView numberH =(ImageView) findViewById(R.id.numberH);
        ImageView numberI =(ImageView) findViewById(R.id.numberI);
        /*
        ImageView numberJ =(ImageView) findViewById(R.id.numberJ);
        räuber?
        */
        ImageView numberK =(ImageView) findViewById(R.id.numberK);
        ImageView numberL =(ImageView) findViewById(R.id.numberL);
        ImageView numberM =(ImageView) findViewById(R.id.numberM);
        ImageView numberN =(ImageView) findViewById(R.id.numberN);
        ImageView numberO =(ImageView) findViewById(R.id.numberO);
        ImageView numberP =(ImageView) findViewById(R.id.numberP);
        ImageView numberQ =(ImageView) findViewById(R.id.numberQ);
        ImageView numberRf =(ImageView)findViewById(R.id.numberR);
        ImageView numberS =(ImageView) findViewById(R.id.numberS);

        ArrayList<ImageView> numbers = new ArrayList<>();
        numbers.add(numberA);
        numbers.add(numberB);
        numbers.add(numberC);
        numbers.add(numberD);
        numbers.add(numberE);
        numbers.add(numberF);
        numbers.add(numberG);
        numbers.add(numberH);
        numbers.add(numberI);
        //numbers.add(numberJ);
        numbers.add(numberK);
        numbers.add(numberL);
        numbers.add(numberM);
        numbers.add(numberN);
        numbers.add(numberO);
        numbers.add(numberP);
        numbers.add(numberQ);
        numbers.add(numberRf);
        numbers.add(numberS);


        /*
        Koordinaten: crossXY);
        */

        ImageView cross20 =(ImageView) findViewById(R.id.cross20);
        ImageView cross30 =(ImageView) findViewById(R.id.cross30);
        ImageView cross40 =(ImageView) findViewById(R.id.cross40);
        ImageView cross50 =(ImageView) findViewById(R.id.cross50);
        ImageView cross60 =(ImageView) findViewById(R.id.cross60);
        ImageView cross70 =(ImageView) findViewById(R.id.cross70);
        ImageView cross80 =(ImageView) findViewById(R.id.cross80);

        ImageView cross11 =(ImageView) findViewById(R.id.cross11);
        ImageView cross21 =(ImageView) findViewById(R.id.cross21);
        ImageView cross31 =(ImageView) findViewById(R.id.cross31);
        ImageView cross41 =(ImageView) findViewById(R.id.cross41);
        ImageView cross51 =(ImageView) findViewById(R.id.cross51);
        ImageView cross61 =(ImageView) findViewById(R.id.cross61);
        ImageView cross71 =(ImageView) findViewById(R.id.cross71);
        ImageView cross81 =(ImageView) findViewById(R.id.cross81);
        ImageView cross91 =(ImageView) findViewById(R.id.cross91);

        ImageView cross02 =(ImageView) findViewById(R.id.cross02);
        ImageView cross12 =(ImageView) findViewById(R.id.cross12);
        ImageView cross22 =(ImageView) findViewById(R.id.cross22);
        ImageView cross32 =(ImageView) findViewById(R.id.cross32);
        ImageView cross42 =(ImageView) findViewById(R.id.cross42);
        ImageView cross52 =(ImageView) findViewById(R.id.cross52);
        ImageView cross62 =(ImageView) findViewById(R.id.cross62);
        ImageView cross72 =(ImageView) findViewById(R.id.cross72);
        ImageView cross82 =(ImageView) findViewById(R.id.cross82);
        ImageView cross92 =(ImageView) findViewById(R.id.cross92);
        ImageView cross102 =(ImageView) findViewById(R.id.cross102);

        ImageView cross03 =(ImageView) findViewById(R.id.cross03);
        ImageView cross13 =(ImageView) findViewById(R.id.cross13);
        ImageView cross23 =(ImageView) findViewById(R.id.cross23);
        ImageView cross33 =(ImageView) findViewById(R.id.cross33);
        ImageView cross43 =(ImageView) findViewById(R.id.cross43);
        ImageView cross53 =(ImageView) findViewById(R.id.cross53);
        ImageView cross63 =(ImageView) findViewById(R.id.cross63);
        ImageView cross73 =(ImageView) findViewById(R.id.cross73);
        ImageView cross83 =(ImageView) findViewById(R.id.cross83);
        ImageView cross93 =(ImageView) findViewById(R.id.cross93);
        ImageView cross103 =(ImageView) findViewById(R.id.cross103);

        ImageView cross14 =(ImageView) findViewById(R.id.cross14);
        ImageView cross24 =(ImageView) findViewById(R.id.cross24);
        ImageView cross34 =(ImageView) findViewById(R.id.cross34);
        ImageView cross44 =(ImageView) findViewById(R.id.cross44);
        ImageView cross54 =(ImageView) findViewById(R.id.cross54);
        ImageView cross64 =(ImageView) findViewById(R.id.cross64);
        ImageView cross74 =(ImageView) findViewById(R.id.cross74);
        ImageView cross84 =(ImageView) findViewById(R.id.cross84);
        ImageView cross94 =(ImageView) findViewById(R.id.cross94);

        ImageView cross25 =(ImageView) findViewById(R.id.cross25);
        ImageView cross35 =(ImageView) findViewById(R.id.cross35);
        ImageView cross45 =(ImageView) findViewById(R.id.cross45);
        ImageView cross55 =(ImageView) findViewById(R.id.cross55);
        ImageView cross65 =(ImageView) findViewById(R.id.cross65);
        ImageView cross75 =(ImageView) findViewById(R.id.cross75);
        ImageView cross85 =(ImageView) findViewById(R.id.cross85);

        ArrayList<ImageView> crossrow0 = new ArrayList<>();
        crossrow0.add(cross20);
        crossrow0.add(cross30);
        crossrow0.add(cross40);
        crossrow0.add(cross50);
        crossrow0.add(cross60);
        crossrow0.add(cross70);
        crossrow0.add(cross80);

        ArrayList<ImageView> crossrow1 = new ArrayList<>();
        crossrow0.add(cross11);
        crossrow0.add(cross21);
        crossrow0.add(cross31);
        crossrow0.add(cross41);
        crossrow0.add(cross51);
        crossrow0.add(cross61);
        crossrow0.add(cross71);
        crossrow0.add(cross81);
        crossrow0.add(cross91);

        ArrayList<ImageView> crossrow2 = new ArrayList<>();
        crossrow0.add(cross02);
        crossrow0.add(cross12);
        crossrow0.add(cross22);
        crossrow0.add(cross32);
        crossrow0.add(cross42);
        crossrow0.add(cross52);
        crossrow0.add(cross62);
        crossrow0.add(cross72);
        crossrow0.add(cross82);
        crossrow0.add(cross92);
        crossrow0.add(cross102);

        ArrayList<ImageView> crossrow3 = new ArrayList<>();
        crossrow0.add(cross03);
        crossrow0.add(cross13);
        crossrow0.add(cross23);
        crossrow0.add(cross33);
        crossrow0.add(cross43);
        crossrow0.add(cross53);
        crossrow0.add(cross63);
        crossrow0.add(cross73);
        crossrow0.add(cross83);
        crossrow0.add(cross93);
        crossrow0.add(cross103);

        ArrayList<ImageView> crossrow4 = new ArrayList<>();
        crossrow0.add(cross14);
        crossrow0.add(cross24);
        crossrow0.add(cross34);
        crossrow0.add(cross44);
        crossrow0.add(cross54);
        crossrow0.add(cross64);
        crossrow0.add(cross74);
        crossrow0.add(cross84);
        crossrow0.add(cross94);

        ArrayList<ImageView> crossrow5 = new ArrayList<>();
        crossrow0.add(cross25);
        crossrow0.add(cross35);
        crossrow0.add(cross45);
        crossrow0.add(cross55);
        crossrow0.add(cross65);
        crossrow0.add(cross75);
        crossrow0.add(cross85);


        for (int y=0;y<=5;y++){
            for(int x=0; x <= 10; x++){

            }
        }





        /*strassengerade*/
        ImageView streeteA =(ImageView) findViewById(R.id.streeteA);
        ImageView streetAB =(ImageView) findViewById(R.id.streetAB);
        ImageView streetBC =(ImageView) findViewById(R.id.streetBC);
        ImageView streetCf =(ImageView) findViewById(R.id.streetCf);
        ImageView streetgD =(ImageView) findViewById(R.id.streetgD);
        ImageView streetDE =(ImageView) findViewById(R.id.streetDE);
        ImageView streetEF =(ImageView) findViewById(R.id.streetEF);
        ImageView streetFG =(ImageView) findViewById(R.id.streetFG);
        ImageView streetGh =(ImageView) findViewById(R.id.streetGh);
        ImageView streetiH =(ImageView) findViewById(R.id.streetiH);
        ImageView streetHI =(ImageView) findViewById(R.id.streetHI);
        ImageView streetIJ =(ImageView) findViewById(R.id.streetIJ);
        ImageView streetJK =(ImageView) findViewById(R.id.streetJK);
        ImageView streetKL =(ImageView) findViewById(R.id.streetKL);
        ImageView streetLj =(ImageView) findViewById(R.id.streetLj);
        ImageView streetkM =(ImageView) findViewById(R.id.streetkM);
        ImageView streetMN =(ImageView) findViewById(R.id.streetMN);
        ImageView streetNO =(ImageView) findViewById(R.id.streetNO);
        ImageView streetOP =(ImageView) findViewById(R.id.streetOP);
        ImageView streetPl =(ImageView) findViewById(R.id.streetPl);
        ImageView streetmQ =(ImageView) findViewById(R.id.streetmQ);
        ImageView streetQR =(ImageView) findViewById(R.id.streetQR);
        ImageView streetRS =(ImageView) findViewById(R.id.streetRS);
        ImageView streetSn =(ImageView) findViewById(R.id.streetSn);


        /* strassen rechtshoch  */
        ImageView streetaA =(ImageView) findViewById(R.id.streetaA);
        ImageView streetbB =(ImageView) findViewById(R.id.streetbB);
        ImageView streetcC =(ImageView) findViewById(R.id.streetcC);
        ImageView streeteD =(ImageView) findViewById(R.id.streeteD);
        ImageView streetAE =(ImageView) findViewById(R.id.streetAE);
        ImageView streetBF =(ImageView) findViewById(R.id.streetBF);
        ImageView streetCG =(ImageView) findViewById(R.id.streetCG);
        ImageView streetgH =(ImageView) findViewById(R.id.streetgH);
        ImageView streetDI =(ImageView) findViewById(R.id.streetDI);
        ImageView streetEJ =(ImageView) findViewById(R.id.streetEJ);
        ImageView streetFK =(ImageView) findViewById(R.id.streetFK);
        ImageView streetGL =(ImageView) findViewById(R.id.streetGL);
        ImageView streetHM =(ImageView) findViewById(R.id.streetHM);
        ImageView streetIN =(ImageView) findViewById(R.id.streetIN);
        ImageView streetJO =(ImageView) findViewById(R.id.streetJO);
        ImageView streetKP =(ImageView) findViewById(R.id.streetKP);
        ImageView streetLl =(ImageView) findViewById(R.id.streetLl);
        ImageView streetMQ =(ImageView) findViewById(R.id.streetMQ);
        ImageView streetNR =(ImageView) findViewById(R.id.streetNR);
        ImageView streetOS =(ImageView) findViewById(R.id.streetOS);
        ImageView streetPn =(ImageView) findViewById(R.id.streetPn);
        ImageView streetQp =(ImageView) findViewById(R.id.streetQp);
        ImageView streetRq =(ImageView) findViewById(R.id.streetRq);
        ImageView streetSr =(ImageView) findViewById(R.id.streetSr);

        /* strassen rechtsrunter */
        ImageView streetAb =(ImageView) findViewById(R.id.streetAb);
        ImageView streetBc =(ImageView) findViewById(R.id.streetBc);
        ImageView streetCd =(ImageView) findViewById(R.id.streetCd);
        ImageView streetDA =(ImageView) findViewById(R.id.streetDA);
        ImageView streetEB =(ImageView) findViewById(R.id.streetEB);
        ImageView streetFC =(ImageView) findViewById(R.id.streetFC);
        ImageView streetGf =(ImageView) findViewById(R.id.streetGf);
        ImageView streetHD =(ImageView) findViewById(R.id.streetHD);
        ImageView streetIE =(ImageView) findViewById(R.id.streetIE);
        ImageView streetJF =(ImageView) findViewById(R.id.streetJF);
        ImageView streetKG =(ImageView) findViewById(R.id.streetKG);
        ImageView streetLh =(ImageView) findViewById(R.id.streetLh);
        ImageView streetkH =(ImageView) findViewById(R.id.streetkH);
        ImageView streetMI =(ImageView) findViewById(R.id.streetMI);
        ImageView streetNJ =(ImageView) findViewById(R.id.streetNJ);
        ImageView streetOK =(ImageView) findViewById(R.id.streetOK);
        ImageView streetPL =(ImageView) findViewById(R.id.streetPL);
        ImageView streetmM =(ImageView) findViewById(R.id.streetmM);
        ImageView streetQn =(ImageView) findViewById(R.id.streetQN);
        ImageView streetRO =(ImageView) findViewById(R.id.streetRO);
        ImageView streetSP =(ImageView) findViewById(R.id.streetSP);
        ImageView streetoQ =(ImageView) findViewById(R.id.streetoQ);
        ImageView streetpR =(ImageView) findViewById(R.id.streetpR);
        ImageView streetqS =(ImageView) findViewById(R.id.streetqS);









/*
        cross20.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "siedlung oder stadt setzen", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        wuerfel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "würfelnneu", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
    }




/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/

}



