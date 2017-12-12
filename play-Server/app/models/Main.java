package models;

import actors.Lobby;
import models.dosimeter.FilmBadge;
import models.dosimeter.TLD;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Created by F.Arian on 06.11.17.
 */

public class Main {
    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();
    private static final LocalDate LOCAL_DATE = LocalDate.now();
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public static void main(String[] args) {
        //Isotope isotope = new Isotope(ISOTOPETYPE.IRIDIUM_192, 100);
        //Isotope isotope2 = new Isotope(ISOTOPETYPE.IRIDIUM_192, 100);
        //RT_Camera camera = new RT_Camera(NAME.SENTINEL, MODEL.SIGMA_880, isotope);
        Personal personal1 = new Personal("Susann", "Sumadirana", "31.08.1975");

        // RT_Camera camera2 = new RT_Camera(NAME.SENTINEL, MODEL.SIGMA_880, isotope2);
        Personal personal2 = new Personal("Farhad", "arian", "13.02.1983");
        //Team team = new Team(personal, TYPE.RT, camera);
        //Team team2 = new Team(personal2, TYPE.RT, camera2);

        Lobby lobby=new Lobby();

        lobby.addPersonalInLobby(personal2);
        lobby.addPersonalInLobby(personal1);
        System.out.println(lobby.getPersonalInLobby());



    }


}
