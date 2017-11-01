package actors;

import models.DevelopmentCard;
import models.Player;
import models.Resource;

import java.util.List;

/**
 * Created by F.Arian on 05.07.17.
 */
public class Information {

    /**
     * defines the type of information the object holds. Mandatory information!
     */
    private String type;
    /**
     * Contains information, depending on the type. For example resource cards.
     * Optional information
     */
    private List<?> content;
    /**
     * Player that is important for the information. Optional information
     */
    private Player player;
    /**
     * amount of something
     */
    int amount;
    /**
     * dices in case the information is about the dices
     */
    int[] dices;
    /**
     * a resource card
     **/
    private Resource resourceCard;
    /**
     * a development card
     */
    private DevelopmentCard developmentCard;

    /**
     * constructor
     * create an information object
     *
     * @param type
     */
    public Information(String type) {
        this.setType(type);
        this.setContent(null);
        this.setPlayer(null);
        this.setAmount(0);
        this.setDices(null);
        this.setResourceCard(null);

    }

    public String getType() {
        return type;
    }

    public List<?> getContent() {
        return content;
    }

    public Player getPlayer() {
        return player;
    }

    public int getAmount() {
        return amount;
    }

    public int[] getDices() {
        return dices;
    }

    public Resource getResourceCard() {
        return resourceCard;
    }

    public DevelopmentCard getDevelopmentCard() {
        return developmentCard;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDices(int[] dices) {
        this.dices = dices;
    }

    public void setResourceCard(Resource resourceCard) {
        this.resourceCard = resourceCard;
    }

    public void setDevelopmentCard(DevelopmentCard developmentCard) {
        this.developmentCard = developmentCard;
    }

}
