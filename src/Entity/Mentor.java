package Entity;
import User.User;
import User.UserType;
/**
 * @author zhuwentao
 * @version 1.0
 * @data 2022/12/9
 */
public class Mentor extends User{
    private String menid;//��ʦ����
    private String subid;//����ѧ��
    private String name;//��ʦ����

    public Mentor(UserType type, String loadname, String passwd, String menid, String subid, String name) {
        super(type, loadname, passwd);
        this.menid = menid;
        this.subid = subid;
        this.name = name;
    }



    public String getMenid() {
        return menid;
    }

    public String getSubid() {
        return subid;
    }

    public String getName() {
        return name;
    }

    public void setMenid(String menid) {
        this.menid = menid;
    }

    public void setSubid(String subid) {
        this.subid = subid;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void menu() {
        System.out.println("Mentor");
    }
}