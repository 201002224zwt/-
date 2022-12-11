package Entity;

import java.sql.Blob;

public class paper {
    private Master master;

    public paper(Master master) {
        this.master = master;
    }

    private String id_paper;
    private String name;
    private String periodical;
    private int state;
    private String time;
    private String index_type;
    private int Attribution;
    private Blob materials;

    public paper(String id_paper, String name, String periodical, int state, String time, String index_type, int attribution, Blob materials) {
        this.id_paper = id_paper;
        this.name = name;
        this.periodical = periodical;
        this.state = state;
        this.time = time;
        this.index_type = index_type;
        Attribution = attribution;
        this.materials = materials;
    }

    public paper() {

    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public String getId_paper() {
        return id_paper;
    }

    public void setId_paper(String id_paper) {
        this.id_paper = id_paper;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriodical() {
        return periodical;
    }

    public void setPeriodical(String periodical) {
        this.periodical = periodical;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIndex_type() {
        return index_type;
    }

    public void setIndex_type(String index_type) {
        this.index_type = index_type;
    }

    public int getAttribution() {
        return Attribution;
    }

    public void setAttribution(int attribution) {
        Attribution = attribution;
    }

    public Blob getMaterials() {
        return materials;
    }

    public void setMaterials(Blob materials) {
        this.materials = materials;
    }
}
