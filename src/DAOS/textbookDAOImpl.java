package DAOS;

import Entity.textbook;
import Entity.textbook;

import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class textbookDAOImpl extends DAOBase implements textbookDAO{
    private static final String INSERT_SQL = "INSERT INTO textbook(mid, name, press,time,ranking,materials) VALUES(?,?,?,?,?,?)";

    @Override
    public void submittextbook(textbook textbook) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
            InputStream in = new FileInputStream(new File(textbook.getMaterials()));
            psmt.setString(1, textbook.getMaster().getSid());
            psmt.setString(2,textbook.getName());
            psmt.setString(3,textbook.getPress());
            psmt.setString(4,textbook.getTime());
            psmt.setInt(5,textbook.getRanking());
            psmt.setBlob(6,in);
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


    private static final String SELECT_SQL = "SELECT name, press,time,ranking,materials FROM textbook WHERE mid = ?";

    public textbook gettextbook(String master_sid){
        Connection con = null;
        textbook textbook = new textbook();
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SELECT_SQL);
            psmt.setString(1,master_sid);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                textbook.setName(rs.getString("name"));
                textbook.setPress(rs.getString("unit"));
                textbook.setTime(rs.getString("time"));
                textbook.setRanking(rs.getInt("ranking"));
                Blob photo = rs.getBlob("materials");
                InputStream in = photo.getBinaryStream();
                OutputStream out = new FileOutputStream(new File("src/textbook_materials.jpg"));
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
        return textbook;
    }
}
