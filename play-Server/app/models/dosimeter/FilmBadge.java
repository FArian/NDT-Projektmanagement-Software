package models.dosimeter;


import com.fasterxml.jackson.annotation.JsonRootName;
import models.DATA;


/**
 * Created by F.Arian on 06.11.17.
 */
@JsonRootName("FilmBadge")
public class FilmBadge extends DOSIMETER {
    private String id;
    private static int instanceCounter = 0;
    private int counter = 0;

    public FilmBadge() {
        this.id = DATA.generateUniqueId();
        instanceCounter++;
        counter = instanceCounter;
    }

    public String getId() {
        return id;
    }

    public int getCounter() {
        return counter;
    }


    @Override
    public String toString() {
        return "\n" + "FILM_BADGE{ " +
                "  NAME= " + super.getName() +
                ", SERIAL_NUMBER= " + super.getSerialNumber() +
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
                "}" + "\n";

    }
}
