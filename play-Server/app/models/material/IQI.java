package models.material;

import models.DATA;
import models.enums.TYPE;

/**
 * Created by F.Arian on 06.12.17.
 * class Image Quality Indicators
 */
public class IQI extends MATERIAL {
    private static int instanceCounter = 0;
    private static String id = DATA.generateUniqueId();
    private int counter = 0;

    public IQI() {
        super("IQI", TYPE.QUALITY);
        instanceCounter++;
        counter = instanceCounter;
    }

    public static String getId() {
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
