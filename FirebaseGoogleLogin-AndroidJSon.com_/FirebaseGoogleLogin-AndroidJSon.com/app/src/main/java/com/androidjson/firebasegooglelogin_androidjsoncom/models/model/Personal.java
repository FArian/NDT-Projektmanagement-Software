package com.androidjson.firebasegooglelogin_androidjsoncom.models.model;


import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.dosimeter.*;
import com.androidjson.firebasegooglelogin_androidjsoncom.models.model.enums.*;
import com.squareup.picasso.Picasso;

/**
 * Created by F.Arian on 06.11.17.
 */
public class Personal {
    private String id;
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
    private String email;
    private TLD tld;
    private FilmBadge filmBadge;
    private String weight;
    private String height;
    private String nationalId;
    private String startDate;
    private String finishedDate;
    private PERSONALTYPE personalType;
    private GeigerAlarm geiger;
    private PocketDosimeter dosimeter;
    private boolean isInRest;




    public Personal(String firstName, String lastName) {
        this.setFirstName(firstName);
        this.setAddress("IT WAS NOT ENTERED");
        this.setCompanyName("IT WAS NOT ENTERED");
        this.setMobileNr("IT WAS NOT ENTERED");
        this.setTelNr("IT WAS NOT ENTERED");
        this.setTld(new TLD());
        this.setFilmBadge(new FilmBadge());
        this.setWeight("IT WAS NOT ENTERED");
        this.setHeight("IT WAS NOT ENTERED");
        this.setNationalId("IT WAS NOT ENTERED");
        this.setLastName(lastName);
        this.setEmail("EMAIL NOT SET");
        this.setStartDate(DATA.dateUpDate("00.00.0000"));
        this.setFinishedDate(DATA.dateUpDate("00.00.0000"));
        this.setPersonalType(PERSONALTYPE.RADIOGRAPHER);
        this.age = new int[5];
        this.id = DATA.creatId("-" + getLastName());
        this.setGeiger(new GeigerAlarm());
        this.setDosimeter(new PocketDosimeter());
        instanceCounter++;
        counter = instanceCounter;
        this.setIsInRest(false);
        tld.setName("MY_TLD_"+getLastName());
        filmBadge.setName("MY_FilmBadge_"+getLastName());
        this.setBirthday("00.00.0000");
    }

    public Personal(){


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        this.id = DATA.creatId("-" + lastName);
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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
        this.finishedDate = finishedDate;
    }


    public boolean isStatus() {
        if (this.tld == null) {
            return false;
        }
        if (this.filmBadge == null) {
            return false;
        }
        return true;
    }

    public boolean setIsInRest(boolean isInRest) {
        return this.isInRest = isInRest;
    }

    public boolean getIsInRest() {
        return this.isInRest;
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
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "  PERSONAL{ " +
                "  STATUS= " + isStatus() +
                "  EMAIL= " + getEmail() +
                ", FIRST_NAME= " + firstName +
                ", LAST_NAME= " + lastName +
                ", ID= " + id +
                ", BIRTHDAY= " + birthday +
                //", AGE= " + getAge()[4] + " YEARS OLD AND " + getAge()[3] + " MONTH " +
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
                ", COUNTER = " + getCounter() +
                "}" + "\n";
    }
}
