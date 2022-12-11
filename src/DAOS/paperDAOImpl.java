package DAOS;

import Entity.paper;
import Entity.paper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class paperDAOImpl extends DAOBase implements paperDAO{
    private static final String INSERT_SQL = "INSERT INTO paper(sid,id_paper,name,periodical,state,time,index_type,Attribution,materials) VALUES(?,?,?,?,?,?,?,?,?)";

    @Override
    public void submitpaper(paper paper) {
        Connection con = null;

        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
            psmt.setString(1,paper.getMaster().getSid());
            psmt.setString(1,paper.getId_paper());
            psmt.setString(2,paper.getName());
            psmt.setString(3,paper.getPeriodical());
            psmt.setInt(4,paper.getState());
            psmt.setString(5,paper.getTime());
            psmt.setString(6,paper.getIndex_type());
            psmt.setInt(7,paper.getAttribution());
            psmt.setBlob(8,paper.getMaterials());
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


    private static final String SELECT_SQL = "SELECT id_paper,name,periodical,state,time,index_type,Attribution,materials FROM paper WHERE sid = ?";

    public paper getPaper(String master_sid){
        Connection con = null;
        paper paper = new paper();
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SELECT_SQL);
            psmt.setString(1,master_sid);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                paper.setId_paper(rs.getString("id_paper"));
                paper.setName(rs.getString("name"));
                paper.setPeriodical(rs.getString("periodical"));
                paper.setState(rs.getInt("state"));
                paper.setTime(rs.getString("time"));
                paper.setIndex_type(rs.getString("index_type"));
                paper.setAttribution(rs.getInt("state"));
                paper.setMaterials(rs.getBlob("materials"));
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
        return paper;
    }




}
