package DAOS;

import Entity.Master;
import User.UserManage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
            psmt.setString(2, master.getName());
            psmt.setString(3, master.getSubject().subid);
            psmt.setString(4, master.getAddmissiontime());
            psmt.setInt(5, master.getStype());
            psmt.executeUpdate();
            psmt.close();


            //设置该教师的登录账号
            try {
                UserManage.saveInfo(master);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        return null;
    }
}
