package models;

/**
 * Created by F.Arian on 06.11.17.
 */
public class Personal {
	private String firstName;
	private String lastName;
	private String birthday;
	private int personalId;
	private String adress;
	private String companyName;
	private String mobileNr;
	private String telNr;
	private TLD tld;
	private FilmBadge filmBadge;
	private String weight;
	private String height;
	private String nationalId;
	private String stratDate;
	private String finishedDate;
	private PersonalType personalType;
	private boolean status;

	public Personal(String firstName, String lastName, String birthday, int personalId, String adress,
			String companyName, String mobileNr, String telNr, TLD tld, FilmBadge filmBadge, String weight,
			String height, String nationalId, String stratDate, PersonalType personalType, boolean status) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.setBirthday(birthday);
		this.personalId = personalId;
		this.adress = adress;
		this.companyName = companyName;
		this.mobileNr = mobileNr;
		this.telNr = telNr;
		this.tld = tld;
		this.filmBadge = filmBadge;
		this.weight = weight;
		this.height = height;
		this.nationalId = nationalId;
		this.stratDate = DateFormatLocal.dateUpDate(stratDate);
		this.finishedDate = DateFormatLocal.dateUpDate(null);
		this.personalType = personalType;
		this.status = status;
	}

	public Personal(String firstName, String lastName, String birthday, int personalId, String adress, String stratDate,
			PersonalType personalType) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.personalId = personalId;
		this.adress = adress;
		this.stratDate = stratDate;
		this.personalType = personalType;
		this.status = false;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = DateFormatLocal.dateUpDate(birthday);
	}

	public int getPersonalId() {
		return personalId;
	}

	public void setPersonalId(int personalId) {
		this.personalId = personalId;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
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
		return stratDate;
	}

	public void setStratDate(String stratDate) {
		this.stratDate = DateFormatLocal.dateUpDate(stratDate);
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

	@Override
	public String toString() {
		return "Personal [firstName=" + firstName + ", lastName=" + lastName + ", birthday=" + birthday
				+ ", personalId=" + personalId + ", adress=" + adress + ", companyName=" + companyName + ", mobileNr="
				+ mobileNr + ", telNr=" + telNr + ", tld=" + tld + ", filmBadge=" + filmBadge + ", weight=" + weight
				+ ", height=" + height + ", nationalId=" + nationalId + ", stratDate=" + stratDate + ", finishedDate="
				+ finishedDate + ", personalType=" + personalType + ", status=" + status + "]";
	}

}
