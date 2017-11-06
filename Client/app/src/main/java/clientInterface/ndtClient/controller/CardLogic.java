package rzeznika.ndtClient.controller;

/**
 * Created by Lena on 27.06.2017.
 */

import java.util.ArrayList;
import java.util.List;

import rzeznika.ndtClient.model.DevelopmentCard;
import rzeznika.ndtClient.model.Game;
import rzeznika.ndtClient.model.Player;

/**
 * Class that provides methods for the logic of the development cards
 *
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
            case INVENTION:
                if (cards.contains(DevelopmentCard.INVENTION)) {
                    return true;
                }
                break;
            case KNIGHT:
                if (cards.contains(DevelopmentCard.KNIGHT)) {
                    return true;
                }
                break;
            case MONOPOLY:
                if (cards.contains(DevelopmentCard.MONOPOLY)) {
                    return true;
                }
                break;
            case ROADBUILDING:
                if (cards.contains(DevelopmentCard.ROADBUILDING)) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }
}
