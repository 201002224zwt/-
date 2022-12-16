package User;

import DAOS.DAOFactory;
import Entity.Course;
import Entity.SubjectMaster;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class subjectmaster extends User implements Menu{

    private static SubjectMaster s;
    public subjectmaster(UserType type, String loadname, String passwd) {
        super(type, loadname, passwd);
        s = DAOFactory.getSubjectMasterDAO().getSubjectMaster(loadname);
    }

    public static void menu() {



        while (true){
            System.out.println("--------------学科负责人功能菜单---------------");
            System.out.println("1.确定待选课程列表");
            System.out.println("2.退出系统");
            System.out.println("请选择：");
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



    private static void  showCourseList(LinkedList<Course> courses){

        Integer i=1;
        Iterator<Course> itr = courses.iterator();

        while (itr.hasNext()){
            Course course=itr.next();

            String stateString=null;
            if (course.getState()==1){
                stateString="助教未确定";
            }
            else if(course.getState()==2)
            {
                stateString="助教已确定";
            }
            else
            {
                stateString="尚无助教需求";
            }
            System.out.println(i.toString()+"\t"+course.getCouseid()+"\t"+course.getName()+"\t"+course.getHours()+"\t"+course.getApplications()+"\t"+stateString);
            i++;
        }
    }

    private static void makeCourseList(){
        //查看自己学科的所有课程列表
        LinkedList<Course> courses= DAOFactory.getCourseDAO().getAllCourses(s.getSmid());
        Iterator<Course> itr = courses.iterator();
        LinkedList<Course> needTutorCourses=new LinkedList<>();
        LinkedList<Course> noChooseCourse=new LinkedList<>();
        Integer i=1;//行号

        //遍历一次，把0 与12 状态分别放到不同的课程列表
        while (itr.hasNext()){
            Course course=itr.next();

            //说明已经添加到了助教课程列表
            if (course.getState()!=0){  //如果是0：无需助教  1：需要助教，且助教尚未确定  2：需要助教，且助教已确定
                needTutorCourses.add(course);
                continue;
            }
            // System.out.println(i.toString()+"\t"+course.getCouseid()+"\t"+course.getName()+"\t"+course.getHours()+"\t"+course.getApplications()+"\t������������");
            noChooseCourse.add(course);
            i++;
        }


        System.out.println("助教课程列表最新情况如下：");
        if(needTutorCourses.size()==0){
            System.out.println("助教课程列表为空！");
        }
        else
        {
            showCourseList(needTutorCourses);
        }


        while (true){
            System.out.println("待加入助教课程列表的课程信息如下：");
            showCourseList(noChooseCourse);
            System.out.println("请选择对助教课程列表的操作");
            System.out.println("1.增加记录");
            System.out.println("2.删除记录");
            System.out.println("3.修改记录");
            System.out.println("4.返回上级菜单");
            int choose=0;
            System.out.println("");
            Scanner sc=new Scanner(System.in);
            choose=sc.nextInt();

            switch (choose){
                case 1:
                    System.out.println("请输入要加入列表课程的行号");
                    int row=sc.nextInt();

                    Course tmp=noChooseCourse.get(row-1);
                    noChooseCourse.remove(tmp);
                    tmp.setState(1);
                    needTutorCourses.add(tmp);
                    //Course��״̬��Ϊ1

                    DAOFactory.getCourseDAO().changeCourseState(tmp.getCouseid(), 1);
                    System.out.println("更新后的助教列表如下");
                    showCourseList(needTutorCourses);
                    break;
                case 2:
                    System.out.println("请输入要删除列表课程的行号");
                    break;
                case 4:
                    return;
            }
        }


    }

}
