package com.androidjson.firebasegooglelogin_androidjsoncom.models.model.dosimeter;

import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.DATA;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.enums.*;


/**
 * Created by F.Arian on 06.11.17.
 */
public abstract class DOSIMETER {
    private String serialNumber;
    private String name;
    private String madeIn;
    private boolean calibration;
    private String calibrationDate;
    private String calibrationExpire;
    private String calibrationInstitut;
    private LOCATION location;
    private TYPE type;
    private boolean status;
    private String calibrationMessage;
    private String value;

    public DOSIMETER() {
        this.setSerialNumber(DATA.generateUniqueId());
        this.setValue("");
        this.setStatus(true);
        this.setCalibration(true);
        this.setMadeIn("NOT SET");
        this.setCalibrationDate(DATA.getLocalDate());
        this.setCalibrationExpire(DATA.changeDate(0, 0, 1));
        this.setCalibrationInistitut("NOT SET");
        this.setLocation(LOCATION.CENTRAL);
        this.setType(TYPE.SAFETY);
        this.setCalibrationMessage("FIRST CALIBRATION MESSAGE");
        if (isStatus()) {
            this.calibrationMessage();
        }
        this.setName("MY_DOSIMETER");

    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }




    /**
     * status is false ,if calibration expert
     *
     * @return
     */
    public boolean isStatus() {
        return !isCalibration();
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCalibrationMessage() {
        return calibrationMessage + "\n";
    }

    public void setCalibrationMessage(String calibrationMessage) {
        this.calibrationMessage = calibrationMessage;
    }

    public String getSerialNumber() {
        return serialNumber + "\n";
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name + "\n";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMadeIn() {
        return madeIn + "\n";
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    /**
     * check it , if calibration expert ?
     *
     * @return
     */
    public boolean isCalibration() {
        boolean result = getCalibrationExpire() == DATA.getDate();
        if (result) {
            this.setCalibrationMessage("CALIBRATION EXPERT");

        }
        return calibration;
    }

    public void setCalibration(boolean calibration) {
        this.calibration = calibration;
    }

    public String getCalibrationDate() {
        return calibrationDate + "\n";
    }

    public void setCalibrationDate(String calibrationDate) {
        this.calibrationDate = calibrationDate;
    }

    public String getCalibrationExpire() {
        return calibrationExpire + "\n";
    }

    public void setCalibrationExpire(String calibrationExpire) {
        this.calibrationExpire = calibrationExpire;
    }

    public String getCalibrationInistitut() {
        return calibrationInstitut + "\n";
    }

    public void setCalibrationInistitut(String calibrationInistitut) {
        this.calibrationInstitut = calibrationInistitut;
    }

    /**
     * where is the device now?
     *
     * @return
     */
    public LOCATION getLocation() {
        return location;
    }

    public void setLocation(LOCATION location) {
        this.location = location;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    /**
     * send message to HPS for Calibration
     * in two weeks, the calibration will expire
     */
    public void calibrationMessage() {

        if (getCalibrationExpire() == DATA.addNewDayToDate(-14)) {
            this.setCalibrationMessage("IN TWO WEEKS, THE CALIBRATION WILL BE EXPIRED.");

        }
    }

    public String getCalibrationInstitut() {
        return calibrationInstitut;
    }

    public void setCalibrationInstitut(String calibrationInstitut) {
        this.calibrationInstitut = calibrationInstitut;
    }

    @Override
    public String toString() {
        return "DOSIMETER_ID{" + "\n" +
                "SERIAL_NUMBER=" + serialNumber +
                ", NAME=" + name +
                ", MADE_IN=" + madeIn +
                ", CALIBRATION=" + calibration +
                ", CALIBRATION_DATE=" + calibrationDate +
                ", CALIBRATION_EXPIRE=" + calibrationExpire +
                ", CALIBRATION_INSTITUTE=" + calibrationInstitut +
                ", LOCATION=" + location +
                ", TYPE=" + type +
                ", STATUS=" + status +
                ", CALIBRATION_MESSAGE=" + calibrationMessage +
                "}";
    }
}