import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author caoqike
 * @date 2022-12-08 21:52:07
 */
public class SubjectMasterimp extends DAOBase implements SubjectMasterDAO{

    private static final String SUBJECTMASTER_INSERT_SQL = "INSERT INTO SubjectMaster(smid,subid,name) VALUES(?,?,?)";
    @Override
    public void addSubjectMaster(SubjectMaster subjectMaster) {
        Connection con = null;
        con = getConnection();
        try {
            //������Ϣд�����ݿ�
            PreparedStatement psmt = con.prepareStatement(SUBJECTMASTER_INSERT_SQL);
            psmt.setString(1, subjectMaster.smid);
            psmt.setString(2, subjectMaster.subid);
            psmt.setString(3, subjectMaster.name);
            psmt.executeUpdate();
            psmt.close();


            //�û���Ϣ��Ϣд���ļ�
            try {
                UserManage.saveInfo(subjectMaster);
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSubjectMaster(SubjectMaster subjectMaster) {

    }

    @Override
    public void deleteSubjectMaster(SubjectMaster subjectMaster) {

    }

    @Override
    public SubjectMaster getSubjectMaster(String id) {
        return null;
    }
}
