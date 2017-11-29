package models;


/**
 * Created by F.Arian on 29.11.17.
 */
public class Viewer {
    private String name;
    private int serialNumber;
    private String detdescriptive;
    private Location location;
    private String buyDate;
    private boolean status;


    private Type type;

    public Viewer(String name, int serialNumber) {
        this.setName(name);
        this.setSerialNumber(serialNumber);
        this.setDetdescriptive("currently not changed");
        this.setLocation(Location.CENTRAL);
        this.setBuyDate(null);
        this.setStatus(true);
        this.setType(Type.D_ROOM);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDetdescriptive() {
        return detdescriptive;
    }

    public void setDetdescriptive(String detdescriptive) {
        this.detdescriptive = detdescriptive;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
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


}
