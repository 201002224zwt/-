package DAOS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Entity.award;

public class awardDAOImpl extends DAOBase implements awardDAO{
    private static final String INSERT_SQL = "INSERT INTO award(sid,id_award,name,reward_grade,award_grade,ranking,time,materials) VALUES(?,?,?,?,?,?,?,?)";

    @Override
    public void submitaward(award award) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
            psmt.setString(1,award.getMaster().getSid());
            psmt.setString(1, award.getId_award());
            psmt.setString(2,award.getName());
            psmt.setInt(3,award.getAward_grade());
            psmt.setInt(4,award.getReward_grade());
            psmt.setString(5,award.getRanking());
            psmt.setString(6,award.getTime());
            psmt.setBlob(7,award.getMaterials());
            psmt.executeUpdate();
            psmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    private static final String SELECT_SQL = "SELECT id_award,name,reward_grade,award_grade,ranking,time,materials FROM award WHERE sid = ?";

    public award getAward(String master_sid){
        Connection con = null;
        award award = new award();
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SELECT_SQL);
            psmt.setString(1,master_sid);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                award.setId_award(rs.getString("id_award"));
                award.setName(rs.getString("name"));
                award.setAward_grade(rs.getInt("grade"));
                award.setRanking(rs.getString("ranking"));
                award.setTime(rs.getString("time"));
                award.setMaterials(rs.getBlob("materials"));
            }
            psmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return award;
    }
}
