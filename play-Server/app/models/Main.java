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
        RadioActiveIsotope isotope = new RadioActiveIsotope(ISOTOPETYPE.IRIDIUM_192, 100);
        RtCamera rtCamera = new RtCamera(NAME.SENTINEL, MODEL.SIGMA_880,null, isotope);

        //Personal personal = new Personal("Farhad", "khodayary", "14.02.1983", 1, tld, filmBadge);
        //RadiographicFilm filmAGFA=new RadiographicFilm(NAME.AGFA,Type.STRUCTURIX_D4,MODEL.ROLL,SIZE.ROLLPAC_100mmX90m);
        //RadiographicFilm filmKodak=new RadiographicFilm(NAME.KODAK,Type.AA400,MODEL.ROLL,SIZE.ROLLPAC_100mmX100m);
        //RadiographicFilm filmFujiFilm=new RadiographicFilm(NAME.FUJIFILM,Type.IX20,MODEL.ROLL,SIZE.ROLLPAC_100mmX100m);

        //System.out.println(personal);
        //System.out.println("START :" +"\n"+rtCamera);
        //System.out.println(filmAGFA);


    }


}
