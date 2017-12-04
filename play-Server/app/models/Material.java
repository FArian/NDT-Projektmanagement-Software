package models;


/**
 * Created by F.Arian on 06.11.17.
 */
public class Material {
	private String name;
	private String model;
	private Type type;
	private int SerialNumber;
	private Project project;

	public Material(String name, String model, Type type, int serialNumber) {
		setModel(model);
		setName(name);
		setSerialNumber(serialNumber);
		setType(type);
		this.setProject(new Project());
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setSerialNumber(int serialNumber) {
		SerialNumber = serialNumber;
	}


	public String getModel() {
		return model;
	}

	public String getName() {
		return name;
	}

	public int getSerialNumber() {
		return SerialNumber;
	}

	public Type getType() {
		return type;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
