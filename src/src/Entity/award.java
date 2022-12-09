package Entity;

import java.sql.Blob;

public class award {

    private Master master;

    public award(Master master) {
        this.master = master;
    }

    private String id_award;
    private String name;
    private int reward_grade;
    private int award_grade;
    private String ranking;
    private String time;
    private Blob materials;


    public award(String id_award, String name, int reward_grade, int award_grade, String ranking, String time, Blob materials) {
        this.id_award = id_award;
        this.name = name;
        this.reward_grade = reward_grade;
        this.award_grade = award_grade;
        this.ranking = ranking;
        this.time = time;
        this.materials = materials;
    }


    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }
    public String getId_award() {
        return id_award;
    }

    public void setId_award(String id_award) {
        this.id_award = id_award;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReward_grade() {
        return reward_grade;
    }

    public void setReward_grade(int reward_grade) {
        this.reward_grade = reward_grade;
    }

    public int getAward_grade() {
        return award_grade;
    }

    public void setAward_grade(int award_grade) {
        this.award_grade = award_grade;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
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
