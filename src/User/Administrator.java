//import User.UserType;
package User;
//import User.*;

import DAOS.DAO;
import DAOS.DAOFactory;
import Entity.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author caoqike
 * @date 2022-12-08 21:17:51
 */

//研究生培养管理员
public class Administrator extends User implements Menu{

    public void menu() {


        while(true)
        {
            System.out.println("\n\n\n");
            System.out.println("-------------研究生培养管理员功能菜单-------------");
            System.out.println("1.录入学科负责人信息");
            System.out.println("2.录入授课教师信息");
            System.out.println("3.录入导师信息");
            System.out.println("4.录入学生信息");
            System.out.println("5.录入学科基本信息");
            System.out.println("6.录入课程基本信息");
            System.out.println("7.退出系统");
            System.out.println("请选择：");
            Scanner sc=new Scanner(System.in);
            int choose=sc.nextInt();
            switch (choose)
            {
                case 1:
                    addSubjectMaster();
                    break;

                //补全
                case 2:
                    addTeacher();
                    break;

                case 3:
                    addMentor();
                    break;
                case 4:
                    try {
                        addMaster();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;

                case 5:
                    addSubject();
                    break;

                case 6:
                    addCourse();
                    break;
                case 7:
                    System.out.println("感谢使用！");
                    return;
            }
        }

    }

    public void addMentor(){
        Scanner sc=new Scanner(System.in);
        System.out.println("------------新建导师信息-------------");
        System.out.println("导师名称：");
        String name=sc.next();

        System.out.println("导师工号：");
        String tid=sc.next();

        System.out.println("所属学科号：");
        String subid=sc.next();

        System.out.println("登录密码：");
        String passwd=sc.next();

        Mentor mentor=new Mentor(tid,subid,name);
        DAOFactory.getMentorDAO().addMentor(mentor);
        mentor m = new mentor(UserType.Mentor,tid,passwd);
        UserManage.saveInfo(m);
        System.out.println("录入导师信息成功!");

    }
    public void addSubject(){
        Scanner sc=new Scanner(System.in);
        System.out.println("------------新建学科信息-------------");
        System.out.println("学科名称：");
        String name=sc.next();

        System.out.println("学科号：");
        String subid=sc.next();


        Subject subject=new Subject(subid,name);
        DAOFactory.getSubjectDAO().addSubject(subject);
        System.out.println("录入学科信息成功!");

    }
    public void addSubjectMaster(){
        Scanner sc=new Scanner(System.in);
        System.out.println("------------新建学科负责人信息-------------");
        System.out.println("姓名：");
        String name=sc.next();

        System.out.println("工作编号：");
        String smid=sc.next();
        System.out.println("登录密码：");
        String passwd=sc.next();
        System.out.println("所管理的学科号：");
        String subid=sc.next();


        SubjectMaster subjectMaster=new SubjectMaster(smid,subid,name);
        subjectmaster s = new subjectmaster(UserType.SubjectMaster,smid,passwd);
        DAOFactory.getSubjectMasterDAO().addSubjectMaster(subjectMaster);
        UserManage.saveInfo(s);

    }
    public void addTeacher(){
        Scanner sc=new Scanner(System.in);
        System.out.println("------------新建授课教师信息-------------");
        System.out.println("教师名称：");
        String name=sc.next();

        System.out.println("教师工号：");
        String tid=sc.next();

        System.out.println("登录密码：");
        String passwd=sc.next();

        Teacher teacher=new Teacher(tid,name);
        DAOFactory.getTeacherDAO().addTeacher(teacher);
        teacher t = new teacher(UserType.Teacher,tid,passwd);
        UserManage.saveInfo(t);
        System.out.println("录入导师信息成功!");

    }

    public void addCourse(){
        Scanner sc=new Scanner(System.in);
        System.out.println("------------新建课程基本信息-------------");
        System.out.println("课程号：");
        String id=sc.next();

        System.out.println("所属学科号：");
        String subid=sc.next();


        System.out.println("授课教师工号：");
        String tid=sc.next();

        System.out.println("课程名称：");
        String name=sc.next();

        System.out.println("学时：");
        int hours= Integer.parseInt(sc.next());

        System.out.println("选课人数：");
        int applications=Integer.parseInt(sc.next());

        System.out.println("课程状态：");
        int state = 0;

        Course course=new Course(id,subid,tid,name,hours,applications,state);
        DAOFactory.getCourseDAO().addCourse(course);
        System.out.println("录入课程基本信息成功!");

    }

    public void addMaster() throws ParseException {
        Scanner sc=new Scanner(System.in);
        System.out.println("------------新建研究生信息-------------");
        System.out.println("研究生学号：");
        String id=sc.next();

        System.out.println("研究生姓名：");
        String name=sc.next();

        System.out.println("登录密码：");
        String passwd=sc.next();

        System.out.println("导师工号：");
        String subid=sc.next();


        System.out.println("入学时间：");
        String addmissiontime=sc.next();
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(addmissiontime);

        //将java.util.Date转换为java.sql.Date的方法是调用构造器
        java.sql.Date bDate = new java.sql.Date(date.getTime());

        System.out.println("学历类型：");
        System.out.println("1.硕士研究生 2.博士研究生");
        System.out.println("请选择：");
        int stype=Integer.parseInt(sc.next());

        Master master =new Master(id,name,subid, bDate,stype);
        DAOFactory.getMasterDAO().addMaster(master);
        master m = new master(UserType.Master,id,passwd);

        DAOFactory.getUserDAO().addUser(m);
        System.out.println("录入研究生信息成功!");

    }

    public Administrator(UserType type, String name, String passwd) {

        super(type, name, passwd);

    }

}
