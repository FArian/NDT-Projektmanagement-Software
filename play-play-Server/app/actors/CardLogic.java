package actors;

import models.DevelopmentCard;
import models.Edge;
import models.Game;
import models.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by F.Arian on 08.07.17.
 */
public class CardLogic {

    /** an instance of game **/
    private Game game;

    /**
     * Constructor for the logic of the cards
     *
     * @param game
     */
    public CardLogic(Game game) {
        this.game = game;

    }

    // /**
    // * a development card that gives all the other players card of a specific
    // * resource type to the player that played it
    // *
    // * @param id
    // * @param res
    // */
    // private void monopol(int id, Resources res) {
    // Player player = game.getPlayer(id);
    // while (player.getRessources().contains(res)) {
    // controller.removeResource(id, res);
    // controller.addResources(id, res);
    // }
    // }

    /**
     * Used to buy a development Card. Shifts a Developmentcard from gameModel
     * to playerModel
     *
     * @param id
     * @return boolean
     */
    public boolean developmentCardPossible(int id) {
        Player player = game.getPlayer(id);
        int[] amount = player.getResAmountArray();
        if (amount[0] > 0 && amount[1] > 0 && amount[2] > 0) {
            return true;
        } else {

            return false;
        }
    }

    /**
     * checks if a development card can be played (is not buyed this round, and
     * is available)
     *
     * @param player
     *            who wants to play the devCard
     * @param dev
     *            the to playing card
     * @param newCards
     *            cards bought this round
     * @return boolean if its possible
     */
    public boolean isCardOkay(Player player, DevelopmentCard dev, List<DevelopmentCard> newCards) {
        ArrayList<DevelopmentCard> cards = new ArrayList<DevelopmentCard>();
        cards.addAll(player.getDevelopmentCards());
        for (int i = 0; i < newCards.size(); i++) {
            cards.remove(newCards.get(i));
        }

        switch (dev) {
            case Erfindung:
                if (cards.contains(DevelopmentCard.Erfindung)) {
                    return true;
                }
                break;
            case Ritter:
                if (cards.contains(DevelopmentCard.Ritter)) {
                    return true;
                }
                break;
            case Monopol:
                if (cards.contains(DevelopmentCard.Monopol)) {
                    return true;
                }
                break;
            case Straßenbau:
                if (cards.contains(DevelopmentCard.Straßenbau)) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }
}
