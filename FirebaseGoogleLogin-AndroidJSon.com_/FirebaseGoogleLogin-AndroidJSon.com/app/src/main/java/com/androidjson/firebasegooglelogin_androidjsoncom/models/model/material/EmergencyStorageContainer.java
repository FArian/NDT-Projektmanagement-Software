package com.androidjson.firebasegooglelogin_androidjsoncom.models.model.material;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.DATA;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.enums.*;

/**
 * Created by F.Arian on 28.01.18.
 */

public class EmergencyStorageContainer extends MATERIAL {


    private  String id;
    private static int instanceCounter = 0;
    private int counter = 0;

    public EmergencyStorageContainer() {
        super("EMERGENCY STORAGE CONTAINER",TYPE.SAFETY);
        this.id = DATA.generateUniqueId();
        instanceCounter++;
        counter = instanceCounter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static int getInstanceCounter() {
        return instanceCounter;
    }


    public int getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return "EmergencyStorageContainer{" +
                ", ID='" + id + '\'' +
                ", COUNTER=" + counter +
                "}";
    }
}
