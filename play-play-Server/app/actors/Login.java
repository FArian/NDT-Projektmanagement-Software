package actors;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Created by F.Arian on 08.07.17.
 */
public class Login {
    private Logger login = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static File file = new File("CATANLOGGIN");

    public Login(String strParam) {
        if (!file.exists())
            file.mkdir();
        Logger rootLogger = Logger.getLogger("");
        FileHandler fileHandler = null;


        try {
            fileHandler = new FileHandler(new File(file, strParam).getAbsolutePath());

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        rootLogger.setLevel(Level.INFO);
        fileHandler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                String result = "";
                if (record.getLevel().intValue() >= Level.WARNING.intValue()) {
                    result += "ATTENTION";
                }
                result += record.getLevel() + ":";
                result += this.formatMessage(record);
                result += "\r\n";
                return result;
            }
        });
        rootLogger.addHandler(fileHandler);
    }

    /**
     * Getter for the Logger
     *
     * @return Logger
     */

    public Logger getLogin() {
        return login;
    }

    /**
     * Setter for the Logger
     *
     * @param login
     */
    public void setLogger(Logger login) {
        this.login = login;
    }
}
