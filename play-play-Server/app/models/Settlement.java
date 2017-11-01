package models;

/**
 * Created by F.Arian on 08.07.17.
 */
public class Settlement {
    /**
     * The player who rules the settlement
     */
    private Player player;

    /**
     * Constructor to create a settlement
     *
     * @param player
     */
    public Settlement(Player player) {
        setplayer(player);
    }

    /**
     * sets the ruler of the settlement
     *
     * @param player
     */
    public void setplayer(Player player) {
        this.player = player;
    }

    /**
     * gets the ruler of the settlement
     *
     * @return
     */
    public Player getPlayer() {
        return this.player;
    }
}
