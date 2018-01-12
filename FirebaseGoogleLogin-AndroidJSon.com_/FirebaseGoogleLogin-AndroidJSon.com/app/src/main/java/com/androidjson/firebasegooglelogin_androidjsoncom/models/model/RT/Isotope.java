package com.androidjson.firebasegooglelogin_androidjsoncom.models.model.RT;

import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.*;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.enums.*;

import org.threeten.bp.LocalDateTime;

/**
 * Created by F.Arian on 06.11.17.
 */
public class Isotope {
    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();
    private static String name;
    private static String date;
    private static double ACTIVITY;
    private static LocalDateTime DATEOFINSTALATION = LOCAL_DATE_TIME.minusMinutes(1);
    private static String id;
    private static int instanceCounter = 0;
    int counter = 0;
    private double halfLife;
    private double activity;
    private double activityDimensions;
    private double price;
    private String assembly_Model_Number;
    private String gamma_Energy_Range;
    private String approximate_Steel_Working_Thickness;
    private ISOTOPETYPE isotopetype;
    private String serialNumber;


    public Isotope(ISOTOPETYPE type, double activity) {
        this.ACTIVITY = activity;
        this.setDate(DATA.getLocalDate());
        this.setIsotopetype(type);
        this.setName(type.name().toString());
        this.setActivity(activity);
        this.setActivityDimensions(type, activity);
        this.setHalfLife(-1);
        this.setSource_Assembly_and_Authorized_Contents(type);
        this.id = DATA.generateUniqueId();
        this.setSerialNumber(DATA.generateUniqueId());
        instanceCounter++;
        counter = instanceCounter;

    }

    public static LocalDateTime getDATEOFINSTALATION() {
        return DATEOFINSTALATION;
    }

    public static double getACTIVITY() {
        return ACTIVITY;
    }

    public static String getId() {
        return id;
    }

    public int getCounter() {
        return counter;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public ISOTOPETYPE getIsotopetype() {
        return isotopetype;
    }

    public void setIsotopetype(ISOTOPETYPE isotopetype) {
        this.isotopetype = isotopetype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Isotope.name = name;
    }

    public double getHalfLife() {

        return this.halfLife;
    }

    public void setHalfLife(double halfLife) {
        this.halfLife = halfLife;
    }

    /**
     * get current Half Life of Radioactive Isotope
     */
    public void currentHalfLife() {
        if (DATEOFINSTALATION != LOCAL_DATE_TIME) {
            double[] time = DATA.getPeriodTime(DATEOFINSTALATION.minusMonths(1));
            double min = 0, hour = 0, days = 0, month = 0, years = 0;
            min = time[0];
            hour = time[1];
            days = time[2];
            month = time[3];
            years = time[4];
            days = days + (month * 30) + (years * 360);
            if (days > 0) {
                this.setActivity(this.getActivity() * Math.pow(2, -days / getHalfLife()));
            }

        }


    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = DATA.dateUpDate(date);
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

    public void setActivityDimensions(double activityDimensions) {
        this.activityDimensions = activityDimensions;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAssembly_Model_Number() {
        return assembly_Model_Number;
    }

    public void setAssembly_Model_Number(String assembly_Model_Number) {
        this.assembly_Model_Number = assembly_Model_Number;
    }

    public String getGamma_Energy_Range() {
        return gamma_Energy_Range;
    }

    public void setGamma_Energy_Range(String gamma_Energy_Range) {
        this.gamma_Energy_Range = gamma_Energy_Range;
    }

    public String getApproximate_Steel_Working_Thickness() {
        return approximate_Steel_Working_Thickness;
    }

    public void setApproximate_Steel_Working_Thickness(String approximate_Steel_Working_Thickness) {
        this.approximate_Steel_Working_Thickness = approximate_Steel_Working_Thickness;
    }


    private void setSource_Assembly_and_Authorized_Contents(ISOTOPETYPE isotopetype) {
        switch (isotopetype) {
            case SELENIUM_75:
                this.setAssembly_Model_Number("A424-25W");
                this.setGamma_Energy_Range("66-401keV");
                this.setHalfLife(120);
                this.setApproximate_Steel_Working_Thickness("3-29mm");
                break;
            case IRIDIUM_192:
                this.setAssembly_Model_Number("A424-9");
                this.setGamma_Energy_Range("206-612keV");
                this.setHalfLife(74);
                this.setApproximate_Steel_Working_Thickness("12-63mm");
                break;
            case YTTERBIUM_169:
                this.setAssembly_Model_Number("91810");
                this.setGamma_Energy_Range("8-308keV");
                this.setHalfLife(32);
                this.setApproximate_Steel_Working_Thickness(" 2-20mm");
                break;
            case COBALT_60:
                this.setAssembly_Model_Number("A424-19");
                this.setGamma_Energy_Range("1.17-1.33MeV");
                /**
                 * Half-Life COBALT_60 = 5,27 years or 1923.55 days
                 */
                this.setHalfLife(1923.55);
                this.setApproximate_Steel_Working_Thickness("50-150mm");
                break;
            case CAESIUM_137:
                this.setAssembly_Model_Number("A424-30");
                this.setGamma_Energy_Range("N/A");
                /**
                 * Half-Life CAESIUM_137 = 30 years or 10950 days
                 */
                this.setHalfLife(10950);
                this.setApproximate_Steel_Working_Thickness("N/A");
                break;

        }

    }

    /**
     * @param isotopetype
     * @param activity
     */
    private void setActivityDimensions(ISOTOPETYPE isotopetype, double activity) {
        if (activity > 100) return;
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
            case CAESIUM_137:
                break;
            case COBALT_60:
                break;
            case YTTERBIUM_169:
                break;

        }

    }

    @Override
    public String toString() {
        return "\n"+"ISOTOPE{" +
                " ISOTOPE_TYPE= " + isotopetype +
                ", PRICE= " + price +
                ", ID= " + getId() +
                ", SERIAL_NUMBER= " + serialNumber +
                ", HALF_LIFE= " + halfLife +
                ", FIRST_ACTIVITY= " + ACTIVITY +
                ", CURRENT_ACTIVITY= " + activity +
                ", ACTIVITY_DIMENSIONS= " + activityDimensions +
                ", ASSEMBLY_MODEL_NUMBER= " + assembly_Model_Number +
                ", GAMMA_ENERGY_RANGE= " + gamma_Energy_Range +
                ", COUNTER = " + getCounter() +
                "}" + "\n";

    }
}
