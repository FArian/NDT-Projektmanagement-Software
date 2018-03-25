package controllers;

import actors.Lobby;
import actors.serverInterface.Interface;
import play.mvc.Controller;
import play.mvc.LegacyWebSocket;
import play.mvc.WebSocket;

/**
 * Created by F.Arian on 06.11.17.
 */
public class SocketController extends Controller {
    Lobby lobby=new Lobby();

    public LegacyWebSocket<String> getSocket() {
        return WebSocket.withActor(out -> Interface.props(out, lobby));
    }

}
