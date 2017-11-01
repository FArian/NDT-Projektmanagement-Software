package actors;

import java.util.Observable;

/**
 * Created by F.Arian on 14.07.17.
 */
public class Server extends Observable implements Runnable {
    /**
     * the protocol version that is supported by this server
     */
    private static final String PROTOCOL = "1.0";
    /**
     * Actor a game
     */
    private GameObservable gameObservable;
    /**
     * the logger
     */
    private ServerLog serverLog;
    /**
     * id counter that increases with each connected client
     */
    private int idCounter;
    private GameActor gameActor;

    @Override
    public void run() {


    }

    public Server() {
        this.serverLog = new ServerLog();
        MessageWriter messageWriter = new MessageWriter();
        messageWriter.setLog(serverLog);
        gameObservable = new GameObservable();
        this.addObserver(messageWriter);
        idCounter = 0;
    }

    /**
     * Increases the id counter and returns therefore the current id that is not
     * yet used.
     *
     * @return
     */
    private synchronized int getAndIncreaseIDCounter() {
        idCounter++;
        return idCounter;
    }

    /**
     * Getter for the logger
     *
     * @return ServerLog
     */
    public ServerLog getServerLog() {
        return serverLog;
    }
    /**
     * Setter for the logger
     *
     * @param serverLog
     */
    public void setServerLog(ServerLog serverLog) {
        this.serverLog = serverLog;
    }
}
