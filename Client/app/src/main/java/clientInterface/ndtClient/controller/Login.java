package rzeznika.ndtClient.controller;
/**
 * Created by F.Arian on 06.06.17.
 */
import java.util.logging.Logger;


public class Login {
    private static final Logger login = Logger.getLogger(Login.class.getName());

    public Login(String strParam) {
        login.info(strParam);
        login.fine("Fine...");
        login.finer("Finer...");
        login.finest("Finest...");
        login.warning("Warning...");
        login.severe("Severe...");
    }
    /**
     * Getter for the Logger
     *
     * @return Logger
     */

    public Logger getLogin() {
        return login;
    }



}
