package User;

import DAOS.DAOFactory;
import Entity.Mentor;

public class mentor extends User implements Menu{
    private Mentor m;
    public mentor(UserType type, String loadname, String passwd) {
        super(type, loadname, passwd);
        this.m = DAOFactory.getMentorDAO().getMentor(loadname);
    }
    public static void menu() {
        System.out.println("Mentor");
    }

}
