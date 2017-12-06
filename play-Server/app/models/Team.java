package models;

import actors.serverInterface.ServerLog;
import models.dosimeter.RadiometerDosimeter;
import models.enums.*;
import models.material.HandlingTongs;
import models.material.RadiationSigns;
import models.material.Viewer;
import models.processing.ChemicalsDeveloper;
import models.processing.ChemicalsFixer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by F.Arian on 06.11.17.
 */
public class Team {
    private TYPE type;
    private static String id;
    private String name;
    private List<Personal> personals=new ArrayList<>();
    private List<ChemicalsDeveloper> developers= new ArrayList<>();
    private List<ChemicalsFixer> fixers=new ArrayList<>();
    private List<RadiographicFilm> films=new ArrayList<>();
    private RadiometerDosimeter dosimeter;
    private Viewer viewer;
    private HandlingTongs handlingTongs;
    private RtCamera rtCamera;
    private List<RadiationSigns> signs=new ArrayList<>();
    private LOCATION location;
    private boolean status;
    private Project project;
    private ServerLog log=new ServerLog();
    private String teamReport;
    private static int counter=DATA.counter(0);
    public int getCounter() {return counter;}

    public Team(Personal personals, TYPE teamType, RtCamera rtCamera) {
        this.personals.add(personals);
        this.setType(teamType);
        this.setTeamReport("TEAM START REPORT");
        this.setLocation(LOCATION.CENTRAL);

        if(getType().equals(TYPE.RT)){
            this.films.add(new RadiographicFilm(NAME.OTHER, TYPE.OTHER, MODEL.OTHER, SIZE.OTHER));
            this.setRtCamera(rtCamera);
            this.setStatus(rtCamera.ready_RT_CAMERA());
            if(!isStatus()){
                this.getLog().info(" CHECK IF IS RT_CAMERA READY ? ".toString());
            }

            this.signs.add(new RadiationSigns());
            this.setHandlingTongs(new HandlingTongs());
            this.setViewer(new Viewer());
            this.setDosimeter(new RadiometerDosimeter());
            this.developers.add(new ChemicalsDeveloper(NAME.OTHER,MODEL.OTHER,SIZE.OTHER));
            this.fixers.add(new ChemicalsFixer(NAME.OTHER,MODEL.OTHER,SIZE.OTHER));

        }
        this.id=DATA.creatId("-"+getType().name().toString());
        this.setName(getId()+"_TEAM");


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

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        Team.id = id;
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

    public List<ChemicalsDeveloper> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<ChemicalsDeveloper> developers) {
        this.developers = developers;
    }

    public List<ChemicalsFixer> getFixers() {
        return fixers;
    }

    public void setFixers(List<ChemicalsFixer> fixers) {
        this.fixers = fixers;
    }

    public List<RadiographicFilm> getFilms() {
        return films;
    }

    public void setFilms(List<RadiographicFilm> films) {
        this.films = films;
    }

    public RadiometerDosimeter getDosimeter() {
        return dosimeter;
    }

    public void setDosimeter(RadiometerDosimeter dosimeter) {
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

    public RtCamera getRtCamera() {
        return rtCamera;
    }

    public void setRtCamera(RtCamera rtCamera) {
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }


    @Override
    public String toString() {
        return "TEAM{" + "\n"+
                ", NAME= " + name +
                ", TYPE= " + type +
                ", ID= " + id +
                ", DOSIMETER= " + dosimeter +
                ", VIEWER= " + viewer +
                ", HANDLING_TONGS= " + handlingTongs +
                ", SIGNS= " + signs +
                ", LOCATION= " + location +
                ", STATUS= " + status +
                ", PROJECT= " + project +
                ", TEAM_REPORT= " + teamReport +
                ", PERSONALS= " + personals +
                ", RT_CAMERA= " + rtCamera +
                ", FILMS= " + films +
                ", DEVELOPERS= " + developers +
                ", FIXERS= " + fixers +"\n"+
                "}";
    }
}
