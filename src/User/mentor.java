package User;

import DAOS.DAOFactory;
import Entity.Master;
import Entity.Mentor;
import Entity.award;

import java.util.*;

import java.util.Scanner;

public class mentor extends User implements Menu{
    private Mentor m;
    public mentor(UserType type, String loadname, String passwd) {
        super(type, loadname, passwd);
        System.out.println(loadname);
        m = DAOFactory.getMentorDAO().getMentor(loadname);
    }


    private void ShowStudent(){
        System.out.println(m.getMenid());
        List<Master> masterlist = DAOFactory.getMasterDAO().getMasterByMentor(m.getMenid());
        if(masterlist.size() == 0){
            System.out.println("未查找到研究生！");
        }
        else{
            System.out.println("研究生编号\t研究生姓名\t研究生入学年份\t类型");
            Iterator<Master> iterator = masterlist.iterator();
            while(iterator.hasNext()){
                Master temp = iterator.next();
                String type;
                if(temp.getStype() == 1){
                    type = "硕士研究生";
                }
                else{
                    type = "博士研究生";
                }
                System.out.println(temp.getSid()+'\t'+temp.getName()+'\t'+temp.getAddmissiontime()+'\t'+type);
            }
        }

    }

    private static void Academicmodule(){

    }

    private static void Achievemodule(){
        boolean if_continue = true;
        while (if_continue) {
            System.out.println("--------------初审学生成果---------------");
            System.out.println("1.初审论文成果");
            System.out.println("2.初审奖励成果");
            System.out.println("3.初审标准成果");
            System.out.println("4.初审报告成果");
            System.out.println("5.初审专利成果");
            System.out.println("6.初审软硬件平台成果");
            System.out.println("7.初审教材成果");
            System.out.println("8.退出系统");
            System.out.println("请选择：");
            String choose;
            boolean flag = true;
            while(flag){
                Scanner sc = new Scanner(System.in);
                choose = sc.next();
                switch (choose) {
                    case "1":
                        ViewAward();
                        flag = false;
                        break;

                    case "2":
                        Academicmodule();
                        flag = false;
                        break;
                    case "3":
                        Achievemodule();
                        flag = false;
                        break;
                    case "4":
                        flag = false;
                        if_continue = false;
                        break;
                    default:
                        System.out.println("输入错误，请重新输入:");
                }
            }

        }


    }

    public void menu() {
        boolean if_continue = true;
        while (if_continue) {
            System.out.println("--------------研究生功能菜单---------------");
            System.out.println("1.查看学生基本信息");
            System.out.println("2.学术交流活动认证模块");
            System.out.println("3.成果初审模块");
            //
            System.out.println("4.退出系统");
            System.out.println("请选择：");
            String choose;
            boolean flag = true;
            while(flag){
                Scanner sc = new Scanner(System.in);
                choose = sc.next();
                switch (choose) {
                    case "1":
                        ShowStudent();
                        flag = false;
                        break;

                    case "2":
                        Academicmodule();
                        flag = false;
                        break;
                    case "3":
                        Achievemodule();
                        flag = false;
                        break;
                    case "4":
                        flag = false;
                        if_continue = false;
                        break;
                    default:
                        System.out.println("输入错误，请重新输入:");
                }
            }

        }
    }


    private static void ViewAward() {
        Scanner sc=new Scanner(System.in);
        System.out.println("------------初审奖励成果-------------");
        System.out.println("请输入要审核奖励的学生学号");
        String mid = sc.next();
        Master master = new Master();
        master.setSid(mid);
        ArrayList<award> awardlist;

        awardlist = DAOFactory.getawardDAO().getAward(master.getSid());

        System.out.println("学生" + mid + "所获奖励如下");

        System.out.printf("%-10s  %-10s  %-10s  %-10s  %-10s  %-10s \n","name","level","grade","ranking","time","materials");

        for (int i = 0; i < awardlist.size(); i++){
            award award = new award();
            award = awardlist.get(i);
            System.out.printf("%-10s  %-10d  %-10d  %-10d  %-10s  %-10s \n", award.getName() , award.getReward_grade() ,  award.getAward_grade() ,
                    award.getRanking() , award.getTime() , award.getMaterials());
        }

        System.out.println("请对该学生的奖励成果进行初审 :");

        for (int i = 0; i < awardlist.size(); i++){
            award award = new award();
            award = awardlist.get(i);
            int num = i+1;
            System.out.println("请输入第" + num +"个奖励初审结果:(通过或不通过)");
            String res = sc.next();
            award.setTutor_view(res);
            DAOFactory.getawardDAO().firstsubmit(award);
        }

        System.out.println();
    }

}
