package models;


/**
 * Created by F.Arian on 04.12.17.
 */
public class ChemicalsDeveloper extends PROCESSING {
    private static String id;

    public ChemicalsDeveloper(NAME name, MODEL model, SIZE size) {
        super(name, model, size);
        this.id = DATA.generateUniqueId();
    }

    /**
     * static for calling from parent Class in toString()
     * @return
     */
    public static String getID() {
        return id;
    }

    @Override
    public String toString() {
        return "CHEMICALS_DEVELOPER{" +"\n"+
                ", NAME= " + super.getName() +
                ", ID = " + getID() +
                ", MODEL= " + super.getModel() +
                ", SIZE= " + super.getSize()+
                ", SERIAL_NUMBER= " + super.getSerialNumber()+
                ", DESCRIPTION= " + super.getDescription() +
                ", EXPIRE_DATE= " + super.getExpireDate() +
                ", DATE_IS_EXPIRED= " + super.isExpiredDate() +
                ", LOCATION= " + super.getLocation() + "\n" +
                "} ";
    }
}
