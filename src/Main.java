import User.UserManage;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author caoqike
 * @date 2022-12-03 20:15:23
 */
public class Main {
    public static void main(String[] args) {
//        DAOBase daoBase=new DAOBase();
//        Connection conn=daoBase.getConnection();
//
//        Statement stmt = null;
//        try {
//            stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("select name from Master");
//            System.out.println("111");
//            while (rs.next()){
//                String SN = rs.getString("name");
////                String id = rs.getString("id");
////                String mid=rs.getString("mid");
//                System.out.println(SN);
//            }
//
//            conn.close();//����
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        System.out.println("--------------��ӭʹ���о����������ںͳɹ��϶��ۺϹ���ϵͳ----------------");
        System.out.println("\t1.��¼");
        System.out.println("\t2.ע��");
        System.out.println("\t3.�˳�");
        System.out.println("��ѡ��1~3����");

        Scanner sc=new Scanner(System.in);

        int choose=sc.nextInt();//����һ������
        UserManage um=new UserManage();
        switch (choose){
            case 1:
                System.out.println("login");
                try {
                    um.login();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                System.out.println("register");
                um.register();
                break;

        }
    }
}
