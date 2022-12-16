package User;

import DAOS.DAOFactory;
import Entity.Choose;
import Entity.Course;
import Entity.Master;
import Entity.Subject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class master extends User implements Menu{

    private static Master m ;

    public master(UserType type, String name, String passwd) {
        super(type,name,passwd);
        m = DAOFactory.getMasterDAO().getMaster(name);
    }

    //
//    public master(UserType type, String loadname, String passwd) {
//        super(type, loadname, passwd);
//    }


    public static void  showCourseList(LinkedList<Course> courses, LinkedList<Choose> myChooses){

        Integer i=1;//行号
        Iterator<Course> itr = courses.iterator();

        while (itr.hasNext()){
            Course course=itr.next();

            String stateString="未选，可选";

            Iterator<Choose> itr1 = myChooses.iterator();

            while (itr1.hasNext()){
                Choose choose= itr1.next();
                if(choose.getCouseid().equals(course.getCouseid())){
                    stateString="已选，不可重复选";

                    break;
                }
            }


            System.out.println(i.toString()+"\t"+course.getCouseid()+"\t"+course.getName()+"\t"+course.getHours()+"\t"+course.getApplications()+"\t"+stateString);
            i++;
        }
    }


    static void tutorMenu(){
        while(true)
        {
            System.out.println("---------助教课程子模块----------");
            System.out.println("1.选课");
            System.out.println("2.退课");
            System.out.println("3.返回上级菜单");
            int choose;
            Scanner sc=new Scanner(System.in);
            choose=sc.nextInt();
            switch (choose){
                case 1:
                    chooseCourse();
                    break;
                case 2:
                    deleteCourse();
                    break;
                case 3:
                    return;
            }
        }

    }

    public static void deleteCourse(){
        System.out.println("----------------------退课-----------------------");

        LinkedList<Choose> allChooses = DAOFactory.getChooseDAO().getAllChooses();
        Iterator<Choose> itr = allChooses.iterator();
        LinkedList<Choose> myChooses=new LinkedList<>();
        while (itr.hasNext()) {

            Choose choose=itr.next();
            if (choose.getMid().trim().equals(m.getSid().trim())){
                myChooses.add(choose);
            }
        }
        if(myChooses.size()==0){
            System.out.println("本人无选课记录，无法退课！");
            return;
        }
        //展现本人的已选课程列表 1 或 2 个
        System.out.println("本人已选课程如下：");
        Iterator<Choose> itr11 = myChooses.iterator();
        System.out.println("行号\t课程号\t课程名\t");
        int i=1;
        while (itr11.hasNext()) {

            Choose tmp=itr11.next();
            Course course=DAOFactory.getCourseDAO().getCourse(tmp.getCouseid());
            System.out.println(i+"\t"+course.getCouseid().trim()+"\t"+course.getName());
            i+=1;
        }

        System.out.println("请输入要退选的行号：");
        Scanner sc=new Scanner(System.in);
        int row=sc.nextInt();
        Choose choose=myChooses.get(row-1);

        DAOFactory.getChooseDAO().deleteChoose(choose);
        System.out.println("删除选课记录成功！");


    }

    public static void chooseCourse(){

        System.out.println("----------------------选课-----------------------");
        String subid= DAOFactory.getMentorDAO().getMentor(m.getMenid()).getSubid();

        //查看自己学科的特定状态的课程列表
        LinkedList<Course> courses= DAOFactory.getCourseDAO().getStateCourses(subid,1);

        LinkedList<Choose> myChooses=new LinkedList<>();


        LinkedList<Choose> allChooses = DAOFactory.getChooseDAO().getAllChooses();


        // System.out.println(allChooses.size());


        //查看所有选课记录当中有几个是当前硕士选的
        //默认设成2
        Integer volAvailabe=2;
        Integer volAlready=0;

        Iterator<Choose> itr = allChooses.iterator();

        while (itr.hasNext()) {

            Choose choose=itr.next();
            System.out.println("---");

//            char array0[] =choose.getMid().toCharArray();//替换字符串中不可见字符
//
//            char array[]= this.sid.toCharArray();//替换字符串中不可见字符
//
//            System.out.println(this.sid);
//            System.out.println(choose.getMid().trim());



            if (choose.getMid().trim().equals(m.getSid().trim())){

                myChooses.add(choose);
                volAvailabe--;
                volAlready++;
            }
        }


        System.out.println("助教课程列表如下：");
        showCourseList(courses,myChooses);


        //展现本人的已选课程列表 1 或 2 个
        System.out.println("本人已选课程如下：");
        Iterator<Choose> itr11 = myChooses.iterator();
        System.out.println("课程号\t课程名\t");
        while (itr11.hasNext()) {

            Choose tmp=itr11.next();
            Course course=DAOFactory.getCourseDAO().getCourse(tmp.getCouseid());
            System.out.println(course.getCouseid()+course.getName());
        }

        while (volAvailabe>0){
            System.out.println("当前硕士已选择"+ volAlready.toString() +"个志愿。还可选择"+volAvailabe.toString()+"个志愿");
            volAlready++;
            volAvailabe--;
            System.out.println("请输入要选的课程行号");
            int row;

            Scanner sc=new Scanner(System.in);
            row=sc.nextInt();
            Choose choose=new Choose(courses.get(row-1).getCouseid(),m.getSid());
            myChooses.add(choose);
            DAOFactory.getChooseDAO().addChoose(choose);

            //展现本人的已选课程列表 1 或 2 个
            System.out.println("本人已选课程情况更新如下：");
            Iterator<Choose> itr1 = myChooses.iterator();
            System.out.println("课程号\t课程名\t");
            while (itr1.hasNext()) {

                Choose tmp=itr1.next();
                Course course=DAOFactory.getCourseDAO().getCourse(tmp.getCouseid());
                System.out.println(course.getCouseid()+course.getName());
            }



            int res=0;
            while (res!=1&&res!=2&&volAvailabe>0){
                System.out.println("是否继续添加选课记录？ 1.是 2.否");
                res=sc.nextInt();
                if(res==1){
                    break;
                }
                else if (res==2){
                    return;
                }
                else
                {
                    System.out.println("输入有误。请输入1或者2");
                }

            }
            System.out.println("助教课程列表如下：");
            showCourseList(courses,myChooses);
        }
    }

    public static void menu() {

        while (true) {
            System.out.println("--------------研究生功能菜单---------------");
            System.out.println("1.助教课程子模块");

            //
            System.out.println("2.退出系统");
            System.out.println("请选择：");
            int choose;
            Scanner sc = new Scanner(System.in);
            choose = sc.nextInt();
            switch (choose) {
                case 1:
                    //可以获得学科下的state=1的课程
                    //通过导师号
                    tutorMenu();

                    break;

                case 2:
                    return;
            }
        }
    }




}
