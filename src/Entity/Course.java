package Entity;

/**
 * @author zhuwentao
 * @version 1.0
 * @data 2022/12/9
 */
public class Course {
    private String couseid;//课程号
    private Subject subid;//对应学科
    private String tid;//教授课程教师
    private String name;//课程名称
    private int hours;//课时
    private int applications;//选课人数
    private int state;//课程状态

    public Course(String couseid, Subject subid, String tid, String name, int hours, int applications, int state) {
        this.couseid = couseid;
        this.subid = subid;
        this.tid = tid;
        this.name = name;
        this.hours = hours;
        this.applications = applications;
        this.state = state;
    }

    public String getCouseid() {
        return couseid;
    }

    public Subject getSubid() {
        return subid;
    }

    public String getTid() {
        return tid;
    }

    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }

    public int getApplications() {
        return applications;
    }

    public int getState() {
        return state;
    }
}
