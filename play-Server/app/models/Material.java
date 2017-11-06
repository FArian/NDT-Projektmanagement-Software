package model;

public class Material {
	private String name;
	private String model;
	private Type type;
	private int SerialNumber;
	private Location location;

	public Material(String name, String model, Type type, int serialNumber, Location location) {
		setLocation(location);
		setModel(model);
		setName(name);
		setSerialNumber(serialNumber);
		setType(type);
	}

	public void setLocation(Location location) {
		this.location = location;
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

	public Location getLocation() {
		return location;
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
}
