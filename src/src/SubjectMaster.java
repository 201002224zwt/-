/**
 * @author caoqike
 * @date 2022-12-08 21:49:27
 */

//学科负责人
public class SubjectMaster extends User{
    String smid;
    String subid;
    String name;

    public SubjectMaster(UserType type, String loadname, String passwd, String smid, String subid, String name) {
        super(type, loadname, passwd);
        this.smid = smid;
        this.subid = subid;
        this.name = name;
    }

    @Override
    public void menu() {
        System.out.println("s");
    }



}
