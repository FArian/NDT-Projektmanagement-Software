package models;

import models.dosimeter.FilmBadge;
import models.dosimeter.Tld;
import models.enums.ISOTOPETYPE;
import models.enums.MODEL;
import models.enums.NAME;
import models.enums.TYPE;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by F.Arian on 06.11.17.
 */

public class Main {
    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();
    private static final LocalDate LOCAL_DATE=LocalDate.now();
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public static void main(String[] args) {
        RadioactiveIsotope isotope = new RadioactiveIsotope(ISOTOPETYPE.IRIDIUM_192, 100);
        RtCamera camera=new RtCamera(NAME.SENTINEL, MODEL.SIGMA_880,isotope);
        Personal personal=new Personal("Farhad","arian","13.02.1983",new Tld(),new FilmBadge());
        Team team=new Team(personal, TYPE.RT,camera);
        Project project=new Project();
        System.out.println(team);

    }


}
