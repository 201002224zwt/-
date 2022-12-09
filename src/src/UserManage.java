import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author caoqike
 * @date 2022-12-06 10:16:46
 */
enum UserType{
    Administrator,
    SubjectMaster,//学科负责人
    Master,
    Guider,//导师
    Doctor,
    Teacher,//授课教师


}
abstract class User implements Serializable {
    UserType type;
    String loadname;//id
    String passwd;
    public abstract void menu();

    public User(UserType type, String loadname, String passwd) {
        this.type = type;
        this.loadname = loadname;
        this.passwd = passwd;
    }
}
public class UserManage {


    public static void saveInfo(User user) throws IOException{
        FileOutputStream fos= null;
        try {

            ObjectInputStream ois = null;

            File file=new File("userinfo.txt");
            LinkedList<User> objs=null;
            if (file.length()==0){
                objs=new LinkedList<>();
            }
            else
            {
                ois = new ObjectInputStream(new FileInputStream("userinfo.txt"));
                System.out.println(file.length());
                objs = (LinkedList<User>) ois.readObject();
                ois.close();
            }
            objs.add(user);
            fos = new FileOutputStream("userinfo.txt");//对象输出流
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(objs);
            System.out.println("写入完毕！！");
            oos.close();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public  void register() throws IOException {

        System.out.println("请输入用户名：");
        Scanner sc=new Scanner(System.in);

        String name=sc.next();
        System.out.println("请输入密码：");
        String passwd=sc.next();


        //只能注册管理员，然后用管理员注册其他角色的登录信息
//        System.out.println("请选择用户身份（1~6）：");
//        System.out.println("1、系统管理员");
//        System.out.println("2、学科负责人");
//        System.out.println("3、导师");
//        System.out.println("4、授课教师");
//        System.out.println("5、硕士研究生");
//        System.out.println("6、博士研究生");
//        int itype=sc.nextInt();
        UserType type=UserType.Administrator;
        User user= new Administrator(type,name,passwd);//文件输出流

        saveInfo(user);
//        FileOutputStream fos= null;
//        try {
//
//            ObjectInputStream ois = null;
//
//            File file=new File("userinfo.txt");
//            LinkedList<User> objs=null;
//            if (file.length()==0){
//                objs=new LinkedList<>();
//            }
//            else
//            {
//                ois = new ObjectInputStream(new FileInputStream("userinfo.txt"));
//                System.out.println(file.length());
//                objs = (LinkedList<User>) ois.readObject();
//                ois.close();
//            }
//            objs.add(user);
//            fos = new FileOutputStream("userinfo.txt");//对象输出流
//            ObjectOutputStream oos=new ObjectOutputStream(fos);
//            oos.writeObject(objs);
//            System.out.println("写入完毕！！");
//            oos.close();
//        } catch (FileNotFoundException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
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

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("userinfo.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        LinkedList<User> objs = (LinkedList<User>) ois.readObject();
        Iterator<User> itr = objs.iterator();

        while (itr.hasNext()) {
                User u=itr.next();
            System.out.println(u.loadname);
                if (u.loadname.equals(name)){
                    flag=1;

                    if (u.passwd.equals(passwd)){
                        System.out.println("登录成功");
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
