package controllers;

import actors.GameObservable;
import com.fasterxml.jackson.databind.JsonNode;
import play.mvc.Controller;
import akka.actor.*;
import play.libs.F.*;
import play.mvc.WebSocket;
import play.mvc.LegacyWebSocket;
import actors.GameActor;

/**
 * Created by F.Arian on 14.06.17.
 */
public class SocketController extends Controller {

    GameObservable gameObservable = new GameObservable();

    public  LegacyWebSocket<String> gameSocket() {
        return WebSocket.withActor(out -> GameActor.props(out, gameObservable));
    }

}
