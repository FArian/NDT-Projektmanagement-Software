package models;


import actors.serverInterface.ServerLog;

/**
 * Created by F.Arian on 29.11.17.
 */
public class Viewer {
    private String name;
    private String serialNumber;
    private MODEL model;
    private String detdescriptive;
    private LOCATION location;
    private String buyDate;
    private boolean status;
    private String id;
    private ServerLog log=new ServerLog();
    private TYPE type;
    private String madeIn;
    private SIZE size;

    public Viewer() {
        this.setName("VIEWER");
        this.setModel(MODEL.MODEL_GERMANY);
        this.setDetdescriptive("CURRENTLY NOT CHANGED");
        this.setLocation(LOCATION.CENTRAL);
        this.setBuyDate(null);
        this.setStatus(true);
        this.setType(TYPE.D_ROOM);
        this.id=DATA.generateUniqueId();
        this.setSerialNumber(DATA.generateUniqueId());
        this.getLog().info(" NEW OBJECT CREATED, NAME : " +getName());
        this.setMadeIn("GERMANY");
        this.setSize(SIZE.VIEWING_SCREEN_100X400mm);

    }

    public SIZE getSize() {
        return size;
    }

    public void setSize(SIZE size) {
        this.size = size;
    }


    public String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }


    public MODEL getModel() {
        return model;
    }

    public void setModel(MODEL model) {
        this.model = model;
    }


    public ServerLog getLog() {
        return log;
    }


    public String getId() {
        return id;
    }


    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String  getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDetdescriptive() {
        return detdescriptive;
    }

    public void setDetdescriptive(String detdescriptive) {
        this.detdescriptive = detdescriptive;
    }

    public LOCATION getLocation() {
        return location;
    }

    public void setLocation(LOCATION location) {
        this.location = location;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "VIEWER{" +"\n"+
                ", NAME= " + name +
                ", ID= " + id +
                ", MODEL= " + getModel() +
                ", SIZE= " + size +"\n"+
                ", SERIAL_NUMBER= " + serialNumber +
                ", DET_DESCRIPTIVE= " + detdescriptive +
                ", LOCATION= " + location +
                ", BUY_DATE= " + buyDate +
                ", STATUS= " + status +
                ", TYPE= " + type +
                ", MADE_IN= " + madeIn +"\n"+
                "}";
    }
}
