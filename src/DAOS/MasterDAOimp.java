
package DAOS;

import Entity.Course;
import Entity.Master;
import User.UserManage;
import User.UserType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author zhuwentao
 * @version 1.0
 * @data 2022/12/10 9:01
 */
public class MasterDAOimp extends DAOBase implements MasterDAO {
    @Override
    public void addMaster(Master master) {
        //构造连接
        Connection con = null;
        con = getConnection();
        try {
            //增加一条记录
            String sql="insert into Master values(?,?,?,?,?)";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, master.getSid());
            psmt.setString(2, master.getMenid());
            psmt.setString(3, master.getName());

            System.out.println(master.getAddmissiontime().toString());
            psmt.setDate(4, master.getAddmissiontime());
            psmt.setInt(5, master.getStype());
            psmt.executeUpdate();
            psmt.close();

            //设置该学生的登录账号
            //UserManage.saveInfo(master);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMaster(Master master) {

    }

    @Override
    public void updateMaster(Master master) {

    }

    @Override
    public Master getMaster(String id) {
        String sql="select * from Master where mid=?";

        Connection con = null;
       Master master =null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                master = new Master(rs.getString("mid"), rs.getString("name"),rs.getString("menid"),rs.getDate("admissiontime"),rs.getInt("type"));
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
        return master;

    }




}