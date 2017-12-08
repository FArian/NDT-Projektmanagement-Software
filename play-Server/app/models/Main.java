package models;

import models.RT.Isotope;
import models.RT.RT_Camera;
import models.dosimeter.FilmBadge;
import models.dosimeter.TLD;
import models.enums.ISOTOPETYPE;
import models.enums.MODEL;
import models.enums.NAME;
import models.enums.TYPE;
import models.material.IQI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by F.Arian on 06.11.17.
 */

public class Main {
    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();
    private static final LocalDate LOCAL_DATE = LocalDate.now();
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public static void main(String[] args) {
        Isotope isotope = new Isotope(ISOTOPETYPE.IRIDIUM_192, 100);
        Isotope isotope2 = new Isotope(ISOTOPETYPE.IRIDIUM_192, 100);
        RT_Camera camera = new RT_Camera(NAME.SENTINEL, MODEL.SIGMA_880, isotope);
        RT_Camera camera2 = new RT_Camera(NAME.SENTINEL, MODEL.SIGMA_880, isotope2);
        Personal personal = new Personal("Far-had", "arian", "13.02.1983", new TLD(), new FilmBadge());
        Personal personal2 = new Personal("Farhad", "arian", "13.02.1983", new TLD(), new FilmBadge());
        Team team = new Team(personal, TYPE.RT, camera);
        Team team2 = new Team(personal2, TYPE.RT, camera2);

        System.out.println(team);



    }


}
