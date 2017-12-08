package models.material;

import models.ServerLog;
import models.DATA;
import models.enums.TYPE;

/**
 * Created by F.Arian on 29.11.17.
 */
public class LeadAron extends MATERIAL {
    private static String id;
    private static int instanceCounter = 0;
    private ServerLog log = new ServerLog();
    private int counter = 0;

    public LeadAron() {
        super("LeadAron", TYPE.SAFETY);
        this.id = DATA.generateUniqueId();
        this.getLog().info(" NEW OBJECT CREATED, NAME: " + getName());
        instanceCounter++;
        counter = instanceCounter;
    }

    public static String getId() {
        return id;
    }

    public int getCounter() {
        return counter;
    }

    public ServerLog getLog() {
        return log;
    }


    @Override
    public String toString() {
        return "\n"+"LEAD_ARON{ " +
                "  NAME= " + super.getName() +
                ", ID= " + getId() +
                ", MODEL= " + getModel() +
                ", TYPE= " + getType() +
                ", SERIAL_NUMBER= " + getSerialNumber() +
                ", COUNTER = " + getCounter() +
                "}"+"\n";
    }
}
