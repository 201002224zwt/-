package DAOS;
import Entity.AcademicActivity;
import Entity.Master;
import User.UserManage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AcademicActivityDAOimp extends DAOBase implements AcademicActivityDAO{


    /**
     *学术活动认定流程如下：
     * 学生提交会议名称，时间，地点（自动生成学术活动id号码）
     * 导师认定该会议为高水平会议
     * （----以上是在会议开始前----）
     * 学生提交所作报告名称和参会时间地点
     * 学院管理员查看记录并确认
     *
     * 故insert记录时，先插入的是学术活动号码，学生学号，会议名称，会议时间地点。
     **/
    @Override
    public void addAcademicActivity(AcademicActivity activity) {
        //构造连接
        Connection con = null;
        con = getConnection();
        try {
            //增加一条记录
            //待改
            String sql="insert into AcademicActivity(ActivityId,mid,ActivityName,Date) values(?,?,?,?)";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, activity.getActivity_id());
            psmt.setString(2,activity.getMaster_id());
            psmt.setString(3, activity.getActivity_name());
            psmt.setString(4, activity.getDate());
            psmt.executeUpdate();
            psmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     *
     *
     **/
    @Override
    public void deleteAcademicActivity(String ActivityId) {

    }

    @Override
    public void updateAcademicActivity(AcademicActivity activity) {

    }

    @Override
    public void updateTutorView(boolean permitted) {

    }

    @Override
    public void updateSubjectMasterView(boolean permitted) {

    }

    @Override
    public Master getAcademicActivity(String MasterId) {
        return null;
    }
}
