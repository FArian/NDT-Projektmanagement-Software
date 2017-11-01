package models;

/**
 * Created by F.Arian on 08.07.17.
 */
public class Street {
    /**
     * The player to whom the street belongs
     */
    private Player player;

    /**
     * Constructor to create a street.
     *
     * @param player
     */
    public Street(Player player) {
        this.setPlayer(player);
    }

    /**
     * sets the player to whom the street belongs
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * gets the player to whom the street belongs
     *
     * @return player
     */
    public Player getPlayer() {
        return this.player;
    }
}
