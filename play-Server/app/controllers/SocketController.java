package controllers;

import actors.serverInterface.Interface;
import actors.serverInterface.ObservableMail;
import play.mvc.Controller;
import play.mvc.WebSocket;
import play.mvc.LegacyWebSocket;

/**
 * Created by F.Arian on 14.06.17.
 */
public class SocketController extends Controller {

    ObservableMail observableMail = new ObservableMail();

    public  LegacyWebSocket<String> getSocket() {
        return WebSocket.withActor(out -> Interface.props(out, observableMail));
    }

}
