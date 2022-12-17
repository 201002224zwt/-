package DAOS;

import Entity.paper;

import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class paperDAOImpl extends DAOBase implements paperDAO{
    private static final String INSERT_SQL = "INSERT INTO thesis(mid,name,periodical,state,time,index_type,Attribution,materials) VALUES(?,?,?,?,?,?,?,?)";

    @Override
    public void submitpaper(paper paper) {
        Connection con = null;

        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(INSERT_SQL);
            InputStream in = new FileInputStream(new File(paper.getMaterials()));
            psmt.setString(1,paper.getMaster().getSid());
            psmt.setString(2,paper.getName());
            psmt.setString(3,paper.getPeriodical());
            psmt.setInt(4,paper.getState());
            psmt.setString(5,paper.getTime());
            psmt.setString(6,paper.getIndex_type());
            psmt.setInt(7,paper.getAttribution());
            psmt.setBlob(8,in);
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


    private static final String SELECT_SQL = "SELECT name,periodical,state,time,index_type,Attribution,materials FROM thesis WHERE mid = ?";

    public paper getPaper(String master_sid){
        Connection con = null;
        paper paper = new paper();
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(SELECT_SQL);
            psmt.setString(1,master_sid);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                paper.setName(rs.getString("name"));
                paper.setPeriodical(rs.getString("periodical"));
                paper.setState(rs.getInt("state"));
                paper.setTime(rs.getString("time"));
                paper.setIndex_type(rs.getString("index_type"));
                paper.setAttribution(rs.getInt("state"));
                Blob photo = rs.getBlob("materials");
                InputStream in = photo.getBinaryStream();
                OutputStream out = new FileOutputStream(new File("src/paper_materials.jpg"));
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
        return paper;
    }

    private static final String TUTOR_INSERT_SQL = "INSERT INTO thesis(tutor_view) VALUES(?)";

    public void firstsubmit(paper paper) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(TUTOR_INSERT_SQL);
            psmt.setBoolean(9,paper.isTutor_view());
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

    private static final String LAST_INSERT_SQL = "INSERT INTO thesis(last_view) VALUES(?)";

    public void lastsubmit(paper paper) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(LAST_INSERT_SQL);
            psmt.setBoolean(10,paper.isLast_view());
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

}
