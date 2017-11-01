package actors;

import models.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by F.Arian on 08.07.17.
 */
public class GameLogic {
    /** instance of the class Game **/
    private Game game;


    /**
     * Constructor of the GameLogic class
     *
     * @param game
     */
    public GameLogic(Game game) {
        this.game = game;

    }

    /**
     * Checks if the active player has the longest street. In case that is true
     * it updates all references. Has to be called after a player has built a
     * street. Checks all the adjacent streets based on the last street built.
     *
     * @param lastEdge last Edge
     *            -> where the last street was built
     * @param player Player object
     */
    public boolean longestStreet(Edge lastEdge, Player player) {


        int longestStreet = streetLength(lastEdge, player);

        if (longestStreet > game.getLongestStreet()) {
            game.setLongestStreet(longestStreet);
            if (game.getHasLongestStreet() == null) {
                game.setHasLongestStreet(player);
                player.setHasLongestStreet(true);
                return true;

            } else if (!game.getHasLongestStreet().equals(player)) {
                game.getHasLongestStreet().setHasLongestStreet(false);
                game.setHasLongestStreet(player);
                player.setHasLongestStreet(true);
                return true;
            }

        }
        return false;

    }

    /**
     * Returns the longest street which includes the edge
     * @param edge
     * @param player
     * @return int
     */
    public int streetLength(Edge edge, Player player) {
        List<Edge> visited = new ArrayList<Edge>();
        visited.add(edge);
        int leftPath = getPath(edge.getCross1(), visited, player);
        int rightPath = getPath(edge.getCross2(), visited, player);
        int longestStreet = leftPath + rightPath + 1;
        return longestStreet;
    }

    /**
     * Returns the longest path from a specific edge
     *
     * @param cross cross
     * @param visited
     * @param player player object
     * @return int: length of the path
     */

    public int getPath(Cross cross, List<Edge> visited, Player player) {
        List<Integer> path = new ArrayList<Integer>();
        List<Edge> edges = game.getEdges(cross);
        if ((cross.getCity() == null && cross.getSettlement() == null) || (cross.getPlayer().equals(player))) {
            for (Edge e : edges) {
                if (!visited.contains(e) && e.getStreet() != null && e.getStreet().getPlayer().equals(player)) {
                    visited.add(e);
                    // List<Edge> visitedClone = new ArrayList<Edge>();
                    // visitedClone.addAll(visited);
                    path.add(1 + getPath(e.getCross1().equals(cross) ? e.getCross2() : e.getCross1(), visited, player));
                }
            }
            if (path.isEmpty()) {
                return 0;
            }
            return Collections.max(path);
        } else {
            return 0;
        }
    }

