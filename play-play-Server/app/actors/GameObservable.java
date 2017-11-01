package actors;

import akka.actor.ActorRef;
import com.sun.org.apache.regexp.internal.RE;
import models.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by F.Arian on 08.07.17.
 * Class that handles one game. Holds all the clients and information of that
 * game and provides methods to change it.
 */

public class GameObservable extends Observable {
    /**
     * the clients that are connected with that game
     */

    List<GameActor> clients;
    /**
     * the game that is handled
     */
    private Game game;
    /**
     * The players of the game - connected to the clients -> each client has one
     * player in that list
     */
    private ArrayList<Player> players;
    /**
     * provides methods for the contruction logic
     */
    private ConstructorLogic constructorLogic;
    /**
     * provides methods for the card logic
     */
    private CardLogic cardLogic;
    /**
     * provides methods for the game logic
     */
    private GameLogic gameLogic;
    /**
     * the message writer for that game. Can write messages to all clients in
     * the GameObserver list
     */
    private MessageWriter mWriter;
    /**
     * status of the game
     */
    private boolean gameRunning;
    /**
     * value if blue is still available
     */
    private boolean blueAvailable;
    /**
     * value if red is still available
     */
    private boolean redAvailable;
    /**
     * value if white is still available
     */
    private boolean whiteAvailable;
    /**
     * value if orange is still available
     */
    private boolean orangeAvailable;
    /**
     * counter for the rounds that are already done in that game
     */
    private int round;
    /**
     * the current value of the dices
     */
    private int dice;
    /**
     * the client which turn it is now
     */
    private int whosTurnIndex;
    /**
     * tells the tradeindex, starting at -1 since method starts by counting it up
     **/
    private int tradeIndex = -1;
    /**
     * variable to check and set if the obsarvable Lobby is still open
     */
    private boolean gameStartClosed;
    /**
     * Constructor for the GameHandler
     *
     * @param mWriter message writer
     */
    private ServerLog serverLog;
    private ServerLogic serverLogic;
    /**
     * id counter that increases with each connected client
     */
    private static int idCounter;

    public GameObservable() {
        this.idCounter = 1;
        this.clients = new ArrayList<GameActor>();
        this.mWriter = new MessageWriter();
        this.mWriter.setClient(clients);
        this.addObserver(mWriter);
        this.game = new Game();
        this.players = new ArrayList<Player>();
        this.serverLogic = new ServerLogic(game);
        this.gameLogic = new GameLogic(game);
        this.setConstructionLogic(new ConstructorLogic(game));
        this.setCardLogic(new CardLogic(game));
        this.setRound(1);
        this.gameStartClosed = false;
        this.gameRunning = true;
        this.blueAvailable = true;
        this.redAvailable = true;
        this.whiteAvailable = true;
        this.orangeAvailable = true;
        this.whosTurnIndex = 0;
        this.dice = 0;
        this.serverLog = new ServerLog();

    }

    /**
     * Starts a new GameObservable which opens and handles a new Game. Connected
     * clients will automatically be put in that game. Maximum number of clients
     * that can connect are 4!
     *
     * @param out from GameActor
     */
    /**
     * private void gameStart(ActorRef out) {
     * while (clients.size() < 4) {
     * if (!isGameClosed()) {
     * GameActor clinet = new GameActor(out, this);
     * this.addGameActor(clinet);
     * for (int i = 0; i < this.getClients().size(); i++) {
     * serverLog.info("Neuer Client mit id: " + this.getClients().get(i).getId());
     * }
     * } else {
     * serverLog.info("Versuchte Anmeldung eines Client nach Spielbeginn");
     * }
     * }
     * }
     * <p>
     * /**
     * Adds a new GameActor client. May change values of the players or the game!
     * Writes messages to the clients! Notifies Observers!
     *
     * @param client actual client
     */
    public void addGameActor(GameActor client) {
        Information info = new Information("");
        clients.add(client);
        client.setId(idCounter);
        client.getPlayer().setId(idCounter);
        System.out.println("Player ID: " + client.getId() + " ID:" + client.getPlayer().getId());
        System.out.println("Player Status:" + client.getStatus());
        System.out.println(client.getPlayer());
        System.out.println("Client Size:" + clients.size());
        info.setPlayer(client.getPlayer());
        setChanged();
        notifyObservers(info);

    }


