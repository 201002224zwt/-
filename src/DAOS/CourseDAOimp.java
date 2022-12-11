package DAOS;

import Entity.Course;
import Entity.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author zhuwentao
 * @version 1.0
 * @data 2022/12/9 20:33
 */
public class CourseDAOimp extends DAOBase implements CourseDAO{

    @Override
    public void addCourse(Course course) {
        //构造连接
        Connection con = null;
        con = getConnection();
        try {
            //增加一条记录
            String sql="insert into Course values(?,?,?,?,?,?,?)";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, course.getCouseid());
            psmt.setString(2, course.getSubid().subid);
            psmt.setString(3, course.getTid());
            psmt.setString(4, course.getName());
            psmt.setInt(5, course.getHours());
            psmt.setInt(6, course.getApplications());
            psmt.setInt(7, course.getState());

            psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCourse(Course course) {

    }

    @Override
    public void updateCourse(Course course) {

    }

    @Override
    public Teacher getCourse(String courseid) {
        return null;
    }
}
