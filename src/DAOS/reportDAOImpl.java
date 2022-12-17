package DAOS;

import Entity.report;
import Entity.report;

import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class reportDAOImpl extends DAOBase implements reportDAO{
    private static final String INSERT_SQL = "INSERT INTO report(mid, name, type, unit, time, ranking, materials) VALUES(?,?,?,?,?,?,?)";

    @Override
    public void submitreport(report report) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
            InputStream in = new FileInputStream(new File(report.getMaterials()));
            psmt.setString(1, report.getMaster().getSid());
            psmt.setString(2, report.getName());
            psmt.setInt(3, report.getType());
            psmt.setString(4, report.getUnit());
            psmt.setString(5, report.getTime());
            psmt.setInt(6, report.getRanking());
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

    private static final String SELECT_SQL = "SELECT  name, type, unit, time, ranking, materials FROM report WHERE mid = ?";

    public report getreport(String master_sid){
        Connection con = null;
        report report = new report();
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SELECT_SQL);
            psmt.setString(1,master_sid);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                report.setName(rs.getString("name"));
                report.setType(rs.getInt("type"));
                report.setUnit(rs.getString("unit"));
                report.setTime(rs.getString("time"));
                report.setRanking(rs.getInt("ranking"));
                Blob photo = rs.getBlob("materials");
                InputStream in = photo.getBinaryStream();
                OutputStream out = new FileOutputStream(new File("src/report_materials.jpg"));
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
        return report;
    }

}
