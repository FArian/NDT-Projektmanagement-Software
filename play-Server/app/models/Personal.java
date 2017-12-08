package models;

import models.dosimeter.FilmBadge;
import models.dosimeter.GeigerAlarm;
import models.dosimeter.PocketDosimeter;
import models.dosimeter.TLD;
import models.enums.PERSONALTYPE;

/**
 * Created by F.Arian on 06.11.17.
 */
public class Personal {
    private static String id;
    /**
     * instance counter for objects
     */
    private static int instanceCounter = 0;
    int counter = 0;
    private String firstName;
    private String lastName;
    private String birthday;
    private int[] age;
    private String address;
    private String companyName;
    private String mobileNr;
    private String telNr;
    private TLD tld;
    private FilmBadge filmBadge;
    private String weight;
    private String height;
    private String nationalId;
    private String startDate;
    private String finishedDate;
    private PERSONALTYPE personalType;
    private boolean status;
    private GeigerAlarm geiger;
    private PocketDosimeter dosimeter;
    private ServerLog log = new ServerLog();


    public Personal(String firstName, String lastName, String birthday, TLD tld, FilmBadge filmBadge) {
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
        this.id = DATA.creatId("-" + getLastName());
        this.setGeiger(new GeigerAlarm());
        this.setDosimeter(new PocketDosimeter());


        instanceCounter++;
        counter = instanceCounter;
        getLog().info("NEW OBJECT CREATED, FIRSTNAME: " + getFirstName() + " NAME: " + getLastName() + "-" + getClass());


    }

    public ServerLog getLog() {
        return log;
    }


    public int getCounter() {

        return counter;
    }

    public GeigerAlarm getGeiger() {
        return geiger;
    }

    public void setGeiger(GeigerAlarm geiger) {
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

    public TLD getTld() {
        return tld;
    }

    public void setTld(TLD tld) {
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

    public void setStartDate(String startDate) {
        this.startDate = DATA.dateUpDate(startDate);
    }

    @Override
    public String toString() {
        return  "  PERSONAL{ " +
                "  FIRST_NAME= " + firstName +
                ", LAST_NAME= " + lastName +
                ", ID= " + id +
                ", BIRTHDAY= " + birthday +
                ", AGE= " + getAge()[4] + " YEARS OLD AND " + getAge()[3] + " MONTH " +
                ", ADDRESS= " + address +
                ", COMPANY_NAME= " + companyName +
                ", MOBILE_NR= " + mobileNr +
                ", TEL_NR= " + telNr +
                ", TLD= " + tld +
                ", FILM_BADGE= " + filmBadge +
                ", GEIGER_DOSIMETER_ALARM= " + geiger +
                ", POCKET_DOSIMETER= " + dosimeter +
                ", WEIGHT= " + weight +
                ", HEIGHT= " + height +
                ", NATIONAL_ID= " + nationalId +
                ", START_DATE= " + startDate +
                ", FINISHED_DATE= " + finishedDate +
                ", PERSONAL_TYPE= " + personalType +
                ", STATUS= " + status +
                ", COUNTER = " + getCounter() +
                "}"+"\n";
    }
}
