package DAOS;

import Entity.hs_platform;

import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class hs_platformDAOImpl extends DAOBase implements hs_platformDAO{
    private static final String INSERT_SQL = "INSERT INTO hs_platform(mid, name, unit, time, ranking, materials) VALUES(?,?,?,?,?,?)";

    @Override
    public void submiths_platform (hs_platform platform) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
            InputStream in = new FileInputStream(new File(platform.getMaterials()));
            psmt.setString(1, platform.getMaster().getSid());
            psmt.setString(2, platform.getName());
            psmt.setString(3,platform.getUnit());
            psmt.setString(4,platform.getTime());
            psmt.setInt(5, platform.getRanking());
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

    private static final String SELECT_SQL = "SELECT  name, unit, time, ranking, materials FROM hs_platform WHERE mid = ?";

    public hs_platform geths_platform(String master_sid){
        Connection con = null;
        hs_platform hs_platform = new hs_platform();
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SELECT_SQL);
            psmt.setString(1,master_sid);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                hs_platform.setName(rs.getString("name"));
                hs_platform.setUnit(rs.getString("unit"));
                hs_platform.setTime(rs.getString("time"));
                hs_platform.setRanking(rs.getInt("ranking"));
                Blob photo = rs.getBlob("materials");
                InputStream in = photo.getBinaryStream();
                OutputStream out = new FileOutputStream(new File("src/platform_materials.jpg"));
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
        return hs_platform;
    }
}