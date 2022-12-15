/**
 * @author caoqike
 * @date 2022-12-08 21:49:27
 */
package Entity;
import DAOS.DAOFactory;
import User.User;
import User.UserType;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

//ѧ�Ƹ�����
public class SubjectMaster extends User{
    public  String smid;
    public String subid;
    public String name;


    public SubjectMaster(UserType type, String loadname, String passwd, String smid, String subid, String name) {
        super(type, loadname, passwd);
        this.smid = smid;
        this.subid = subid;
        this.name = name;
    }

    @Override
    public void menu() {



        while (true){
            System.out.println("--------------ѧ�Ƹ����˹��ܲ˵�---------------");
            System.out.println("1.ȷ����ѡ�γ��б�");
            System.out.println("2.�˳�ϵͳ");
            System.out.println("��ѡ��");
            int choose;
            Scanner sc=new Scanner(System.in);
            choose=sc.nextInt();
            switch (choose){
                case 1: makeCourseList();
                    break;

                case 2:
                    return;
            }

        }



    }



    public  void  showCourseList(LinkedList<Course> courses){

        Integer i=1;//�к�
        Iterator<Course> itr = courses.iterator();

        while (itr.hasNext()){
            Course course=itr.next();

            String stateString=null;
            if (course.getState()==1){
                stateString="����δȷ��";
            }
            else if(course.getState()==2)
            {
                stateString="������ȷ��";
            }
            else
            {
                stateString="������������";
            }
            System.out.println(i.toString()+"\t"+course.getCouseid()+"\t"+course.getName()+"\t"+course.getHours()+"\t"+course.getApplications()+"\t"+stateString);
            i++;
        }
    }

    public void makeCourseList(){
        //�鿴�Լ�ѧ�Ƶ����пγ��б�
        LinkedList<Course> courses= DAOFactory.getCourseDAO().getAllCourses(subid);
        Iterator<Course> itr = courses.iterator();
        LinkedList<Course> needTutorCourses=new LinkedList<>();
        LinkedList<Course> noChooseCourse=new LinkedList<>();
        Integer i=1;//�к�

        //����һ�Σ���0 ��12 ״̬�ֱ�ŵ���ͬ�Ŀγ��б�
        while (itr.hasNext()){
            Course course=itr.next();

            //˵���Ѿ���ӵ������̿γ��б�
            if (course.getState()!=0){ //�����0����������  1����Ҫ���̣���������δȷ��  2����Ҫ���̣���������ȷ��
                needTutorCourses.add(course);
                continue;
            }
           // System.out.println(i.toString()+"\t"+course.getCouseid()+"\t"+course.getName()+"\t"+course.getHours()+"\t"+course.getApplications()+"\t������������");
            noChooseCourse.add(course);
            i++;
        }


        System.out.println("���̿γ��б�����������£�");
        if(needTutorCourses.size()==0){
            System.out.println("���̿γ��б�Ϊ�գ�");
        }
        else
        {
           showCourseList(needTutorCourses);
        }


        while (true){
            System.out.println("���������̿γ��б�Ŀγ���Ϣ���£�");
            showCourseList(noChooseCourse);
            System.out.println("��ѡ������̿γ��б�Ĳ���");
            System.out.println("1.���Ӽ�¼");
            System.out.println("2.ɾ����¼");
            System.out.println("3.�޸ļ�¼");
            System.out.println("4.�����ϼ��˵�");
            int choose=0;
            System.out.println("");
            Scanner sc=new Scanner(System.in);
            choose=sc.nextInt();

            switch (choose){
                case 1:
                    System.out.println("������Ҫ�����б�γ̵��к�");
                    int row=sc.nextInt();

                    Course tmp=noChooseCourse.get(row-1);
                    noChooseCourse.remove(tmp);
                    tmp.setState(1);
                    needTutorCourses.add(tmp);
                    //Course��״̬��Ϊ1

                    DAOFactory.getCourseDAO().changeCourseState(tmp.getCouseid(), 1);
                    System.out.println("���º�������б�����");
                    showCourseList(needTutorCourses);
                    break;
                case 2:
                    System.out.println("������Ҫɾ���б�γ̵��к�");
                    break;
                case 4:
                    return;
            }
        }


    }



}
