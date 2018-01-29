package com.androidjson.firebasegooglelogin_androidjsoncom.models.model.material;

import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.*;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.enums.*;


/**
 * Created by F.Arian on 29.11.17.
 */
public class HandlingTongs extends MATERIAL {
    private  String id;
    private static int instanceCounter = 0;
    private double length;
    private int counter = 0;

    public HandlingTongs() {
        super("HANDLING_TONGS", TYPE.SAFETY);
        this.setLength(-1);
        this.id = DATA.generateUniqueId();
        instanceCounter++;
        counter = instanceCounter;

    }

    public int getCounter() {
        return counter;
    }

    public String getId() {
        return id;
    }


    public double getLength() {
        return length;
    }

    public void setLength(double longer) {
        this.length = longer;
    }

    @Override
    public String toString() {
        return  "\n"+"HANDLING_TONGS{" +
                "  NAME= " + super.getName() +
                ", ID= " + getId() +
                ", SERIAL_NUMBER= " + super.getSerialNumber() +
                ", MODEL= " + super.getModel() +
                ", TYPE= " + super.getType() +
                ", LENGTH= " + length +
                ", COUNTER = " + getCounter() +
                "}"+"\n";
    }
}
