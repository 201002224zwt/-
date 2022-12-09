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


    //ѧ�ƻ�����Ϣ���DAO
    public static SubjectDAO getSubjectDAO(){
        return new SubjectDAOimp();
    }
    //ѧ�Ƹ����˵�DAO
    public static SubjectMasterDAO getSubjectMasterDAO(){
        return new SubjectMasterimp();
    }

}
