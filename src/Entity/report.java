package Entity;


import java.sql.Blob;

public class report {
    private Master master;

    public report(Master master) {
        this.master = master;
    }

    private String id_report;
    private String name;
    private int type;
    private String unit;
    private String time;
    private int ranking;
    private Blob materials;


    public report(String id_report, String name, int type, String unit, String time, int ranking, Blob materials) {
        this.id_report = id_report;
        this.name = name;
        this.type = type;
        this.unit = unit;
        this.time = time;
        this.ranking = ranking;
        this.materials = materials;
    }

    public report() {

    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }


    public String getId_report() {
        return id_report;
    }

    public void setId_report(String id_report) {
        this.id_report = id_report;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
