package models.enums;

/**
 * Created by F.Arian on 06.11.17.
 */

public enum LOCATION {
    CENTRAL, PROJECT, ATOMENERGIE_INSTITUT, ONTHEWAY;

    public String message(LOCATION location) {
        String result = null;
        switch (location) {
            case CENTRAL:
                result = "The device is now in CENTRAL".toUpperCase() + "\n";
                break;
            case PROJECT:
                result = "The device is now in PROJECT".toUpperCase() + "\n";
                break;
            case ONTHEWAY:
                result = "The device is now ON THE WAY".toUpperCase() + "\n";
                break;
            case ATOMENERGIE_INSTITUT:
                result = "The device is now in Institute for Nuclear Power Engineering".toUpperCase() + "\n";
                break;
            default:
                result = "Location has not been communicated".toUpperCase() + "\n";
                break;
        }

        return result;
    }
}
