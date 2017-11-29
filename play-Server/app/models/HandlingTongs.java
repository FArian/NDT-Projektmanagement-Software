package models;

/**
 * Created by F.Arian on 29.11.17.
 */
public class HandlingTongs extends Material {


    private double longer;

    public HandlingTongs(double longer) {
        super("HandlingTongs", null, Type.SAFETY, -1);
        this.setLonger(longer);
    }

    public double getLonger() {
        return longer;
    }

    public void setLonger(double longer) {
        while (longer>4){
            System.out.printf("To long");
            setLonger(longer);
        }
        this.longer = longer;
    }
}
