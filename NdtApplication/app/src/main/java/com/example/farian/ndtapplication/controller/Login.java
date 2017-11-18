package com.example.farian.ndtapplication.controller;

import java.util.logging.Logger;

/**
 * Created by F.Arian on 08.11.17.
 */

public class Login {
    private static final Logger LOGIN= Logger.getLogger(Login.class.getName());

    public Login(String strParam) {
        LOGIN.info(strParam);
        LOGIN.fine("Fine...");
        LOGIN.finer("Finer...");
        LOGIN.finest("Finest...");
        LOGIN.warning("Warning...");
        LOGIN.severe("Severe...");
    }
    /**
     * Getter for the Logger
     * @return Logger
     */

    public Logger getLogin() {
        return LOGIN;
    }

}
