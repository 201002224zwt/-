package DAOS;

/**
 * @author caoqike,zhuwentao
 * @date 2022-12-08 21:31:54
 */
public class DAOFactory {
    private static DAOFactory daoFactory;
    static {
        daoFactory = new DAOFactory();

    }
    private DAOFactory(){

    }

    public static DAOFactory getInstance(){
        return daoFactory;
    }


    //SubjectDAO
    public static SubjectDAO getSubjectDAO(){
        return new SubjectDAOimp();
    }

    //TeacherDAO
    public static TeacherDAO getTeacherDAO(){
        return new TeacherDAOimp();
    }

    //CourseDAO
    public static CourseDAO getCourseDAO(){
        return new CourseDAOimp();
    }



    //MasterDAO
    public static MasterDAO getMasterDAO(){
        return new MasterDAOimp();
    }

    //MentorDAO
    public static MentorDAO getMentorDAO(){
        return new MentorDAOimp();
    }

    //SubjectMasterDAO
    public static SubjectMasterDAO getSubjectMasterDAO(){
        return new SubjectMasterimp();
    }

    //ChooseDAO
    public static ChooseDAO getChooseDAO(){return  new ChooseDAOimp();}

    //UserDAO
    public static UserDAO getUserDAO(){return  new UserDAOimp();}

    public static AcademicActivityDAO getAcademicActivityDAO(){
        return new AcademicActivityDAOimp();
    }

}
