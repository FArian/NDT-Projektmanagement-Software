package com.androidjson.firebasegooglelogin_androidjsoncom.client;

import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.dosimeter.*;

/**
 * Created by F.Arian on 11.01.18.
 */

public class Material {

    private String value;
    private String info;
    private FilmBadge filmBadge;
    private TLD tld;
    private GeigerAlarm geigerAlarm;
    private PocketDosimeter dosimeter;
    private Radiometer radiometer;


    private int materialCoverDrawableId;

    public Material(){
        super();
    }
    public Material(String info,String value){
        super();
        this.value=value;
        this.info=info;
    }
    public Material(FilmBadge filmBadge,int imageCoverDrawableId){
        super();
        this.filmBadge=filmBadge;
    }
    public Material(TLD tld,int imageCoverDrawableId){
        super();
        this.tld=tld;
    }
    public Material(GeigerAlarm geigerAlarm,int imageCoverDrawableId){
        super();
        this.geigerAlarm=geigerAlarm;
    }

    public FilmBadge getFilmBadge() {
        return filmBadge;
    }

    public void setFilmBadge(FilmBadge filmBadge) {
        this.filmBadge = filmBadge;
    }

    public TLD getTld() {
        return tld;
    }

    public void setTld(TLD tld) {
        this.tld = tld;
    }

    public GeigerAlarm getGeigerAlarm() {
        return geigerAlarm;
    }

    public void setGeigerAlarm(GeigerAlarm geigerAlarm) {
        this.geigerAlarm = geigerAlarm;
    }

    public PocketDosimeter getDosimeter() {
        return dosimeter;
    }

    public void setDosimeter(PocketDosimeter dosimeter) {
        this.dosimeter = dosimeter;
    }

    public Radiometer getRadiometer() {
        return radiometer;
    }

    public void setRadiometer(Radiometer radiometer) {
        this.radiometer = radiometer;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getMaterialCoverDrawableId() {
        return materialCoverDrawableId;
    }

    public void setMaterialCoverDrawableId(int materialCoverDrawableId) {
        this.materialCoverDrawableId = materialCoverDrawableId;
    }

}
