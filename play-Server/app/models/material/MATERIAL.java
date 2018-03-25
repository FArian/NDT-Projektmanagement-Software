package models.material;


import models.ServerLog;
import models.DATA;
import models.enums.TYPE;

/**
 * Created by F.Arian on 06.11.17.
 */
public class MATERIAL {
    private String name;
    private String model;
    private TYPE type;
    private String SerialNumber;
    private ServerLog log = new ServerLog();
    private boolean isStatus;


    public MATERIAL(String name, TYPE type) {
        setName(name);
        setModel(DATA.creatId("-" + name));
        setSerialNumber(DATA.generateUniqueId());
        setType(type);
        log.info("NEW OBJECT CREATED,NAME:  " +getClass() + "");


    }

    public boolean isStatus() {
        return isStatus;
    }

    public void setStatus(boolean status) {
        isStatus = status;
    }



    public ServerLog getLog() {
        return log;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MATERIAL{" +
                " NAME= " + getName() +
                ", MODEL= " + getModel() +
                ", TYPE= " + getType() +
                ", SERIAL_NUMBER= " + getSerialNumber() +
                "}";
    }
}
