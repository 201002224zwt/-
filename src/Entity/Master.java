package Entity;
import User.User;
import User.UserType;

/**
 * @author zhuwentao
 * @version 1.0
 * @data 2022/12/9
 */

public class Master extends User {
    private String sid;//学生学号
    private String name;//学生姓名
    private Subject subject;//所属学科
    private String addmissiontime;//入学时间
    private int stype;//学生类型（博士研究生，硕士研究生）

    public Master(UserType type, String loginname, String passwd,String sid, String name, Subject subject, String addmissiontime, int stype) {
        super(type,loginname,passwd);
        this.sid = sid;
        this.name = name;
        this.subject = subject;
        this.addmissiontime = addmissiontime;
        this.stype = stype;
    }

    public String getSid() {
        return sid;
    }

    public String getName() {
        return name;
    }

    public Subject getSubject() {
        return subject;
    }

    public String getAddmissiontime() {
        return addmissiontime;
    }

    public int getStype() {
        return stype;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setAddmissiontime(String addmissiontime) {
        this.addmissiontime = addmissiontime;
    }

    public void setStype(int stype) {
        this.stype = stype;
    }

    @Override
    public String toString() {
        return "Master{" +
                "sid='" + sid + '\'' +
                ", name='" + name + '\'' +
                ", subject=" + subject +
                ", addmissiontime='" + addmissiontime + '\'' +
                ", stype=" + stype +
                '}';
    }

    @Override
    public void menu() {
        System.out.println("M");
    }
}
