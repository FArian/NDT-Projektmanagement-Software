package com.androidjson.firebasegooglelogin_androidjsoncom.models.model.processing;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.*;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.enums.*;

/**
 * Created by F.Arian on 04.12.17.
 */
public class Fixer extends PROCESSING {
    private  String id = "";
    private static int instanceCounter = 0;
    private int counter = 0;

    public Fixer(NAME name, MODEL model, SIZE size) {
        super(name, model, size);
        this.id = DATA.generateUniqueId();
        instanceCounter++;
        counter = instanceCounter;

    }

    /**
     * static for calling from parent Class in toString()
     *
     * @return
     */
    public  String getID() {
        return id;
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return "FIXER{" +
                "  NAME= " + super.getName() +
                ", ID = " + getID() +
                ", MODEL= " + super.getModel() +
                ", SIZE= " + super.getSize() +
                ", SERIAL_NUMBER= " + super.getSerialNumber() +
                ", DESCRIPTION= " + super.getDescription() +
                ", EXPIRE_DATE= " + super.getExpireDate() +
                ", DATE_IS_EXPIRED= " + super.isExpiredDate() +
                ", LOCATION= " + super.getLocation() +
                ", COUNTER = " + counter +
                "}";
    }
}
