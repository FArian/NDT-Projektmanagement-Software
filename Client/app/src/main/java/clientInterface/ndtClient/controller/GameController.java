package rzeznika.ndtClient.controller;

import java.util.ArrayList;



import rzeznika.ndtClient.model.Cross;
import rzeznika.ndtClient.model.DevelopmentCard;
import rzeznika.ndtClient.model.Edge;
import rzeznika.ndtClient.model.Game;
import rzeznika.ndtClient.model.HarberType;
import rzeznika.ndtClient.model.Player;
import rzeznika.ndtClient.model.Resource;
import rzeznika.ndtClient.model.Street;

import java.util.Observable;
import java.util.Random;

/**
 * Created by F.Arian on 06.06.17.
 * Class that provides methods to make changes to the games status
 * ( all players and the whole gameboard ); extends Observable.
 */

public class GameController extends Observable {

    /**
     * Object game from Class Game
     */
    private Game game;
    /**
     * list of Players
     */
    private ArrayList<Player> players;
    /**
     * instance of the class gameLogic
     **/
    private GameLogic gameLogic;
    /**
     * Error Message
     */
    private String errorMessage;
    /**
     * instance of the class cardLogic
     **/
    private CardLogic cardLogic;
    /** instance of the class constructionLogic **/
    private ConstructorLogic constructionLogic;

    /**
     * instance of the class buttonManager
     **/
    //private ButtonManager buttonManager;
    /**
     * instance of the class tradeLogic
     */
    private TradeLogic tradeLogic;
    private Login log;


    /**
     * Constructor for the GameController, that changes the game's status
     *
     * @param game
     */

    Random random = new Random();


    public GameController(Game game) {
        this.log=new Login("GameController");
        this.game = game;
        this.players = game.getPlayer();
        this.gameLogic = new GameLogic(game);
        this.cardLogic = new CardLogic(game);
        this.constructionLogic = new ConstructorLogic (game);
        log.getLogin().info("Keine Fehlermeldung bei GameContoller!");
    }


