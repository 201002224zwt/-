/**
 * @author caoqike
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


    //学科基本信息表的DAO
    public static SubjectDAO getSubjectDAO(){
        return new SubjectDAOimp();
    }
    //学科负责人的DAO
    public static SubjectMasterDAO getSubjectMasterDAO(){
        return new SubjectMasterimp();
    }

}
