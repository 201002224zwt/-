import javax.sound.midi.Soundbank;
import java.util.Scanner;

/**
 * @author caoqike
 * @date 2022-12-08 21:17:51
 */

//�о�����������Ա
public class Administrator extends User{

    @Override
    public void menu() {
        System.out.println("1.¼��ѧ�Ƹ�������Ϣ");
        System.out.println("2.¼���ڿν�ʦ��Ϣ");

        System.out.println("3.¼�뵼ʦ��Ϣ");
        System.out.println("4.¼��ѧ����Ϣ");
        System.out.println("5.¼��ѧ�ƻ�����Ϣ");
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
                break;


            case 5:
                addSubject();
                break;


        }
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
    public Administrator(UserType type, String name, String passwd) {

        super(type, name, passwd);

    }

}