    /**
     * Checks if the current player has the largest army. In case it is true, it
     * updates all references.
     *
     * @return boolean
     */
    public boolean largestArmy(Player player) {
        if (player.getKnights() > game.getKnightPower()) {
            if (game.getHasKnightPower() != null) {
                Player knightPower = game.getHasKnightPower();
                knightPower.addVictoryPoint(-2);
                knightPower.setHasKnightPower(false);
            }
            game.setHasKnightPower(player);
            player.setHasKnightPower(true);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the neighbouring crossempty and the cross at that position are
     * free
     *
     * @param cross
     *             the centre cross
     * @return boolean are all neighbour crossempty free
     */
    public boolean spaceRule(Cross cross) {
        ArrayList<Edge> edges = game.getEdges(cross);
        for (int i = 0; i < edges.size(); i++) {
            if (game.hasBuilding(edges.get(i).getCross1()) || game.hasBuilding(edges.get(i).getCross2())) {
                return false;
            }
        }
        return true;
    }

    /**
     * is used to change a cross in its position for JSON
     *
     * @param cross
     *            to cahnge
     * @return Position []
     */


    public Position[] giveLands(Cross cross) {
        Position[] lands = new Position[3];
        int trigger = 0;
        LandTile[] allSea = game.getGameboard().getSea();
        LandTile[] allLand = game.getGameboard().getLand();
        for (int i = 0; i < allLand.length; i++) {
            if (trigger < 3) {
                for (int j = 0; j < 6; j++) {
                    if (allLand[i].getCrosses()[j].equals(cross)) {
                        lands[trigger] = allLand[i].getPosition();
                        trigger++;
                    }
                }
            }
        }

        for (int i = 0; i < allSea.length; i++) {
            if (trigger < 3) {
                for (int j = 0; j < 6; j++) {
                    if (allSea[i].getCrosses()[j] != null && allSea[i].getCrosses()[j].equals(cross)) {
                        lands[trigger] = allSea[i].getPosition();
                        trigger++;
                    }
                }
            }
        }
        return lands;
    }

    /**
     * Gives the Position of the two tiles which surround a edge/street (based
     * on the protocol version 0.2)
     *
     * @param cross1 cross 1 position
     * @param cross2 cross 2 position
     * @return land
     */

    public Position[] giveLands(Cross cross1, Cross cross2) {
        Position[] cross1Lands = giveLands(cross1);
        Position[] cross2Lands = giveLands(cross2);
        Position[] lands = new Position[2];
        int trigger = 0;
        for (int i = 0; i < cross1Lands.length; i++) {
            for (int j = 0; j < cross2Lands.length; j++) {
                if (cross1Lands[i] == cross2Lands[j]) {
                    lands[trigger] = cross1Lands[i];
                    trigger++;
                }
            }
        }
        return lands;
    }

    /**
     * gives the Position of a tile
     *
     * @param index
     *            of the tile
     * @return Position
     */
    public Position giveTile(int index) {
        return game.getGameboard().getLand()[index].getPosition();
    }


    /**
     * Translates a position to a cross
     *
     * @param pos describing a cross
     * @return cross meant
     */
    public Cross locationToCross(Position[] pos) {
        Cross cross = null;

        // rechnet die location in Form von Buchstaben in die tiles um
        ArrayList<LandTile> tiles = new ArrayList<LandTile>();

        for (int i = 0; i < 3; i++) {
            if (game.getGameboard().getLand(pos[i]) != null) {
                tiles.add(game.getGameboard().getLand(pos[i]));
            } else {
                tiles.add(game.getGameboard().getSea(pos[i]));
            }
        }

        // Ermittelt aus den Tiles das Cross
        ArrayList<Cross> crosses = new ArrayList<Cross>();
        for (int firstTile = 0; firstTile < 6; firstTile++) {
            for (int secondTile = 0; secondTile < 6; secondTile++) {
                if (tiles.get(0).getCrosses()[firstTile].equals(tiles.get(1).getCrosses()[secondTile])) {
                    crosses.add(tiles.get(0).getCrosses()[firstTile]);
                }
            }
        }
        for (int thirdTile = 0; thirdTile < 6; thirdTile++) {
            for (int i = 0; i < crosses.size(); i++) {
                if (tiles.get(2).getCrosses()[thirdTile].equals(crosses.get(i))) {
                    cross = crosses.get(i);
                }
            }
        }
        return cross;
    }

    /**
     * Translates a position to a edge
     *
     * @param pos
     *            describing a cross
     * @return edge meant
     */
    public Edge locationToEdge(Position[] pos) {
        // rechnet die location in Form von Koordinaten in die tiles um

        ArrayList<LandTile> tiles = new ArrayList<LandTile>();

        for (int i = 0; i < 2; i++) {
            if (game.getGameboard().getLand(pos[i]) != null) {
                tiles.add(game.getGameboard().getLand(pos[i]));
            } else {
                tiles.add(game.getGameboard().getSea(pos[i]));
            }
        }

        // Ermittelt aus den Tiles das Cross
        ArrayList<Cross> crosses = new ArrayList<Cross>();
        for (int firstTile = 0; firstTile < 6; firstTile++) {
            for (int secondTile = 0; secondTile < 6; secondTile++) {
                if (tiles.get(0).getCrosses()[firstTile].equals(tiles.get(1).getCrosses()[secondTile])) {
                    crosses.add(tiles.get(0).getCrosses()[firstTile]);
                }
            }
        }
        return game.getEdge(crosses.get(0), crosses.get(1));

    }

    /**
     * Translates a character to its land tile index
     *
     * @param pos
     * @return int -> tileIntex
     */

    public int getTileIndex(Position pos) {
        return game.getGameboard().getLandIndex(pos);
    }

    /**
     * Shows if the new Robber position is possible
     *
     * @param pos
     * @return
     */
    public boolean validRobber(int pos) {
        if (pos != game.getGameboard().getRobber()) {
            return true;
        }
        return false;
    }

    /**
     * Shows if 2 Crosses are neighbors
     *
     * @param cross1
     * @param cross2
     * @return
     */
    public boolean neighborCrosses(Cross cross1, Cross cross2) {
        Edge[] edges = game.getGameboard().getEdges();
        for (int i = 0; i < edges.length; i++) {
            if (edges[i].getCross1().getPosition().equals(cross1.getPosition())
                    && edges[i].getCross2().getPosition().equals(cross2.getPosition())
                    || edges[i].getCross1().getPosition().equals(cross2.getPosition())
                    && edges[i].getCross2().getPosition().equals(cross1.getPosition())) {
                return true;
            }
        }
        return false;
    }

    /**
     * gets all possible targets for robber
     *
     * @param pos
     *            where robber is set
     * @return ArrayList<Player> with targets
     */
    public ArrayList<Player> giveTargets(int pos) {
        ArrayList<Player> player = new ArrayList<Player>();
        int[] help = new int[4];
        boolean valid = true;
        int index = 0;
        for (int i = 0; i < 6; i++) {
            if (game.getGameboard().getLand()[pos].getCrosses()[i].getPlayer() != null) {
                int id = game.getGameboard().getLand()[pos].getCrosses()[i].getPlayer().getId();
                for (int j = 0; j < 4; j++) {
                    if (id == help[j]) {
                        valid = false;
                    }
                }
                if (valid == true) {
                    help[index] = id;
                    index++;
                }
                valid = true;
            }
        }
        for (int j = 0; j < 4; j++) {
            if (help[j] != 0) {
                player.add(game.getPlayer(help[j]));
            }
        }
        return player;
    }

    /**
     * gets all tiles with a number0 chip with the searched int
     *
     * @param dice Dice
     *            searched int
     * @return List<Land> of the tiles
     */
    public List<LandTile> getTilefromNumberChip(int dice) {
        List<LandTile> tile = new ArrayList<LandTile>();
        LandTile[] tiles = game.getGameboard().getLand();
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].getChipNr() == (Integer) dice) {
                tile.add(tiles[i]);
            }
        }
        return tile;
    }

    /**
     * gets all the players wich have a building at this tile
     *
     * @param tile Tile to search
     * @return players
     */
    public ArrayList<Player> getPlayerBuildings(LandTile tile) {
        ArrayList<Player> players = new ArrayList<Player>();
        for (Cross i : tile.getCrosses()) {
            if (i.getSettlement() != null) {
                players.add(i.getPlayer());
            } else if (i.getCity() != null) {
                players.add(i.getPlayer());
                players.add(i.getPlayer());
            }
        }
        return players;
    }

    /**
     * used to translate an array List of resources to an array
     *
     * @param res ArrayList
     * @return int[]
     */
    public int[] getResAmount(ArrayList<Resource> res) {
        int[] amount = new int[5];
        int grain = 0;
        int wool = 0;
        int ore = 0;
        int lumber = 0;
        int brick = 0;
        for (int i = 0; i < res.size(); i++) {
            if (res.get(i) == Resource.Getreide) {
                grain++;
            }
            if (res.get(i) == Resource.Erz) {
                ore++;
            }
            if (res.get(i) == Resource.Wolle) {
                wool++;
            }
            if (res.get(i) == Resource.Holz) {
                lumber++;
            }
            if (res.get(i) == Resource.Lehm) {
                brick++;
            }
        }
        amount[0] = grain;
        amount[1] = wool;
        amount[2] = ore;
        amount[3] = lumber;
        amount[4] = brick;

        return amount;
    }

    /**
     * used to get a list of all neighbor tiles
     *
     * @param location of the cross
     * @return lands
     */
    public ArrayList<LandTile> getNeighbourLands(Cross location) {
        ArrayList<LandTile> lands = new ArrayList<LandTile>();
        LandTile[] allLands = game.getGameboard().getLand();
        for (LandTile l : allLands) {
            for (Cross c : l.getCrosses()) {
                if (c.equals(location)) {
                    lands.add(l);
                    break;
                }
            }
        }
        return lands;
    }
}
