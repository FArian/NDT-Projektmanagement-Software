package rzeznika.ndtClient.controller;

import java.util.ArrayList;
import java.util.List;

import rzeznika.ndtClient.model.Cross;
import rzeznika.ndtClient.model.Edge;
import rzeznika.ndtClient.model.Game;
import rzeznika.ndtClient.model.Player;
import rzeznika.ndtClient.model.Street;

/**
 * Created by Lena on 04.07.2017.
 */

/**
 * Class that provides methods for the construction logic.
 */
public class ConstructorLogic {


    /** instance of the class game **/
    private Game game;
    /** a Logger **/
    private Login log;
    /** instance of the class gameLogic **/
    private GameLogic gameLogic;

    /**
     * Constructor for the construction logic
     *
     * @param game actual game
     */
    public ConstructorLogic(Game game) {
        this.game = game;
        log = new Login("ConstructorLogicLog");
        this.gameLogic = new GameLogic(game);
    }

    /**
     * Checks if the player can build a street between two specific crosses
     *
     * @param cross1
     *             startcross
     * @param cross2
     *             endcross
     * @param id
     *             the id of the player that wants to place the street
     * @return boolean
     */

    public boolean isStreetPossible(int id, Cross cross1, Cross cross2) {
        Edge edge = game.getEdge(cross1, cross2);
        if (game.isValidEdge(cross1, cross2) && edge.getStreet() == null) {
            Player player = game.getPlayer(id);
            int[] amount = player.getResAmountArray();
            if (amount[3] > 0 && amount[4] > 0) {
                if (game.hasNeighbourCross(edge, player) || game.hasNeighbourStreet(edge, player)) {
                    return true;
                } else {
                    log.getLogin().info(
                            this.getClass() + " No adjacent street, settlement or city that belongs to the player!");
                }
            } else {
                log.getLogin().info(this.getClass() + " Not enough ressources to build a street!");
            }
        } else {
            log.getLogin().info(this.getClass() + " Not a valid edge or street already existing!");
        }
        return false;
    }

    /**
     * Checks if the player can build a street between two specific crosses
     * without paying the resources. Used for the first two rounds of the game
     * and the PROGRESSCARD0
     *
     * @param cross1
     *             startcross
     * @param cross2
     *             endcross
     * @param id
     *             the id of the player that wants to place the street
     * @return boolean
     */

    public boolean isFreeStreetPossible(int id, Cross cross1, Cross cross2) {
        Edge edge = game.getEdge(cross1, cross2);
        if (game.isValidEdge(cross1, cross2) && edge.getStreet() == null) {
            for (int i = 0; i < game.getPlayer().size(); i++) {
                if (game.getPlayer().get(i).getId() == id) {
                    Player player = game.getPlayer().get(i);
                    if (game.hasNeighbourCross(edge, player) || game.hasNeighbourStreet(edge, player)) {
                        return true;
                    } else {
                        log.getLogin().info(this.getClass()
                                + " No adjacent street, settlement or city that belongs to the player!");
                    }
                }
            }
        } else {
            log.getLogin().info(this.getClass() + " Not a valid edge or street already existing!");
        }
        return false;
    }

    /**
     * Checks if the player can build a settlement on that cross.
     *
     * @param cross
     *             position of settlement
     * @param id
     *             the id of the player that wants to place the settlement
     * @return boolean
     */
    public boolean isSettlementPossible(int id, Cross cross) {
        if (cross.getSettlement() == null && cross.getCity() == null) {
            for (int i = 0; i < game.getPlayer().size(); i++) {
                if (game.getPlayer().get(i).getId() == id) {
                    Player player = game.getPlayer().get(i);
                    if (gameLogic.spaceRule(cross) && game.hasNeighbourStreet(cross, player)) {
                        return true;
                    } else {
                        log.getLogin().info(this.getClass() + " Not possible to build a settlement!");
                        return false;
                    }
                }
            }
        } else {
            log.getLogin().info(this.getClass() + " There already is a settlement or city!");
            return false;
        }
        return false;
    }

