package Entity;

import java.sql.Blob;

public class standard {
    private Master master;


    public standard(Master master) {
        this.master = master;
    }

    private String id_standard;
    private String name;
    private String standard_level;
    private String time;
    private Blob materials;

    public standard(String id_standard, String name, String standard_level, String time, Blob materials) {
        this.id_standard = id_standard;
        this.name = name;
        this.standard_level = standard_level;
        this.time = time;
        this.materials = materials;
    }

    public standard() {

    }


    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }


    public String getId_standard() {
        return id_standard;
    }

    public void setId_standard(String id_standard) {
        this.id_standard = id_standard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStandard_level() {
        return standard_level;
    }

    public void setStandard_level(String standard_level) {
        this.standard_level = standard_level;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Blob getMaterials() {
        return materials;
    }

    public void setMaterials(Blob materials) {
        this.materials = materials;
    }
}