package models.material;

import models.DATA;
import models.enums.TYPE;


/**
 * Created by F.Arian on 29.11.17.
 */
public class RadiationSigns extends MATERIAL {


    private  String id;
    private static int instanceCounter = 0;
    private String name;
    private TYPE type;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toLowerCase();


    }

    public int getCounter() {
        return counter;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "\n"+"RADIATION_SIGNS{" +
                "  NAME= " + name +
                ", ID= " + getId() +
                ", TYPE= " + type +
                ", COUNTER = " + getCounter() +
                "}"+"\n";
    }
}
