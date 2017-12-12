package models.enums;

/**
 * Created by F.Arian on 01.12.17.
 */
public enum ISOTOPETYPE {
    IRIDIUM_192, SELENIUM_75, COBALT_60, YTTERBIUM_169, CAESIUM_137, X_Ray100KV, X_Ray200KV, X_Ray220KV, X_Ray120KV, LINAC_8MeV;

    @Override
    public String toString() {
        return "ISOTOPETYPE{} " + super.toString();
    }
}
