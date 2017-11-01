package models;

/**
 * Created by F.Arian on 08.07.17.
 */
public class Edge {

    private Cross cross1, cross2;
    private Street street;

    /**
     * Generates a Edge object
     *
     * @param cross1
     * @param cross2
     */

    public Edge(Cross cross1, Cross cross2) {
        this.cross1 = cross1;
        this.cross2 = cross2;
        this.street = null;
    }

    /**
     * Getter for the first cross
     *
     * @return Cross1
     */
    public Cross getCross1() {
        return this.cross1;
    }

    /**
     * Getter for the first cross
     *
     * @return Cross2
     */
    public Cross getCross2() {
        return this.cross2;
    }

    /**
     * Getter for the street
     *
     * @return street
     */
    public Street getStreet() {
        return this.street;
    }

    /**
     * Setter for the street
     *
     * @param street
     */
    public void setStreet(Street street) {
        this.street = street;
    }

    /**
     * Setter for the cross1
     *
     * @param cross1
     */
    public void setCross1(Cross cross1) {
        this.cross1 = cross1;
    }

    /**
     * Setter for the  cross2
     *
     * @param cross2
     */
    public void setCross2(Cross cross2) {
        this.cross2 = cross2;
    }
}
