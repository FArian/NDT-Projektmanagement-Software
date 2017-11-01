package actors;

import models.*;

import java.util.*;

/**
 * Created by F.Arian on 08.07.17.
 */
public class ServerLogic {
    /** random Object **/
    private Random random;

    /**instance of the class game **/
    private Game game;
    /**instance of the class gameLogic **/
    private GameLogic gameLogic;

    /** constructor for serverLogic
     *
     * @param game we use
     */
    public ServerLogic(Game game) {
        this.random = new Random();
        this.game = game;
        gameLogic = new GameLogic(game);
    }

    /**
     * Simulates a pair of dices being rolled
     *
     * @return int[] - [0]: first dice, [1]: second dice
     */
    public int[] rollDices() {
        int[] dices = new int[2];
        dices[0] = random.nextInt(6) + 1;
        dices[1] = random.nextInt(6) + 1;
        return dices;
    }

    /** used to calculate the earnings of all players after a dice throw
     *
     * @param dice was thrown
     * @return earnings
     */

    public Map<Player, List<Resource>> getEarnings(int dice) {

        Map<Player, List<Resource>> earnings = new HashMap<Player, List<Resource>>();
        List<LandTile> tiles = gameLogic.getTilefromNumberChip(dice);
        List<Resource> resPlayer0 = new ArrayList<Resource>();
        List<Resource> resPlayer1 = new ArrayList<Resource>();
        List<Resource> resPlayer2 = new ArrayList<Resource>();
        List<Resource> resPlayer3 = new ArrayList<Resource>();
        ArrayList<Player> allPlayers = game.getPlayer();
        for (LandTile k : tiles) {
            if (!k.isRobber()) {
                Resource res = k.getType();
                ArrayList<Player> playersHelp = gameLogic.getPlayerBuildings(k);
                for (int i = 0; i < playersHelp.size(); i++) {
                    if (playersHelp.get(i).equals(allPlayers.get(0))) {
                        resPlayer0.add(res);
                    } else if (playersHelp.get(i).equals(allPlayers.get(1))) {
                        resPlayer1.add(res);
                    } else if (playersHelp.get(i).equals(allPlayers.get(2))) {
                        resPlayer2.add(res);
                    } else if (allPlayers.size() > 3 && playersHelp.get(i).equals(allPlayers.get(3))) {
                        resPlayer3.add(res);
                    }
                }
            }
        }
        earnings.put(allPlayers.get(0), resPlayer0);
        earnings.put(allPlayers.get(1), resPlayer1);
        earnings.put(allPlayers.get(2), resPlayer2);
        if (allPlayers.size() > 3) {
            earnings.put(allPlayers.get(3), resPlayer3);
        }
        return earnings;
    }

    /** used to handle the stealing of a card with robber
     *
     * @param target to steal from
     * @return Resources stolen card
     */
    public Resource handelStealCard(int target) {
        Resource card = null;
        Player player = game.getPlayer(target);
        ArrayList<Resource> res = player.getRessources();
        if (res.size() > 0) {
            Random random = new Random();
            int cardIndex = random.nextInt(res.size());
            card = res.get(cardIndex);
        }
        return card;
    }

    /** used to gove the players their start Resources
     *
     * @param clientHandler to get res
     * @param location he sat his setlement
     * @return int[] res
     */
    @SuppressWarnings("incomplete-switch")
    public int[] getStartResources(GameActor clientHandler, Cross location) {
        int[] startRes = new int[5];
        int grain = 0;
        int wool = 0;
        int ore = 0;
        int lumber = 0;
        int brick = 0;

        ArrayList<LandTile> lands = gameLogic.getNeighbourLands(location);
        for (LandTile l : lands) {
            Resource res = l.getType();
            if (res != null) {
                switch (res) {
                    case Getreide:
                        grain++;
                        clientHandler.getPlayer().addResource(Resource.Getreide);
                        break;
                    case Wolle:
                        wool++;
                        clientHandler.getPlayer().addResource(Resource.Wolle);
                        break;
                    case Erz:
                        ore++;
                        clientHandler.getPlayer().addResource(Resource.Erz);
                        break;
                    case Holz:
                        lumber++;
                        clientHandler.getPlayer().addResource(Resource.Holz);
                        break;
                    case Lehm:
                        brick++;
                        clientHandler.getPlayer().addResource(Resource.Lehm);
                        break;
                }
            }
        }
        startRes[0] = grain;
        startRes[1] = wool;
        startRes[2] = ore;
        startRes[3] = lumber;
        startRes[4] = brick;
        return startRes;
    }

}
