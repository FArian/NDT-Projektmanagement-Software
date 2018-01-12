package com.androidjson.firebasegooglelogin_androidjsoncom.models.model.material;

import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.*;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.enums.*;

/**
 * Created by F.Arian on 29.11.17.
 */
public class LeadAron extends MATERIAL {
    private  String id;
    private static int instanceCounter = 0;
    private int counter = 0;

    public LeadAron() {
        super("LeadAron", TYPE.SAFETY);
        this.id = DATA.generateUniqueId();
        instanceCounter++;
        counter = instanceCounter;
    }

    public  String getId() {
        return id;
    }

    public int getCounter() {
        return counter;
    }


    @Override
    public String toString() {
        return "\n"+"LEAD_ARON{ " +
                "  NAME= " + super.getName() +
                ", ID= " + getId() +
                ", MODEL= " + getModel() +
                ", TYPE= " + getType() +
                ", SERIAL_NUMBER= " + getSerialNumber() +
                ", COUNTER = " + getCounter() +
                "}"+"\n";
    }
}
