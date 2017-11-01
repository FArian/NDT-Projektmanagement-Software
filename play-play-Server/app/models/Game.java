package models;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by F.Arian on 14.06.17.
 */
public class Game {

    /**
     * Our gameboard on which we play
     */
    private GameBoard gameboard;
    /**
     * The List of players
     */
    private ArrayList<Player> players;
    private ArrayList<Resource> resources;
    /**
     * A List of all the developmentCards which belong to no player
     */
    private ArrayList<DevelopmentCard> developmentCards;
    /**
     * An array of all the index resources Cards which belong to no player
     */
    private int[] indexResources;
    /**
     * the number0 of knights which must be topped to get the KnightPower Card
     */
    private int knightPower;
    /**
     * tells which player got the biggest Knight Power
     */
    private Player hasKnightPower;
    /**
     * the amount of the longest street currently placed on the gameboard
     */
    private int longestStreet;
    /**
     * tells which player has the longest
     */
    private Player hasLongestStreet;

    /**
     * Represents a trade action
     */
    private Trade trade;

    /**
     * tells which player is winner
     */
    private Player winner;
    /**
     * tells round of play
     */
    private int round;
    /**
     * The newest chat message. [0]: Name of the player who sendt the message,
     * [1]: the message
     */
    private String[] chat;


    /**
     * Game constructor
     * creates a Game with the Players and other resources for start
     */
    public Game() {
        this.players = new ArrayList<Player>();
        this.players.add(new Player());
        this.players.add(new Player());
        this.players.add(new Player());
        this.players.add(new Player());
        this.gameboard =null;
        this.initializableResources();
        this.initializableDevelopmentCard();
        this.winner = null;
        this.round = 1;
        this.longestStreet = 4;
        this.knightPower = 2;
        this.trade = new Trade();
        this.setChatMessage(new String[2]);


    }
    /**
     * Creates the stack of development cards
     */
    private void initializableDevelopmentCard() {
        ArrayList<DevelopmentCard> developmentCards2;
        developmentCards2 = new ArrayList<DevelopmentCard>();
        developmentCards = new ArrayList<DevelopmentCard>();
        for (int i = 0; i < 2; i++) {
            developmentCards2.add(DevelopmentCard.Monopol);
        }
        for (int i = 0; i < 2; i++) {
            developmentCards2.add(DevelopmentCard.Straßenbau);
        }
        for (int i = 0; i < 2; i++) {
            developmentCards2.add(DevelopmentCard.Erfindung);
        }
        for (int i = 0; i < 14; i++) {
            developmentCards2.add(DevelopmentCard.Ritter);
        }
        for (int i = 0; i < 5; i++) {
            developmentCards2.add(DevelopmentCard.Siegpunkt);
        }

        Random r = new Random();
        for (int i = 0; i < 25; i++) {
            developmentCards.add(developmentCards2.remove(r.nextInt(developmentCards2.size())));
        }
        // TEST
        System.out.println(this.getClass() + " FOLGENDE ENTWICKLUNGSKARTEN SIND IM STAPEL: ");
        for (int i = 0; i < this.developmentCards.size(); i++) {
            System.out.println("ENTWICKLUNGSKARTEN : " +this.developmentCards.get(i));
        }
    }

    /**
     * Initial of resource cards: int[0]== GRAIN; int[1] WOOL; int[2]
     * ORE; int[3] LUMBER; int[4] BRICK;
     * wir have 18 LandTiles for a Resource Types
     */

    private void initializableResources() {
        indexResources = new int[5];
        for (int i = 0; i < indexResources.length; i++) {
            indexResources[i] = 18;
        }

    }

    /**
     * Add ressource to indexResources
     *
     * @param resource a indexResources to add
     */
    public void addResources(Resource resource) {
        if (resource == Resource.Getreide) {
            indexResources[0]++;
        } else if (resource == Resource.Wolle) {
            indexResources[1]++;
        } else if (resource == Resource.Erz) {
            indexResources[2]++;
        } else if (resource == Resource.Holz) {
            indexResources[3]++;
        } else if (resource == Resource.Lehm) {
            indexResources[4]++;
        }
    }

