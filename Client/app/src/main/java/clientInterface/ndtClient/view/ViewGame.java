package rzeznika.ndtClient.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import rzeznika.ndtClient.R;
import rzeznika.ndtClient.controller.GameController;
import rzeznika.ndtClient.model.Game;
import rzeznika.ndtClient.model.Player;

/**
 * Created by Lena on 07.07.2017.
 */

public class ViewGame extends Activity{

    private Game game;
    private GameController gameController;
    private Player player;


    private GameBoardView island;
    private ViewCards cards;

    private ViewPlayerOwn viewPlayerOwn;
    private ViewPlayerOpponent viewPlayerOpponent1;
    private ViewPlayerOpponent viewPlayerOpponent2;
    private ViewPlayerOpponent viewPlayerOpponent3;

    /** Index to save if Opponent1 declined a Trade offer */
    int x1 = 0;
    /** Index to save if Opponent2 declined a Trade offer */
    int x2 = 0;
    /** Index to save if Opponent3 declined a Trade offer */
    int x3 = 0;

    //ViewTrade2 trade = new ViewTrade2();
    //ViewTrade3zu1 trade2 = new ViewTrade3zu1();

    public ViewGame(Game game){
        this.game = game;
        this.island = new GameBoardView(game.getGameboard(), game);
        ViewCards cards = new ViewCards();
        this.viewPlayerOwn = new ViewPlayerOwn();
        this.viewPlayerOpponent1 = new ViewPlayerOpponent();
        this.viewPlayerOpponent2 = new ViewPlayerOpponent();
        this.viewPlayerOpponent3 = new ViewPlayerOpponent();

    }

    /* *************** GENERAL **************** */

    //Label state_information;

    //Label state_information_add;

    /** Image Views for DICE Pictures */

    ImageView Dice1_1 = (ImageView) findViewById(R.id.diceplace_1);
    ImageView Dice1_2 = (ImageView) findViewById(R.id.diceplace_1);
    ImageView Dice1_3 = (ImageView) findViewById(R.id.diceplace_1);
    ImageView Dice1_4 = (ImageView) findViewById(R.id.diceplace_1);
    ImageView Dice1_5 = (ImageView) findViewById(R.id.diceplace_1);
    ImageView Dice1_6 = (ImageView) findViewById(R.id.diceplace_1);

    ImageView Dice2_1 = (ImageView) findViewById(R.id.diceplace_2);
    ImageView Dice2_2 = (ImageView) findViewById(R.id.diceplace_2);
    ImageView Dice2_3 = (ImageView) findViewById(R.id.diceplace_2);
    ImageView Dice2_4 = (ImageView) findViewById(R.id.diceplace_2);
    ImageView Dice2_5 = (ImageView) findViewById(R.id.diceplace_2);
    ImageView Dice2_6 = (ImageView) findViewById(R.id.diceplace_2);

    Button btn_throwDice;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dice1_1.setImageResource(R.drawable.dice_1);
        Dice1_2.setImageResource(R.drawable.dice_2);
        Dice1_3.setImageResource(R.drawable.dice_3);
        Dice1_4.setImageResource(R.drawable.dice_4);
        Dice1_5.setImageResource(R.drawable.dice_5);
        Dice1_6.setImageResource(R.drawable.dice_6);

        Dice2_1.setImageResource(R.drawable.dice_1);
        Dice2_2.setImageResource(R.drawable.dice_2);
        Dice2_3.setImageResource(R.drawable.dice_3);
        Dice2_4.setImageResource(R.drawable.dice_4);
        Dice2_5.setImageResource(R.drawable.dice_5);
        Dice2_6.setImageResource(R.drawable.dice_6);
        btn_throwDice = (Button) findViewById(R.id.btn_throwDice);




