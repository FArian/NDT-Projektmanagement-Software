package models.material;

import models.DATA;
import models.enums.TYPE;


/**
 * Created by F.Arian on 29.11.17.
 */
public class RadiationSigns extends MATERIAL {


    private String name;
    private TYPE type;
    private static String id;

    public RadiationSigns() {
        super("DEFAULT-NAME",TYPE.SAFETY);
        this.id= DATA.generateUniqueId();
    }

    public static String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
            this.name = name.toLowerCase();


    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "RADIOATIO_SIGNS{" +"\n"+
                ", NAME= " + name +"\n"+
                ", ID= " + getId() +"\n"+
                ", TYPE= " + type +"\n"+
                "}";
    }
}