    private void setRound(int round) {
        this.round = round;
    }

    private void setCardLogic(CardLogic cardLogic) {
        this.cardLogic = cardLogic;
    }

    private void setConstructionLogic(ConstructorLogic constructorLogic) {
        this.constructorLogic = constructorLogic;
    }

    /**
     * Getter for the game that is handled
     *
     * @return Game game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Setter for the game
     *
     * @param game actual game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Getter for the message writer
     *
     * @return MessageWriter mWriter
     */
    public MessageWriter getmWriter() {
        return mWriter;
    }

    /**
     * Setter for the message writer
     *
     * @param mWriter message writer
     */
    public void setmWriter(MessageWriter mWriter) {
        this.mWriter = mWriter;
    }


    /**
     * Getter for the value if the lobby is already closed
     *
     * @return boolean
     */
    public boolean isGameClosed() {
        return this.gameStartClosed;
    }

    /**
     * Getter for all GameActor
     *
     * @return List<GameActor> clients list of all clients
     */
    public List<GameActor> getClients() {
        return clients;
    }

    public GameActor getClient(Player player) {
        for (GameActor c : clients) {
            if (c.getPlayer().equals(player)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Getter for the player
     *
     * @return ArrayList<Player> players List of all players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Checks if the game is ready to start and handles the game start if
     * necessary. May change values of the players or the game! Writes messages
     * to the clients!
     */
    public void checkAndSetGameStart() {
        int clientNumber = clients.size();
        int clientReady = 0;

        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getStatus().equals("Wartet auf Spielbeginn")) {
                clientReady++;
                serverLog.info("ClientReady " + clientReady);
            }
        }

        if ((clientNumber == 4)) {
            this.gameStartClosed = true;
            handleGameStart();
            serverLog.info("Game Start with :" +getPlayers().size());
        }

    }

    public void handleGameStart() {
        System.out.println(this.getClass() + " Test: VOR setStartingOrder");
        for (int i = 0; i < players.size(); i++) {
            System.out.println("Client mit id " + clients.get(i).getId() + " hat status_ " + clients.get(i).getStatus()
                    + " und sein Player hat id: " + clients.get(i).getPlayer().getId() + " und status: "
                    + clients.get(i).getPlayer().getStatus());
        }
        game.createGameboard();
        mWriter.sendCard(game.getGameboard());
        setStartingOrder();
        // ____________TEST
        System.out.println(this.getClass() + " Test: NACH setStartingOrder");
        for (int i = 0; i < players.size(); i++) {
            System.out.println("Client mit id " + clients.get(i).getId() + " hat status  " + clients.get(i).getStatus()
                    + " und sein Player hat id: " + clients.get(i).getPlayer().getId() + " und status: "
                    + clients.get(i).getPlayer().getStatus());
        }
        for (int i = 1; i < clients.size(); i++) {
            clients.get(i).setStatus("Warten");
        }
        clients.get(0).setStatus("Dorf bauen");

        // ____________TEST

        for (int i = 0; i < players.size(); i++) {
            System.out.println("Client mit id " + clients.get(i).getId() + " hat status_ " + clients.get(i).getStatus()
                    + " und sein Player hat id: " + clients.get(i).getPlayer().getId() + " und status: "
                    + clients.get(i).getPlayer().getStatus());
        }
    }

    private void setStartingOrder() {

        ArrayList<int[]> dices = new ArrayList<int[]>();
        for (int i = 0; i < 3; i++) {
            dices.add(serverLogic.rollDices());
        }
        Map<GameActor, Integer> map = new HashMap<GameActor, Integer>();
        int one = dices.get(0)[0] + dices.get(0)[1];
        int two = dices.get(1)[0] + dices.get(1)[1];
        int three = dices.get(2)[0] + dices.get(2)[1];

        map.put(clients.get(0), one);
        map.put(clients.get(1), two);
        map.put(clients.get(2), three);

        ArrayList<Integer> ints = new ArrayList<Integer>();
        ints.add(one);
        ints.add(two);
        ints.add(three);

        ArrayList<GameActor> clientOrder = new ArrayList<GameActor>();

        // 4 Clients
        if (clients.size() == 4) {
            int[] fourth = serverLogic.rollDices();
            int four = fourth[0] + fourth[1];
            map.put(clients.get(3), four);
            ints.add(four);
            dices.add(fourth);
            Collections.sort(ints);

            // TEST
            for (int i = 0; i < ints.size(); i++) {
                System.out.println(this.getClass() + " GEORDNETE LISTE " + ints.get(i));
            }
            // -------

            for (int i = ints.size() - 1; i >= 0; i--) {
                for (GameActor k : map.keySet()) {
                    if (map.get(k) == ints.get(i)) {
                        clientOrder.add(k);
                        map.remove(k);
                        break;
                    }
                }
            }

            for (int i = 0; i < clients.size(); i++) {
                mWriter.sendDices(clients.get(i).getPlayer(), dices.get(i));
            }

            // 3 Clients
        } else {
            Collections.sort(ints);
            for (int i = ints.size() - 1; i >= 0; i--) {
                for (GameActor k : map.keySet()) {
                    if (map.get(k) == ints.get(i)) {
                        clientOrder.add(k);
                        map.remove(k);
                        break;
                    }
                }
            }

            for (int i = 0; i < dices.size(); i++) {
                mWriter.sendDices(clients.get(i).getPlayer(), dices.get(i));
            }
        }

        this.clients = clientOrder;
        setOrderedPlayers();

    }

    /**
     * Setter for the Players when the Clients have been reordered
     */
    private void setOrderedPlayers() {
        ArrayList<Player> newPlayerList = new ArrayList<Player>();
        for (int i = 0; i < clients.size(); i++) {
            newPlayerList.add(clients.get(i).getPlayer());
        }
        game.setPlayer(newPlayerList);
    }

    /**
     * Checks if the color is still available
     *
     * @param color chosen color
     * @return boolean true if color is still available
     */
    public boolean isAvailable(String color) {
        if (color.equals("Rot")) {
            return redAvailable;
        }
        if (color.equals("Blau")) {
            return blueAvailable;
        }
        if (color.equals("Weiß")) {
            return whiteAvailable;
        }
        if (color.equals("Orange")) {
            return orangeAvailable;
        }
        return false;
    }

    /**
     * Setter for a color that is used
     *
     * @param color
     */
    public void setColorUsed(String color) {
        if (color.equals("Rot")) {
            redAvailable = false;
        } else if (color.equals("Blau")) {
            blueAvailable = false;
        } else if (color.equals("Weiß")) {
            whiteAvailable = false;
        } else if (color.equals("Orange")) {
            orangeAvailable = false;
        }
    }

    public void setDice(int owndice) {
        this.dice = owndice;
    }

    /**
     * Handles the earning of resources after the dices where rolled. May change
     * values of the players or the game! Writes messages to the clients!
     *
     * @param owndice dice number
     */

    public void handleEarning(int owndice) {
        Map<Player, List<Resource>> earnings = serverLogic.getEarnings(owndice);
        for (Player p : earnings.keySet()) {
            ArrayList<Resource> resForP = (ArrayList<Resource>) earnings.get(p);
            if (resForP.size() > 0) {
                for (int i = 0; i < resForP.size(); i++) {
                    if (game.hasResLeft(resForP.get(i))) {
                        p.addResource(resForP.get(i));
                    } else {
                        getClient(p).sendError();
                        resForP.remove(i);
                    }
                }
                int[] resources = gameLogic.getResAmount(resForP);
                //Testen
                System.out.println(this.getClass() + "Rohstoffe hinzugefügt für spieler" + p.getName());
                for (int i = 0; i < resForP.size(); i++) {
                    System.out.println(resForP.get(i));
                }
                mWriter.sendEarnings(p, resources);
            }
        }

    }

    /**
     * Checks for all players if the amount of their resources is over 7, except
     * for the player who rolled the seven, he should already be checked. Sets
     * the status to "Karten wegen Räuber abgeben" if it is over 7.
     *
     * @param rolledDices player who has rolled the dices
     */

    public void checkResforSeven(Player rolledDices) {
        for (GameActor c : clients) {
            if (!c.getPlayer().equals(rolledDices)) {
                if (c.getPlayer().getRes() > 7) {
                    c.setStatus("Karten wegen Räuber abgeben");
                }
            }
        }
    }

    public boolean isCardsForSevenFinished() {
        for (GameActor c : clients) {
            if (c.getStatus().equals("Karten wegen Räuber abgeben")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Changes which players turn it is. May change values of the players or the
     * game! Writes messages to the clients!
     * <p>
     * lastClient client who had the last turn
     */

    public void nextOne() {
        this.printGameRes();
        int indexNext = whosTurnIndex + 1;
        if (indexNext >= clients.size()) {
            indexNext = 0;
            round++;
            System.out.println(this.getClass() + " Die aktuelle Runde wurde erhöht auf : " + round);
            if (round == 2 || round == 3) {
                switchDirection();
            }
        }
        whosTurnIndex = indexNext;
        if (round > 3) {
            clients.get(whosTurnIndex).setStatus("Dorf bauen");
        } else {
            while (clients.get(whosTurnIndex).getStatus().equals("Karten wegen Räuber abgeben")) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            clients.get(whosTurnIndex).setStatus("Würfeln");
        }


    }

    private void switchDirection() {
        //TEST
        for (int i = 0; i < clients.size(); i++) {
            System.out.println(this.getClass() + " Reihenfolge davor: " + i + ". = " + clients.get(i).getId());
        }
        Collections.reverse(clients);
        // TEST
        for (int i = 0; i < clients.size(); i++) {
            System.out.println(this.getClass() + " Reihenfolge danach: " + i + ". = " + clients.get(i).getId());
        }
    }

    /**
     * Test method that prints out the amount of resources the game has left
     */

    private void printGameRes() {
        int[] res = game.getRessources();
        System.out.println(this.getClass() + " Im Spiel gibt es noch folgende Rohstoffe:");
        System.out.println(this.getClass() + " Getreide: " + res[0]);
        System.out.println(this.getClass() + " Wolle: " + res[1]);
        System.out.println(this.getClass() + " Erz: " + res[2]);
        System.out.println(this.getClass() + " Holz: " + res[3]);
        System.out.println(this.getClass() + " Lehm: " + res[4]);
    }

    public int getRound() {
        return round;
    }

    /**
     * Sets a settlement. May change values of the players or the game! Writes
     * messages to the clients! Handles the resources.
     *
     * @param player player who has build a street
     * @param pos    Position of the new settlement
     * @param costs  costs for the settlement
     */
    public void setSettlement(Player player, Position[] pos, boolean costs) {
        gameLogic.locationToCross(pos).setSettlement(player);
        player.removeAvailableSettlement();
        player.addVictoryPoint(1);
        checkForVictory(player);
        if (costs) {
            //--Remove Resources from players Resources-------
            player.removeResource(Resource.Holz);
            player.removeResource(Resource.Lehm);
            player.removeResource(Resource.Getreide);
            player.removeResource(Resource.Wolle);
            //-------------Add Resources to Game--------------
            game.addResources(Resource.Holz);
            game.addResources(Resource.Lehm);
            game.addResources(Resource.Getreide);
            game.addResources(Resource.Wolle);
        }
        mWriter.sendBuilding(player.getId(), "Dorf", pos);
        this.handleStreetInterrupted(gameLogic.locationToCross(pos));

    }

    private void handleStreetInterrupted(Cross newBuilding) {
        if (game.getHasLongestStreet() != null) {

            List<Edge> edges = game.getEdges(newBuilding);

            Map<Player, Long> players = edges.stream().map(edge -> {
                return edge.getStreet() != null ? edge.getStreet().getPlayer() : null;
            }).filter(player -> {
                return player != null;
            }).collect(Collectors.groupingBy(w -> w, Collectors.counting()));
            Player player = null;
            for (Map.Entry<Player, Long> entry : players.entrySet()) {
                if (entry.getValue() > 1) {
                    player = entry.getKey();
                }
            }

            if (player != null && !player.equals(newBuilding.getPlayer())
                    && player.equals(game.getHasLongestStreet())) {
                this.recalculateLongestStreet();

            }
        }
    }

    private void recalculateLongestStreet() {
        game.resetLongestStreet();
        int longest = 0;
        Player ruler = null;
        Edge[] edges = game.getGameboard().getEdges();
        for (Edge e : edges) {
            if (e.getStreet() != null) {
                int length = gameLogic.streetLength(e, e.getStreet().getPlayer());
                if (length > longest) {
                    longest = length;
                    ruler = e.getStreet().getPlayer();
                }
            }
        }
        if (longest > game.getLongestStreet()) {
            game.setLongestStreet(longest);
            game.setHasLongestStreet(ruler);
            ruler.setHasLongestStreet(true);
            mWriter.sendLongestStreet(ruler);
        } else {
            mWriter.sendLongestStreetReset();
        }
    }

    /**
     * Checks if the player has 10 or more victory points. When that is the
     * case, it ends the game and writes a message to all clients.
     *
     * @param player player whom victory points are checked
     */
    private void checkForVictory(Player player) {
        if (player.getPlayerVictoryPoints() > 9) {
            mWriter.sendWinner(player);
            gameRunning = false;
        }
    }

    /**
     * Method that removes the startresources that the players get for the
     * second settlement from the bank.
     *
     * @param startRes start resources
     */
    public void removeStartRes(int[] startRes) {
        for (int i = 0; i < startRes.length; i++) {
            while (startRes[i] > 0) {
                switch (i) {
                    case 0:
                        game.removeResources(Resource.Getreide);
                        break;
                    case 1:
                        game.removeResources(Resource.Wolle);
                        break;
                    case 2:
                        game.removeResources(Resource.Erz);
                        break;
                    case 3:
                        game.removeResources(Resource.Holz);
                        break;
                    case 4:
                        game.removeResources(Resource.Lehm);
                        break;
                }
                startRes[i]--;
            }
        }

    }

    /**
     * sets a street. May change values of the players or the game! Writes
     * messages to the clients! Handles the resources.
     *
     * @param player
     * @param pos
     * @param costs
     */
    public void setStreet(Player player, Position[] pos, boolean costs) {
        Edge edge = gameLogic.locationToEdge(pos);
        edge.setStreet(new Street(player));
        player.removeAvailableStreet();
        if (costs) {
            player.removeResource(Resource.Holz);
            player.removeResource(Resource.Lehm);
            //-----------------------------------
            game.addResources(Resource.Holz);
            game.addResources(Resource.Lehm);
        }
        mWriter.sendBuilding(player.getId(), "Straße", pos);
        if (round > 2) {
            checkLongestStreet(player, edge);
        }
    }

    /**
     * Checks if a player has earned the longest street and handles giving the
     * special card in that case. Sends messages to the clients.
     *
     * @param player player who is checked if he has the longest street
     * @param edge   edge were the last street was built
     */
    private void checkLongestStreet(Player player, Edge edge) {
        if (gameLogic.longestStreet(edge, player)) {
            mWriter.sendLongestStreet(player);
            player.addVictoryPoint(2);
            checkForVictory(player);
        }
    }

    /**
     * Sets a city. May change values of the players or the game! Writes
     * messages to the clients! Handles the resources.
     *
     * @param player player who has built a city
     * @param pos    position of the new city
     * @param b      boolean
     */
    public void setCity(Player player, Position[] pos, boolean b) {
        gameLogic.locationToCross(pos).setCity(player);
        player.removeAvailableCity();
        player.addVictoryPoint(1);
        checkForVictory(player);
        player.removeResource(Resource.Getreide);
        player.removeResource(Resource.Getreide);
        player.removeResource(Resource.Erz);
        player.removeResource(Resource.Erz);
        player.removeResource(Resource.Erz);
        //--------------------------------------
        game.addResources(Resource.Getreide);
        game.addResources(Resource.Getreide);
        game.addResources(Resource.Erz);
        game.addResources(Resource.Erz);
        game.addResources(Resource.Erz);
        mWriter.sendBuilding(player.getId(), "Stadt", pos);

    }

    /**
     * Returns which turn it is
     *
     * @return GameObservable whosTurn
     */

    public int getWhosTurn() {
        return whosTurnIndex;
    }

    /**
     * Getter for the card logic
     *
     * @return CardLogic
     */
    public CardLogic getCardLogic() {
        return cardLogic;
    }

    /**
     * Checks if a player has earned the largest army and handles giving the
     * special card in that case. Sends messages to the clients.
     *
     * @param player player who is checked if he has the largest army
     */
    public void checkForLargestArmy(Player player) {
        if (gameLogic.largestArmy(player)) {
            mWriter.sendKnightPower(player);
            player.addVictoryPoint(2);
            checkForVictory(player);
        }
    }

    /**
     * Getter for the constructor Logic
     *
     * @return ConstructorLogic
     */
    public ConstructorLogic getConstructionLogic() {
        return constructorLogic;
    }

    /**
     * Method that handles the resources of all the players when a player has
     * played a monopoly card. Sends messages to the clients.
     *
     * @param player player who has played a monopoly card
     * @param res    resource of the monopol
     * @param type   of the resource
     */
    public void handleMonopolyRes(Player player, Resource res, String type) {
        int earning = 0;
        for (GameActor c : clients) {
            if (!c.getPlayer().equals(player)) {
                Player opponent = c.getPlayer();
                int costs = 0;
                while (opponent.playerhasResource(res)) {
                    opponent.removeResource(res);
                    costs++;
                    earning++;
                }
                if (costs > 0) {
                    mWriter.sendMonopolyCosts(opponent, type, costs);
                }
            }
        }
        if (earning > 0) {
            for (int i = 0; i < earning; i++) {
                player.addResource(res);
            }
            mWriter.sendMonopolyEarning(player, type, earning);
        }
    }

    /**
     * Handles switching resources when the player wants to trade with the bank.
     * Sends messages to the clients.
     *
     * @param player player who wants to trade with the bank
     * @param give   resources that the player gives to the bank
     * @param get    resources that the player gets from the bank
     */
    public void seeTrade(Player player, int[] give, int[] get) {
        player.addResIndex(get);
        game.removeRes(get);
        player.removeResIndex(get);
        game.addRes(give);
        mWriter.sendEarnings(player, get);
        mWriter.sendCosts(player, "Handel", give, true);
    }

    public void setTrade(Player player, int[] give, int[] get) {
        tradeIndex++;
        for (GameActor i : clients) {
            i.setTradeActive(true);
        }
        game.getTrade().setTradeIndex(tradeIndex);
        game.getTrade().setGet(get);
        game.getTrade().setGive(give);
        mWriter.sendTradeRequest(player, give, get, tradeIndex);
    }

    /**
     * Handles the trade between two player, if both players are okay with it.
     * Switches the resources. Sends messages to the clients.
     *
     * @param player player who is trading
     * @param id     of the player
     */

    public void doTrade(Player player, int id) {
        int[] give = game.getTrade().getGive();
        int[] get = game.getTrade().getGet();
        int k = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId() == id) {
                k = i;
            }
        }
        player.addResIndex(get);
        player.removeResIndex(give);
        players.get(k).addResIndex(give);
        players.get(k).removeResIndex(get);
        for (GameActor i : clients) {
            i.setTradeActive(false);
        }
        mWriter.sendEarnings(player, get);
        mWriter.sendCosts(player, "Handel", give, true);
        mWriter.sendEarnings(players.get(k), give);
        mWriter.sendCosts(players.get(k), "Handel", get, true);
    }


    /**
     * Increases the id counter and returns therefore the current id that is not
     * yet used.
     *
     * @return
     */
    public synchronized int getAndIncreaseIDCounter() {
        if (idCounter == 0) {
            idCounter++;
        }

        return idCounter++;
    }

}
