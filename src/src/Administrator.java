import javax.sound.midi.Soundbank;
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
                break;


            case 5:
                addSubject();
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
    public Administrator(UserType type, String name, String passwd) {

        super(type, name, passwd);

    }

}