        btn_throwDice.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                setDiceInvisible1();
                setDiceInvisible2();
                int[] diceresult = gameController.rollDicePair();
                setUpDice(diceresult);


            }
        });

    }




    /** Set Dice 1 Invisible */
    public void setDiceInvisible1() {

        Dice1_1.setVisibility(View.INVISIBLE);
        Dice1_2.setVisibility(View.INVISIBLE);
        Dice1_3.setVisibility(View.INVISIBLE);
        Dice1_4.setVisibility(View.INVISIBLE);
        Dice1_5.setVisibility(View.INVISIBLE);
        Dice1_6.setVisibility(View.INVISIBLE);
    }

    /** Set Dice 2 Invisible */
    public void setDiceInvisible2() {

        Dice2_1.setVisibility(View.INVISIBLE);
        Dice2_2.setVisibility(View.INVISIBLE);
        Dice2_3.setVisibility(View.INVISIBLE);
        Dice2_4.setVisibility(View.INVISIBLE);
        Dice2_5.setVisibility(View.INVISIBLE);
        Dice2_6.setVisibility(View.INVISIBLE);
    }

    /**
     * setUp Dices

     * @param dices hand over Dices Result
     */
    public void setUpDice(int[] dices) {
        //TODO
        int x;
        int y;
        x = dices[0];
        y = dices[1];
        int dice = x + y;
        if (dice == 7) {
            //robber();
        }
        //state_information_add.setText(player.getName() + " hat eine " + dice + " gewürfelt");
        // String status1 = game.getPlayer().get(0).getStatus();
        // String status2 = game.getPlayer().get(1).getStatus();
        // String status3 = game.getPlayer().get(2).getStatus();
        // String status4 = game.getPlayer().get(3).getStatus();
        // if(status1.equals("Handeln oder Bauen") || status2.equals("Handeln
        // oder Bauen") || status3.equals("Handeln oder Bauen") ||
        // status4.equals("Handeln oder Bauen")){

        if (x == 1) {
            setDiceInvisible1();
            Dice1_1.setVisibility(View.VISIBLE);

        } else if (x == 2) {
            setDiceInvisible1();
            Dice1_2.setVisibility(View.VISIBLE);

        } else if (x == 3) {
            setDiceInvisible1();
            Dice1_3.setVisibility(View.VISIBLE);

        } else if (x == 4) {
            setDiceInvisible1();
            Dice1_4.setVisibility(View.VISIBLE);

        } else if (x == 5) {
            setDiceInvisible1();
            Dice1_5.setVisibility(View.VISIBLE);

        } else if (x == 6) {
            setDiceInvisible1();
            Dice1_6.setVisibility(View.VISIBLE);

        }

        if (y == 1) {
            setDiceInvisible2();
            Dice2_1.setVisibility(View.VISIBLE);

        } else if (y == 2) {
            setDiceInvisible2();
            Dice2_2.setVisibility(View.VISIBLE);

        } else if (y == 3) {
            setDiceInvisible2();
            Dice2_3.setVisibility(View.VISIBLE);
        } else if (y == 4) {
            setDiceInvisible2();
            Dice2_4.setVisibility(View.VISIBLE);
        } else if (y == 5) {
            setDiceInvisible2();
            Dice2_5.setVisibility(View.VISIBLE);
        } else if (y == 6) {
            setDiceInvisible2();
            Dice2_6.setVisibility(View.VISIBLE);
        }
    }

    /** removes all Images of the pane_streets */
    public void removeStreets() {
        //TODO
    }

    /** removes all buildings on the pane_buildings */
    public void removeBuildings() {
        //TODO
    }

    /**
     * draws the island
     */
    public void setUpGameboard() {
        //TODO
    }

    /**
     * draws the playerOwn
     */
    public void setUpPlayerOwn() {
        //TODO
    }

    /** Set up Labels from Opponent 1 */
    public void setUpPlayerOpponent1() {
        //TODO
    }

    /** Set up Labels from Opponent 2 */
    public void setUpPlayerOpponent2() {
        //TODO
    }

    /** Set up Labels from Opponent 3 */
    public void setUpPlayerOpponent3() {
        //TODO
    }

    /** Set up Bank Labels */
    public void setUpBank() {
        //TODO
    }

    /** Set up Trade */
    public void setUpTrade2() {
        //TODO
    }

    /** setUp Trade 3zu1 */
    public void setUpTrade3zu1() {
        //TODO
    }



    public ViewCards getCards() {
        return cards;
    }

    public GameBoardView getIsland() {
        return island;
    }

    public ViewPlayerOwn getPlayerOwn() {
        return viewPlayerOwn;
    }

    public ViewPlayerOpponent getPlayerOpponent1() {
        return viewPlayerOpponent1;
    }

    public ViewPlayerOpponent getPlayerOpponent2() {
        return viewPlayerOpponent2;
    }

    public ViewPlayerOpponent getPlayerOpponent3() {
        return viewPlayerOpponent3;
    }


    public Game getGame() {
        return game;
    }

    /*public void setText(String text) {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {

                state_information_add.setText(text);

            }
        });*/

    public Button getBtn_throwDice() {
        return btn_throwDice;
    }

    public ImageView getDice1_1() {
        return Dice1_1;
    }

    public ImageView getDice1_2() {
        return Dice1_2;
    }

    public ImageView getDice1_3() {
        return Dice1_3;
    }

    public ImageView getDice1_4() {
        return Dice1_4;
    }

    public ImageView getDice1_5() {
        return Dice1_5;
    }

    public ImageView getDice1_6() {
        return Dice1_6;
    }

    public ImageView getDice2_1() {
        return Dice2_1;
    }

    public ImageView getDice2_2() {
        return Dice2_2;
    }

    public ImageView getDice2_3() {
        return Dice2_3;
    }

    public ImageView getDice2_4() {
        return Dice2_4;
    }

    public ImageView getDice2_5() {
        return Dice2_5;
    }

    public ImageView getDice2_6() {
        return Dice2_6;
    }




}
