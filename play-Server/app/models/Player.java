package models;


import java.util.ArrayList;

/**
 * Created by F.Arian on 05.07.17.
 */
public class Player {

    /**
     * the ID of the player
     */
    private int id;
    /**
     * name of the player
     */
    private String Name;
    /**
     * Actual status of the player
     */
    private String status;

    /**
     * number of the picture for the player
     */
    private int pictureNr;
    /**
     * color of player
     */
    private String color;
    /**
     * the resources the player currently has
     */
    private ArrayList<Resource> resources;
    /**
     * the developmentCards the player currently has
     */
    private ArrayList<DevelopmentCard> developmentCards;
    /**
     * the harbors the player can use to trade with
     */
    private ArrayList<HarberType> harbers;
    /**
     * the number of knightcards the player already played
     */
    private int knights;
    /**
     * the number of victory points the player currently has
     */
    private int playerVictoryPoints;
    /**
     * the number of victory cards the player currently has
     */
    private int playerVictoryCards;
    /**
     * the number of settlements the player can still place
     */
    private int playerAvailableSettlements;
    /**
     * the number of street the player can still place
     */
    private int playerAvailableStreet;
    /**
     * the number of city the player can still place
     */
    private int playerAvailableCitys;
    /**
     * the number of development cards a player has
     */
    private int playerDevCards;
    /**
     * the number of resources the player has
     */
    private int playerResAmount;
    /**
     * if the player has longest street
     */
    private boolean hasLongestStreet;
    /**
     * if the player is knighPower
     */
    private boolean hasKnighPower;


    public Player() {
        this.setId(0);
        this.setName("name");
        this.setColor("null");
        this.setPlayerVictoryPoints(0);
        this.setResources(new ArrayList<Resource>());
        this.setPlayerResAmount(0);
        this.setDevelopmentCards(new ArrayList<DevelopmentCard>());
        this.setPlayerDevCards(0);
        this.setKnights(0);
        this.setPlayerAvailableStreet(15);
        this.setPlayerAvailableSettlements(5);
        this.setPlayerAvailableCitys(4);
        this.setStatus("");
        this.setPictureNr(0);
        this.setPlayerVictoryCards(0);
        this.setHasKnightPower(false);
        this.setHasLongestStreet(false);


    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPictureNr(int pictureNr) {
        this.pictureNr = pictureNr;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }

    public void setDevelopmentCards(ArrayList<DevelopmentCard> developmentCards) {
        this.developmentCards = developmentCards;
    }

    /**
     * adds a harbers
     *
     * @param harbers
     */
    public void addHarbers(HarberType harbers) {
        this.harbers.add(harbers);
    }

    public void setKnights(int knights) {
        this.knights = knights;
    }

    /**
     * Checks if the player has a knight card
     *
     * @return boolean
     */
    public boolean playerHasKnightCard() {
        for (DevelopmentCard d : developmentCards) {
            if (d == DevelopmentCard.Siegpunkt) {
                return true;
            }
        }

        return false;
    }

    /**
     * checks if the player has a roadbuilding card
     *
     * @return boolean
     */
    public boolean playerHasRoadBuildingCard() {
        for (DevelopmentCard d : developmentCards) {
            if (d == DevelopmentCard.Straßenbau) {
                return true;
            }
        }
        return false;
    }

    /**
     * checks if the player has an invention card
     *
     * @return boolean
     */
    public boolean playerHasInventionCard() {
        for (DevelopmentCard d : developmentCards) {
            if (d == DevelopmentCard.Erfindung) {
                return true;
            }
        }
        return false;
    }

    /**
     * checks if the player has a monopoly card
     *
     * @return boolean
     */
    public boolean playerHasMonopolyCard() {
        for (DevelopmentCard d : developmentCards) {
            if (d == DevelopmentCard.Monopol) {
                return true;
            }
        }
        return false;
    }

    /**
     * checks if the player has a kind of resource
     *
     * @param res
     * @return boolean
     */
    public boolean playerhasResource(Resource res) {
        for (Resource r : resources) {
            if (r == res) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds resources in form of an int[]
     *
     * @param give
     */
    public void addResIndex(int[] give) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < give[i]; j++) {
                if (i == 0) {
                    addResource(Resource.Getreide);
                }
                if (i == 1) {
                    addResource(Resource.Wolle);
                }
                if (i == 2) {
                    addResource(Resource.Erz);
                }
                if (i == 3) {
                    addResource(Resource.Holz);
                }
                if (i == 4) {
                    addResource(Resource.Lehm);
                }
            }
        }
    }

