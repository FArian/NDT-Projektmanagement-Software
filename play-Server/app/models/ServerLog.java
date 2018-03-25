package models;

import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.logging.*;

/**
 * Created by F.Arian on 06.11.17.
 */
public class ServerLog extends Observable {
    private static File folder;
    private Logger log;

    /**
     * Constructor that creates the main Logger
     */
    public ServerLog() {
        this.setLog(Logger.getLogger(Logger.GLOBAL_LOGGER_NAME));
        this.folder = new File("ServerLogs");
        if (!folder.exists())
            folder.mkdir();
        Logger root = Logger.getLogger("");
        FileHandler txt = null;
        try {
            txt = new FileHandler(new File(folder, "ServerLog.txt").getAbsolutePath());
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        root.setLevel(Level.INFO);
        txt.setFormatter(new Formatter() {

            @Override
            public String format(LogRecord record) {
                String ret = "";

                if (record.getLevel().intValue() >= Level.WARNING.intValue()) {
                    ret += "ATTENTION";
                }
                ret += record.getLevel() + " :";
                ret += this.formatMessage(record);
                ret += "\r\n";
                return ret;
            }
        });
        root.addHandler(txt);

    }

    public static File getFolder() {
        return folder;
    }

    public static void setFolder(File folder) {
        ServerLog.folder = folder;
    }

    /**
     * uses the getLog().info(string) method but also notifies the view with
     * that string
     *
     * @param msg
     */
    public void info(String msg) {

        notifyView(msg);
        getLog().info("\n" + msg);
    }

    /**
     * notifies the view with a new string that can be printed for example
     *
     * @param info
     */
    public void notifyView(String info) {
        setChanged();
        notifyObservers(info);
    }

    /**
     * Getter for the Logger
     *
     * @return Log
     */
    public Logger getLog() {
        return log;
    }

    /**
     * Setter for the Logger
     *
     * @param log
     */
    public void setLog(Logger log) {
        this.log = log;
    }


}
