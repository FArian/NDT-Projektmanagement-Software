package models;

/**
 * Created by F.Arian on 08.07.17.
 */
public class Trade {
    /**
     * the id the server gives a trade
     */
    private int tradeID;
    /**
     * the id of the latest player that accepted the trade
     */
    private int accepted;


    private int tradeSend;

    /**
     * Constructor for the Trade
     */


    private int[] give;
    private int[] get;
    private int[] accept;

    public Trade() {
        this.tradeID = 0;
        this.accepted = 0;
        accept=new int[3];
        tradeSend = 0;
    }

    public void tradeRequest(int[] give, int[] get) {
        this.setGive(give);
        this.setGet(get);
    }

    /**
     * Getter for the trade id
     *
     * @return int tradeIndex
     */
    public int getTradeIndex() {
        return this.tradeID;
    }

    /**
     * Setter for the trade id
     *
     */
    public void setTradeIndex(int tradeID) {
        this.tradeID = tradeID;
    }

    /**
     * Getter for the id of the player who accepted the trade
     *
     * @return int accepted
     */
    public int getAccepted() {
        return accepted;
    }

    /**
     * Setter for the id of the player who accepted the trade
     *
     * @param accepted
     */
    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public int[] getGive() {
        return give;
    }

    public void setGive(int[] give) {
        this.give = give;
    }

    public int[] getGet() {
        return get;
    }

    public void setGet(int[] get) {
        this.get = get;
    }

    public void setAccepter(int id){
        int i=0;
        while(accept[i]!=0){
            i++;
        }
        accept[i]=id;
    }

    public void resetAccepter(){
        int i=0;
        while(i<3){
            accept[i]=0;
            i++;
        }

    }
    public void removeAccepter(int id){
        int i=0;
        while(i<3&&accept[i]!=id){
            i++;
        }
        accept[i]=0;
    }

    public boolean isInAccepter(int id){
        for(int i=0; i<3;i++){
            if (accept[i]==id){
                return true;
            }
        }
        return false;
    }

    public int getTradeSend() {
        return tradeSend;
    }

    public void setTradeSend(int tradeSend) {
        this.tradeSend = tradeSend;
    }

}
