package rzeznika.ndtClient.controller.ButtonControl;

/**
 * Created by Lena on 11.07.2017.
 */

public class ButtonManager {

    /* ***************** BM PARAMETERS ****************** *//*
    *//** view class to control all UI Elements during the game *//*
    private ViewGame viewGame;
    *//** actual game board *//*
    private Game game;
    *//** Controller of the view Elements of the Player myself *//*
    private ViewPlayerOwn viewPlayerOwn;
    private ViewControllerStart viewControllerStart;
    *//** controller for the view elements of the gameboard *//*
    private ViewControllerGame ViewControllerGame;
    *//** Controller of the view Elements of the Start Scene Multiplayer *//*
    private ViewControllerStart viewControllerStartMP;
    *//** Controller of the view Elements of the Start Scene MultiplayerStart *//*
    private ViewControllerStart viewControllerStartMPStart;
    *//** Controller of the view Elements of the Start Scene Singleplayer *//*
    private ViewControllerStart viewControllerStartSP;
    *//** actual Client *//*
    private Client client;
    *//** actual GameMaster *//*





    private GameController gameController;
    private BM_Start bm_start;
    private BM_Buildings bm_buildings;
    //private BM_Robber bm_robber;


	*//* ****************** KONSTRUKTOR ******************** *//*

    *//**
     * Konstruktor ButtonManager
     * @param viewGame view class to control all UI Elements during the game
     * @param game actual game
     * @param player actual player
     * @param client actual client
     * @param viewControllerStart
     * @param ViewControllerGame
     *//*
    public ButtonManager(ViewGame viewGame, Game game, GameController player, Client client,
                         ViewControllerStart viewControllerStart,
                         ViewControllerGame ViewControllerGame
                         ) {
        this.viewGame = viewGame;

        this.gameController = player;
        this.client = client;
        this.game = game;
        this.viewControllerStart = viewControllerStart;
        this.ViewControllerGame = ViewControllerGame;
        this.viewControllerStartMP = viewControllerStartMP;
        this.viewControllerStartMPStart = viewControllerStartMPStart;
        this.viewControllerStartSP = viewControllerStartSP;
    }

    *//**
     * set up ButtomManager
     *//*
    public void setUpBM() {
        this.bm_start = new BM_Start();

        new BM_Dice(game, gameController, ViewControllerGame);
        //new BM_EndMove(client, ViewControllerGame);
        //this.bm_buildings = new BM_Buildings(game, client, ViewControllerGame, controller, gm);
        *//*new BM_Chat (game, client, ViewControllerGame);
        new BM_Trade_4zu1(ViewControllerGame, game,client);
        new BM_Trade_3zu1(ViewControllerGame, game,client);
        new BM_Trade_Brick(ViewControllerGame, game,client);
        new BM_Trade_Lumber(ViewControllerGame, game,client);
        new BM_Trade_Grain(ViewControllerGame, game,client);
        new BM_Trade_Wool(ViewControllerGame, game,client);
        new BM_Trade_Ore(ViewControllerGame, game,client);
        new BM_Trade_Opponent(ViewControllerGame,game,client);
        this.bm_robber = new BM_Robber(game,client, ViewControllerGame, viewGame.getViewPlayerMyself(), bm_buildings);
        new BM_DC(game,client, ViewControllerGame, bm_buildings, viewGame);
        new BM_Knightpower_LongestStreet(ViewControllerGame);*//*


    }

    *//*public BM_Start getBm_start() {
        return bm_start;
    }

    public BM_Buildings getBm_buildings() {
        return bm_buildings;
    }

    public BM_Robber getBm_robber() {
        return bm_robber;
    }*/









}

