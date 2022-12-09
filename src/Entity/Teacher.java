package Entity;
import User.User;
import User.UserType;
/**
 * @author zhuwentao
 * @version 1.0
 * @data 2022/12/9 19:29
 */

public class Teacher extends User{
    private String id;//导师工号
    private String name;//导师姓名



    public Teacher(UserType type, String loginname, String passwd,String id, String name) {
        super(type,loginname,passwd);
        this.id = id;
        this.name = name;
    }



    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public void menu() {
        System.out.println("t");
    }
}
