package DAOS;

import Entity.patent;
import Entity.patent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class patentDAOImpl extends DAOBase implements patentDAO {
    private static final String INSERT_SQL = "INSERT INTO patent(id_patent, name,  type,  number,  time,  state, ranking, materials) VALUES(?,?,?,?,?,?,?)";

    @Override
    public void submitpatent(patent patent) {
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
            psmt.setString(1, patent.getId_patent());
            psmt.setString(1, patent.getName());
            psmt.setInt(1, patent.getType());
            psmt.setString(1, patent.getNumber());
            psmt.setString(1, patent.getTime());
            psmt.setString(1, patent.getState());
            psmt.setInt(1, patent.getRanking());
            psmt.setBlob(5, patent.getMaterials());
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


    private static final String SELECT_SQL = "SELECT id_patent, name,  type,  number,  time,  state, ranking, materials FROM patent WHERE sid = ?";

    public patent getpatent(String master_sid){
        Connection con = null;
        patent patent = new patent();
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SELECT_SQL);
            psmt.setString(1,master_sid);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                patent.setId_patent(rs.getString("id_patent"));
                patent.setName(rs.getString("name"));
                patent.setType(rs.getInt("type"));
                patent.setNumber(rs.getString("number"));
                patent.setTime(rs.getString("time"));
                patent.setRanking(rs.getInt("ranking"));
                patent.setMaterials(rs.getBlob("materials"));
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
        return patent;
    }




}