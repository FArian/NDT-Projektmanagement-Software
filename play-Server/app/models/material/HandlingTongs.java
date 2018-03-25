package models.material;

import models.ServerLog;
import models.DATA;
import models.enums.TYPE;

/**
 * Created by F.Arian on 29.11.17.
 */
public class HandlingTongs extends MATERIAL {
    private  String id;
    private static int instanceCounter = 0;
    private double length;
    private int counter = 0;
    private ServerLog log = new ServerLog();

    public HandlingTongs() {
        super("HANDLING_TONGS", TYPE.SAFETY);
        this.setLength(-1);
        this.id = DATA.generateUniqueId();
        this.getLog().info(" NEW OBJECT CREATED, NAME : " + getName() + " " + getClass());
        instanceCounter++;
        counter = instanceCounter;

    }

    public int getCounter() {
        return counter;
    }

    public String getId() {
        return id;
    }

    public ServerLog getLog() {
        return log;
    }

    public void setLog(ServerLog log) {
        this.log = log;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double longer) {
        this.length = longer;
    }

    @Override
    public String toString() {
        return  "\n"+"HANDLING_TONGS{" +
                "  NAME= " + super.getName() +
                ", ID= " + getId() +
                ", SERIAL_NUMBER= " + super.getSerialNumber() +
                ", MODEL= " + super.getModel() +
                ", TYPE= " + super.getType() +
                ", LENGTH= " + length +
                ", COUNTER = " + getCounter() +
                "}"+"\n";
    }
}
