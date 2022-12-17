package DAOS;

import Entity.standard;
import Entity.standard;

import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class standardDAOImpl extends DAOBase implements standardDAO {
    private static final String INSERT_SQL = "INSERT INTO standard(mid, name, level, time, materials) VALUES(?,?,?,?,?)";

    @Override
    public void submitstandard(standard standard) {
        Connection con = null;
        try {
            con = getConnection();
            InputStream in = new FileInputStream(new File(standard.getMaterials()));
            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
            psmt.setString(1,standard.getMaster().getSid());
            psmt.setString(2, standard.getName());
            psmt.setInt(3, standard.getStandard_level());
            psmt.setString(4, standard.getTime());
            psmt.setBlob(5, in);
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

    private static final String SELECT_SQL = "SELECT name, level, time, materials FROM standard WHERE sid = ?";

    public standard getstandard(String master_sid){
        Connection con = null;
        standard standard = new standard();
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SELECT_SQL);
            psmt.setString(1,master_sid);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                standard.setName(rs.getString("name"));
                standard.setStandard_level(rs.getInt("level"));
                standard.setTime(rs.getString("time"));
                Blob photo = rs.getBlob("materials");
                InputStream in = photo.getBinaryStream();
                OutputStream out = new FileOutputStream(new File("src/standard_materials.jpg"));
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
        return standard;
    }




}