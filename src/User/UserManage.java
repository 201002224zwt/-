package User;
import DAOS.DAOFactory;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author caoqike
 * @date 2022-12-06 10:16:46
 */

public class UserManage {

    public void delUser(String name ,String id){

    }
    public static void saveInfo(User user){
        DAOFactory.getUserDAO().addUser(user);
    }
    public  void register() {

        System.out.println("请输入用户名：");
        Scanner sc=new Scanner(System.in);

        String name=sc.next();
        System.out.println("请输入密码：");
        String passwd=sc.next();
        UserType type=UserType.Administrator;
        User user= new Administrator(type,name,passwd);//文件输出流

        saveInfo(user);

    }

    public void login() throws IOException, ClassNotFoundException {
        String name;
        String passwd;
        System.out.println("请输入用户名：");
        Scanner sc=new Scanner(System.in);

        name=sc.next();
        System.out.println("请输入密码：");
        passwd=sc.next();

        int flag=0;

//        ObjectInputStream ois = null;
//        try {
//            ois = new ObjectInputStream(new FileInputStream("userinfo.txt"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        LinkedList<User> objs = DAOFactory.getUserDAO().getAllUsers();


        System.out.println(objs.size());
        Iterator<User> itr = objs.iterator();

        while (itr.hasNext()) {
                User u=itr.next();
            System.out.println(u.loadname);
                if (u.loadname.trim().equals(name)){
                    flag=1;
                    if (u.passwd.trim().equals(passwd)){
                        System.out.println("登录成功");
                        System.out.println(u.type);
                        u.menu();
                    }
                    else
                    {
                        System.out.println("密码错误");
                    }
                    break;
                }


        }
        if (flag==0){
            System.out.println("用户名不存在");
        }


    }
}
