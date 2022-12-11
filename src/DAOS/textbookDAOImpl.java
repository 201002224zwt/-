package DAOS;

import Entity.textbook;
import Entity.textbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class textbookDAOImpl extends DAOBase implements textbookDAO{
    private static final String INSERT_SQL = "INSERT INTO textbook(id_text,name, press,time,ranking,materials) VALUES(?,?,?,?,?,?)";

    @Override
    public void submittextbook(textbook textbook) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
            psmt.setString(1, textbook.getId_text());
            psmt.setString(2,textbook.getName());
            psmt.setString(3,textbook.getPress());
            psmt.setString(4,textbook.getTime());
            psmt.setInt(4,textbook.getRanking());
            psmt.setBlob(5,textbook.getMaterials());
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


    private static final String SELECT_SQL = "SELECT id_text,name, press,time,ranking,materials FROM textbook WHERE sid = ?";

    public textbook gettextbook(String master_sid){
        Connection con = null;
        textbook textbook = new textbook();
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SELECT_SQL);
            psmt.setString(1,master_sid);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                textbook.setId_text(rs.getString("id_textbook"));
                textbook.setName(rs.getString("name"));
                textbook.setPress(rs.getString("unit"));
                textbook.setTime(rs.getString("time"));
                textbook.setRanking(rs.getInt("ranking"));
                textbook.setMaterials(rs.getBlob("materials"));
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
        return textbook;
    }


}
