package models;

import actors.serverInterface.ServerLog;

/**
 * Created by F.Arian on 29.11.17.
 */
public class HandlingTongs extends MATERIAL {
    private double length;
    private static String id;
    private ServerLog log=new ServerLog();

    public HandlingTongs() {
        super("HANDLING_TONGS",DATA.creatId("SET IT"), TYPE.SAFETY);
        this.setLength(-1);
        this.id=DATA.generateUniqueId();
        this.getLog().info(" NEW OBJECT CREATED, NAME : " +getName() +" "+getClass());

    }

    public  String getId() {
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
        return "HANDLING_TONGS{" +"\n"+
                ", NAME= " + super.getName() +
                ", ID= " + getId() +
                ", SERIAL_NUMBER= " + super.getSerialNumber() +
                ", MODEL= " + super.getModel() +
                ", TYPE= " + super.getType() +
                ", SERIAL_NUMBER= " + super.getSerialNumber() +
                ", PROJECT_NAME= " + super.getProject().getName() +
                ", PROJECT_NR= " + super.getProject().getProjectNumber() +
                ", PROJECT_LOCATION= " + super.getProject().getLocation()+
                ", LENGTH= " + length +"\n"+
                "} ";
    }
}
