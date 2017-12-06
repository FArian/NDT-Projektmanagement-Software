package models;

import models.dosimeter.FilmBadge;
import models.dosimeter.GeigerDosimeterAlarm;
import models.dosimeter.PocketDosimeter;
import models.dosimeter.Tld;
import models.enums.PERSONALTYPE;

/**
 * Created by F.Arian on 06.11.17.
 */
public class Personal {
    private String firstName;
    private String lastName;
    private String birthday;
    private int[] age;
    private static String id;
    private String address;
    private String companyName;
    private String mobileNr;
    private String telNr;
    private Tld tld;
    private FilmBadge filmBadge;
    private String weight;
    private String height;
    private String nationalId;
    private String startDate;
    private String finishedDate;
    private PERSONALTYPE personalType;
    private boolean status;
    private Project project;
    private GeigerDosimeterAlarm geiger;
    private PocketDosimeter dosimeter;
    private static int counter=DATA.counter(0);
    public int getCounter() {return counter;}

    public Personal(String firstName, String lastName, String birthday, Tld tld, FilmBadge filmBadge) {
        this.setFirstName(firstName);
        this.setBirthday(birthday);
        this.setAddress("IT WAS NOT ENTERED");
        this.setCompanyName("IT WAS NOT ENTERED");
        this.setMobileNr("IT WAS NOT ENTERED");
        this.setTelNr("IT WAS NOT ENTERED");
        this.setTld(tld);
        this.setFilmBadge(filmBadge);
        this.setWeight("IT WAS NOT ENTERED");
        this.setHeight("IT WAS NOT ENTERED");
        this.setNationalId("IT WAS NOT ENTERED");
        this.setLastName(lastName);
        this.setStartDate(DATA.dateUpDate("00.00.0000"));
        this.setFinishedDate(DATA.dateUpDate("00.00.0000"));
        this.setPersonalType(PERSONALTYPE.RADIOGRAPHER);
        if (tld != null && filmBadge != null) {
            this.setStatus(true);
        }
        this.age = new int[5];
        this.setProject(new Project());
        this.id=creatId("-"+getLastName());
        this.setGeiger(new GeigerDosimeterAlarm());
        this.setDosimeter(new PocketDosimeter());

    }

    public GeigerDosimeterAlarm getGeiger() {
        return geiger;
    }

    public void setGeiger(GeigerDosimeterAlarm geiger) {
        this.geiger = geiger;
    }

    public PocketDosimeter getDosimeter() {
        return dosimeter;
    }

    public void setDosimeter(PocketDosimeter dosimeter) {
        this.dosimeter = dosimeter;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName != null) {
            firstName = firstName.toLowerCase();
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        String str = "khodayari";
        String str2 = "khodayary";
        String str3 = "خدایاری";
        if (lastName != null) {
            if (lastName.equals(str) || lastName.equals(str2) || lastName.equals(str3)) {
                lastName = "اوسگولیاری";
                setFirstName("عجوبه خلقت");
                setAddress("تهران . خیابان ستارخان");
                setCompanyName("شرکت ایران ول");
                setMobileNr("گوشتکوب خدایاری");
                setTelNr("فروخته");
                this.setBirthday(DATA.changeDate(0, 0, -120));
            }
            lastName = lastName.toUpperCase();

        }
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = DATA.dateUpDate(birthday);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMobileNr() {
        return mobileNr;
    }

    public void setMobileNr(String mobileNr) {
        this.mobileNr = mobileNr;
    }

    public String getTelNr() {
        return telNr;
    }

    public void setTelNr(String telNr) {
        this.telNr = telNr;
    }

    public Tld getTld() {
        return tld;
    }

    public void setTld(Tld tld) {
        this.tld = tld;
    }

    public FilmBadge getFilmBadge() {
        return filmBadge;
    }

    public void setFilmBadge(FilmBadge filmBadge) {
        this.filmBadge = filmBadge;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getStratDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = DATA.dateUpDate(startDate);
    }

    public String getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(String finishedDate) {
        this.finishedDate = DATA.dateUpDate(finishedDate);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public PERSONALTYPE getPersonalType() {
        return personalType;
    }

    public void setPersonalType(PERSONALTYPE personalType) {
        this.personalType = personalType;
    }

    public int[] getAge() {
        age = DATA.getPeriodTime(this.getBirthday());
        return age;
    }


    public String getId() {
        return this.id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartDate() {
        return startDate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    public  String creatId(String keyId) {
        String result = "";
        double d;
        for (int i = 1; i < 4; i++) {
            d = Math.random() * 10;
            result = result + ((int) d);
            if (i % 3 == 0) {
                result = result + keyId;
            }
        }
        return result;
    }


    @Override
    public String toString() {
        return "PERSONAL{ " + "\n" +
                ", FIRST_NAME= " + firstName + "\n" +
                ", LAST_NAME= " + lastName + "\n" +
                ", ID= " + id + "\n" +
                ", BIRTHDAY= " + birthday + "\n" +
                ", AGE= " + getAge()[4] + " YEARS OLD AND " + getAge()[3] + " MONTH " + "\n" +
                ", ADDRESS= " + address + "\n" +
                ", COMPANY_NAME= " + companyName + "\n" +
                ", MOBILE_NR= " + mobileNr + "\n" +
                ", TEL_NR= " + telNr + "\n" +
                ", TLD= " + tld + "\n" +
                ", FILM_BADGE= " + filmBadge + "\n" +
                ", GEIGER_DOSIMETER_ALARM= " + geiger + "\n" +
                ", POCKET_DOSIMETER= " + dosimeter + "\n" +
                ", WEIGHT= " + weight + "\n" +
                ", HEIGHT= " + height + "\n" +
                ", NATIONAL_ID= " + nationalId + "\n" +
                ", START_DATE= " + startDate + "\n" +
                ", FINISHED_DATE= " + finishedDate + "\n" +
                ", PERSONAL_TYPE= " + personalType + "\n" +
                ", STATUS= " + status + "\n" +
                ", PROJECT_NAME= " + project.getName() + "\n" +
                ", PROJECT_NR= " + project.getProjectNumber() + "\n" +
                " }";
    }
}
