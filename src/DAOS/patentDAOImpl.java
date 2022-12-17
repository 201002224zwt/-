package DAOS;

import Entity.patent;
import Entity.patent;

import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class patentDAOImpl extends DAOBase implements patentDAO {
    private static final String INSERT_SQL = "INSERT INTO patent(mid , name,  type,  time, state, ranking, materials, number) VALUES(?,?,?,?,?,?,?,?)";

    @Override
    public void submitpatent(patent patent) {
        Connection con = null;
        try {
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
            InputStream in = new FileInputStream(new File(patent.getMaterials()));
            psmt.setString(1,patent.getMaster().getSid());
            psmt.setString(2, patent.getName());
            psmt.setInt(3, patent.getType());
            psmt.setString(8, patent.getNumber());
            psmt.setString(4, patent.getTime());
            psmt.setString(5, patent.getState());
            psmt.setInt(6, patent.getRanking());
            psmt.setBlob(7, in);
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


    private static final String SELECT_SQL = "SELECT name,  type,  number,  time,  state, ranking, materials FROM patent WHERE sid = ?";

    public patent getpatent(String master_sid){
        Connection con = null;
        patent patent = new patent();
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SELECT_SQL);
            psmt.setString(1,master_sid);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                patent.setName(rs.getString("name"));
                patent.setType(rs.getInt("type"));
                patent.setNumber(rs.getString("number"));
                patent.setTime(rs.getString("time"));
                patent.setRanking(rs.getInt("ranking"));
                Blob photo = rs.getBlob("materials");
                InputStream in = photo.getBinaryStream();
                OutputStream out = new FileOutputStream(new File("src/patent_materials.jpg"));
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
        return patent;
    }




}