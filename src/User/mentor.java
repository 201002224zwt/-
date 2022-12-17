package User;

import DAOS.DAOFactory;
import Entity.Master;
import Entity.Mentor;

import java.util.Iterator;
import java.util.List;
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

    }

    public void menu() {
        boolean if_continue = true;
        while (if_continue) {
            System.out.println("--------------研究生功能菜单---------------");
            System.out.println("1.查看学生基本信息");
            System.out.println("2.学术交流活动认证模块");
            System.out.println("3.成果提交模块");
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

}
