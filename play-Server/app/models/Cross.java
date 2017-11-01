package models;

/**
 * Created by F.Arian on 08.07.17.
 */
public class Cross {
    /**
     * Defines the Position in the field
     */
    private Position position;
    /**
     * The City that is placed on this cross
     */
    private City city;
    /**
     * The settlement that is placed on this cross
     */
    private Settlement settlement;
    private LandTile[] landTiles;
    private Player player;
    /**
     * Boolean to check if the cross is used to display a possible settlement
     */
    private boolean isPossibleSettlement = false;
    /**
     * Boolean to check if the cross is used to display a possible city
     */
    private boolean isPossibleCity = false;

    /**
     * creates a new cross, on which neither a city nor a settlement is placed
     */
    public Cross(Position position) {
        this.settlement = null;
        this.city = null;
        this.position = position;
        this.player = null;
    }

    /**
     * Returns the settlement
     *
     * @return the settlement placed on this cross
     */
    public Settlement getSettlement() {
        return this.settlement;
    }

    public void setSettlement(Player player) {
        this.settlement = new Settlement(player);
        this.player = player;
    }

    /**
     * removes a settlement from that cross
     */
    public void removeSettlement() {
        this.settlement = null;
    }

    public Position getPosition() {
        return this.position;
    }

    public City getCity() {
        return this.city;
    }

    public LandTile[] getLandTiles() {
        return this.landTiles;
    }

    public Player getPlayer() {
        return this.player;
    }

    public boolean isPossibleSettlement() {
        return this.isPossibleSettlement;
    }

    public boolean isPossibleCity() {
        return this.isPossibleCity;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setCity(City city) {
        this.settlement = null;
        this.city = new City(player);
    }
    /** Builds a city ruled by player at the cross
     *
     * @param player the player who will rule the new city
     */
    public void setCity(Player player){
        this.settlement = null;
        this.city = new City(player);
    }

    public void setLandTiles(LandTile[] landTiles) {
        this.landTiles = landTiles;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setPossibleSettlement(boolean possibleSettlement) {
        this.isPossibleSettlement = possibleSettlement;
    }

    public void setPossibleCity(boolean possibleCity) {
        this.isPossibleCity = possibleCity;
    }
    public boolean equals(Cross crossObject) {
        return (crossObject.position.getX() == this.position.getX() && crossObject.position.getY() == this.position.getY());
    }
}
