package models;

/**
 * Created by F.Arian on 06.11.17.
 */
public class Personal {
    private String firstName;
    private String lastName;
    private String birthday;
    private int[] age;
    private int id;
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
    private PersonalType personalType;
    private boolean status;
    private Project project;

    public Personal(String firstName, String lastName, String birthday, int id, TLD tld, FilmBadge filmBadge) {
        this.setFirstName(firstName);
        this.setBirthday(birthday);
        this.setId(id);
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
        this.setStartDate(DateFormatLocal.dateUpDate("00.00.0000"));
        this.setFinishedDate(DateFormatLocal.dateUpDate("00.00.0000"));
        this.setPersonalType(PersonalType.RADIOGRAPHER);
        if (id > 0 && tld != null && filmBadge != null) {
            this.setStatus(true);
        }
        this.age = new int[5];
        this.setProject(new Project());

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
                this.setBirthday(DateFormatLocal.changeDate(0, 0, -120));
            }
            lastName = lastName.toUpperCase();

        }
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = DateFormatLocal.dateUpDate(birthday);
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

    public void setStartDate(String startDate) {
        this.startDate = DateFormatLocal.dateUpDate(startDate);
    }

    public String getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(String finishedDate) {
        this.finishedDate = DateFormatLocal.dateUpDate(finishedDate);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public PersonalType getPersonalType() {
        return personalType;
    }

    public void setPersonalType(PersonalType personalType) {
        this.personalType = personalType;
    }

    public int[] getAge() {
        age = DateFormatLocal.getPeriodTime(this.getBirthday());
        return age;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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


    @Override
    public String toString() {
        return "PERSONAL{" + "\n" +
                "FIRST_NAME=" + firstName + "\n" +
                ", LAST_NAME=" + lastName + "\n" +
                ", BIRTHDAY=" + birthday + "\n" +
                ", AGE= " + getAge()[4] + " YEARS OLD AND " + getAge()[3] + " MONTH " + "\n" +
                ", PERSONAL_ID=" + id + "\n" +
                ", ADDRESS=" + address + "\n" +
                ", COMPANY_NAME=" + companyName + "\n" +
                ", MOBILE_NR=" + mobileNr + "\n" +
                ", TEL_NR=" + telNr + "\n" +
                ", TLD=" + tld + "\n" +
                ", FILM_BADGE=" + filmBadge + "\n" +
                ", WEIGHT=" + weight + "\n" +
                ", HEIGHT=" + height + "\n" +
                ", NATIONAL_ID=" + nationalId + "\n" +
                ", START_DATE=" + startDate + "\n" +
                ", FINISHED_DATE=" + finishedDate + "\n" +
                ", PERSONAL_TYPE=" + personalType + "\n" +
                ", STATUS=" + status + "\n" +
                ", PROJECT_NAME=" + project.getName() + "\n" +
                ", PROJECT_NR=" + project.getProjectNumber() + "\n" +
                '}';
    }
}
