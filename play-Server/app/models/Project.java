package models;

import java.util.ArrayList;

/**
 * Created by F.Arian on 06.11.17.
 */
public class Project {
	private String name;
	private int projectNumber;
	private String location;
	private ArrayList<Team> teams;
	private String customer;
	private String joinCompany;
	private boolean join;



	public Project(){
		this.setName(null);
		this.setProjectNumber(0);
		this.setLocation(null);
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(int projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ArrayList<Team> getTeams() {
		return teams;
	}

	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getJoinCompany() {
		return joinCompany;
	}

	public void setJoinCompany(String joinCompany) {
		this.joinCompany = joinCompany;
	}

	public boolean isJoin() {
		return join;
	}

	public void setJoin(boolean join) {
		this.join = join;
	}
}

