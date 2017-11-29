package models;

/**
 * Created by F.Arian on 06.11.17.
 */

public enum Location {
    CENTRAL, PROJECT, ATOMENERGIE_INSTITUT, ONTHEWAY;

    public String message(Location location) {
        String result = null;
        switch (location) {
            case CENTRAL:
                result = "The device is now in CENTRAL";
                break;
            case PROJECT:
                result = "The device is now in PROJECT";
                break;
            case ONTHEWAY:
                result = "The device is now ON THE WAY";
                break;
            case ATOMENERGIE_INSTITUT:
                result = "The device is now in Institute for Nuclear Power Engineering";
                break;
            default:
                result = "Location has not been communicated";
                break;
        }

        return result;
    }
}