    public Game getGame() {
        return game;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setConnectionLost() {
        setChanged();
        notifyObservers(new Information("ServerError"));
    }

    /**
     * Sets the id of the own player
     *
     * @param id ID player
     */
    public void setOwnId(int id) {

        players.get(0).setId(id);
        log.getLogin().info(+id+" für player with PicNummer: "+players.get(0).getPictureNr());

    }

    /**
     * Sets the status of the player with that id
     *
     * @param id     ID player
     * @param status status player
     */
    public void setStatus(int id, String status) {
        if (game.getPlayer(id) != null) {
            game.getPlayer(id).setStatus(status);
            setChanged();
            notifyObservers(new Information("StatusChanged"));
        }
    }

    /**
     * setter for own resAmount
     *
     * @param amountofRes Resources
     */
    public void sendOwnResAmount(int amountofRes) {
        game.getPlayers().get(0).setPlayerResAmount(amountofRes);

    }

    /**
     * sets the amount of resources an opponent has
     *
     * @param playerId  to set
     * @param resAmount to set
     */
    public void sendOpponentResAmount(int playerId, int resAmount) {
        game.getPlayer(playerId).setPlayerResAmount(resAmount);
        Information information = new Information("OpponentChanged");
        information.setAmount(resAmount);
        information.setPlayer(game.getPlayer(playerId));
        setChanged();
        notifyObservers(information);

    }

    /**
     * Sets the current number0 of the dices
     *
     * @param dices dices
     */
    public void setDices(int[] dices) {
        Player player = game.getPlayer(dices[0]);
        int dice[] = new int[2];
        dice[0] = dices[1];
        dice[1] = dices[2];
        Information information = new Information("DicesChanged");
        information.setDices(dice);
        information.setPlayer(player);
        setChanged();
        notifyObservers(information);
    }


    /**
     * used to throw the dices
     **/
    public Integer rollDices() {

        return random.nextInt(6) + random.nextInt(6) + 2;
    }

    /**
     * Simulates a pair of dices being rolled
     *
     * @return int[] - [0]: first dice, [1]: second dice
     */
    public int[] rollDicePair() {
        int[] dices = new int[2];
        dices[0] = random.nextInt(6) + 1;
        dices[1] = random.nextInt(6) + 1;
        return dices;
    }

    /**
     * sets the ids of the other Players
     *
     * @param id for Player
     */

    public void setId(int id) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId() == 0) {
                players.get(i).setId(id);
                break;
            }
        }
    }

    /**
     * Sets the name of the player with that id
     *
     * @param id   ID player
     * @param name Name player
     */

    public void setName(int id, String name) {
        game.getPlayer(id).setName(name);
        setChanged();
        notifyObservers(new Information("PlayerNamesChanged"));
    }

    /**
     * Sets the color of the player with that id
     *
     * @param id    ID player
     * @param color Color Player
     */
    public void setColor(int id, String color) {
        game.getPlayer(id).setColor(color);
        setChanged();
        notifyObservers(new Information("PlayerColorsChanged"));
    }

    /**
     * Sets the victory points from the player with that id
     *
     * @param id     ID player
     * @param number number0 victory points
     */

    public void setVictoryPoints(int id, int number) {
        if (game.getPlayer(id) != null) {
            game.getPlayer(id).setPlayerVictoryPoints(number);
            setChanged();
            if (game.getPlayer().get(0).getId() == id) {
                notifyObservers(new Information("PlayerChanged"));
            } else {
                notifyObservers(new Information("OpponentChanged"));
            }
        }

    }

    /**
     * Gives the longest street card to the player with that id
     *
     * @param id ID player
     */

    public void setHasLongestStreet(int id) {
        if (id != 0) {
            game.setHasLongestStreet(game.getPlayer(id));
        } else {
            game.resetLongestStreet();
        }
        setChanged();
        notifyObservers(new Information("LongestStreetChanged"));
    }

    public void resetHasLongestStreet() {
        setChanged();
        notifyObservers(new Information("LongestStreetReset"));
    }

    /**
     * Gives the knightpower card to the player with that id
     *
     * @param id ID player
     */

    public void setHasKnightPower(int id) {
        game.setHasKnightPower(game.getPlayer(id));
        setChanged();
        notifyObservers(new Information("KnightsChanged"));

    }

    /**
     * adds several resources to the player with id
     *
     * @param msgId     of the layer
     * @param resources to add
     */

    public void addResList(int msgId, ArrayList<Resource> resources) {
        for (int i = 0; i < resources.size(); i++) {
            game.getPlayer(msgId).addResource(resources.get(i));
        }
        //Information for Android Monitor or console
        System.out.println(this.getClass() + " Spieler: " + game.getPlayer(msgId) + " und seine Anzahl an Resourcen ist: " + game.getPlayer(msgId).getRes() + " und in Liste sind: " + game.getPlayer(msgId).getResources().size());
        Information information = new Information("Earning");
        information.setPlayer(game.getPlayer(msgId));
        information.setContent(resources);
        setChanged();
        notifyObservers(information);
    }

    /**
     * show game started
     */

    public void gameStarted() {
        setChanged();
        notifyObservers(new Information("GameStarted"));

    }

    /**
     * used to steal a resource via the robber
     *
     * @param player   to steal
     * @param resource to steal
     */

    public void removeRobberResource(Player player, Resource resource) {
        Information information = new Information("CardStolen");
        information.setResourceCard(resource);
        information.setPlayer(player);
        setChanged();
        notifyObservers(information);
    }

    /**
     * Removes a specific resource from the players cards
     *
     * @param id             player
     * @param removeResource resources
     */
    public void removeResource(int id, Resource removeResource) {
        Player player = this.game.getPlayer(id);
        player.removeResource(removeResource);
        setChanged();
        Information information = new Information("Costs");
        ArrayList<Resource> resourceArrayList = new ArrayList<Resource>();
        resourceArrayList.add(removeResource);
        information.setContent(resourceArrayList);
        notifyObservers(information);
    }

    public GameLogic getGameLogic() {
        return gameLogic;

    }

    /**
     * Sets a settlement from the player at that cross
     *
     * @param cross  cross
     * @param player player object
     */
    public void setSettlement(Cross cross, Player player) {
        cross.setSettlement(player);
        removeAvailableSettlement(player.getId());
        setChanged();
        notifyObservers(new Information("CrossChanged"));
        setHarbor(player.getId(), cross);
    }

    private void removeAvailableSettlement(int id) {
        this.game.getPlayer(id).removeAvailableSettlement();
        setChanged();
        notifyObservers(new Information("PlayerChanged"));
    }

    /**
     * Sets the harbour if the cross belongs to it
     *
     * @param cross cross position
     * @param id    ID player
     */
    private void setHarbor(int id, Cross cross) {
        Cross[] crosses = this.game.getGameboard().getHarborCrosses();
        for (int i = 0; i < crosses.length; i++) {
            if (cross.equals(crosses[i])) {
                addHarbour(id, this.game.getGameboard().getHarbors()[i / 2]);
            }
        }
    }

    /**
     * Grants the access to a specific harbour to a player
     *
     * @param id     ID player
     * @param harbor harbors
     */
    private void addHarbour(int id, HarberType harbor) {
        this.game.getPlayer(id).addHarbor(harbor);
        Information information = new Information("HarborsChanged");
        information.setPlayer(this.game.getPlayer(id));
        setChanged();
        notifyObservers(information);
    }

    /**
     * Sets a city from the player at that cross. Removes an available city,
     * adds an available settlement. Removes the settlement from that cross.
     *
     * @param cross  cross
     * @param player player object
     */

    public void setCity(Cross cross, Player player) {
        cross.setCity(player);
        cross.removeSettlement();
        removeAvailableCity(player.getId());
        setChanged();
        notifyObservers(new Information("CrossChanged"));
    }

    /**
     * Removes a city from all cities the player with that id has still left
     *
     * @param id ID player
     */

    private void removeAvailableCity(int id) {
        this.game.getPlayer(id).removeAvailableCity();
        setChanged();
        notifyObservers(new Information("PlayerChanged"));
    }

    /**
     * Sets a street for the player at that Edge. Removes an available street
     * from the players streets.
     *
     * @param edge   edge
     * @param player player object
     */

    public void setStreet(Edge edge, Player player) {
        edge.setStreet(new Street(player));
        removeAvailableStreet(player.getId());
        setChanged();
        notifyObservers(new Information("EdgeChanged"));

    }

    /**
     * Removes a street from all streets the player with that id has still left
     *
     * @param id ID player
     */
    public void removeAvailableStreet(int id) {
        this.game.getPlayer(id).removeAvailableStreet();
        setChanged();
        notifyObservers(new Information("PlayerChanged"));
    }

    /**
     * adds one victorypoint for the player with id
     **/
    public void addVictoryCard(int id) {
        game.getPlayer(id).addVictoryCard();
        setChanged();
        notifyObservers(new Information("VictoryCardsChanged"));
    }

    /**
     * Adds a development card to the players cards
     *
     * @param id               ID player
     * @param developmentCards development cards player
     */
    public void addDevelopmentCard(int id, DevelopmentCard developmentCards) {
        game.getPlayer(id).addDevCards(developmentCards); //TODO Unsicher, ob richtige Methode
        setChanged();
        notifyObservers(new Information("DevelopmentCardChanged"));
    }

    /**
     * Sets the robber on a specific tile
     *
     * @param tile Landtile
     *             new position of the robber
     */
    public void setRobber(int tile) {
        game.getGameboard().setRobber(tile);
        setChanged();
        notifyObservers(new Information("RobberChanged"));

    }

    /**
     * Getter for the TradeLogic
     *
     * @return TradeLogic
     */
    public TradeLogic getTradeLogic() {
        return tradeLogic;
    }

    public void setKnightTarget(int targetId) {
        Information info = new Information("KnightTarget");
        info.setPlayer(game.getPlayer(targetId));
        setChanged();
        notifyObservers(info);

    }
    /**
     * removes the devCard from the player
     *
     * @param player
     *            who used the dev Card
     * @param type
     *            of the used Card
     */
    public void removeDevCard(Player player, String type) {
        if (player.getId() == game.getPlayer().get(0).getId()) {
            switch (type) {
                case "Ritter":
                    player.removeDevCards(DevelopmentCard.KNIGHT);
                    break;
                case "Straßenbau":
                    player.removeDevCards(DevelopmentCard.ROADBUILDING);
                    break;
                case "Monopol":
                    player.removeDevCards(DevelopmentCard.MONOPOLY);
                    break;
                case "Erfindung":
                    player.removeDevCards(DevelopmentCard.INVENTION);
                    break;
                case "Siegpunkt":
                    player.removeDevCards(DevelopmentCard.VICTORYPOINT);
                    player.addVictoryPoint(1);
                    break;
            }
        } else {
            player.decreaseDevCard();
        }
    }


    /**
     * shows that a dev Card was played
     *
     * @param type     of the played card
     * @param playerId of the player using it
     */
    public void devCardPlayed(String type, int playerId) {
        Player player = game.getPlayer(playerId);
        removeDevCard(player, type);
        Information info = new Information("DevCardPlayed");
        switch (type) {
            case "Ritter":
                info.setDevelopmentCard(DevelopmentCard.KNIGHT);
                player.addKnights();
                break;
            case "Straßenbau":
                info.setDevelopmentCard(DevelopmentCard.ROADBUILDING);
                break;
            case "Monopol":
                info.setDevelopmentCard(DevelopmentCard.MONOPOLY);
                break;
            case "Erfindung":
                info.setDevelopmentCard(DevelopmentCard.INVENTION);
                break;
            case "Siegpunkt":
                info.setDevelopmentCard(DevelopmentCard.VICTORYPOINT);
                break;
        }
        info.setPlayer(player);
        setChanged();
        notifyObservers(info);
    }

    /**
     * shows that the player with id has won the game
     *
     * @param winner id of the winner
     */
    public void gameEnd(int winner) {
        game.setWinner(winner);
        setChanged();
        Information info = new Information("GameEnd");
        info.setPlayer(game.getPlayer(winner));
        notifyObservers(info);

    }
}

