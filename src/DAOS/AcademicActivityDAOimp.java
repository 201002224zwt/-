package DAOS;
import Entity.AcademicActivity;
import Entity.Master;
import Entity.standard;
import User.UserManage;

import java.awt.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 * 该表的插入操作不再通过AcademicActivity类来进行
 * 而是直接传入字段
 * AcademicActivity类仅作为返回字段使用
 *
 **/

public class AcademicActivityDAOimp extends DAOBase implements AcademicActivityDAO{


    /**
     *学术活动认定流程如下：
     * 学生提交会议名称，时间，地点（自动生成学术活动id号码）
     * 导师认定该会议为高水平会议
     * （----以上是在会议开始前----）
     * 学生提交所作报告名称和参会时间地点
     * 学院管理员查看记录并确认
     * 故insert记录时，先插入的是学术活动号码，学生学号，会议名称，会议时间地点。
     **/
    @Override
    public void addAcademicActivity(AcademicActivity activity) {
        //构造连接
        Connection con;
        con = getConnection();
        try {
            //增加一条记录
            //待改
            String sql="insert into AcademicActivity(ActivityId,mid,ActivityName,Date) values(?,?,?,?)";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, activity.getActivity_id());
            psmt.setString(2,activity.getMaster_id());
            psmt.setString(3, activity.getActivity_name());
            psmt.setDate(4, activity.getDate());
            psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *当导师认证不通过时，
     * 或者当学院认证不通过时，
     * 就将该记录在系统中删除
     **/
    @Override
    public void deleteAcademicActivity(String ActivityId) {

    }

    /**
     * 导师认证通过后
     * 学生上传参会证明
     **/
    @Override
    public void updateAcademicActivity(AcademicActivity activity) {

    }


    @Override
    public void updateTutorView(boolean permitted,String ActivityId) {
        //构造连接
        Connection con;
        con = getConnection();
        try {
            String sql="Update AcademicActivity set TutorView = ? where ActivityId = ? ";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setBoolean(1,permitted);
            psmt.setString(2, ActivityId);
            psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSubjectMasterView(boolean permitted,String ActivityId) {
        //构造连接
        Connection con;
        con = getConnection();
        try {
            String sql="Update AcademicActivity set MasterView = ? where ActivityId = ? ";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setBoolean(1,permitted);
            psmt.setString(2, ActivityId);
            psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 导入图片
     * @param file 文件
     * @param ActivityId 学术活动表的主键，用于查找
     **/
    @Override
    public void conservePicture(FileInputStream file, String ActivityId,String ImageType) {
        //构造连接
        Connection con;
        con = getConnection();
        try {
            String sql="Update AcademicActivity set Certificate = ?,ImageType = ? where ActivityId = ? ";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setBinaryStream(1, file);
            psmt.setString(2, ImageType);
            psmt.setString(3,ActivityId);
            psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<AcademicActivity> getAcademicActivity(String MasterId) {
        Connection con = null;
        List<AcademicActivity> l = new ArrayList<>();
        try{
            con = getConnection();
            String sql="select MasterId from AcademicActivity where mid = ?";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,MasterId);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                AcademicActivity a = new AcademicActivity();
                a.setActivity_id(rs.getString("ActivityId"));
                a.setActivity_name(rs.getString("ActivityName"));
                a.setDate(rs.getDate("Date"));
                a.setTutor_view(rs.getBoolean("TutorView"));
                a.setMaster_view(rs.getBoolean("MasterView"));
                a.setImage_type(rs.getString("ImageType"));
                a.setCertificate((FileInputStream) rs.getBinaryStream("Certificate"));
                l.add(a);
            }
            psmt.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                assert con != null;
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return l;
    }
}