    /**
     * Player builds a free Settlement on game on the cross. Used in the
     * beginning of the game
     *
     * @param cross
     *             position of settlement
     * @param id
     *             the id of the player that wants to place the settlement
     * @return boolean
     */
    public boolean isFreeSettlementPossible(int id, Cross cross) {
        if (gameLogic.spaceRule(cross)) {
            return true;
        } else {
            log.getLogin().info("Not possible to build a free Settlement here!");
            return false;
        }
    }

    /**
     * Checks if the player can build a city at that cross
     *
     * @param cross
     *            position of the city
     * @param id
     *            the id of the player that wants to place the city
     * @return boolean
     */
    public boolean isCityPossible(int id, Cross cross) {
        Player player = game.getPlayer(id);
        int[] amount = player.getResAmountArray();

        if (amount[0] > 1 && amount[2] > 2 && cross.getSettlement() != null
                && cross.getSettlement().getPlayer().getId() == id) {
            return true;
        } else {
            log.getLogin().info(this.getClass() + " Not possible to build a city!");
            return false;
        }
    }

    /**
     * Checks if it is possible for the player to build a street at that edge.
     * Does not take the required resources in account!
     *
     * @param player
     * @param edge
     * @return boolean
     */
    public boolean isStreetLocationPossible(Player player, Edge edge) {
        if (edge.getStreet() == null) {
            if (game.hasNeighbourCross(edge, player) || game.hasNeighbourStreet(edge, player)) {
                return true;
            }
        }
        return false;
    }

    /**
     * gets the edges where start streets can be build
     *
     * @param player
     *            who builds
     * @return ArrayList<Edge> of possible streets
     */
    public ArrayList<Edge> getPossibleStartStreets(Player player) {
        ArrayList<Cross> settlements = new ArrayList<Cross>();
        Cross[] allCrosses = game.getGameboard().getCrosses();
        for (int i = 0; i < allCrosses.length; i++) {
            if (allCrosses[i].getSettlement() != null && allCrosses[i].getPlayer().equals(player)) {
                settlements.add(allCrosses[i]);
            }
        }
        System.out.println(this.getClass() + " Anzahl an Dörfer = " + settlements.size());
        ArrayList<Edge> streets = new ArrayList<Edge>();
        for (Cross c : settlements) {
            if (!game.hasNeighbourStreet(c, player)) {
                streets.addAll(game.getEdges(c));
            }
        }
        // TEST

        System.out.println(this.getClass() + " Mögliche Straßen: ");
        for (int i = 0; i < streets.size(); i++) {
            System.out.println(this.getClass() + " Edge " + i + " " + streets.get(i).getCross1().getPosition().getX()
                    + "," + streets.get(i).getCross1().getPosition().getY() + "Cross2: "
                    + streets.get(i).getCross2().getPosition().getX() + ","
                    + streets.get(i).getCross2().getPosition().getY());
        }
        // -----
        return streets;
    }

    /**
     * Gives all edges where the player can build a street. Does not check any
     * resources. Not for the start phase. If an edge is given it takes it into
     * calculation and handles the calculation as if the player has already
     * built a street at that edge.
     *
     * @param edge
     * @param player
     * @return ArrayList<Edge>
     */
    public ArrayList<Edge> getPossibleFreeStreets(Player player, Edge edge) {

        ArrayList<Edge> possibleEdges = new ArrayList<Edge>();
        Edge[] allEdges = new Edge[72];
        allEdges = game.getGameboard().getEdges().clone();

        if (edge != null) {
            for (int i = 0; i < allEdges.length; i++) {
                if (allEdges[i].getCross1().getPosition().getX() == edge.getCross1().getPosition().getX()
                        && allEdges[i].getCross1().getPosition().getY() == edge.getCross1().getPosition().getY()
                        && allEdges[i].getCross2().getPosition().getX() == edge.getCross2().getPosition().getX()
                        && allEdges[i].getCross2().getPosition().getY() == edge.getCross2().getPosition().getY()) {
                    if (allEdges[i].getStreet() == null) {
                        allEdges[i].setStreet(new Street(player));
                        System.out.println(this.getClass() + " Das erste Edge wurde gefunden und temporär gesetzt!");
                    } else {
                        log.getLogin().info("Ungültiges Edge. Edge besitzt bereits eine Straße!");
                    }
                }
            }
        }

        for (Edge e : allEdges) {
            if (isStreetLocationPossible(player, e)) {
                possibleEdges.add(e);

            }
        }
        return possibleEdges;
    }

