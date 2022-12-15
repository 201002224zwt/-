package DAOS;

import Entity.Choose;
import Entity.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @author caoqike
 * @date 2022-12-10 15:40:12
 */
public class ChooseDAOimp extends DAOBase implements ChooseDAO{
    @Override
    public void addChoose(Choose choose) {
        //��������
        Connection con = null;
        con = getConnection();
        try {
            //����һ����¼
            String sql="insert into choose values(?,?)";
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, choose.getCouseid());
            psmt.setString(2, choose.getMid());
            psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("���ѡ�μ�¼�ɹ���");

    }

    @Override
    public void updateChoose(Choose choose) {

    }
    private static final String CHOOSE_DELETE_SQL = "DELETE FROM choose WHERE mid=? and couseid=?";
    @Override
    public void deleteChoose(Choose choose) {
        Connection con = null;
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(CHOOSE_DELETE_SQL);
            psmt.setString(1, choose.getMid());
            psmt.setString(2, choose.getCouseid());
            psmt.executeUpdate();
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


    }

    @Override
    public Choose getChoose(String couid, String mid) {
        return null;
    }

    @Override
    public LinkedList<Choose> getAllChooses() {
        LinkedList<Choose>list=new LinkedList<>();
        String sql="select * from choose";

        Connection con = null;

        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                Choose choose = new Choose(rs.getString("couseid"),
                        rs.getString("mid")
                );
                list.add(choose);
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


}
