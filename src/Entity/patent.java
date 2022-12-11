package Entity;

import java.sql.Blob;

public class patent {
    private String id_patent;
    private String name;
    private int type;
    private String number;
    private String time;
    private String state;
    private int ranking;
    private Blob materials;

    public patent(String id_patent, String name, int type, String number, String time, String state, int ranking, Blob materials) {
        this.id_patent = id_patent;
        this.name = name;
        this.type = type;
        this.number = number;
        this.time = time;
        this.state = state;
        this.ranking = ranking;
        this.materials = materials;
    }

    public String getId_patent() {
        return id_patent;
    }

    public void setId_patent(String id_patent) {
        this.id_patent = id_patent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public Blob getMaterials() {
        return materials;
    }

    public void setMaterials(Blob materials) {
        this.materials = materials;
    }
}
