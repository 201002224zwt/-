package DAOS;

import Entity.Master;
import Entity.SubjectMaster;
import User.UserManage;
import User.UserType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author caoqike
 * @date 2022-12-08 21:52:07
 */
public class SubjectMasterimp extends DAOBase implements SubjectMasterDAO {

    private static final String SUBJECTMASTER_INSERT_SQL = "INSERT INTO SubjectMaster(smid,subid,name) VALUES(?,?,?)";
    @Override
    public void addSubjectMaster(SubjectMaster subjectMaster) {
        Connection con = null;
        con = getConnection();
        try {
            //???????д???????
            PreparedStatement psmt = con.prepareStatement(SUBJECTMASTER_INSERT_SQL);
            psmt.setString(1, subjectMaster.getSmid());
            psmt.setString(2, subjectMaster.getSubid());
            psmt.setString(3, subjectMaster.getName());
            psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSubjectMaster(SubjectMaster subjectMaster) {

    }

    @Override
    public void deleteSubjectMaster(SubjectMaster subjectMaster) {

    }

    @Override
    public SubjectMaster getSubjectMaster(String id) {

        String sql="select * from SubjectMaster where smid=?";

        Connection con = null;
        SubjectMaster subjectMaster =null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                subjectMaster = new SubjectMaster(id, rs.getString("subid"),rs.getString("name"));
            }
            psmt.close();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return subjectMaster;
    }
}
