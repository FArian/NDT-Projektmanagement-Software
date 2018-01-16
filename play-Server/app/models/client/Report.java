package models.client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by F.Arian on 16.01.18.
 */
public class Report {

    private List<String> film_Infos;
    private String dateOfReport;
    private List<Integer> sizes;
    private List<String> sizeUnits;
    private List<Integer> counter;
    private String id = null;

    public Report() {
        this.film_Infos = new ArrayList<>();
        this.dateOfReport = "";
        this.sizes = new ArrayList<>();
        this.sizeUnits = new ArrayList<>();
        this.counter = new ArrayList<>();
        this.id = "Not SET";
    }

    public void creatId() {
        String result = "";
        double d;
        for (int i = 1; i < 4; i++) {
            d = Math.random() * 10;
            result = result + ((int) d);
            if (i % 3 == 0) {
                result = "ID-" +result;
            }
        }
        this.id = result;
    }

    public String getDateOfReport() {
        return dateOfReport;
    }

    public void setDateOfReport(String dateOfReport) {
        this.dateOfReport = dateOfReport;
    }

    public List<String> getFilmInfo() {
        return film_Infos;
    }

    public String getFilmInfos(int index) {
        for (int i = 0; i < getFilmInfo().size(); i++) {
            if (i == index) {
                return getFilmInfo().get(i);
            }
        }
        return null;
    }

    public void setFilmInfos(int index, String value) {
        this.film_Infos.add(index, value);
    }

    public List<Integer> getSizes() {
        return sizes;
    }

    public void setSizes(int index, int value) {
        this.sizes.add(index, value);
    }

    public List<String> getSizeUnits() {
        return sizeUnits;
    }

    public String getSizeUnit(int index) {
        for (int i = 0; i < getSizeUnits().size(); i++) {
            if (i == index) {
                return getSizeUnits().get(i);
            }
        }
        return null;
    }

    public void setSizeUnits(int index, String value) {
        this.sizeUnits.add(index, value);

    }

    public List<Integer> getCounter() {
        return counter;
    }

    public int getCounter(int index) {
        for (int i = 0; i < getCounter().size(); i++) {
            if (i == index) {
                return getCounter().get(i);
            }
        }

        return 0;
    }

    public void setCounter(int index, int value) {
        this.counter.add(index, value);

    }

    @Override
    public String toString() {
        return " REPORT { " + "\n" +
                ", REPORT NUMBER= " + id + "\n" +
                ", FILM INFOS= " + film_Infos + "\n" +
                ", SIZES= " + sizes + "\n" +
                ", SIZE UNITS= " + sizeUnits + "\n" +
                ", COUNTERS= " + counter + "\n" +
                ", DATE OF REPORT= " + dateOfReport + "\n" +
                "} " + "\n";
    }
}
