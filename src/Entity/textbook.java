package Entity;

import java.sql.Blob;

public class textbook {
    private String id_text;
    private String name;
    private String press;
    private String time;
    private int ranking;
    private Blob materials;

    public textbook(String id_text, String name, String press, String time, int ranking, Blob materials) {
        this.id_text = id_text;
        this.name = name;
        this.press = press;
        this.time = time;
        this.ranking = ranking;
        this.materials = materials;
    }

    public String getId_text() {
        return id_text;
    }

    public void setId_text(String id_text) {
        this.id_text = id_text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
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
