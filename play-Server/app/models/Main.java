package models;


/**
 * Created by F.Arian on 06.11.17.
 */

public class Main {

    public static void main(String[] args) {
        FilmBadge filmBadge=new FilmBadge("Serial03","FilmBadge");

        filmBadge.getCalibrationMessage();
        filmBadge.isCalibration();
        filmBadge.calibrationMessage();
        System.out.printf(filmBadge.getCalibrationMessage());
        System.out.printf(filmBadge.getLocation().message(Location.CENTRAL));
    }

}