    /**
     * Gives all edges where the player can build a street. Checks also if the
     * player has enough resources.
     *
     * @param player player object
     * @return ArrayList<Edge>
     */
    public ArrayList<Edge> getPossibleStreets(Player player) {
        ArrayList<Edge> possibleEdges = new ArrayList<Edge>();
        Edge[] allEdges = game.getGameboard().getEdges();
        System.out.println(this.getClass() + " Die aktuelle Runde ist " + game.getRound());
        if (game.getRound() < 3) {
            possibleEdges = getPossibleStartStreets(player);
        } else {

            int[] amount = player.getResAmountArray();
            if (amount[3] > 0 && amount[4] > 0) {

                for (Edge e : allEdges) {
                    if (isStreetLocationPossible(player, e)) {
                        possibleEdges.add(e);
                    }
                }
            }
        }
        return possibleEdges;
    }

    /**
     * Checks if it is possible for the player to build a settlement at that
     * cross. Does not take the required resources in account!
     *
     * @param player
     * @param cross
     * @return boolean
     */
    public boolean isSettlementLocationPossible(Player player, Cross cross) {
        if (cross.getSettlement() == null && cross.getCity() == null) {
            if (gameLogic.spaceRule(cross) && game.hasNeighbourStreet(cross, player)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gives all crosses where the player can build a settlement. Checks also if
     * the player has enough resources.
     *
     * @param player
     * @return List<Cross>
     */
    public List<Cross> getPossibleSettlements(Player player) {
        List<Cross> possibleCrosses = new ArrayList<Cross>();
        Cross[] allCrosses = game.getGameboard().getCrosses();
        if (game.getRound() < 3) {
            for (Cross c : allCrosses) {
                if (gameLogic.spaceRule(c)) {
                    possibleCrosses.add(c);
                }
            }
        } else {

            int[] amount = player.getResAmountArray();
            if (amount[0] > 0 && amount[1] > 0 && amount[3] > 0 && amount[4] > 0) {

                for (Cross c : allCrosses) {
                    if (isSettlementLocationPossible(player, c)) {
                        possibleCrosses.add(c);
                    }
                }
            }
        }
        return possibleCrosses;
    }

    /**
     * Checks if it is possible for the player to build a city at that cross.
     * Does not take the required resources in account!
     *
     * @param player
     * @param cross
     * @return boolean
     */
    public boolean isCityLocationPossible(Player player, Cross cross) {
        if (cross.getSettlement() != null && cross.getSettlement().getPlayer().equals(player)) {
            return true;
        }
        return false;
    }

    /**
     * Gives all crosses where the player can build a city. Checks also if the
     * player has enough resources.
     *
     * @param player
     * @return List<Cross>
     */
    public List<Cross> getPossibleCities(Player player) {
        List<Cross> possibleCrosses = new ArrayList<Cross>();
        int[] amount = player.getResAmountArray();
        if (amount[0] > 1 && amount[2] > 2) {
            Cross[] allCrosses = game.getGameboard().getCrosses();
            for (Cross c : allCrosses) {
                if (isCityLocationPossible(player, c)) {
                    possibleCrosses.add(c);
                }
            }
        }
        return possibleCrosses;
    }

}
