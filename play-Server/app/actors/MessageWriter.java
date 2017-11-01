package actors;

import akka.actor.ActorRef;
import models.*;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

/**
 * Created by F.Arian on 05.07.17.
 * Class that provides methods for writing messages to all clients in a game.
 */
public class MessageWriter implements Observer {
    /**
     * All clients in the game; Class gets clients via setter method
     */
    private List<GameActor> clients;
    /**
     * the logger
     */
    private ServerLog log;


    public MessageWriter() {
        this.log = new ServerLog();
    }


    @Override
    public synchronized void update(Observable o, Object arg) {
        Information information = (Information) arg;
        switch (information.getType()) {

            case "NewClient":
                System.out.println("Im Case NewClient !");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sendStatusUpdate(information.getPlayer());
                break;
        }

    }

    public void sendStatusUpdate(Player player) {
        for (GameActor i : clients) {
            if (i.getPlayer().equals(player)) {
                String message = TOJSON.statusUpdate(player.getStatus(), player, false).toString() + "\n";
                log.info("Server sendet: " + message);
                i.sendStatusUpdateToClient(message);
            }
        }

    }


    public void setClient(List<GameActor> clients) {
        this.clients = clients;
    }

    public void sendCard(GameBoard gameboard) {
        LandTile[] landTiles = gameboard.reorder();
        HarberType[] harbers = gameboard.getHarbors();
        Position[][] harborPos = gameboard.harborPos();
        Position rob = gameboard.getLand()[gameboard.getRobber()].getPosition();
        String card = String.valueOf(TOJSON.card(landTiles, harbers, harborPos, rob));
        for (GameActor i : clients) {
            i.sendCardToClient(card);
        }

    }

    public void sendDices(Player player, int[] dices) {
        for (GameActor i : clients) {
            Object json = TOJSON.diceResult(player.getId(), dices);
            i.sendDiceToClient(json);
        }
    }

    public void sendEarnings(Player p, int[] resources) {
        for (GameActor i : clients) {
            i.sendEarningsToClient(p, resources);
        }
    }

    /**
     * Sends a chat message from player with that id to all players (inclusive
     * the player who sendt the message)
     *
     * @param id      id of the player who send a message
     * @param message message string
     */
    public void sendChat(int id, String message) {
        for (GameActor i : clients) {
            i.sendChatToCleint(id, message);
        }
    }

    /**
     * Sends a new building with location and who it built to each client;
     * building can be of type: settlement, city or street
     *
     * @param id   id of he player who hast built
     * @param type type of the building
     * @param pos  position of the building
     */

    public void sendBuilding(int id, String type, Position[] pos) {
        String message = TOJSON.building(id, type, pos).toString();
        log.getLog().info("mWriter sendet an alle: " + message);
        for (GameActor i : clients) {
            i.sendBuildingToClient(message);

        }


    }

    /**
     * Sends the information that a player has earned the longest street
     *
     * @param player player who has earned the longest street
     */
    public void sendLongestStreet(Player player) {
        for (GameActor i : clients) {
            i.sendLongestStreetToClient(player);
        }

    }

    /**
     * sends the information that the longest street was reseted
     */
    public void sendLongestStreetReset() {
        for (GameActor i : clients) {
            ActorRef out = i.getOut();
            i.sendLongestStreetResetToClient();
            ;
        }
    }

    /**
     * Message that contains the winner if the game has ended
     *
     * @param player winner of the game
     */
    public void sendWinner(Player player) {
        for (GameActor i : clients) {
            i.sendWinnerToClient(player);
        }
    }

    /**
     * Sends a cost object to each player. Differentiates between known and
     * unknown costs.
     *
     * @param player          player who has to pay
     * @param type            type of costs
     * @param resources       resources to give
     * @param openForEveryone true if everyone should read the message
     */
    public void sendCosts(Player player, String type, int[] resources, boolean openForEveryone) {
        String output = TOJSON.costs(player, type, resources, false).toString();
        for (GameActor i : clients) {
            ActorRef out = i.getOut();
            if (i.getPlayer().equals(player)) {
                log.info("An Client: " + i.getId() + output);
                i.sendCostsToClient(output);
            } else {
                if (openForEveryone) {
                    log.info("An Client: " + i.getId() + output);
                    i.sendCostesForEveryOne(output);
                } else {
                    String outputUnknown = TOJSON.costs(player, type, resources, true).toString();
                    log.info("An Client: " + i.getId() + output);
                    i.sendCostesOutPutUnknow(output);

                }
            }
        }
    }

    /**
     * Sends a message that the robber has been moved to a new Position pos.
     * Sends also the target if the player that moved the robber has stolen a
     * card.
     *
     * @param player player who moves the robber
     * @param pos    new position of the robber
     * @param target player who is robbed
     */
    public void sendRobberSet(Player player, Position pos, int target) {
        for (GameActor i : clients) {
            i.sendRobberSetToClient(player, pos, target);
        }

    }

