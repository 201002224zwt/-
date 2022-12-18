package DAOS;

import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Entity.award;

public class awardDAOImpl extends DAOBase implements awardDAO{
    private static final String INSERT_SQL = "INSERT INTO award(mid,name,level,grade,ranking,time,materials) VALUES(?,?,?,?,?,?,?)";

    @Override
    public void submitaward(award award) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
            InputStream in = new FileInputStream(new File(award.getMaterials()));
            psmt.setString(1,award.getMaster().getSid());
            psmt.setString(2,award.getName());
            psmt.setInt(3,award.getAward_grade());
            psmt.setInt(4,award.getReward_grade());
            psmt.setInt(5,award.getRanking());
            psmt.setString(6,award.getTime());
            psmt.setBlob(7,in);
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


    private static final String SELECT_SQL = "SELECT name,reward_grade,award_grade,ranking,time,materials FROM award WHERE mid = ?";

    public award getAward(String master_sid){
        Connection con = null;
        award award = new award();
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SELECT_SQL);
            psmt.setString(1,master_sid);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                award.setName(rs.getString("name"));
                award.setAward_grade(rs.getInt("grade"));
                award.setRanking(rs.getInt("ranking"));
                award.setTime(rs.getString("time"));
                award.setMaterials(rs.getString("materials"));
                Blob photo = rs.getBlob("materials");
                InputStream in = photo.getBinaryStream();
                OutputStream out = new FileOutputStream(new File("src/award_materials.jpg"));
                byte[] buf = new byte[1024];
                int len =0;
                while((len = in.read(buf))!= -1) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
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

    private static final String TUTOR_INSERT_SQL = "INSERT INTO award(tutor_view) VALUES(?)";

    public void firstsubmit(award award) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(TUTOR_INSERT_SQL);
            psmt.setBoolean(8,award.isTutor_view());
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

    private static final String LAST_INSERT_SQL = "INSERT INTO award(last_view) VALUES(?)";

    public void lastsubmit(award award) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(LAST_INSERT_SQL);
            psmt.setBoolean(9,award.isLast_view());
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

}
