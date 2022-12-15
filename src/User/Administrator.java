//import User.UserType;
package User;
//import User.*;

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

//�о�����������Ա
public class Administrator extends User{

    @Override
    public void menu() {


        while(true)
        {
            System.out.println("\n\n\n");
            System.out.println("-------------�о�����������Ա���ܲ˵�-------------");
            System.out.println("1.¼��ѧ�Ƹ�������Ϣ");
            System.out.println("2.¼���ڿν�ʦ��Ϣ");
            System.out.println("3.¼�뵼ʦ��Ϣ");
            System.out.println("4.¼��ѧ����Ϣ");
            System.out.println("5.¼��ѧ�ƻ�����Ϣ");
            System.out.println("6.¼��γ̻�����Ϣ");
            System.out.println("7.�˳�ϵͳ");
            System.out.println("��ѡ��");
            Scanner sc=new Scanner(System.in);
            int choose=sc.nextInt();
            switch (choose)
            {
            case 1:
                addSubjectMaster();
                break;

            //��ȫ
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
                System.out.println("��лʹ�ã�");
                return;
        }
        }

    }

    public void addMentor(){
        Scanner sc=new Scanner(System.in);
        System.out.println("------------�½���ʦ��Ϣ-------------");
        System.out.println("��ʦ���ƣ�");
        String name=sc.next();

        System.out.println("��ʦ���ţ�");
        String tid=sc.next();

        System.out.println("����ѧ�ƺţ�");
        String subid=sc.next();

        System.out.println("��¼���룺");
        String passwd=sc.next();

        Mentor mentor=new Mentor(UserType.Mentor,tid,passwd,tid,subid,name);
        DAOFactory.getMentorDAO().addMentor(mentor);
        System.out.println("¼�뵼ʦ��Ϣ�ɹ�!");

    }
    public void addSubject(){
        Scanner sc=new Scanner(System.in);
        System.out.println("------------�½�ѧ����Ϣ-------------");
        System.out.println("ѧ�����ƣ�");
        String name=sc.next();

        System.out.println("ѧ�ƺţ�");
        String subid=sc.next();


        Subject subject=new Subject(subid,name);
        DAOFactory.getSubjectDAO().addSubject(subject);
        System.out.println("¼��ѧ����Ϣ�ɹ�!");

    }
    public void addSubjectMaster(){
        Scanner sc=new Scanner(System.in);
        System.out.println("------------�½�ѧ�Ƹ�������Ϣ-------------");
        System.out.println("������");
        String name=sc.next();

        System.out.println("������ţ�");
        String smid=sc.next();
        System.out.println("��¼���룺");
        String passwd=sc.next();
        System.out.println("�������ѧ�ƺţ�");
        String subid=sc.next();


        SubjectMaster subjectMaster=new SubjectMaster(UserType.SubjectMaster,smid,passwd,smid,subid,name);

        DAOFactory.getSubjectMasterDAO().addSubjectMaster(subjectMaster);

    }
    public void addTeacher(){
        Scanner sc=new Scanner(System.in);
        System.out.println("------------�½��ڿν�ʦ��Ϣ-------------");
        System.out.println("��ʦ���ƣ�");
        String name=sc.next();

        System.out.println("��ʦ���ţ�");
        String tid=sc.next();

        System.out.println("��¼���룺");
        String passwd=sc.next();

        Teacher teacher=new Teacher(UserType.Teacher,tid,passwd,tid,name);
        DAOFactory.getTeacherDAO().addTeacher(teacher);
        System.out.println("¼�뵼ʦ��Ϣ�ɹ�!");

    }

    public void addCourse(){
        Scanner sc=new Scanner(System.in);
        System.out.println("------------�½��γ̻�����Ϣ-------------");
        System.out.println("�γ̺ţ�");
        String id=sc.next();

        System.out.println("����ѧ�ƺţ�");
        String subid=sc.next();


        System.out.println("�ڿν�ʦ���ţ�");
        String tid=sc.next();

        System.out.println("�γ����ƣ�");
        String name=sc.next();

        System.out.println("ѧʱ��");
        int hours= Integer.parseInt(sc.next());

        System.out.println("ѡ��������");
        int applications=Integer.parseInt(sc.next());

        System.out.println("�γ�״̬��");
        int state = 0;

        Course course=new Course(id,subid,tid,name,hours,applications,state);
        DAOFactory.getCourseDAO().addCourse(course);
        System.out.println("¼��γ̻�����Ϣ�ɹ�!");

    }

    public void addMaster() throws ParseException {
        Scanner sc=new Scanner(System.in);
        System.out.println("------------�½��о�����Ϣ-------------");
        System.out.println("�о���ѧ�ţ�");
        String id=sc.next();

        System.out.println("�о���������");
        String name=sc.next();

        System.out.println("��¼���룺");
        String passwd=sc.next();

        System.out.println("��ʦ���ţ�");
        String subid=sc.next();


        System.out.println("��ѧʱ�䣺");
        String addmissiontime=sc.next();
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(addmissiontime);

        //��java.util.Dateת��Ϊjava.sql.Date�ķ����ǵ��ù�����
        java.sql.Date bDate = new java.sql.Date(date.getTime());

        System.out.println("ѧ�����ͣ�");
        System.out.println("1.˶ʿ�о��� 2.��ʿ�о���");
        System.out.println("��ѡ��");
        int stype=Integer.parseInt(sc.next());



        Master master =new Master(UserType.Master,id,passwd,id,name,subid, bDate,stype);
        DAOFactory.getMasterDAO().addMaster(master);
        System.out.println("¼���о�����Ϣ�ɹ�!");

    }

    public Administrator(UserType type, String name, String passwd) {

        super(type, name, passwd);

    }

}