    /**
     * Remove resource from indexResources
     *
     * @param resource a indexResources to remove
     */
    public void removeResources(Resource resource) {
        if (resource == Resource.Getreide) {
            indexResources[0]--;
        } else if (resource == Resource.Wolle) {
            indexResources[1]--;
        } else if (resource == Resource.Erz) {
            indexResources[2]--;
        } else if (resource == Resource.Holz) {
            indexResources[3]--;
        } else if (resource == Resource.Lehm) {
            indexResources[4]--;
        }
    }


    /**
     * Getter for the first developmentcard
     *
     * @return DevelopmentCard
     */

    public DevelopmentCard drawCard() {
        if (developmentCards.size() > 0) {
            return developmentCards.remove(0);
        } else {
            return null;
        }
    }

    /**
     * Initial of Development cards
     */

    private void F() {
        ArrayList<DevelopmentCard> devCardsBuffer;
        devCardsBuffer = new ArrayList<DevelopmentCard>();
        developmentCards = new ArrayList<DevelopmentCard>();
        for (int i = 0; i < 2; i++) {
            devCardsBuffer.add(DevelopmentCard.Monopol);
        }
        for (int i = 0; i < 2; i++) {
            devCardsBuffer.add(DevelopmentCard.Straßenbau);
        }
        for (int i = 0; i < 2; i++) {
            devCardsBuffer.add(DevelopmentCard.Erfindung);
        }
        for (int i = 0; i < 14; i++) {
            devCardsBuffer.add(DevelopmentCard.Ritter);
        }
        for (int i = 0; i < 5; i++) {
            devCardsBuffer.add(DevelopmentCard.Siegpunkt);
        }

        Random r = new Random();
        for (int i = 0; i < 25; i++) {
            developmentCards.add(devCardsBuffer.remove(r.nextInt(devCardsBuffer.size())));
        }
        // TEST
        System.out.println(this.getClass() + " FOLGENDE ENTWICKLUNGSKARTEN SIND IM STAPEL: ");
        for (int i = 0; i < this.developmentCards.size(); i++) {
            System.out.println("Entwickelungskarte : " +this.developmentCards.get(i) + "  ");
        }

    }


    public GameBoard getGameboard() {
        return this.gameboard;
    }

