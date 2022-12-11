package DAOS;

import Entity.standard;
import Entity.standard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class standardDAOImpl extends DAOBase implements standardDAO {
    private static final String INSERT_SQL = "INSERT INTO standard(id_standard, name, standard_level, time, materials) VALUES(?,?,?,?,?)";

    @Override
    public void submitstandard(standard standard) {
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
            psmt.setString(1, standard.getId_standard());
            psmt.setString(2, standard.getName());
            psmt.setString(3, standard.getStandard_level());
            psmt.setString(4, standard.getTime());
            psmt.setBlob(5, standard.getMaterials());
            psmt.executeUpdate();
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static final String SELECT_SQL = "SELECT id_standard, name, standard_level, time, materials FROM standard WHERE sid = ?";

    public standard getstandard(String master_sid){
        Connection con = null;
        standard standard = new standard();
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SELECT_SQL);
            psmt.setString(1,master_sid);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                standard.setId_standard(rs.getString("id_standard"));
                standard.setName(rs.getString("name"));
                standard.setStandard_level(rs.getString("standard_level"));
                standard.setTime(rs.getString("time"));
                standard.setMaterials(rs.getBlob("materials"));
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
        return standard;
    }




}