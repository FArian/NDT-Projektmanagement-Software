package models;

import actors.serverInterface.ServerLog;

/**
 * Created by F.Arian on 29.11.17.
 */
public class LeadAron extends MATERIAL {
    private static String id;
    private ServerLog log=new ServerLog();

    public LeadAron() {
        super("LeadAron",DATA.creatId("-LEAD_ARON"), TYPE.SAFETY);
        this.id=DATA.generateUniqueId();
        this.getLog().info(" NEW OBJECT CREATED, NAME: "+getName());
    }
    public static String getId() {
        return id;
    }

    public ServerLog getLog() {
        return log;
    }


    @Override
    public String toString() {
        return "LEAD_ARON{ " +"\n"+
                ", NAME= " + super.getName() +"\n"+
                ", ID= " + getId() +"\n"+
                ", MODEL= " + getModel() +"\n"+
                ", TYPE= " + getType() +"\n"+
                ", SERIAL_NUMBER= " + getSerialNumber() +"\n"+
                ", PROJECT_NAME= " + super.getProject().getName() +"\n"+
                ", PROJECT_NR= " + super.getProject().getProjectNumber() +"\n"+
                ", PROJECT_LOCATION= " + super.getProject().getLocation()+"\n"+
                "} ";
    }
}
