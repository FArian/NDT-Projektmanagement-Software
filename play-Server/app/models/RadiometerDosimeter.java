package models;
/**
 * Created by F.Arian on 06.11.17.
 */
public class RadiometerDosimeter extends DOSIMETER {
	private static String id;

	public RadiometerDosimeter() {
		this.id=DATA.generateUniqueId();

	}

	public static String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "RADIOMETER_DOSIMETER{" +"\n"+
				", SERIAL_NUMBER= " + super.getSerialNumber() +
				", NAME= " + super.getName()+
				", ID= " + this.getId()+
				", MADE_IN= " + super.getMadeIn() +
				", CALIBRATION= " + super.isCalibration() +
				", CALIBRATION_DATE= " + super.getCalibrationDate() +
				", CALIBRATION_EXPIRE= " + super.getCalibrationExpire() +
				", CALIBRATION_INSTITUTE= " + super.getCalibrationInistitut() +
				", LOCATION= " + super.getLocation() +
				", TYPE= " + super.getType() +
				", STATUS= " + super.isStatus() +
				", CALIBRATION_MESSAGE= " + super.getCalibrationMessage() +
				", PROJECT_NAME= " + super.getProject().getName() +
				", PROJECT_NR= " + super.getProject().getProjectNumber() +
				", PROJECT_LOCATION= " + super.getProject().getLocation()+"\n"+
				"} ";
	}
}
