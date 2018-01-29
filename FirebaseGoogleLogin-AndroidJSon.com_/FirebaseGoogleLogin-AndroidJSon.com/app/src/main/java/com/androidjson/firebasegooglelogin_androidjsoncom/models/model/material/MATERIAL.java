package com.androidjson.firebasegooglelogin_androidjsoncom.models.model.material;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.*;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.enums.*;

/**
 * Created by F.Arian on 06.11.17.
 */
public class MATERIAL {
    private String name;
    private String model;
    private TYPE type;
    private String SerialNumber;



    public MATERIAL(String name, TYPE type) {
        setName(name);
        setModel(DATA.creatId("-" + name));
        setSerialNumber(DATA.generateUniqueId());
        setType(type);
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