    public void sendRobberCostAndEarning(Player robber, Player targetPlayer, Resource stolenCard) {
        for (GameActor i : clients) {
            if (i.getPlayer().equals(robber) || i.getPlayer().equals(targetPlayer)) {
                System.out.println(this.getClass() + " WEGEN RÄUBER: " +
                        TOJSON.robberCost(false, targetPlayer, stolenCard).toString() + "\n");
                //---------
                i.sendRobberCostToClient(false, targetPlayer, stolenCard);

                System.out.println(
                        this.getClass() + " WEGEN RÄUBER: " +
                                TOJSON.robberEarning(false, robber, stolenCard) + "\n");
                //----------
                i.sendEarningsToClient(false, robber, stolenCard);
            } else {

                i.sendRobberCostToClient(true, targetPlayer, stolenCard);
                i.sendEarningsToClient(true, robber, stolenCard);
            }
        }
    }

    /**
     * Sends information that someone has played a knight card, contains the
     * target if the player has stolen a card from someone
     *
     * @param player player who played a knight card
     * @param pos    position of the robber
     * @param target player who is robbed
     */
    public void sendKnightPlayed(Player player, Position pos, int target) {
        for (GameActor i : clients) {
            i.sendKnightPlayedToClient(player, pos, target);
        }
    }

    /**
     * Sends the knight power if a player has earned it
     *
     * @param player player who gets the knight power
     */
    public void sendKnightPower(Player player) {
        for (GameActor i : clients) {
            i.sendKnightPowerToClient(player);
        }
    }

    /**
     * Sends the info that someone has played a roadbuilding card
     *
     * @param player  player who has played the roadbuilding card
     * @param streets streets that the player built
     */
    public void sendRoadBuilding(Player player, List<Position[]> streets) {
        for (GameActor i : clients) {
            i.sendRoadBuildingToClient(player, streets);
        }
    }

    /**
     * Sends the info that a player has played a monopoly card
     *
     * @param player player who has played a monopoly card
     * @param type   resource type for the monopoly
     */
    public void sendMonopolyCard(Player player, String type) {
        for (GameActor i : clients) {
            i.sendMonopolyCardToClient(player, type);
        }

    }

    /**
     * Sends the costs for each player that are created when someone has played
     * a monopoly card
     *
     * @param opponent player who hast to pay
     * @param type     type of resource
     * @param costs    resources that the player has to give away
     */
    public void sendMonopolyCosts(Player opponent, String type, int costs) {
        for (GameActor i : clients) {
            i.sendMonopolyCostsToClient(opponent, type, costs);
        }
    }

    /**
     * Sends the earning for the player who played a monopoly card
     *
     * @param player  player who has played the monopoly card
     * @param type    type of resource
     * @param earning resources that the player is earning
     */
    public void sendMonopolyEarning(Player player, String type, int earning) {
        for (GameActor i : clients) {
            i.sendMonopolyEarningToClient(player, type, earning);
        }
    }

    /**
     * Sends the info that someone has played an invention card
     *
     * @param player  player who has played an invention card
     * @param resSend resources that the player gets
     */
    public void sendInventionCard(Player player, int[] resSend) {
        for (GameActor i : clients) {
            i.sendInventionCardToClient(player, resSend);
        }
    }

    /**
     * Sends a trade request from a player to each player
     *
     * @param player     player who sends the trade request
     * @param give       resources that the player wants to give away
     * @param get        resources that the player wants to get
     * @param tradeIndex tradeIndex
     */
    public void sendTradeRequest(Player player, int[] give, int[] get, int tradeIndex) {
        for (GameActor i : clients) {
            i.sendTradeRequestToClient(player, give, get, tradeIndex);
        }
    }

    /**
     * Sends a message to each player when someone has accepted a trade offer
     *
     * @param player     player who gets the trade message
     * @param bool       true if trade is accepted
     * @param tradeIndex tradeIndex
     */
    public void TradeAccept(Player player, boolean bool, int tradeIndex) {
        for (GameActor i : clients) {
            i.TradeAcceptToClient(player, bool, tradeIndex);
        }
    }

    /**
     * Handles the trade when both players accepted the offer
     *
     * @param player player who trades
     * @param id     id of the player
     */
    public void doTrade(Player player, int id) {
        for (GameActor i : clients) {
            i.doTradeToClient(player, id);
        }
    }

    /**
     * signalises that a trade is no longer wished from player
     *
     * @param player player who stopped the trade
     */
    public void TradeBreak(Player player, int index) {
        for (GameActor i : clients) {
            i.TradeBreakToClient(player, index);
        }
    }

    /**
     * Setter for the logger
     *
     * @param log server logger
     */
    public void setLog(ServerLog log) {
        this.log = log;
    }
}
