package com.androidjson.firebasegooglelogin_androidjsoncom.models.model.material;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.*;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.enums.*;


/**
 * Created by F.Arian on 29.11.17.
 */
public class RadiationSigns extends MATERIAL {


    private  String id;
    private static int instanceCounter = 0;
    private int counter = 0;

    public RadiationSigns() {
        super("DEFAULT-NAME", TYPE.SAFETY);
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
        return "\n"+"RADIATION_SIGNS{" +
                "  NAME= " + super.getName() +
                ", ID= " + getId() +
                ", TYPE= " + super.getType() +
                ", COUNTER = " + getCounter() +
                "}"+"\n";
    }
}
