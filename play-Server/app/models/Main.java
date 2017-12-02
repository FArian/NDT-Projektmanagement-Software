package models;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by F.Arian on 06.11.17.
 */

public class Main {
    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public static void main(String[] args) {
        TLD tld = new TLD("00001", "FarhadTLD");
        FilmBadge filmBadge = new FilmBadge("0001myFilmBadge", "FarhadFilmBadge");
        //RadioActiveIsotope isotope = new RadioActiveIsotope(ISOTOPETYPE.IRIDIUM_192, 120);
        //RtCamera rtCamera = new RtCamera(NAME.SENTINEL, MODEL.SIGMA_880, isotope);
        Personal personal = new Personal("Farhad", "ARIAN", "14.02.1983", 1, tld, filmBadge);

        System.out.println(personal);
        //System.out.println(rtCamera);
        //System.out.println(filmBadge);
        //System.out.println(DateFormatLocal.getPeriodTime("14.02.1983"));


    }


}
