package models.processing;

import actors.serverInterface.ServerLog;
import models.*;
import models.enums.LOCATION;
import models.enums.MODEL;
import models.enums.NAME;
import models.enums.SIZE;

import java.time.LocalDate;


/**
 * Created by F.Arian on 04.12.17.
 */
public class PROCESSING {

    private NAME name;
    private MODEL model;
    private SIZE size;
    private String description;
    private String expireDate;
    private LOCATION location;
    private Project project;
    private ServerLog log= new ServerLog();
    private static final LocalDate LOCAL_DATE = LocalDate.now();
    private String serialNumber;
    private static int counter=DATA.counter(0);
    public int getCounter() {return counter;}
    public PROCESSING(NAME name, MODEL model, SIZE size) {
        this.setName(name);
        this.setModel(model);
        this.setSize(size);
        this.setLocation(LOCATION.CENTRAL);
        this.setDescription("not yet set");
        this.setExpireDate(DATA.convertDate(LOCAL_DATE.plusYears(1)));
        this.setProject(new Project());
        this.getLog().info(" NEW OBJECT CREATED, NAME : "+getModel().name().toString() +" "+getClass());
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

    public Project getProject() {
        return project;
    }


    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "PROCESSING{" + "\n" +
                ", NAME= " + name + "\n" +
                ", ID_DEV = " + ChemicalsDeveloper.getID() + "\n" +
                ", ID_FIX= " + ChemicalsFixer.getID() + "\n" +
                ", MODEL= " + model + "\n" +
                ", SIZE= " + size + "\n" +
                ", DESCRIPTION= " + description + "\n" +
                ", EXPIRE_DATE= " + expireDate + "\n" +
                ", DATE_IS_EXPIRED= " + isExpiredDate() + "\n" +
                ", LOCATION= " + getLocation() + "\n" +
                '}';
    }

}