    /**
     * Adds an amount of resources
     *
     * @param amount
     */
    public void addRes(int amount) {
        this.playerResAmount = playerResAmount + amount;
    }

    /**
     * Removes an amount of resources
     *
     * @param amount
     */
    public void removeRes(int amount) {
        this.playerResAmount = playerResAmount - amount;
    }


    /**
     * Removes resources in form of an int[]
     *
     * @param get
     */
    public void removeResIndex(int[] get) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < get[i]; j++) {
                if (i == 0) {
                    removeResource(Resource.Getreide);
                }
                if (i == 1) {
                    removeResource(Resource.Wolle);
                }
                if (i == 2) {
                    removeResource(Resource.Erz);
                }
                if (i == 3) {
                    removeResource(Resource.Holz);
                }
                if (i == 4) {
                    removeResource(Resource.Lehm);
                }

            }
        }
    }


    public void setPlayerVictoryPoints(int playerVictoryPoints) {
        this.playerVictoryPoints = playerVictoryPoints;
    }

    public void setPlayerVictoryCards(int playerVictoryCards) {
        this.playerVictoryCards = playerVictoryCards;
    }

    public void setPlayerAvailableSettlements(int playerAvailableSettlements) {
        this.playerAvailableSettlements = playerAvailableSettlements;
    }

    public void setPlayerAvailableStreet(int playerAvailableStreet) {
        this.playerAvailableStreet = playerAvailableStreet;
    }

    public void setPlayerAvailableCitys(int playerAvailableCitys) {
        this.playerAvailableCitys = playerAvailableCitys;
    }

    public void setPlayerDevCards(int playerDevCards) {
        this.playerDevCards = playerDevCards;
    }

    public void setPlayerResAmount(int playerResAmount) {
        this.playerResAmount = playerResAmount;
    }

    public void setHasLongestStreet(boolean hasLongestStreet) {
        this.hasLongestStreet = hasLongestStreet;
    }

    public void setHasKnightPower(boolean hasKnighPower) {
        this.hasKnighPower = hasKnighPower;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getStatus() {
        return status;
    }

    public int getPictureNr() {
        return pictureNr;
    }

    public String getColor() {
        return color;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public ArrayList<DevelopmentCard> getDevelopmentCards() {
        return developmentCards;
    }

    public ArrayList<HarberType> getHarbers() {
        return harbers;
    }

    public int getKnights() {
        return knights;
    }

    public int getPlayerVictoryPoints() {
        return playerVictoryPoints;
    }

    public int getPlayerVictoryCards() {
        return playerVictoryCards;
    }

    public int getPlayerAvailableSettlements() {
        return playerAvailableSettlements;
    }

    /**
     * Getter for the streets
     *
     * @return
     */

    public int getPlayerAvailableStreet() {
        return playerAvailableStreet;
    }

    public int getPlayerAvailableCitys() {
        return playerAvailableCitys;
    }

    public int getPlayerDevCards() {
        return playerDevCards;
    }

    /**
     * Getter for the amount of resources the player has
     *
     * @return int playerResAmount
     */
    public int getRes() {
        return playerResAmount;
    }

    /**
     * returns amount of all resources
     *
     * @return int[]: index 0: grain index 1: wool index 2: ore index 3: lumber
     * index 4: brick
     */

    public int[] getResAmountArray() {
        int[] amount = new int[5];
        int grain = 0, wool = 0, ore = 0, lumber = 0, brick = 0;
        for (int i = 0; i < resources.size(); i++) {
            if (resources.get(i) == Resource.Getreide) {
                grain++;
            }
            if (resources.get(i) == Resource.Erz) {
                ore++;
            }
            if (resources.get(i) == Resource.Wolle) {
                wool++;
            }
            if (resources.get(i) == Resource.Holz) {
                lumber++;
            }
            if (resources.get(i) == Resource.Lehm) {
                brick++;
            }
        }
        amount[0] = grain;
        amount[1] = wool;
        amount[2] = ore;
        amount[3] = lumber;
        amount[4] = brick;

        return amount;
    }

    /**
     * Getter for the amount of all kind of development cards
     *
     * @return [0] = MONOPOLY, [1] = ROADBUILDING, [2] = YEAROFPLENTY,
     * [3] = KNIGHT, [4] = VICTORYPOINT
     */

    public int[] getDevCardAmount() {
        int[] amount = new int[5];
        int monopoly = 0, roads = 0, plenty = 0, knights = 0, points = 0;

        for (int i = 0; i < developmentCards.size(); i++) {
            if (developmentCards.get(i) == DevelopmentCard.Monopol) {
                monopoly++;
            }
            if (developmentCards.get(i) == DevelopmentCard.Straßenbau) {
                roads++;
            }
            if (developmentCards.get(i) == DevelopmentCard.Erfindung) {
                plenty++;
            }
            if (developmentCards.get(i) == DevelopmentCard.Ritter) {
                knights++;
            }
            if (developmentCards.get(i) == DevelopmentCard.Siegpunkt) {
                points++;
            }
        }
        amount[0] = monopoly;
        amount[1] = roads;
        amount[2] = plenty;
        amount[3] = knights;
        amount[4] = points;

        return amount;
    }


    public boolean isHasLongestStreet() {
        return this.hasLongestStreet;
    }

    public boolean isHasKnighPower() {
        return this.hasKnighPower;
    }

    public void addVictoryCard() {
        this.playerVictoryCards++;
    }

    /**
     * adds victory points
     */
    public void addVictoryPoint(int victoryPoints) {
        this.playerVictoryPoints = playerVictoryPoints + victoryPoints;
    }

    /**
     * adds a list of resources, updates the playerResAmount of resources
     *
     * @param res
     */

    public void addRessources(ArrayList<Resource> res) {
        for (int i = 0; i < res.size(); i++) {
            this.resources.add(res.get(i));
        }
    }

    /**
     * adds a resource, updates the amount of resources
     *
     * @param resources
     */
    public void addResource(Resource resources) {
        this.resources.add(resources);
        this.playerResAmount++;
    }

    public void removeResource(Resource res) {
        for (int i = 0; i < resources.size(); i++) {
            if (resources.get(i) == res) {
                resources.remove(i);
                this.playerResAmount--;
                break;
            }


        }

    }

    /**
     * adds a development card, updates the amount of development cards
     *
     * @param devCards
     */
    public void addDevCards(DevelopmentCard devCards) {
        this.developmentCards.add(devCards);
        this.playerDevCards++;
    }

    /**
     * Adds an amount of development cards
     *
     * @param amount
     */
    public void addDevCards(int amount) {
        this.playerDevCards = playerDevCards + amount;
    }

    public void removeDevCards(DevelopmentCard devCards) {
        for (int i = 0; i < developmentCards.size(); i++) {
            if (developmentCards.get(i).equals(devCards)) {
                developmentCards.remove(i);
                this.playerDevCards--;
                break;
            }
        }
    }

    /**
     * Remove a development card
     */
    public void decreaseDevCard() {
        playerDevCards--;
    }

    /**
     * adds a knight card
     */
    public void addKnights() {
        this.knights++;
    }

    /**
     * adds a harbor
     *
     * @param harbor
     */
    public void addHarbor(HarberType harbor) {
        this.harbers.add(harbor);
    }

    /**
     * adds a settlement
     */
    public void addAvailableSettlement() {
        this.playerAvailableSettlements++;
    }

    /**
     * remove a settlement
     */
    public void removeAvailableSettlement() {
        this.playerAvailableSettlements--;
    }

    /**
     * remove a Street
     */
    public void removeAvailableStreet() {
        this.playerAvailableStreet--;
    }

    /**
     * removes a city and adds the settlement
     */
    public void removeAvailableCity() {
        this.playerAvailableCitys--;
        this.playerAvailableSettlements++;
    }


    public ArrayList<Resource> getRessources() {
        return resources;
    }
    public String toString(){
        return "Name :" +getName()+ " ID :"+getId()+ " Farbe :"+getColor() +  " Resourcen :"+getRes() +
                " Siegpunkt:"+getPlayerVictoryPoints() + " EntwicklungsKarte :"+ getPlayerDevCards() + " Stadt:"+getPlayerAvailableCitys() +"" +
                " Dorf :"+getPlayerAvailableSettlements()+" Available Street : " +getPlayerAvailableStreet() + " Status:"+getStatus() ;
    }
}

