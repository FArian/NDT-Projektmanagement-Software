package com.androidjson.firebasegooglelogin_androidjsoncom.models.model.material;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.*;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.enums.*;

/**
 * Created by F.Arian on 06.12.17.
 * class Image Quality Indicators
 */
public class IQI extends MATERIAL {
    private static int instanceCounter = 0;
    private  String id;
    private int counter = 0;

    public IQI() {
        super("IQI", TYPE.QUALITY);
        instanceCounter++;
        counter = instanceCounter;
        this.id=DATA.generateUniqueId();
    }

    public  String getId() {
        return id;
    }

    public int getCounter() {
        return counter;
    }


    @Override
    public String toString() {
        return "\n"+"IQI{" +
                "  NAME= " + super.getName() +
                ", ID= " + getId() +
                ", SERIAL_NUMBER= " + super.getSerialNumber() +
                ", MODEL= " + super.getModel() +
                ", TYPE= " + super.getType() +
                ", COUNTER = " + getCounter() +
                "}"+"\n";
    }
}
