package models;

/**
 * Created by F.Arian on 08.07.17.
 */
public class City {
    /**
     * a instance that shows which player builded it
     */

    private Player player;

    /**
     * Generates a City object
     *
     * @param player The building Player
     */
    public City(Player player) {
        this.player = player;
    }

    /**
     * Returns the player which rules the city
     *
     * @return The ruling player
     */
    public Player getPlayer() {
        return this.player;
    }
}