    public void setGameboard(GameBoard gameBoard) {
        this.gameboard = gameBoard;
    }


    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Setter for the players
     *
     * @param players
     */

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * Getter for the player with that id
     *
     * @param id
     * @return player
     */
    public Player getPlayer(int id) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getId() == id) {
                return players.get(i);
            }
        }
        return null;
    }

    /**
     * Setter for the players
     *
     * @param players
     */

    public void setPlayer(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * Getter for the player
     *
     * @return ArrayList<Player>
     */
    public ArrayList<Player> getPlayer() {
        return players;
    }


    public ArrayList<Resource> getResources() {
        return resources;
    }

    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }


    public ArrayList<DevelopmentCard> getDevelopmentCards() {
        return developmentCards;
    }

    public void setDevelopmentCards(ArrayList<DevelopmentCard> developmentCards) {
        this.developmentCards = developmentCards;
    }


    //----------------KNIGHT POWER---------------------

    /**
     * Getter for KnightPower
     *
     * @return knightPower
     */
    public int getKnightPower() {
        return this.knightPower;
    }

    /**
     * Getter for hasKnightPower
     *
     * @return Player, who has KnightPower
     */
    public Player getHasKnightPower() {
        return this.hasKnightPower;
    }

    public void setKnightPower(int knightPower) {
        this.knightPower = knightPower;
    }

    /**
     * Takes the KnightPower card from the last owner and hands it to
     * hasKnightPower
     *
     * @param hasKnightPower A player which gets it
     */

    public void setHasKnightPower(Player hasKnightPower) {
        this.hasKnightPower = hasKnightPower;
        this.knightPower++;
    }


    //----------------LONGEST STREET---------------------

    /**
     * Getter for the longest street
     *
     * @return longestStreet
     */

    public int getLongestStreet() {
        return this.longestStreet;
    }

    /**
     * Getter for hasLongestStreet
     *
     * @return hasLongestStreet
     */

    public Player getHasLongestStreet() {
        return this.hasLongestStreet;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setRound(int round) {
        this.round = round;
    }

    /**
     * Getter for hasLongestStreet
     *
     * @return hasLongestStreet
     */

    public void setLongestStreet(int longestStreet) {
        this.longestStreet = longestStreet;
    }

    /**
     * Takes the LongestStreet card from the last owner and hands it to
     * hasLongestStreet
     *
     * @param hasLongestStreet The player which gets it
     */
    public void setHasLongestStreet(Player hasLongestStreet) {
        this.hasLongestStreet = hasLongestStreet;
    }


    public boolean hasNeighbourStreet(Cross cross, Player player) {
        ArrayList<Edge> edges = getEdges(cross);
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getStreet() != null && edges.get(i).getStreet().getPlayer().equals(player)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if that edge has an adjacent street which belongs also to that
     * player
     *
     * @param edge
     * @param player
     * @return boolean
     */
    public boolean hasNeighbourStreet(Edge edge, Player player) {
        Edge[] edges = gameboard.getEdges();
        for (int i = 0; i < edges.length; i++) {
            if (edges[i].getStreet() != null && edges[i].getStreet().getPlayer().equals(player)) {
                if ((edges[i].getCross1().equals(edge.getCross1()))
                        || (edges[i].getCross1().equals(edge.getCross2()))) {
                    return true;
                } else if ((edges[i].getCross2().equals(edge.getCross1()))
                        || (edges[i].getCross2().equals(edge.getCross2()))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns a list of all edges that are adjacent to that cross
     *
     * @param cross
     * @return ArrayList<Edges>
     */
    public ArrayList<Edge> getEdges(Cross cross) {
        ArrayList<Edge> neighbours = new ArrayList<Edge>();
        Edge[] edges = getGameboard().getEdges();
        for (int i = 0; i < edges.length; i++) {
            if ((edges[i].getCross1().equals(cross) || edges[i].getCross2().equals(cross))) {
                neighbours.add(edges[i]);
            }
        }
        return neighbours;
    }


    /**
     * Creates a new gameboard
     *
     * @param card The gameboard we got from the Translator
     */
    public void setGameboard(String[][] card) {
        this.gameboard = new GameBoard(card);
    }

    /**
     * Checks if two crossempty are neighbour crossempty.
     *
     * @param cross1
     * @param cross2
     * @return
     */
    public boolean isValidEdge(Cross cross1, Cross cross2) {
        Edge[] edges = getGameboard().getEdges();
        for (int i = 0; i < edges.length; i++) {
            if ((edges[i].getCross1().equals(cross1) && edges[i].getCross2().equals(cross2))
                    || edges[i].getCross1().equals(cross2) && edges[i].getCross2().equals(cross1)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Getter for an Edge
     *
     * @param cross1
     * @param cross2
     * @return
     * @throws NullPointerException
     */
    public Edge getEdge(Cross cross1, Cross cross2) {
        Edge[] edges = getGameboard().getEdges();
        for (int i = 0; i < edges.length; i++) {
            if ((edges[i].getCross1().equals(cross1) && edges[i].getCross2().equals(cross2))
                    || edges[i].getCross1().equals(cross2) && edges[i].getCross2().equals(cross1)) {
                return edges[i];
            }
        }
        return null;
    }

    /**
     * Checks if the Edge belongs to a cross that the specific player rules
     *
     * @param edge
     * @param player
     * @return boolean
     */
    public boolean hasNeighbourCross(Edge edge, Player player) {
        Cross[] crosses = new Cross[2];
        crosses[0] = edge.getCross1();
        crosses[1] = edge.getCross2();

        if (crosses[0].getSettlement() != null && crosses[0].getSettlement().getPlayer().equals(player)) {
            return true;
        } else if (crosses[1].getSettlement() != null && crosses[1].getSettlement().getPlayer().equals(player)) {
            return true;
        } else if (crosses[0].getCity() != null && crosses[0].getCity().getPlayer().equals(player)) {
            return true;
        } else if (crosses[1].getCity() != null && crosses[1].getCity().getPlayer().equals(player)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if there is a building on that cross
     *
     * @param cross
     * @return boolean
     */
    public boolean hasBuilding(Cross cross) {
        if (cross.getSettlement() != null || cross.getCity() != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Getter for the newest chat messages
     *
     * @return String[]
     */
    public String[] getChatMessage() {
        return chat;
    }

    /**
     * Setter for the newest chat message
     *
     * @param chatMessage
     */
    public void setChatMessage(String[] chatMessage) {
        this.chat = chatMessage;
    }

    /**
     * Getter for the Trade
     *
     * @return Trade
     */
    public Trade getTrade() {
        return this.trade;
    }

    /**
     * Resets the longest street
     */
    public void resetLongestStreet() {
        if (hasLongestStreet != null) {
            this.hasLongestStreet.setHasLongestStreet(false);
            this.hasLongestStreet = null;
        }
        this.longestStreet = 4;
    }

    /**
     * Sets a winner of the current game
     *
     * @param winner
     */
    public void setWinner(int winner) {
        this.winner = getPlayer(winner);

    }

    /**
     * Getter for the winner of the game
     *
     * @return
     */
    public Player getWinner() {
        return this.winner;
    }

    /**
     * creates a new gameboard

     public void createGameboard() {
     gameboard = new GameBoard(card);

     }
     */

    /**
     * Getter for the round
     *
     * @return
     */
    public int getRound() {
        return round;
    }

    /**
     * increases the round by one
     */
    public void increaseRound() {
        this.round++;
    }

    /**
     * Checks if there are resources of a specific type left
     *
     * @param res
     * @return
     */
    @SuppressWarnings("incomplete-switch")
    public boolean hasResLeft(Resource res) {
        switch (res) {
            case Getreide:
                if (indexResources[0] > 0) {
                    return true;
                }
                break;
            case Wolle:
                if (indexResources[1] > 0) {
                    return true;
                }
                break;
            case Erz:
                if (indexResources[2] > 0) {
                    return true;
                }
                break;
            case Holz:
                if (indexResources[3] > 0) {
                    return true;
                }
                break;
            case Lehm:
                if (indexResources[4] > 0) {
                    return true;
                }
                break;
        }
        return false;
    }

    /**
     * add resources to the bank
     *
     * @param give
     */
    public void addRes(int[] give) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < give[i]; j++) {
                if (i == 0) {
                    addResources(Resource.Getreide);

                }
                if (i == 1) {
                    addResources(Resource.Wolle);

                }
                if (i == 2) {
                    addResources(Resource.Erz);

                }
                if (i == 3) {
                    addResources(Resource.Holz);

                }
                if (i == 4) {
                    addResources(Resource.Lehm);

                }
            }
        }
    }

    /**
     * Removes a specific resource from the bank
     *
     * @param get
     */
    public void removeRes(int[] get) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < get[i]; j++) {
                if (i == 0) {
                    removeResources(Resource.Getreide);

                }
                if (i == 1) {
                    removeResources(Resource.Wolle);

                }
                if (i == 2) {
                    removeResources(Resource.Erz);

                }
                if (i == 3) {
                    removeResources(Resource.Holz);

                }
                if (i == 4) {
                    removeResources(Resource.Lehm);

                }
            }
        }
    }

    /**
     * creates a new gameboard
     */
    public void createGameboard() {
        gameboard = new GameBoard();

    }

    /**
     * Getter for ressources
     *
     * @return ressources
     */
    public int[] getRessources() {
        return indexResources;
    }

}
