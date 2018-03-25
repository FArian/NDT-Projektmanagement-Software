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
    private String iqi_Model;
    private int counterOfIqi;


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

    public String get_IQI_Model() {
        return iqi_Model;
    }

    /**
     * example 6 FE DIN 10mm
     * @param pos0 ex 6
     * @param pos1 ex FE
     * @param pos2 ex DIN
     * @param pos3 ex 10 +mm
     */
    public void set_IQI_Model(int pos0, String pos1, String pos2, int pos3) {
        this.iqi_Model=pos0+" "+pos1+" "+pos2+" "+pos3+"mm";
    }

    public int getCounterOfIqi() {
        return counterOfIqi;
    }

    public void setCounterOfIqi(int counterOfIqi) {
        this.counterOfIqi = counterOfIqi;
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
                ", MODEL = " + get_IQI_Model() +
                "}"+"\n";
    }
}
