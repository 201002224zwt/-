package DAOS;

import Entity.*;
import User.User;
import User.Administrator;
import User.UserManage;
import User.UserType;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @author caoqike
 * @date 2022-12-12 09:06:12
 */
public class UserDAOimp  extends DAOBase implements UserDAO{

    @Override
    public void addUser(User user) {
        //构造连接
        Connection con = null;
        con = getConnection();
        try {
            //增加一条记录
            String sql="insert into UserInfo values(?,?,?)";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, user.getLoadname());
            psmt.setString(2, user.getPasswd());
            System.out.println(user.getType().toString());
            psmt.setString(3, user.getType().toString());
            psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(String name) {
        return null;
    }

    @Override
    public LinkedList<User> getAllUsers() {
        LinkedList<User>list=new LinkedList<>();
        String sql="select * from UserInfo";


        Connection con = null;

        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);

            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                switch (rs.getString("type").trim()){
                    case "Master":
                        Master master=DAOFactory.getMasterDAO().getMaster(rs.getString("loadname"));
                        //只有密码需要根据User表 将null 替换
                        master.setPasswd(rs.getString("passwd"));

                        list.add(master);
                        break;
                    case "Mentor":
                        Mentor mentor=DAOFactory.getMentorDAO().getMentor(rs.getString("loadname"));
                        mentor.setPasswd(rs.getString("passwd"));
                        list.add(mentor);
                        break;
                    case "SubjectMaster":
                        SubjectMaster subjectMaster=DAOFactory.getSubjectMasterDAO().getSubjectMaster(rs.getString("loadname"));
                        subjectMaster.setPasswd(rs.getString("passwd"));
                        list.add(subjectMaster);
                        break;
                    case "Teacher":
                        Teacher teacher=DAOFactory.getTeacherDAO().getTeacher(rs.getString("loadname"));
                        teacher.setPasswd(rs.getString("passwd"));
                        list.add(teacher);
                        break;
                    case "Administrator":
                        Administrator administrator=new Administrator(UserType.Administrator,rs.getString("loadname"),rs.getString("passwd"));
                           list.add(administrator);
                           break;
                    }



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
        return list;

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }


}
