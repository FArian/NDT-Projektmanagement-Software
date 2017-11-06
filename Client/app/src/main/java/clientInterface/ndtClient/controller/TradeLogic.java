package rzeznika.ndtClient.controller;

import java.util.Observable;

import rzeznika.ndtClient.model.Game;
import rzeznika.ndtClient.model.Player;
import rzeznika.ndtClient.model.Trade;

/**
 * Created by arzez on 16.07.2017.
 */

public class TradeLogic extends Observable {
    /**
     * trade from the model
     */
    private Trade trade;

    /**
     * constructor
     **/
    public TradeLogic(Game game) {
        this.trade = game.getTrade();
    }

    /**
     * Notifies about incoming trade requests
     */
    public void tradeRequest(int[] give, int[] get){
        trade.tradeRequest(give, get);
        setChanged();
        notifyObservers(new Information("TradeRequest"));

    }
    /**
     * Setter for the TradeIndex. TradeIndex is the trade id that the server
     * sends with every trade
     *
     * @param tradeIndex
     */
    public void setTradeIndex(int tradeIndex) {
        trade.setTradeIndex(tradeIndex);
        setChanged();
        notifyObservers(new Information("TradeIndex"));
    }
    /**
     * Setter for tradeAccepted. Saves the latest player's id which accepted the
     * trade
     *
     * @param tradeAccepted
     */
    public void setAccepted(int tradeAccepted) {
        trade.setAccepted(tradeAccepted);
        setChanged();
        notifyObservers(new Information("TradeAccepted"));

    }
    /**
     * Notifies when a player declined a trade
     * @param player
     */
    public void setAcceptedFalse(Player player){
        Information info = new Information("TradeFalse");
        info.setPlayer(player);
        setChanged();
        notifyObservers(info);
    }

    /** used to signals that the trade is finished **/
    public void setTradeDone() {
        setChanged();
        notifyObservers(new Information("TradeDone"));
    }

    /** used to signal that the trade is canceled by player Declined
     *
     * @param playerDeclined canceler
     */
    public void setTradeCanceled(Player playerDeclined) {
        Information info = new Information("TradeDeclined");
        info.setPlayer(playerDeclined);
        setChanged();
        notifyObservers(info);
    }

    /** shows that the trade is no longer wished for **/
    public void setTradeWithdrawn() {
        setChanged();
        notifyObservers(new Information("TradeWithdrawn"));
    }


}
