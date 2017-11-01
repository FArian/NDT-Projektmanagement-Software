package models;

/**
 * Created by F.Arian on 08.07.17.
 */
public class LandTile {
    /**
     * The dice Number belonging to the tile
     */
    private Integer chipNr;
    private Resource type;
    private char ID;
    private Position position;
    private boolean robber;
    private Cross[] crosses;
    private LandTile[] neighbors;

    /**
     * Creates a land tile
     *
     * @param number the dice Number
     * @param type   the resource type
     * @param robber boolean value
     * @Param id     char A-S
     */
    public LandTile(Integer number, Resource type, boolean robber, String posX, String posY) {
        this.setChipNr(number);
        this.setType(type);
        int x = Integer.parseInt(posX);
        int y = Integer.parseInt(posY);
        this.setPosition(new Position(x, y));
        //this.setID(ID);
        setRobber(robber);

    }

    /**
     * Creates a land tile
     *  @param number the dice Number
     * @param type   the resource type
     * @param robber boolean value
     */
    public LandTile(Integer number, Resource type, boolean robber) {
        this.setChipNr(number);
        this.setType(type);
        this.setRobber(robber);
    }

    /**
     * Getter ans Setter for the chip number0 of a tile
     *
     * @return int
     */
    public Integer getChipNr() {
        return chipNr;
    }

    public void setChipNr(Integer chipNr) {
        this.chipNr = chipNr;
    }



    /**
     * Getter and Setter for the type of the tile
     *
     * @return Resources
     */
    public Resource getType() {
        return type;
    }

    public void setType(Resource type) {
        this.type = type;
    }


    /**
     * tells if robber is set here
     *
     * @return robber
     */
    public boolean isRobber() {
        return robber;
    }

    /**
     * sets or removes robber here
     *
     * @param robber boolean is robber here
     */

    public void setRobber(boolean robber) {
        this.robber = robber;
    }

    /**
     * Getter and Setter for the ID of the land
     *
     * @return ID
     */

    public char getID() {
        return ID;
    }

    public void setID(char id) {
        this.ID = id;
    }




    /**
     * Getter and Setter for the position of the land
     * @return Position
     */

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * returns a list of crossempty
     *
     * @return crossempty
     */
    public Cross[] getCrosses() {
        return crosses;
    }

    /**
     * Setter for the crossempty
     *
     * @param crosses the neighbours
     */
    public void setCrosses(Cross[] crosses) {
        this.crosses = crosses;
    }

    /**
     * returns a list of neighbour lands
     *
     * @return lands
     */

    public LandTile[] getNeighborLands() {
        return neighbors;
    }

    public int hasNeighbor(Resource type) {
        for (int i = 0; i < this.neighbors.length; i++) {
            if (this.neighbors[i].getType() == type) {
                return i;
            }

        }
        return -1;
    }

    /**
     * Setter for the neighbours
     *
     * @param lands the neighbours
     */
    public void setNeighborLands(LandTile[] lands) {
        this.neighbors = lands;
    }

    /**
     * checks if there is a builing at one of the crossempty of the tile
     *
     * @param player
     * @return boolean
     */
    public boolean hasBuilding(Player player) {
        for (Cross c : crosses) {
            if (c.getPlayer() != null && c.getPlayer().equals(player)) {
                return true;
            }
        }
        return false;
    }

}
