package models;

/**
 * Created by F.Arian on 08.07.17.
 */
public class Position {
    private int x, y;

    /**
     * Constructor that creates a position with a specific x and y coordinate
     *
     * @param x
     * @param y
     */

    public Position(int x, int y) {
        this.y = y;
        this.x = x;
    }

    /**
     * Getter for the x coordinate
     *
     * @return int x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Setter for the x coordinate
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Getter for the x coordinate
     *
     * @return int y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Setter for the x coordinate
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

}
