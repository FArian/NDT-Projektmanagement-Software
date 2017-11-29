package models;

import java.util.Date;
/**
 * Created by F.Arian on 06.11.17.
 */
public class RadioActiveIsotope {


    private static String name;
    private static double halfLife;
    private Date date;
    private double activity;
    private double activityDimensions;
    private double price;


    private ISOTOPETYPE isotopetype;

    private enum ISOTOPETYPE {IRIDIUM_192, SELENIUM_75}

    public RadioActiveIsotope(ISOTOPETYPE type, double activity) {
        this.setDate(null);
        this.setIsotopetype(type);
        this.setActivity(activity);
        this.setActivityDimensions(this.isotopetype, this.activity);
    }

    public void setActivityDimensions(double activityDimensions) {
        this.activityDimensions = activityDimensions;
    }

    public ISOTOPETYPE getIsotopetype() {
        return isotopetype;
    }

    public void setIsotopetype(ISOTOPETYPE isotopetype) {
        this.isotopetype = isotopetype;
    }


    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        RadioActiveIsotope.name = name;
    }

    public static double getHalfLife() {
        return halfLife;
    }

    public static void setHalfLife(double halfLife) {
        RadioActiveIsotope.halfLife = halfLife;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getActivity() {
        return activity;
    }

    public void setActivity(double activity) {
        this.activity = activity;
    }

    public double getActivityDimensions() {
        return activityDimensions;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private void setActivityDimensions(ISOTOPETYPE isotopetype, double activity) {
        switch (isotopetype) {
            case IRIDIUM_192:
                if (activity == 100) {
                    this.setActivityDimensions(3 * 2.5);
                    this.setPrice(2112.00);
                }
                if (activity == 80) {
                    this.setActivityDimensions(3 * 2.0);
                    this.setPrice(1829.00);
                }
                if (activity == 70) {
                    this.setActivityDimensions(3 * 2.0);
                    this.setPrice(1686.00);
                }
                if (activity == 60) {
                    this.setActivityDimensions(2 * 2.5);
                    this.setPrice(1585.00);
                }
                if (activity == 50) {
                    this.setActivityDimensions(2 * 2.0);
                    this.setPrice(1432.00);
                }
                if (activity == 40) {
                    this.setActivityDimensions(2 * 1.5);
                    this.setPrice(1303.00);
                }
                if (activity == 30) {
                    this.setActivityDimensions(2 * 1.0);
                    this.setPrice(1186.00);
                }
                if (activity == 20) {
                    this.setActivityDimensions(2 * 1.0);
                    this.setPrice(1113.00);
                }

                break;
            case SELENIUM_75:
                if (activity == 20) {
                    this.setActivityDimensions(2.00);
                    this.setPrice(3000.00);
                }
                if (activity == 30) {
                    this.setActivityDimensions(2.50);
                    this.setPrice(3220.00);
                }
                if (activity == 40) {
                    this.setActivityDimensions(2.50);
                    this.setPrice(3430.00);
                }
                if (activity == 50) {
                    this.setActivityDimensions(2.85);
                    this.setPrice(3661.00);
                }
                if (activity == 60) {
                    this.setActivityDimensions(2.85);
                    this.setPrice(3871.00);
                }
                if (activity == 70) {
                    this.setActivityDimensions(3 * 3);
                    this.setPrice(4092.00);
                }
                if (activity == 80) {
                    this.setActivityDimensions(3 * 3);
                    this.setPrice(4312.00);
                }

                break;

        }

    }

}
