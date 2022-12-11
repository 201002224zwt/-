package DAOS;

import Entity.hs_platform;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class hs_platformDAOImpl extends DAOBase implements hs_platformDAO{
    private static final String INSERT_SQL = "INSERT INTO hs_platform(id_platform, name, unit, time, ranking, materials) VALUES(?,?,?,?,?,?)";

    @Override
    public void submiths_platform (hs_platform platform) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
            psmt.setString(1, platform.getId_platform());
            psmt.setString(1, platform.getName());
            psmt.setString(1,platform.getUnit());
            psmt.setString(1,platform.getTime());
            psmt.setInt(1, platform.getRanking());
            psmt.setBlob(5,platform.getMaterials());
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

    private static final String SELECT_SQL = "SELECT id_platform, name, unit, time, ranking, materials FROM hs_platform WHERE sid = ?";

    public hs_platform geths_platform(String master_sid){
        Connection con = null;
        hs_platform hs_platform = new hs_platform();
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SELECT_SQL);
            psmt.setString(1,master_sid);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                hs_platform.setId_platform(rs.getString("id_platform"));
                hs_platform.setName(rs.getString("name"));
                hs_platform.setUnit(rs.getString("unit"));
                hs_platform.setTime(rs.getString("time"));
                hs_platform.setRanking(rs.getInt("ranking"));
                hs_platform.setMaterials(rs.getBlob("materials"));
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
        return hs_platform;
    }



}