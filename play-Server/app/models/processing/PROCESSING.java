package models.processing;

import models.ServerLog;
import models.DATA;
import models.enums.LOCATION;
import models.enums.MODEL;
import models.enums.NAME;
import models.enums.SIZE;

import java.time.LocalDate;


/**
 * Created by F.Arian on 04.12.17.
 */
public class PROCESSING {

    private static final LocalDate LOCAL_DATE = LocalDate.now();
    private NAME name;
    private MODEL model;
    private SIZE size;
    private String description;
    private String expireDate;
    private LOCATION location;
    private ServerLog log = new ServerLog();
    private String serialNumber;


    public PROCESSING(NAME name, MODEL model, SIZE size) {
        this.setName(name);
        this.setModel(model);
        this.setSize(size);
        this.setLocation(LOCATION.CENTRAL);
        this.setDescription("not yet set");
        this.setExpireDate(DATA.convertDate(LOCAL_DATE.plusYears(1)));
        this.getLog().info("NEW OBJECT CREATED,NAME:  " +getClass() + "\n");
        this.setSerialNumber(DATA.generateUniqueId());

    }


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }


    public ServerLog getLog() {
        return log;
    }


    public NAME getName() {
        return name;
    }

    public void setName(NAME name) {
        this.name = name;
    }

    public MODEL getModel() {
        return model;
    }

    public void setModel(MODEL model) {
        this.model = model;
    }

    public SIZE getSize() {
        return size;
    }

    public void setSize(SIZE size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public LOCATION getLocation() {
        return location;
    }

    public void setLocation(LOCATION location) {
        this.location = location;
    }

    public boolean isExpiredDate() {
        return this.getExpireDate().equals(DATA.getLocalDate());
    }

    @Override
    public String toString() {
        return "PROCESSING{" + "\n" +
                ", NAME= " + name +
                ", MODEL= " + model +
                ", SIZE= " + size +
                ", DESCRIPTION= " + description +
                ", EXPIRE_DATE= " + expireDate +
                ", DATE_IS_EXPIRED= " + isExpiredDate() +
                ", LOCATION= " + getLocation() +
                '}';
    }

}
