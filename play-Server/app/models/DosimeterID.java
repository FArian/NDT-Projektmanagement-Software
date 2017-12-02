package models;


import actors.serverInterface.ServerLog;
/**
 * Created by F.Arian on 06.11.17.
 */
public class DosimeterID {
    private String serialNumber;
    private String name;
    private String madeIn;
    private boolean calibration;
    private String calibrationDate;
    private String calibrationExpire;
    private String calibrationInstitut;
    private Location location;
    private Type type;
    private boolean status;
    private String calibrationMessage;
    private ServerLog log;

    public DosimeterID(String serialNumber, String name) {
        this.setName(name);
        this.setSerialNumber(serialNumber);
        this.setStatus(true);
        this.setCalibration(true);
        this.setMadeIn(null);
        this.setCalibrationDate(DateFormatLocal.getLocalDate());
        this.setCalibrationExpire(DateFormatLocal.changeDate(0, 6, 0));
        this.setCalibrationInistitut(null);
        this.setLocation(Location.CENTRAL);
        this.setType(Type.SAFETY);
        this.setCalibrationMessage("FIRST CALIBRATION MESSAGE");
        this.log = new ServerLog();
        this.getLog().info(" A DOSIMETER: "+getName()+"WITH SERIAL_NUMBER: "+getSerialNumber()+"IS CREATED.");
        if(isStatus()){
            this.calibrationMessage();
        }

    }

    public ServerLog getLog() {
        return log;
    }

    /**
     * status is false ,if calibration expert
     *
     * @return
     */
    public boolean isStatus() {
        return !isCalibration();
    }

    public String getCalibrationMessage() {
        return calibrationMessage + "\n";
    }

    public void setCalibrationMessage(String calibrationMessage) {
        this.calibrationMessage = calibrationMessage;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
        boolean result = getCalibrationExpire() == DateFormatLocal.getDate();
        if (result) {
            this.setCalibrationMessage("CALIBRATION EXPERT");
            getLog().info(this.getCalibrationMessage());
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
        this.calibrationDate = DateFormatLocal.dateUpDate(calibrationDate);
    }

    public String getCalibrationExpire() {
        return calibrationExpire + "\n";
    }

    public void setCalibrationExpire(String calibrationExpire) {
        this.calibrationExpire = DateFormatLocal.dateUpDate(calibrationExpire);
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
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    /**
     * send message to HPS for Calibration
     * in two weeks, the calibration will expire
     */
    public void calibrationMessage() {

        if (getCalibrationExpire() == DateFormatLocal.addNewDayToDate(-14)) {
            this.setCalibrationMessage("IN TWO WEEKS, THE CALIBRATION WILL BE EXPIRED.");
            getLog().info(this.getCalibrationMessage() + "\n");
        }
    }

    @Override
    public String toString() {
        return "DOSIMETER_ID{" + "\n" +
                "SERIAL_NUMBER=" + serialNumber + "\n" +
                ", NAME=" + name + "\n" +
                ", MADE_IN=" + madeIn + "\n" +
                ", CALIBRATION=" + calibration + "\n" +
                ", CALIBRATION_DATE=" + calibrationDate + "\n" +
                ", CALIBRATION_EXPIRE=" + calibrationExpire + "\n" +
                ", CALIBRATION_INSTITUTE=" + calibrationInstitut + "\n" +
                ", LOCATION=" + location + "\n" +
                ", TYPE=" + type + "\n" +
                ", STATUS=" + status +
                ", CALIBRATION_MESSAGE=" + calibrationMessage + "\n" +
                '}';
    }
}