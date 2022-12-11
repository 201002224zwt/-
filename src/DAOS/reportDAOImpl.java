package DAOS;

import Entity.report;
import Entity.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class reportDAOImpl extends DAOBase implements reportDAO{
    private static final String INSERT_SQL = "INSERT INTO report(id_report, name, type, unit, time, ranking, materials) VALUES(?,?,?,?,?,?,?)";

    @Override
    public void submitreport(report report) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
            psmt.setString(1, report.getId_report());
            psmt.setString(1, report.getName());
            psmt.setInt(1, report.getType());
            psmt.setString(1, report.getUnit());
            psmt.setString(1, report.getTime());
            psmt.setInt(1, report.getRanking());
            psmt.setBlob(5,report.getMaterials());
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

    private static final String SELECT_SQL = "SELECT id_report, name, type, unit, time, ranking, materials FROM report WHERE sid = ?";

    public report getreport(String master_sid){
        Connection con = null;
        report report = new report();
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SELECT_SQL);
            psmt.setString(1,master_sid);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                report.setId_report(rs.getString("id_report"));
                report.setName(rs.getString("name"));
                report.setType(rs.getInt("type"));
                report.setUnit(rs.getString("unit"));
                report.setTime(rs.getString("time"));
                report.setRanking(rs.getInt("ranking"));
                report.setMaterials(rs.getBlob("materials"));
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
