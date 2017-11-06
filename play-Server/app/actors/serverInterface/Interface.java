package actors.serverInterface;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * Created by F.Arian on 06.11.17.
 */
public class Interface extends UntypedActor {


    public Interface(ActorRef out, ObservableMail observableMail) {

    }

    @Override
    public void onReceive(Object message) throws Throwable {

    }
    public static Props props(ActorRef out, ObservableMail observableMail) {
        return Props.create(Interface.class, out, observableMail);
    }
}
