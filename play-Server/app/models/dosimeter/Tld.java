package models.dosimeter;


import models.DATA;

/**
 * Created by F.Arian on 06.11.17.
 */
public class TLD extends DOSIMETER {

    private static String id;
    private static int instanceCounter = 0;
    private int counter = 0;

    public TLD() {
        this.id = DATA.generateUniqueId();
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
        return "\n" + "TLD{" +
                "  NAME= " + super.getName() +
                ", SERIAL_NUMBER= " + super.getSerialNumber()+
                ", ID= " + this.getId() +
                ", MADE_IN= " + super.getMadeIn() +
                ", CALIBRATION= " + super.isCalibration() +
                ", CALIBRATION_DATE= " + super.getCalibrationDate() +
                ", CALIBRATION_EXPIRE= " + super.getCalibrationExpire() +
                ", CALIBRATION_INSTITUTE= " + super.getCalibrationInistitut() +
                ", LOCATION= " + super.getLocation() +
                ", TYPE= " + super.getType() +
                ", STATUS= " + super.isStatus() +
                ", CALIBRATION_MESSAGE= " + super.getCalibrationMessage() +
                ", COUNTER = " + getCounter() +
                "}"+"\n";
    }
}
