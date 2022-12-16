package User;

import DAOS.DAOFactory;
import Entity.Teacher;

public class teacher extends User implements Menu{
    private Teacher t;
    public teacher(UserType type, String loadname, String passwd) {
        super(type, loadname, passwd);
        this.t = DAOFactory.getTeacherDAO().getTeacher(loadname);
    }
    public static void menu() {
        System.out.println("t");
    }
}
