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
    SubjectMaster,//ѧ�Ƹ�����
    Master,
    Guider,//��ʦ
    Doctor,
    Teacher,//�ڿν�ʦ


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
            fos = new FileOutputStream("userinfo.txt");//���������
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(objs);
            System.out.println("д����ϣ���");
            oos.close();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public  void register() throws IOException {

        System.out.println("�������û�����");
        Scanner sc=new Scanner(System.in);

        String name=sc.next();
        System.out.println("���������룺");
        String passwd=sc.next();


        //ֻ��ע�����Ա��Ȼ���ù���Աע��������ɫ�ĵ�¼��Ϣ
//        System.out.println("��ѡ���û���ݣ�1~6����");
//        System.out.println("1��ϵͳ����Ա");
//        System.out.println("2��ѧ�Ƹ�����");
//        System.out.println("3����ʦ");
//        System.out.println("4���ڿν�ʦ");
//        System.out.println("5��˶ʿ�о���");
//        System.out.println("6����ʿ�о���");
//        int itype=sc.nextInt();
        UserType type=UserType.Administrator;
        User user= new Administrator(type,name,passwd);//�ļ������

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
//            fos = new FileOutputStream("userinfo.txt");//���������
//            ObjectOutputStream oos=new ObjectOutputStream(fos);
//            oos.writeObject(objs);
//            System.out.println("д����ϣ���");
//            oos.close();
//        } catch (FileNotFoundException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    public void login() throws IOException, ClassNotFoundException {
        String name;
        String passwd;
        System.out.println("�������û�����");
        Scanner sc=new Scanner(System.in);

        name=sc.next();
        System.out.println("���������룺");
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
                        System.out.println("��¼�ɹ�");
                        u.menu();
                    }
                    else
                    {
                        System.out.println("�������");
                    }
                    break;
                }


        }
        if (flag==0){
            System.out.println("�û���������");
        }


    }
}
