package DAOS;

import Entity.Teacher;
import User.UserManage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author zhuwentao
 * @version 1.0
 * @data 2022/12/9 19:35
 */
public class TeacherDAOimp extends DAOBase implements TeacherDAO{
    @Override
    public void addTeacher(Teacher teacher) {
        //构造连接
        Connection con = null;
        con = getConnection();
        try {
            //增加一条记录
            String sql="insert into Teacher values(?,?)";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, teacher.getId());
            psmt.setString(2, teacher.getName());
            psmt.executeUpdate();
            psmt.close();


            //设置该教师的登录账号
            try {
                UserManage.saveInfo(teacher);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTeacher(Teacher teacher) {

    }

    @Override
    public void updateTeacher(Teacher teacher) {

    }

    @Override
    public Teacher getTeacher(String id) {
      return null;
    }
}
