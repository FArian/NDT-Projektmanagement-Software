package models;

/**
 * Created by F.Arian on 29.11.17.
 */
public class RadioatioSigns {


    private String name;
    private Type type;

    public RadioatioSigns(String name) {
        this.name = name;
        this.setType(Type.SAFETY);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
