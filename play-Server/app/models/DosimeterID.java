package models;


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
	private String calibrationInistitut;
	private Location location;
	private Type type;
	private boolean status;
	
	
	
	public DosimeterID(String serialNumber, String name, String madeIn, boolean calibration,
			String calibrationDate,
			String calibrationExpire, String calibrationInistitut, Location location, Type type) {
		this.serialNumber = serialNumber;
		this.name = name;
		this.madeIn = madeIn;
		this.calibration = calibration;
		this.setCalibrationDate(calibrationDate);
		this.setCalibrationExpire(calibrationExpire);
		this.calibrationInistitut = calibrationInistitut;
		this.location = location;
		this.type = type;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMadeIn() {
		return madeIn;
	}
	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}
	public boolean isCalibration() {
		return calibration;
	}
	public void setCalibration(boolean calibration) {
		this.calibration = calibration;
	}
	public String getCalibrationDate() {
		return calibrationDate;
	}
	public void setCalibrationDate(String calibrationDate) {
		this.calibrationDate = DateFormatLocal.dateUpDate(calibrationDate);
	}
	public String getCalibrationExpire() {
		return calibrationExpire;
	}
	public void setCalibrationExpire(String calibrationExpire) {
		this.calibrationExpire = DateFormatLocal.dateUpDate(calibrationExpire);
	}
	public String getCalibrationInistitut() {
		return calibrationInistitut;
	}
	public void setCalibrationInistitut(String calibrationInistitut) {
		this.calibrationInistitut = calibrationInistitut;
	}
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
}