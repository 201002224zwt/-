package Entity;

import java.sql.Blob;

public class hs_platform {
    private Master master;

    public hs_platform(Master master) {
        this.master = master;
    }

    private String id_platform;
    private String name;
    private String unit;
    private String time;
    private int ranking;
    private Blob materials;

    public hs_platform(String id_platform, String name, String unit, String time, int ranking, Blob materials) {
        this.id_platform = id_platform;
        this.name = name;
        this.unit = unit;
        this.time = time;
        this.ranking = ranking;
        this.materials = materials;
    }

    public hs_platform() {

    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public String getId_platform() {
        return id_platform;
    }

    public void setId_platform(String id_platform) {
        this.id_platform = id_platform;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
