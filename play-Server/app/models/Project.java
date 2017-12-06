package models;

import actors.serverInterface.ServerLog;
import com.sun.tools.javac.util.*;

import java.util.*;
import java.util.List;

/**
 * Created by F.Arian on 06.11.17.
 */
public class Project {
	private String name;
	private int projectNumber;
	private String location;
	private String customer;
	private String joinCompany;
	private boolean join;
	private static String id;
	private List<Team> teams= new LinkedList<>();


	private ServerLog log=new ServerLog();


	public Project(){
		this.setName(null);
		this.setProjectNumber(0);
		this.setLocation(null);
		this.id=creatId("-"+getName());
		this.getLog().info(" NEW OBJECT CREATED, NAME : " +getName() +" " + getClass());

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
	public ServerLog getLog() {
		return log;
	}

	public static String getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name!=null){
			this.name = name;
		}


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
	/**
	 * create id String
	 * @param keyId
	 * @return id
	 */
	public String creatId(String keyId) {
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
		return "PROJECT{" +"\n"+
				", NAME= " + name + "\n"+
				", ID= " + getId() + "\n"+
				", PROJECT_NUMBER= " + projectNumber +"\n"+
				", LOCATION= " + location + "\n"+
				", TEAMS=" + teams +"\n"+
				", CUSTOMER= " + customer + "\n"+
				", JOIN=" + join +"\n"+
				", JOIN_COMPANY=" + joinCompany +"\n"+
				'}';
	}
}

