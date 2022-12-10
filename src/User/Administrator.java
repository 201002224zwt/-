//import User.UserType;
package User;
//import User.*;

import DAOS.DAOFactory;
import Entity.*;

import java.util.Scanner;

/**
 * @author caoqike
 * @date 2022-12-08 21:17:51
 */

//研究生培养管理员
public class Administrator extends User{

    @Override
    public void menu() {
        System.out.println("1.录入学科负责人信息");
        System.out.println("2.录入授课教师信息");
        System.out.println("3.录入导师信息");
        System.out.println("4.录入学生信息");
        System.out.println("5.录入学科基本信息");
        System.out.println("6.录入课程基本信息");
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
                addMaster();
                break;

            case 5:
                addSubject();
                break;

            case 6:
                addCourse();
                break;
        }
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


        SubjectMaster subjectMaster=new SubjectMaster(UserType.SubjectMaster,smid,passwd,smid,subid,name);

        DAOFactory.getSubjectMasterDAO().addSubjectMaster(subjectMaster);

    }
    public void addTeacher(){
        Scanner sc=new Scanner(System.in);
        System.out.println("------------新建导师信息-------------");
        System.out.println("教师名称：");
        String name=sc.next();

        System.out.println("教师工号：");
        String tid=sc.next();

        System.out.println("登录密码：");
        String passwd=sc.next();

        Teacher teacher=new Teacher(UserType.Teacher,tid,passwd,tid,name);
        DAOFactory.getTeacherDAO().addTeacher(teacher);
        System.out.println("录入导师信息成功!");

    }

    public void addCourse(){
        Scanner sc=new Scanner(System.in);
        System.out.println("------------新建课程基本信息-------------");
        System.out.println("课程号：");
        String id=sc.next();

        System.out.println("所属学科号：");
        String subid=sc.next();
        Subject subject = DAOFactory.getSubjectDAO().getSubject(subid);

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

        Course course=new Course(id,subject,tid,name,hours,applications,state);
        DAOFactory.getCourseDAO().addCourse(course);
        System.out.println("录入课程基本信息成功!");

    }

    public void addMaster(){
        Scanner sc=new Scanner(System.in);
        System.out.println("------------新建学生信息-------------");
        System.out.println("学生学号：");
        String id=sc.next();

        System.out.println("学生姓名：");
        String name=sc.next();

        System.out.println("登录密码：");
        String passwd=sc.next();

        System.out.println("所属学科号：");
        String subid=sc.next();
        Subject subject = DAOFactory.getSubjectDAO().getSubject(subid);

        System.out.println("入学时间：");
        String addmissiontime=sc.next();

        System.out.println("学历类型：");
        int stype=Integer.parseInt(sc.next());

        Master master =new Master(UserType.Master,id,passwd,id,name,subject,addmissiontime,stype);
        DAOFactory.getMasterDAO().addMaster(master);
        System.out.println("录入学生信息成功!");

    }

    public Administrator(UserType type, String name, String passwd) {

        super(type, name, passwd);

    }

}
