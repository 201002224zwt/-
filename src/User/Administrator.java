//import User.UserType;
package User;
//import User.*;

import DAOS.DAO;
import DAOS.DAOFactory;
import Entity.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
            System.out.println("7.终审学生成果的真实情况");
            System.out.println("8.退出系统");
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
                    Achievemodule();
                    break;
                case 8:
                    System.out.println("感谢使用！");
                    return;
            }
        }

    }

    private void Achievemodule() {
        boolean if_continue = true;
        while (if_continue) {
            System.out.println("--------------终审学生成果---------------");
            System.out.println("1.终审论文成果");
            System.out.println("2.终审奖励成果");
            System.out.println("3.终审标准成果");
            System.out.println("4.终审报告成果");
            System.out.println("5.终审专利成果");
            System.out.println("6.终审软硬件平台成果");
            System.out.println("7.终审教材成果");
            System.out.println("8.录入导师项目信息");
            System.out.println("9.录入项目证明");
            System.out.println("10.退出系统");
            System.out.println("请选择：");
            String choose;
            boolean flag = true;
            while(flag){
                Scanner sc = new Scanner(System.in);
                choose = sc.next();
                switch (choose) {
                    case "1":
                        ViewPaper();
                        flag = false;
                        break;

                    case "2":
                        ViewAward();
                        flag = false;
                        break;
                    case "3":
                        ViewStandard();
                        flag = false;
                        break;
                    case "4":
                        ViewReport();
                        flag = false;
                        break;
                    case "5":
                        ViewPatent();
                        flag = false;
                        break;
                    case "6":
                        ViewPlatform();
                        flag = false;
                        break;
                    case "7":
                        ViewTextbook();
                        flag = false;
                        break;
                    case "8":
                        addProject();
                        flag = false;
                        break;
                    case "9":
                        try {
                            addProjectCertification();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        flag = false;
                        break;
                    case "10":
                        flag = false;
                        if_continue = false;
                        break;
                    default:
                        System.out.println("输入错误，请重新输入:");
                }
            }

        }

    }




    private void ViewAward() {
        Scanner sc=new Scanner(System.in);
        System.out.println("------------终审奖励成果-------------");
        System.out.println("请输入要审核奖励的学生学号");
        String mid = sc.next();
        Master master = new Master();
        master.setSid(mid);
        ArrayList<award> awardlist;

        awardlist = DAOFactory.getawardDAO().last_getAward(master.getSid());

        System.out.println("学生" + mid + "所获奖励如下");

        System.out.printf("%-10s  %-10s  %-10s  %-10s  %-10s  %-30s %-10s\n","name","level","grade","ranking","time","materials","tutor_view");

        for (int i = 0; i < awardlist.size(); i++){
            award award = new award();
            award = awardlist.get(i);
            System.out.printf("%-10s  %-10d  %-10d  %-10d  %-10s  %-30s %-10s\n", award.getName() , award.getReward_grade() ,  award.getAward_grade() ,
                    award.getRanking() , award.getTime() , award.getMaterials() , award.getTutor_view());
        }

        System.out.println("请对该学生的奖励成果进行终审 :");

        for (int i = 0; i < awardlist.size(); i++){
            award award = new award();
            award = awardlist.get(i);
            int num = i+1;
            if(award.getTutor_view().equals("no_pass")){
                System.out.println("该学生第" + num +  "个奖励成果初审不通过，不能进行终审");
                continue;
            }

            System.out.println("请输入第" + num +"个奖励终审结果:(pass或no_pass)");
            String res = sc.next();
            award.setLast_view(res);
            DAOFactory.getawardDAO().lastsubmit(award);
        }
        System.out.println("审核完成，辛苦老师！");
    }


    private void ViewPaper() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------终审论文成果-------------");
        System.out.println("请输入要审核论文的学生学号");
        String mid = sc.next();
        Master master = new Master();
        master.setSid(mid);
        ArrayList<paper> paperlist;

        paperlist = DAOFactory.getpaperDAO().getPaper(master.getSid());

        System.out.println("学生" + mid + "所有论文如下");

        System.out.printf("%-10s  %-10s  %-10s  %-10s  %-10s %-10s %-30s %-10s\n","name","periodical","state","time","index_type","Attribution","materials","tutor_view");

        for (int i = 0; i < paperlist.size(); i++){
            paper paper= new paper();
            paper = paperlist.get(i);
            System.out.printf("%-10s  %-10s  %-10d  %-10s  %-10s  %-10d %-30s %-10s\n", paper.getName(),paper.getPeriodical(),paper.getState(),paper.getTime()
                    ,paper.getIndex_type(),paper.getAttribution(),paper.getMaterials(),paper.getTutor_view());
        }

        System.out.println("请对该学生的论文成果进行终审 :");

        for (int i = 0; i < paperlist.size(); i++){
            paper paper = new paper();
            paper = paperlist.get(i);
            int num = i+1;
            if(paper.getTutor_view().equals("no_pass")){
                System.out.println("该学生第" + num +  "个论文成果初审不通过，不能进行终审");
                continue;
            }
            System.out.println("请输入第" + num +"个论文终审结果:(pass或no_pass)");
            String res = sc.next();
            paper.setLast_view(res);
            DAOFactory.getpaperDAO().lastsubmit(paper);
        }
        System.out.println("审核完成，辛苦老师！");

    }


    private void ViewStandard() {

        Scanner sc = new Scanner(System.in);
        System.out.println("------------终审标准成果-------------");
        System.out.println("请输入要审核标准的学生学号");
        String mid = sc.next();
        Master master = new Master();
        master.setSid(mid);
        ArrayList<standard> standardlist;


        standardlist = DAOFactory.getstandardDAO().getstandard(master.getSid());

        System.out.println("学生" + mid + "所有标准如下");


        System.out.printf("%-10s  %-10s  %-10s  %-30s %-10s\n","name","level","time","materials","tutor_view");

        for (int i = 0; i < standardlist.size(); i++){
            standard standard= new standard();
            standard = standardlist.get(i);
            System.out.printf("%-10s  %-10d  %-10s  %-30s %-10s\n", standard.getName(),standard.getStandard_level(),
                    standard.getTime(),standard.getMaterials(),standard.getTutor_view());
        }

        System.out.println("请对该学生的标准成果进行终审 :");

        for (int i = 0; i < standardlist.size(); i++){
            standard standard = new standard();
            standard = standardlist.get(i);
            int num = i+1;
            if(standard.getTutor_view().equals("no_pass")) {
                System.out.println("该学生第" + num + "个标准成果初审不通过，不能进行终审");
                continue;
            }
            System.out.println("请输入第" + num +"个标准终审结果:(pass或no_pass)");
            String res = sc.next();
            standard.setLast_view(res);
            DAOFactory.getstandardDAO().lastsubmit(standard);
        }

        System.out.println("审核完成，辛苦老师！");

    }


    private void ViewReport() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------终审报告成果-------------");
        System.out.println("请输入要审核报告的学生学号");
        String mid = sc.next();
        Master master = new Master();
        master.setSid(mid);
        ArrayList<report> reportlist;

        reportlist = DAOFactory.getreportDAO().getreport(master.getSid());

        System.out.println("学生" + mid + "所有报告如下");

        System.out.printf("%-10s  %-10s  %-10s  %-10s  %-10s %-30s %-10s\n","name","type","unit","time","ranking","materials","tutor_view");

        for (int i = 0; i < reportlist.size(); i++){
            report report= new report();
            report = reportlist.get(i);
            System.out.printf("%-10s  %-10s %-10s  %-10s  %-10d %-30s %-10s\n", report.getName(),report.getType(),report.getUnit(),
                    report.getTime(),report.getRanking(),report.getMaterials(),report.getTutor_view());
        }

        System.out.println("请对该学生的报告成果进行终审 :");

        for (int i = 0; i < reportlist.size(); i++){
            report report = new report();
            report = reportlist.get(i);
            int num = i+1;
            if(report.getTutor_view().equals("no_pass")) {
                System.out.println("该学生第" + num + "个报告成果初审不通过，不能进行终审");
                continue;
            }
            System.out.println("请输入第" + num +"个报告终审结果:(pass或no_pass)");
            String res = sc.next();
            report.setLast_view(res);
            DAOFactory.getreportDAO().lastsubmit(report);
        }
        System.out.println("审核完成，辛苦老师！");
    }

    private void ViewPatent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------终审专利成果-------------");
        System.out.println("请输入要审核专利的学生学号");
        String mid = sc.next();
        Master master = new Master();
        master.setSid(mid);
        ArrayList<patent> patentlist;

        patentlist = DAOFactory.getpatentDAO().getpatent(master.getSid());

        System.out.println("学生" + mid + "所有专利如下");


        System.out.printf("%-10s  %-10s  %-10s %-10s %-10s  %-10s %-30s %-10s\n","name","type","number","time","state","ranking","materials","tutor_view");

        for (int i = 0; i < patentlist.size(); i++){
            patent patent= new patent();
            patent = patentlist.get(i);
            System.out.printf("%-10s  %-10d %-10s  %-10s  %-10s  %-10d %-30s %-10s\n", patent.getName(),patent.getType(),patent.getNumber(),
                    patent.getTime(),patent.getState(),patent.getRanking(),patent.getMaterials(),patent.getTutor_view());
        }

        System.out.println("请对该学生的专利成果进行终审 :");

        for (int i = 0; i < patentlist.size(); i++){
            patent patent = new patent();
            patent = patentlist.get(i);
            int num = i+1;
            if(patent.getTutor_view().equals("no_pass")) {
                System.out.println("该学生第" + num + "个专利成果初审不通过，不能进行终审");
                continue;
            }
            System.out.println("请输入第" + num +"个专利终审结果:(pass或no_pass)");
            String res = sc.next();
            patent.setLast_view(res);
            DAOFactory.getpatentDAO().lastsubmit(patent);
        }
        System.out.println("审核完成，辛苦老师！");
    }

    private void ViewPlatform() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------初审软硬件平台成果-------------");
        System.out.println("请输入要审核软硬件平台的学生学号");
        String mid = sc.next();
        Master master = new Master();
        master.setSid(mid);
        ArrayList<hs_platform> platformlist;

        platformlist = DAOFactory.getplatformDAO().geths_platform(master.getSid());

        System.out.println("学生" + mid + "所有软硬件平台如下");


        System.out.printf("%-10s  %-10s  %-10s %-10s %-30s %-10s\n","name","unit","time","ranking","materials","tutor_view");

        for (int i = 0; i < platformlist.size(); i++){
            hs_platform platform= new hs_platform();
            platform = platformlist.get(i);
            System.out.printf("%-10s %-10s  %-10s  %-10d %-30s %-10s\n", platform.getName(),platform.getUnit(),
                    platform.getTime(),platform.getRanking(),platform.getMaterials(),platform.getTutor_view());
        }

        System.out.println("请对该学生的软硬件平台成果进行终审 :");

        for (int i = 0; i < platformlist.size(); i++){
            hs_platform platform = new hs_platform();
            platform = platformlist.get(i);
            int num = i+1;
            if(platform.getTutor_view().equals("no_pass")) {
                System.out.println("该学生第" + num + "个平台成果初审不通过，不能进行终审");
                continue;
            }
            System.out.println("请输入第" + num +"个软硬件平台终审结果:(pass或no_pass)");
            String res = sc.next();
            platform.setLast_view(res);
            DAOFactory.getplatformDAO().lastsubmit(platform);
        }
        System.out.println("审核完成，辛苦老师！");

    }


    private void ViewTextbook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------------终审教材成果-------------");
        System.out.println("请输入要审核教材的学生学号");
        String mid = sc.next();
        Master master = new Master();
        master.setSid(mid);
        ArrayList<textbook> textbooklist;

        textbooklist = DAOFactory.gettextbookDAO().gettextbook(master.getSid());

        System.out.println("学生" + mid + "所有教材如下");


        System.out.printf("%-10s  %-10s  %-10s %-10s %-30s %-10s \n","name","press","time","ranking","materials","tutor_view");

        for (int i = 0; i < textbooklist.size(); i++){
            textbook textbook= new textbook();
            textbook = textbooklist.get(i);
            System.out.printf("%-10s  %-10s %-10s  %-10d %-30s %-10s\n", textbook.getName(),textbook.getPress(),
                    textbook.getTime(),textbook.getRanking(),textbook.getMaterials(),textbook.getTutor_view());
        }

        System.out.println("请对该学生的教材成果进行终审 :");

        for (int i = 0; i < textbooklist.size(); i++){
            textbook textbook = new textbook();
            textbook = textbooklist.get(i);
            int num = i+1;
            if(textbook.getTutor_view().equals("no_pass")) {
                System.out.println("该学生第" + num + "个教材成果初审不通过，不能进行终审");
                continue;
            }
            System.out.println("请输入第" + num +"个教材终审结果:(pass或no_pass)");
            String res = sc.next();
            textbook.setLast_view(res);
            DAOFactory.gettextbookDAO().lastsubmit(textbook);
        }
        System.out.println("审核完成，辛苦老师！");
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

    //zxy
    public void addProject(){
        Scanner sc=new Scanner(System.in);
        System.out.println("------------新建导师项目信息-------------");
        System.out.println("项目编号：");
        String projid=sc.next();

        System.out.println("项目导师编号：");
        String menid=sc.next();

        System.out.println("项目名称：");
        String name=sc.next();

        System.out.println("项目类型：");
        String type=sc.next();


        System.out.println("项目负责人编号：");
        String managerid=sc.next();

        Project project = new Project(projid, menid, name, type, managerid);
        DAOFactory.getProjectDAO().addProject(project);
        System.out.println("录入导师项目信息成功！");
    }

    //zxy
    public void addProjectCertification() throws ParseException {
        Scanner sc=new Scanner(System.in);
        System.out.println("------------新建项目证明信息-------------");
        /*certid 不重复证书编号（序号）
            mid 学生学号 sid
            projid 项目编号
            starttime 开始时间
            endtime 结束时间
            assignment 承担工作
         */
        System.out.println("证书编号：");
        String certid = sc.next();

        System.out.println("学生学号：");
        String mid=sc.next();

        System.out.println("项目编号：");
        String projid=sc.next();

        System.out.println("开始时间：");
        String starttime=sc.next();
        Date sdate = new SimpleDateFormat("yyyy-MM-dd").parse(starttime);
        //将java.util.Date转换为java.sql.Date的方法是调用构造器
        java.sql.Date sDate = new java.sql.Date(sdate.getTime());

        System.out.println("结束时间：");
        String endtime=sc.next();
        Date edate = new SimpleDateFormat("yyyy-MM-dd").parse(endtime);
        //将java.util.Date转换为java.sql.Date的方法是调用构造器
        java.sql.Date eDate = new java.sql.Date(edate.getTime());

        System.out.println("承担工作：");
        String assignment=sc.next();

        ProjectCerification projectcerification = new ProjectCerification(certid, mid, projid, sDate, eDate, assignment);
        DAOFactory.getProjectCertificationDAO().addProjectCertification(projectcerification);
        System.out.println("录入项目证明信息成功！");

    }

}
