package com.androidjson.firebasegooglelogin_androidjsoncom.models.model;

import java.util.*;

/**
 * Created by F.Arian on 06.11.17.
 */
public class Project {
    private String id ;
    private static int instanceCounter = 0;
    int counter = 0;
    private String name;
    private String projectNumber;
    private String location;
    private String customer;
    private String joinCompany;
    private boolean join;
    private String startDate;
    private String finishDate;
    private List<Team> teams = new LinkedList<>();



    public Project() {
        this.setLocation("NOT SET");
        this.id=DATA.generateUniqueId();
        instanceCounter++;
        counter = instanceCounter;
        this.setName("ParsJonobi");
        this.setProjectNumber(getCounter() + "-" + DATA.creatId("|" + getStartDate()));
        this.setJoinCompany("parto");


    }

    public String getStartDate() {
        if(startDate==null){
            startDate=DATA.getLocalDate();
        }
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate=DATA.dateUpDate(startDate);
    }

    public String getFinishDate() {
        if(finishDate==null){
            this.finishDate="NOT SET";
        }
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate=DATA.dateUpDate(finishDate);
    }

    public  String getId() {
        return id;
    }

    public int getCounter() {
        return counter;
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean contains(Object o) {
        return false;
    }

    public Iterator<Team> iterator() {
        return null;
    }

    public Object[] toArray() {
        return new Object[0];
    }

    public <T> T[] toArray(T[] a) {
        return null;
    }

    public boolean add(Team team) {
        return false;
    }

    public boolean remove(Object o) {
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public boolean addAll(Collection<? extends Team> c) {
        return false;
    }

    public boolean addAll(int index, Collection<? extends Team> c) {
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        return false;
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {

    }

    public Team get(int index) {
        return null;
    }

    public Team set(int index, Team element) {
        return null;
    }

    public void add(int index, Team element) {

    }

    public Team remove(int index) {
        return null;
    }

    public int indexOf(Object o) {
        return 0;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator<Team> listIterator() {
        return null;
    }

    public ListIterator<Team> listIterator(int index) {
        return null;
    }

    public List<Team> subList(int fromIndex, int toIndex) {
        return null;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            name = name.toUpperCase() + "-PNR:" + getCounter();
            this.name = name;
        }
        this.name = name;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public String getCustomer() {
        if(customer==null){
            customer="NOT SET";
        }
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getJoinCompany() {
        if(joinCompany==null){
            joinCompany="NOT SET";
        }
        return joinCompany;
    }

    public void setJoinCompany(String joinCompany) {
        if (joinCompany != null) {
            this.setJoin(true);
        }
        this.joinCompany = joinCompany;
    }

    public boolean isJoin() {
        return join;
    }

    public void setJoin(boolean join) {
        this.join = join;
    }

    @Override
    public String toString() {
        return "PROJECT{" + "\n" +
                "  NAME= " + name + "\n" +
                ", ID= " + getId() + "\n" +
                ", PROJECT_NUMBER= " + projectNumber + "\n" +
                ", START_DATE= " + getStartDate() + "\n" +
                ", FINISH_DATE= " + getFinishDate() + "\n" +
                ", LOCATION= " + location + "\n" +
                ", TEAMS=" + teams + "\n" +
                ", CUSTOMER= " + getCustomer() + "\n" +
                ", JOIN=" + isJoin() + "\n" +
                ", JOIN_COMPANY=" + getJoinCompany() + "\n" +
                ", COUNTER = " + getCounter() + "\n" +
                '}';
    }
}

