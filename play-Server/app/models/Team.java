package models;

import models.RT.Film;
import models.RT.RT_Camera;
import models.dosimeter.Radiometer;
import models.enums.*;
import models.material.*;
import models.processing.Developer;
import models.processing.Fixer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by F.Arian on 06.11.17.
 */
public class Team {
    private String id;
    private static int instanceCounter = 0;
    int counter = 0;
    private TYPE type;
    private String name;
    private List<Personal> personals = new ArrayList<>();
    private List<Developer> developers = new ArrayList<>();
    private List<Fixer> fixers = new ArrayList<>();
    private List<Film> films = new ArrayList<>();
    private Radiometer dosimeter;
    private Viewer viewer;
    private HandlingTongs handlingTongs;
    private RT_Camera rtCamera;
    private List<RadiationSigns> signs = new ArrayList<>();
    private LOCATION location;
    private boolean status;
    private ServerLog log = new ServerLog();
    private String teamReport;
    private LeadAron leadAron;
    private RadiationSigns radiationSigns;
    private EmergencyStorageContainer emergencyStorageContainer;
    private IQI iqi;


    public Team(Personal personals, TYPE teamType, RT_Camera rtCamera) {
        this.personals.add(personals);
        this.setType(teamType);
        this.setTeamReport("TEAM START REPORT");
        this.setLocation(LOCATION.CENTRAL);

        if (getType().equals(TYPE.RT)) {
            this.films.add(new Film(NAME.OTHER, TYPE.OTHER, MODEL.OTHER, SIZE.OTHER));
            this.setRtCamera(rtCamera);
            this.setStatus(rtCamera.ready_RT_CAMERA());
            if (!isStatus()) {
                this.getLog().info(" CHECK IF IS RT_CAMERA READY ? ".toString());
            }

            this.signs.add(new RadiationSigns());
            this.setHandlingTongs(new HandlingTongs());
            this.setViewer(new Viewer());
            this.setDosimeter(new Radiometer());
            this.developers.add(new Developer(NAME.OTHER, MODEL.OTHER, SIZE.OTHER));
            this.fixers.add(new Fixer(NAME.OTHER, MODEL.OTHER, SIZE.OTHER));

        }
        this.id = DATA.creatId("-" + getType().name().toString());
        this.setName(getId() + "_TEAM");
        instanceCounter++;
        counter = instanceCounter;
        this.getLog().info("NEW OBJECT CREATED,NAME :" + getName() + "-" + getClass());


    }

    public IQI getIqi() {
        return iqi;
    }

    public void setIqi(IQI iqi) {
        this.iqi = iqi;
    }


    public EmergencyStorageContainer getEmergencyStorageContainer() {
        return emergencyStorageContainer;
    }

    public void setEmergencyStorageContainer(EmergencyStorageContainer emergencyStorageContainer) {
        this.emergencyStorageContainer = emergencyStorageContainer;
    }


    public RadiationSigns getRadiationSigns() {
        return radiationSigns;
    }

    public void setRadiationSigns(RadiationSigns radiationSigns) {
        this.radiationSigns = radiationSigns;
    }


    public LeadAron getLeadAron() {
        return leadAron;
    }

    public void setLeadAron(LeadAron leadAron) {
        this.leadAron = leadAron;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCounter() {
        return counter;
    }

    public String getTeamReport() {
        return teamReport;
    }

    public void setTeamReport(String teamReport) {
        this.teamReport = teamReport;
    }

    public ServerLog getLog() {
        return log;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Personal> getPersonals() {
        return personals;
    }

    public void setPersonals(List<Personal> personals) {
        this.personals = personals;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Developer developer) {
        this.developers.add(developer);
    }

    public void setFilm(Film film) {
        this.films.add(film);
    }

    public void setFixer(Fixer fixer) {
        this.fixers.add(fixer);
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    public List<Fixer> getFixers() {
        return fixers;
    }

    public void setFixers(List<Fixer> fixers) {
        this.fixers = fixers;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public Radiometer getDosimeter() {
        return dosimeter;
    }

    public void setDosimeter(Radiometer dosimeter) {
        this.dosimeter = dosimeter;
    }

    public Viewer getViewer() {
        return viewer;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

    public HandlingTongs getHandlingTongs() {
        return handlingTongs;
    }

    public void setHandlingTongs(HandlingTongs handlingTongs) {
        this.handlingTongs = handlingTongs;
    }

    public RT_Camera getRtCamera() {
        return rtCamera;
    }

    public void setRtCamera(RT_Camera rtCamera) {
        this.rtCamera = rtCamera;
    }

    public List<RadiationSigns> getSigns() {
        return signs;
    }

    public void setSigns(List<RadiationSigns> signs) {
        this.signs = signs;
    }

    public LOCATION getLocation() {
        return location;
    }

    public void setLocation(LOCATION location) {
        this.location = location;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "TEAM{" +
                "  NAME= " + name +
                ", TYPE= " + type +
                ", ID= " + id +
                ", DOSIMETER= " + dosimeter +
                ", VIEWER= " + viewer +
                ", HANDLING_TONGS= " + handlingTongs +
                ", SIGNS= " + signs +
                ", LOCATION= " + location +
                ", PERSONALS= " + personals +
                ", RT_CAMERA= " + rtCamera +
                ", FILMS= " + films +
                ", DEVELOPERS= " + developers +
                ", FIXERS= " + fixers +
                ", STATUS= " + status +
                ", TEAM_REPORT= " + teamReport +
                ", COUNTER = " + getCounter() +
                "}";
    }
}
