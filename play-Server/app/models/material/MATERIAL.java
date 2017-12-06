package models.material;


import actors.serverInterface.ServerLog;
import models.DATA;
import models.Project;
import models.enums.TYPE;

/**
 * Created by F.Arian on 06.11.17.
 */
public class MATERIAL {
    private String name;
    private String model;
    private TYPE type;
    private String SerialNumber;
    private Project project;
    private ServerLog log = new ServerLog();
    private static int counter = DATA.counter(0);

    public int getCounter() {
        return counter;
    }

    public MATERIAL(String name, TYPE type) {
        setName(name);
        setModel(DATA.creatId("-" + name));
        setSerialNumber(DATA.generateUniqueId());
        setType(type);
        this.setProject(new Project());

    }

    public ServerLog getLog() {
        return log;
    }


    public void setModel(String model) {
        this.model = model;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }


    public String getModel() {
        return model;
    }

    public String getName() {
        return name;
    }

    public String getSerialNumber() {
        return SerialNumber;
    }

    public TYPE getType() {
        return type;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }


    @Override
    public String toString() {
        return "MATERIAL{" +
                ", NAME='" + getName() + "\n" +
                ", MODEL='" + getModel() + "\n" +
                ", TYPE=" + getType() + "\n" +
                ", SERIAL_NUMBER=" + getSerialNumber() + "\n" +
                ", PROJECT_NAME=" + project.getName() + "\n" +
                ", PROJECT_NR=" + project.getProjectNumber() + "\n" +
                ", PROJECT_LOCATION=" + project.getLocation() + "\n" +
                "}";
    }
}
